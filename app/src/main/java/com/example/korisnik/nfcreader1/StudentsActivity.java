package com.example.korisnik.nfcreader1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentsActivity extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth auth;
    private static final String TAG = "CLASSES";
    private String uid;
    private String cid;
    private int i = 0;
    private Button b;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cid = extras.getString("Predmet");
        }

        database = FirebaseDatabase.getInstance();
        //classes = new HashMap<Integer, String>();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        myListView = (ListView) findViewById(R.id.studentsLV);

        if (user != null) {
            uid = user.getUid();
        }

        myRef = database.getReference("userdata").child(uid).child(cid).child("students");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Log.e ("STUDENTS", data.toString());
                    DataStorage.allStudents.put(i, data.child("name").getValue().toString());
                    DataStorage.allStudentsID.put(i++, data.getKey().toString());
                }
                myListView.setAdapter(new MyStudentsAdapter(getApplicationContext()));
                Log.d("Students", "after setAdapter!");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
        for(int j = 0; j< DataStorage.students.size(); j++){
            myRef.child(DataStorage.students.get(j)).child("evid").setValue(1);
        }
    }
}
