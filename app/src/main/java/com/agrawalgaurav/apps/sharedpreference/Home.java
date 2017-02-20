package com.agrawalgaurav.apps.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalgaurav.apps.sharedpreference.customlist.CustomList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home extends Activity implements View.OnClickListener{
    EditText transferto , amount;
    Button transfer , logout , sms , button;
    TextView balance_view ;
    RequestQueue requestQueue;
    JSONObject resp ;
    public static final String MyPREFERENCES = "MyPrefs" ;
    static  SharedPreferences sharedpreferences ;

        String get_saved_user  ;
        String get_saved_user_pin  ;
        int get_saved_user_balance  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        sharedpreferences  = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        transferto = (EditText) findViewById(R.id.transferto) ;
        amount = (EditText) findViewById(R.id.amount) ;
        balance_view = (TextView) findViewById(R.id.balance_view) ;
        transfer = (Button) findViewById(R.id.transfer) ;
        logout = (Button) findViewById(R.id.logout) ;
        sms = (Button) findViewById(R.id.SMS) ;
        button = (Button) findViewById(R.id.list22);
       /* Button loginButton = (Button) findViewById(R.id.Login);

      try{
          loginButton.setText("updated");
          loginButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(Home.this, "Hello", Toast.LENGTH_SHORT).show();
          }
      });}
      catch(Exception e){

          Toast.makeText(this, "can't run", Toast.LENGTH_SHORT).show();
      }*/

        sms.setOnClickListener(this);
        logout.setOnClickListener(this);
        transfer.setOnClickListener(this);
        button.setOnClickListener(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        get_saved_user  = sharedpreferences.getString("user",null);
        get_saved_user_pin  = sharedpreferences.getString("pin",null);
        get_saved_user_balance  = Integer.parseInt(sharedpreferences.getString("balance","0"));

        if(get_saved_user==null){
            Intent i = new Intent(Home.this, login.class);
            Toast.makeText(this, "You have already logged out", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
        balance_view.setText("Hello "+get_saved_user+", your balance is : "+get_saved_user_balance);

        if(get_saved_user == null || get_saved_user_pin == null || get_saved_user_pin == null ){
//            Toast.makeText(this, "Already saved  : "+get_saved_user, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Home.this, login.class);
            startActivity(i);
        }



/*
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(Home.this, "logged out", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Home.this, login.class);
                startActivity(i);
            }
        });*/




    }


    @Override
    public void onClick(View v) {
//        Toast.makeText(this, "in onclick", Toast.LENGTH_SHORT).show();
        checkLogin();
        switch (v.getId()) {
            case R.id.list22 :
                Toast.makeText(this, "yt", Toast.LENGTH_SHORT).show();
                Intent i5 = new Intent(Home.this, CustomList.class);
                startActivity(i5);

            case R.id.transfer :
                try {
                    int amnt = Integer.parseInt(amount.getText().toString().trim());
                    if(amnt > get_saved_user_balance){
                        Toast.makeText(Home.this, "Amount should be less than available balance "+amnt+"  "+get_saved_user_balance, Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject jsonObj = new JSONObject();
                        JSONObject jsonObj2  = new JSONObject();

                        jsonObj.put("fromuser", get_saved_user);
                        jsonObj.put("pin", get_saved_user_pin);
                        jsonObj.put("touser", transferto.getText().toString().trim());
                        jsonObj.put("amount", amnt);
                        Connect c = new Connect();
//                        Toast.makeText(Home.this, "Tillll", Toast.LENGTH_SHORT).show();

                        Context ctx = Home.this;

                        Home h = new Home();
                        c.getData(jsonObj, ctx, balance_view);
//                        getData(jsonObj);



                                /*if(jsonObj2.getString("result").toString().equals("Failed")){
                                    Toast.makeText(Home.this, "b5", Toast.LENGTH_SHORT).show();

                                    Toast.makeText(Home.this, ""+jsonObj2.getString("message").toString(), Toast.LENGTH_SHORT).show();
                                }else if(jsonObj2.getString("result").toString().equals("Successful")){
                                    Toast.makeText(Home.this, "b6", Toast.LENGTH_SHORT).show();

                                    Toast.makeText(Home.this, ""+jsonObj2.getString("message").toString(), Toast.LENGTH_SHORT).show();
                                    balance_view.setText("Hello "+jsonObj.get("fromuser")+", your balance is : "+jsonObj2.getString("from_user_updated_balance"));

                                }*/

                    }
                }catch(Exception e){
                    transferto.setText(e+"");

                    Toast.makeText(Home.this, "Exception here : "+e, Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.logout:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(Home.this, "logged out", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Home.this, login.class);
                startActivity(i);
                break;

            case R.id.SMS :
//                Toast.makeText(this, "in send sms button", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(Home.this, send_sms.class);
                startActivity(i1);
                break;
            default :
                break;
        }
    }


    public  JSONObject getData(JSONObject jsonObj){

        requestQueue = Volley.newRequestQueue(Home.this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        String url2 = "http://192.168.0.3:8080/RESTfulExample/web/transferbalance" ;

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, url2,

//                new JSONObject(jsonParams),
                jsonObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObj2) {

                       /* resp = response ;
                        Toast.makeText(Home.this, "response : "+resp, Toast.LENGTH_SHORT).show();*/
                        try{
                            if(jsonObj2.getString("result").equals("Failed")){
                                Toast.makeText(Home.this, ""+jsonObj2.getString("message"), Toast.LENGTH_SHORT).show();
                            }else if(jsonObj2.getString("result").equals("Successful")){
                                Toast.makeText(Home.this, ""+jsonObj2.getString("message"), Toast.LENGTH_SHORT).show();
                                balance_view.setText("Hello "+jsonObj2.get("fromuser")+", your balance is : "+jsonObj2.getString("from_user_updated_balance"));

                            } }catch(Exception e){}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Handle Error

                        Toast.makeText(Home.this, "Error Hua : "+error, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };
        requestQueue.add(postRequest);



        return resp ;

    }

    public void checkLogin(){
        String getsaveduser = sharedpreferences.getString("user",null);
        String getsaveduserpin = sharedpreferences.getString("pin",null);

        if(getsaveduser == null || getsaveduserpin == null){
            Toast.makeText(this, "Erased", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Home.this, login.class);
            startActivity(i);

        }
    }


}
