package com.agrawalgaurav.apps.sharedpreference;

import java.util.Date;

/**
 * Created by GAURAV on 28-Jan-17.
 */

public class smspojo {

    String body  ;
    String number ;
    String date ;
    Date smsDayTime ;
    String type ;
    String typeOfSMS  ;


    public smspojo() {
    }

    @Override
    public String toString() {
        return "{" +
                "body : '" + body + '\'' +
                ", number :'" + number + '\'' +
                ", date : '" + date + '\'' +
                ", smsDayTime :" + smsDayTime +
                ", type : '" + type + '\'' +
                ", typeOfSMS : '" + typeOfSMS + '\'' +
                '}';
    }

    public smspojo(String body, String number, String date, Date smsDayTime, String type, String typeOfSMS) {

        this.body = body;
        this.number = number;
        this.date = date;
        this.smsDayTime = smsDayTime;
        this.type = type;
        this.typeOfSMS = typeOfSMS;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getSmsDayTime() {
        return smsDayTime;
    }

    public void setSmsDayTime(Date smsDayTime) {
        this.smsDayTime = smsDayTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeOfSMS() {
        return typeOfSMS;
    }

    public void setTypeOfSMS(String typeOfSMS) {
        this.typeOfSMS = typeOfSMS;
    }
}
