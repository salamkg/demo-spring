package com.example.demo.services.impl;

import com.example.demo.exceptions.MsisdnCheckException;
import com.example.demo.exceptions.NoDataFoundException;
import com.example.demo.mappers.PersonificationRequestMapper;
import com.example.demo.models.dto.PersonificationRequestDTO;
import com.example.demo.models.dto.PromoterDTO;
import com.example.demo.models.entities.PersonificationRequest;
import com.example.demo.models.enums.DocumentType;
import com.example.demo.models.enums.RequestStatus;
import com.example.demo.models.json.PromoterSkppData;
import com.example.demo.models.responses.InfocomPassportData;
import com.example.demo.models.responses.MsisdnCheckResponse;
import com.example.demo.models.responses.PersonificationRequestResponse;
import com.example.demo.models.responses.SimMovement;
import com.example.demo.repositories.PersonificationRequestRepository;
import com.example.demo.services.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PersonificationServiceImpl implements PersonificationService {

    @Autowired
    private MegaBuilderService megaBuilderService;
    @Autowired
    private InfocomService infocomService;
    @Autowired
    private PromoterService promoterService;
    @Autowired
    private PrePersonificationService prePersonificationService;
    @Autowired
    private PersonificationRequestMapper personificationRequestMapper;
    @Autowired
    private PersonificationRequestRepository personificationRequestRepository;
    @Autowired
    private PersonificationService personificationService;

    @Override
    public MsisdnCheckResponse checkMsisdn(String token, String msisdn) {
        checkSimMovement(token, msisdn);

        return MsisdnCheckResponse.builder()
                .message("Check Msisdn")
                .subsId(1L)
                .build();
    }



    public PersonificationRequestDTO create(String msisdn, DocumentType documentType, PromoterDTO promoterDTO,
                                            List<String> childNumbers, Long groupId) {
        Long subsId = 1L;
//        Long subsId = prePersonificationService.getSubsIdByMsisdn(msisdn); // TODO this part not completed

        PersonificationRequestDTO personificationRequestDTO = new PersonificationRequestDTO();
        personificationRequestDTO.setMsisdn(msisdn);
        personificationRequestDTO.setSubsId(subsId); // TODO static id
        personificationRequestDTO.setDocumentType(documentType);
        personificationRequestDTO.setPersonificationRequestUUId(UUID.randomUUID());
        personificationRequestDTO.setPromoterDTO(promoterDTO);
        personificationRequestDTO.setCreatDate(new Date());

        personificationRequestDTO = save(personificationRequestDTO);

        return personificationRequestDTO;
    }

    private PersonificationRequestDTO save(PersonificationRequestDTO personificationRequestDTO) {
        try {
            PersonificationRequest personificationRequest = personificationRequestMapper.toPersonificationRequest(personificationRequestDTO);
            PersonificationRequest savedPersonificationRequest = personificationRequestRepository.save(personificationRequest);
            return personificationRequestMapper.toPersonificationRequestDTO(savedPersonificationRequest);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving personification request: " + e.getMessage());
        }
    }

    @Override
    public String scanPassport(MultipartFile file) {
        try {
            File convertFile = convertToFile(file);
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("D:\\Users\\smadankulov\\IdeaProjects\\langs"); // путь к языковым моделям
            tesseract.setLanguage("rus+eng");  // для распознавания рус+англ текста

            String result = tesseract.doOCR(convertFile);

            result = result.replaceAll("\\s+", "");

            Pattern pattern = Pattern.compile("IP\\w{28,}");
            Matcher matcher = pattern.matcher(result);

            if (matcher.find()) {
                String rawData = matcher.group();

                String countryCode = rawData.substring(2, 5); // KGZ
                String passportSeries = rawData.substring(5, 7); // AN
                String passportNumber = rawData.substring(7, 14); // 1234567
                String genderCode = rawData.substring(15, 16); // 2 M
                String gender = genderCode.equals("2") ? "M" : "Ж";
                String birthDay = rawData.substring(16, 18); //01
                String birthMonth = rawData.substring(18, 20); // 01
                String birthYear = rawData.substring(20, 24); // 1991
                String pin = rawData.substring(15, 29);

                System.out.println("countryCode: " + countryCode + "\n"
                        + "passportSeries: " + passportSeries + "\n"
                        + "passportNumber: " + passportNumber + "\n"
                        + "gender: " + gender + "\n"
                        + "birthDay: " + birthDay + "\n"
                        + "birthMonth: " + birthMonth + "\n"
                        + "birthYear: " + birthYear + "\n"
                        + "pin: " + pin + "\n");
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PersonificationRequestDTO findById(Long personificationRequestId) {
        if (personificationRequestId == null)
            throw new NullPointerException("Error during PersonificationService::findById: personificationRequestId is null");

        PersonificationRequest personificationRequest = personificationRequestRepository.findById(personificationRequestId)
                .orElseThrow(() -> new NoDataFoundException("PersonificationRequest with id " + personificationRequestId + " not found"));
        return personificationRequestMapper.toPersonificationRequestDTO(personificationRequest);
    }

    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile("upload-", ".jpg");
        multipartFile.transferTo(tempFile);
        return tempFile;
    }

    @Override
    public PersonificationRequestResponse createForIDCard(String token, String msisdn, String pin, String firstName,
                                                          String passportSeries, String passportNumber, MultipartFile documentOwner,
                                                          MultipartFile passportFront, MultipartFile passportBack, List<String> childNumbers,
                                                          Long groupId) {
        // Проверка номера на наличие заявок, категорию регистрации и на передачу промоутеру супервайзером
        prePersonificationService.checkMsisdn(token, 1, msisdn);

        // Получение данных из инфокома
        InfocomPassportData infocomPassportData = infocomService.getPassportData(msisdn, pin, passportSeries, passportNumber);
        // Сличение фото

        // Поиск промоутера в оракловой базе по токену
        PromoterSkppData promoterSkppData = promoterService.findPromoterFromSkppByToken(msisdn);
        // Создание промоутера из данных скпп
        PromoterDTO promoterDTO = promoterService.createPromoterFromSkppData(promoterSkppData);

        // Формирование заявки
        PersonificationRequestDTO personificationRequestDTO = create(msisdn, DocumentType.ID_CARD, promoterDTO, childNumbers, groupId);

        // Формирование details с фотками

        // Ищем созданную заявку в таблице tb_infocom_responses

        // Если данные инфокома null, то отправляем на ручное заполнение
        // Если данные инфокома есть, ставим флажок и снова пересохраняем

        // Если !infocomResponseDTO.isPassportDataChecked()
        // то данные пустые и отправка на ручное заполнение

        // !infocomResponseDTO.isInfocomPassportActive()
        // то срок паспорта истек

        // формируем passportInfo из данных инфокома

        // Создание CreatePersonificationRequestResponse

        return null;
    }

    @Override
    public PersonificationRequestResponse createForOtherDoc(String token, String msisdn, String surname, String name,
                                                            String middleName, Date dateOfBirth, String passportSeries,
                                                            String passportNumber, String pin, String organization,
                                                            Date issueDate, String gender, DocumentType documentType,
                                                            MultipartFile documentFront, MultipartFile documentBack,
                                                            MultipartFile documentOwner, List<String> childNumbers,
                                                            Long groupId, int langId) {
        // Проверка номера на наличие заявок, категорию регистрации и на передачу промоутеру супервайзером
        prePersonificationService.checkMsisdn(token, 1, msisdn);

        // Поиск промоутера в оракловой базе по токену
        PromoterSkppData promoterSkppData = promoterService.findPromoterFromSkppByToken(msisdn);
        // Создание промоутера из данных скпп
        PromoterDTO promoterDTO = promoterService.createPromoterFromSkppData(promoterSkppData);

        // Формирование заявки
        PersonificationRequestDTO personificationRequestDTO = create(msisdn, documentType, promoterDTO, childNumbers, groupId);

        // Формирование details с фотками

        // Создание CreatePersonificationRequestResponse

        return null;
    }

    public Map<String, String> convertJsonToMap(String objStr) {
        Map<String, String> params = new HashMap<>();
        if (objStr != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                params = mapper.readValue(objStr, new TypeReference<>() {
                });
            } catch (Exception e) {
                System.out.println("Error converting JSON to Map : " + e.getMessage());
            }
        }
        return params;
    }

    public void checkSimMovement(String token, String msisdn) {
        if (token != null && !token.isEmpty()) {
            Long subsId = 1L;
            SimMovement simMovement = megaBuilderService.getSimMovement(token, subsId);
            if (simMovement.getCode() == 0) {
                log.error("SimMovement for msisdn = {}: {}", msisdn, simMovement);
                throw new MsisdnCheckException("Msisdn is not assigned to promoter");
            }
        } else {
            throw new NullPointerException("Token is empty");
        }
    }

    @Override
    public int countPassedRequestsBySubs(Long subsId) {
        List<RequestStatus> passed = List.of(RequestStatus.NEW, RequestStatus.IN_PROCESS, RequestStatus.APPROVED, RequestStatus.PROCESSED, RequestStatus.ERROR);
        return personificationRequestRepository.countBySubsIdAndStatuses(subsId, passed);
    }

}
