package com.example.mobprog;

import android.graphics.Bitmap;
import android.widget.RadioButton;

public class contactsClass {
    private String _name;
    private String _number;
    private Bitmap _img = null;

    public void setImg(Bitmap img){this._img = img;}
    public Bitmap getImg(){return this._img;}

    public void setName(String name){this._name = name;}
    public String getName(){return this._name;}

    public void setNumber(String number){this._number = number;}
    public String getNumber(){return this._number;}
}
