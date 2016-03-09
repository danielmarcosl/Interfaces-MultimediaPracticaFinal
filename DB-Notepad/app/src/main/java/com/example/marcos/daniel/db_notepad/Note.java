package com.example.marcos.daniel.db_notepad;

import java.io.Serializable;

public class Note implements Serializable {

    private int code;
    private String text;

    public Note(int c, String t) {
        this.code = c;
        this.text = t;
    }

    public int getCode() {
        return this.code;
    }

    public String getText() {
        return this.text;
    }
}
