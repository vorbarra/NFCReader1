package com.example.korisnik.nfcreader1;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import be.appfoundry.nfclibrary.activities.NfcActivity;
import be.appfoundry.nfclibrary.utilities.sync.NfcReadUtilityImpl;

public class MainActivity extends NfcActivity {

    private String cid;
    private int i=0;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cid = extras.getString("Predmet");
        }

        Button btn = (Button) findViewById(R.id.saveBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentsActivity.class);
                intent.putExtra("Predmet", cid );
                MainActivity.this.startActivity(intent);
            }
        });
    }

    final protected static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        for (String message : new NfcReadUtilityImpl().readFromTagWithMap(intent).values()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        Tag myTag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String tagValue=bytesToHex(myTag.getId()).toString();

        DataStorage.students.put(i++, tagValue);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        TextView txtV = new TextView(MainActivity.this);
        txtV.setText(tagValue);
        layout.addView(txtV);
        //Toast.makeText(this, tagValue , Toast.LENGTH_SHORT).show();
    }

}