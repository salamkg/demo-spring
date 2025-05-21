package com.example.demo.document;

public class DocumentFactory {
    TextDocument textDocument = new TextDocument();
    HTMLDocument htmlDocument = new HTMLDocument();
    PDFDocument pdfDocument = new PDFDocument();

    public void save(String file) {
        switch (file) {
            case "pdf":
                pdfDocument.save(file);
            case "text":
                textDocument.save(file);
            case "html":
                htmlDocument.save(file);
        }
    }
}
