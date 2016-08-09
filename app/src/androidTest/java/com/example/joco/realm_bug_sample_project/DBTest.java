package com.example.joco.realm_bug_sample_project;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.junit.experimental.theories.internal.BooleanSupplier;
import org.junit.runner.RunWith;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

@RunWith(AndroidJUnit4.class)
public class DBTest extends InstrumentationTestCase {
    @Test
    public void testRealmDB() {
        testRealmStatic(InstrumentationRegistry.getTargetContext());
    }

    static private String safeToString(Object o) {
        if (o != null) return o.toString();  //+Test.getS();
        else return "null object ";  //+Test.getS();
    }

    static public Realm initRealm(android.content.Context context, boolean delete) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(realmConfig);

        if(delete) {
            Realm.getDefaultInstance().close();
            Realm.deleteRealm(realmConfig);
        }
        return Realm.getDefaultInstance();
    }


    static public void deleteAllData(Realm r) {
        System.out.print("Deleting data...");

        r.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }

    static public void createLines(Realm r) {
        r.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm r) {
                Line l1 = r.createObject(Line.class);
                l1.setTxt("line 1");
                Line l2 = r.createObject(Line.class);
                l2.setTxt("line 2");
            }
        });
    }

    static public void createLineList(Realm r) {
        r.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm r) {
                LineList ll = r.createObject(LineList.class);
                ll.setName("line list 1");

            }
        });
    }

    static public Line getLine(Realm r, int i) {
        RealmResults<Line> lines = r.where(Line.class).findAll();
        System.out.println("lines:"+lines);
        return lines.get(i);
    }

    static public void addLineToList(Realm r, final Line l, final LineList ll) {
        r.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm r) {
                ll.getLines().add(l);

            }
        });
    }

    static public LineList getFirstLineList(Realm r) {
        return r.where(LineList.class).findFirst();
    }

    static public void testRealmStatic(Context c){

        Realm r1 = initRealm(c,true);
        createLines(r1);
        createLineList(r1);
        r1.close();

        Realm r2 = initRealm(c,false);
        Line l1 = getLine(r2, 0);
        Line l2 = getLine(r2, 1);
        LineList ll = getFirstLineList(r2);
        addLineToList(r2, l1, ll);

        

        r2.close();
        //    assertEquals(1,2);

    }


}
