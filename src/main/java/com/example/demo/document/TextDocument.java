package com.example.demo.document;

public class TextDocument implements Document {

    @Override
    public String save(String file) {
        return "Saving Text Document";
    }
}
