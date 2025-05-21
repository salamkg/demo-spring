package com.example.demo.document;

public class HTMLDocument implements Document {

    @Override
    public String save(String file) {
        return "Saving HTML file ";
    }
}
