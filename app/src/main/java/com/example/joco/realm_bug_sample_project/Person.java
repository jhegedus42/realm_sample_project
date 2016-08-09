package com.example.joco.realm_bug_sample_project;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Person extends RealmObject {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // private RealmList<Dog> dogs; // Declare one-to-many relationships

}