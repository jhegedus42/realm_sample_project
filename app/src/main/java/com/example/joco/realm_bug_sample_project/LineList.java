package com.example.joco.realm_bug_sample_project;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class LineList extends RealmObject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public RealmList<Line> getLines() {
        return lines;
    }

    public void setLines(RealmList<Line> lines) {
        this.lines = lines;
    }

    public RealmList<Line> lines;

}
