package com.agrawalgaurav.apps.sharedpreference;

import android.graphics.Bitmap;

/**
 * Created by GAURAV on 29-Jan-17.
 */

public class DataModel {

    String name;
    String type;
    String version_number;
    String feature;
    Bitmap img;


    public DataModel(String name, String type, String version_number, String feature , Bitmap img) {
        this.name=name;
        this.type=type;
        this.version_number=version_number;
        this.feature=feature;
        this.img = img;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }


    public String getVersion_number() {
        return version_number;
    }


    public String getFeature() {
        return feature;
    }

}

