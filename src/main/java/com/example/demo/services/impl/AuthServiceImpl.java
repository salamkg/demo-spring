package com.example.demo.services.impl;

import com.example.demo.exceptions.AppVersionOldException;
import com.example.demo.models.responses.LoginResponse;
import com.example.demo.services.AuthService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private EntityManager em;

    @Override
    public LoginResponse login(String version, String msisdn, String device, int langId, String pinCode) {
        if (version == null) throw new AppVersionOldException("Upgrade version of your app");
//        promoterService.checkPromoterVersion(langId, version);
        System.out.println("msisdn: " + msisdn + " device: " + device + " langId: " + langId + " pin: " + pinCode);

        if (pinCode.equals("")) {
            throw new RuntimeException("Pin code is empty! Incorrect credentials");
        }

        String token = UUID.randomUUID().toString();
        try {
            if (device == null) device = "";
            StoredProcedureQuery query = em.createStoredProcedureQuery("login_prom");
            query.registerStoredProcedureParameter("pi_msisdn", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pi_token", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pi_device", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pi_lang_id", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pi_pin_code", String.class, ParameterMode.IN);

            query.setParameter("pi_msisdn", msisdn);
            query.setParameter("pi_token", token);
            query.setParameter("pi_device", device);
            query.setParameter("pi_lang_id", langId);
            query.setParameter("pi_pin_code", pinCode);
            query.execute();
        } catch (PersistenceException ex) {
            for (Throwable current = ex; current != null; current = current.getCause()) {
                if (current instanceof SQLException) {
                    final SQLException sqlException = (SQLException) current;
                    System.out.println(sqlException.getErrorCode());

                    if (sqlException.getErrorCode() == 20001) {
                        throw new RuntimeException("promoter inactive");
                    } else if (sqlException.getErrorCode() == 20002) {
                        throw new RuntimeException("user blocked");
                    } else if (sqlException.getErrorCode() == 20003) {
                        throw new RuntimeException("incorrect credentials");
                    } else if(sqlException.getErrorCode() == 20004){
                        throw new RuntimeException("incorrect device");
                    }
                    else {
                        throw new RuntimeException("system error");
                    }
                }
            }
        } finally {
            em.close();
        }

//        String role = megaBuilderNewService.getPromoterRole(token);
        return new LoginResponse("OK", token, "role");

    }
}
