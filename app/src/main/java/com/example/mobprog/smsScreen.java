package com.example.mobprog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

public class smsScreen extends AppCompatActivity {
    RadioButton relative;
    RadioButton work;
    RadioButton friend;
    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_screen);

        relative = findViewById(R.id.rbRelativeSMS);
        work = findViewById(R.id.rbWorkSMS);
        friend = findViewById(R.id.rbFriendSMS);
        relative.setChecked(true);

        ArrayList<contactsClass> myList = (ArrayList<contactsClass>) getIntent().getSerializableExtra("workList");
        ArrayList<contactsClass> friendList = (ArrayList<contactsClass>) getIntent().getSerializableExtra("friendList");
        ArrayList<contactsClass> relativeList = (ArrayList<contactsClass>) getIntent().getSerializableExtra("relativeList");


    }

    public void sendMessage(View view) {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Mesajlar gönderildi.");
        dlgAlert.setTitle("GÖDERİLDİ!");
        dlgAlert.setPositiveButton("Tamam",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
    }

    public void RBChanged(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbRelativeSMS:
                if (checked){
                    work.setChecked(false);
                    friend.setChecked(false);
                }
                break;
            case R.id.rbWorkSMS:
                if (checked) {
                    relative.setChecked(false);
                    friend.setChecked(false);
                }
                break;
            case R.id.rbFriendSMS:
                if (checked) {
                    work.setChecked(false);
                    relative.setChecked(false);
                }
                break;
        }
    }
}