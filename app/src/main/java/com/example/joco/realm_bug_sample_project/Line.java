package com.example.joco.realm_bug_sample_project;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

import io.realm.RealmObject;

public class Line extends RealmObject {

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String txt;
}
