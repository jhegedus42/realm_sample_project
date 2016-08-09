package com.example.joco.realm_bug_sample_project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1(getApplicationContext());
      //  DBTest.testRealmStatic(getApplicationContext());
    }
    static void test1(Context c){

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(c).deleteRealmIfMigrationNeeded().build();


        Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);

// Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        // Persist your data in a transaction
        realm.beginTransaction();
        Person person = realm.createObject(Person.class); // Create managed objects directly
        realm.commitTransaction();

        RealmQuery<Person> query = realm.where(Person.class);

        RealmResults<Person> result1 = query.findAll();

        System.out.println(result1);

        realm.close();
    }
}
