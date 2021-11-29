package com.example.mobprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.Person;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class contacts extends AppCompatActivity {
    public static final int REQUEST_READ_CONTACTS = 1;
    ListView directory;
    RadioButton relative;
    RadioButton work;
    RadioButton friend;
    List<contactsClass> workList;
    List<contactsClass> relativeList;
    List<contactsClass> firendList;
    ArrayList<contactsClass> contactss = new ArrayList<contactsClass>();


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        directory = findViewById(R.id.directory);
        relative = findViewById(R.id.rbRelative);
        work = findViewById(R.id.rbWork);
        friend = findViewById(R.id.rbFriend);
        relative.setChecked(true);

        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) == getPackageManager().PERMISSION_GRANTED){
            Cursor x = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,null);
            while (x.moveToNext()){
                String name = x.getString(x.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = x.getString(x.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String contactID = x.getString(x.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

                contactsClass i = new contactsClass();
                i.setName(name);
                i.setNumber(number);
                i.setImg(ContactPhoto(contactID));
                contactss.add(i);

            }
            x.close();
            contactsAdapter contactsAdapter = new contactsAdapter(this, contactss);
            if(directory!=null)
                directory.setAdapter(contactsAdapter);
        }
        else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }

    }


    public Bitmap ContactPhoto(String ID){
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(ID));
        Uri photoUri = Uri.withAppendedPath(contactUri,ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor x = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,null);
        if(x != null && x.getCount() > 0){
            x.moveToNext();
            byte[] data = x.getBlob(0);
            if(data != null)
                return BitmapFactory.decodeStream(new ByteArrayInputStream(data));
            else
                return null;
        }
        x.close();
        return null;
    }

    public void RBChanged(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbRelative:
                if (checked){
                    work.setChecked(false);
                    friend.setChecked(false);
                }
                break;
            case R.id.rbWork:
                if (checked) {
                    relative.setChecked(false);
                    friend.setChecked(false);
                }
                break;
            case R.id.rbFriend:
                if (checked) {
                    work.setChecked(false);
                    relative.setChecked(false);
                }
                break;
        }
    }

    public void selectCat(View view) {
        TextView name = view.findViewById(R.id.contactName);
        contactsClass person = new contactsClass();
        for (int i = 0; i< contactss.size(); i++){
            if(contactss.get(i).getName() == name.getText().toString()){
                person = contactss.get(i);
            }
        }
        if(work.isChecked())
            workList.add(person);
        else if(friend.isChecked())
            firendList.add(person);
        else if(relative.isChecked())
            relativeList.add(person);
    }

    public void smsScreen(View view) {
        Intent i = new Intent(getApplicationContext(), smsScreen.class);
        i.putExtra("workList", (Serializable) workList);
        i.putExtra("friendList", (Serializable) firendList);
        i.putExtra("relativeList", (Serializable) relativeList);
        startActivity(i);
        finish();
    }
}