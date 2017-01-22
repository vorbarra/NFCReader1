package com.example.korisnik.nfcreader1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ClassesActivity extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth auth;
    private static final String TAG = "CLASSES";
    private String uid;
    //public static HashMap<Integer, String> classes;
    private int i = 0;
    private Button b;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        database = FirebaseDatabase.getInstance();
        //classes = new HashMap<Integer, String>();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        myListView = (ListView) findViewById(R.id.ListView);
        myListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ClassesActivity.this, MainActivity.class);
                intent.putExtra("Predmet", DataStorage.classes.get(i) );
                ClassesActivity.this.startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ClassesActivity.this, MainActivity.class);
                intent.putExtra("Predmet", DataStorage.classesId.get(i) );
                ClassesActivity.this.startActivity(intent);
            }
        });

        if (user != null) {
            uid = user.getUid();
        }

        myRef = database.getReference("userdata").child(uid);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String x = dataSnapshot.getKey().toString().replace(" ", "");
                if (!x.equals("name")) {
                    DataStorage.classes.put(i, dataSnapshot.child("name").getValue().toString());
                    DataStorage.classesId.put(i++,dataSnapshot.getKey().toString());
                } else {
                    //
                    myListView.setAdapter(new MyAdapter(getApplicationContext()));
                    Log.d("CLASSES", "after setAdapter!");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}