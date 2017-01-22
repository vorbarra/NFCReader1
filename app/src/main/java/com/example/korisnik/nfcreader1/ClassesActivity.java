package com.example.korisnik.nfcreader1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassesActivity extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth auth;
    private static final String TAG = "CLASSES";
    private String uid;
    private HashMap<Integer, String> classes;
    private int i = 0;
    private TextView txt;
    private Button b;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        database = FirebaseDatabase.getInstance();
        classes = new HashMap<Integer, String>();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        b = (Button) findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("Clicked!");
            }
        });


        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            uid = user.getUid();
        }
        txt = (TextView) findViewById(R.id.textView);
        myRef = database.getReference("userdata").child(uid);
        /*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                ArrayList<DataSnapshot> children = (ArrayList<DataSnapshot>) dataSnapshot.getChildren();
                txt.setText("user = " + children.toString() );
                Log.d(TAG, "Child=" + children.toString());

                int i = 0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //Log.e(TAG, "Child=" + child.toString());
                    if(child.getKey() != "name")
                        classes.put(i++,child.child("name").getValue().toString());
                        //classes.put(i++,child.getKey());
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        */
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String x = dataSnapshot.getKey().toString().replace(" ", "");
                if ( !x.equals("name") ) {
                    Log.e(TAG, dataSnapshot.toString());

                    x = "";

                    classes.put(i++, dataSnapshot.child("name").getValue().toString());
                    Toast.makeText(ClassesActivity.this, "iz OnChildAdded", Toast.LENGTH_LONG).show();
                } else
                    showData();
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


        txt.setText("user = " + user.getUid());
        if (!classes.isEmpty())
            txt.setText(classes.get(1));
        else Toast.makeText(this, "Prazna lista",
                Toast.LENGTH_SHORT).show();

    }

    private void showData() {
        txt.setText(classes.get(1));
    }

}