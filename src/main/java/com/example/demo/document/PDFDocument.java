package com.example.demo.document;

public class PDFDocument implements Document {

    @Override
    public String save(String file) {
        return "Saving PDF Document";
    }
}
