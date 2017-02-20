package com.agrawalgaurav.apps.sharedpreference;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import  android.view.View;
import java.util.HashMap;
import java.util.Map;

public class login extends Activity  {
    RequestQueue requestQueue;
    EditText username ;
    EditText Pin ;
    Button loginButton , resetButton ;
    String username1 , pin1 ;

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        Pin = (EditText) findViewById(R.id.userpin);
        loginButton = (Button) findViewById(R.id.Login);
        resetButton = (Button) findViewById(R.id.reset);


        requestQueue = Volley.newRequestQueue(login.this);
        Map<String, String> jsonParams = new HashMap<String, String>();

        /*jsonParams.put("email", "user@gmail.com");
        jsonParams.put("username", "user");
        jsonParams.put("password", "pass");*/

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String getsaveduser = sharedpreferences.getString("user",null);
        String getsaveduserpin = sharedpreferences.getString("pin",null);

        if(getsaveduser != null || getsaveduserpin != null){
            Toast.makeText(this, "Already Logged in user : "+getsaveduser, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(login.this, Home.class);
            startActivity(i);
        } else Toast.makeText(this, "Please login", Toast.LENGTH_SHORT).show();

        final TextView tv = (TextView) findViewById(R.id.signup);



        //////////////////// start image request
        final JSONObject jsonObj = new JSONObject();


        int maxWidth = 400;
        int maxHeight = 500;
        String url1 = "http://192.168.0.3:8080/RESTfulExample/web/getimage" ;
//          String url1 = "https://static.pexels.com/photos/1380/black-and-white-city-skyline-buildings.jpg";
        ImageRequest imageRequest = new ImageRequest(url1, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                // Assign the response to an ImageView
                ImageView imageView = (ImageView) findViewById(R.id.img);
                imageView.setImageBitmap(response);
            }
        }, maxWidth, maxHeight,null, null);

        //add request to queue
        requestQueue.add(imageRequest);
//////////////////// end image request


///////////////////////////////////// post json request
  /*      String url2 = "http://192.168.0.3:8080/RESTfulExample/web/postjson" ;
        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, url2,

//                new JSONObject(jsonParams),
                jsonObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        tv.setText(tv.getText()+"\n2 Success : "+response.toString());

                        Toast.makeText(login.this, "Recieved : "+response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Handle Error
                        tv.setText(tv.getText()+"\n2 Failure : "+error.toString());
                        Toast.makeText(login.this, "Error bhaya : "+error, Toast.LENGTH_LONG).show();
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
        requestQueue.add(postRequest);*/
//////////////////////////////////////////////////////////////////////////////

//////////////// start string request
        String url3 = "http://192.168.0.3:8080/RESTfulExample/web/verifyUser";

///////////////  end string request

        loginButton.setOnClickListener(new View.OnClickListener() {
//            EditText username = (EditText) findViewById(R.id.username);
//            EditText Pin = (EditText) findViewById(R.id.userpin);

            public void onClick(View v) {


                username1 = username.getText().toString() ;
                  pin1 = Pin.getText().toString();
                try {
                    jsonObj.put("username", username1 );
                    jsonObj.put("pin", pin1 );
                    Toast.makeText(login.this, "username : "+username1+" , "+pin1, Toast.LENGTH_SHORT).show();
                }catch(Exception e){

                }



                String url2 = "http://192.168.0.3:8080/RESTfulExample/web/postjson" ;
                JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, url2,

//                new JSONObject(jsonParams),
                        jsonObj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
//                                Toast.makeText(login.this, response.toString(), Toast.LENGTH_SHORT).show();
                                try {
//                                String a = ;

//                                Toast.makeText(login.this, "a is : "+a, Toast.LENGTH_SHORT).show();

                                    if(response.getString("status").equals("verified")){

                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.clear();
                                        editor.putString("user",username1);
                                        editor.putString("pin", pin1);
                                        editor.putString("balance",response.getString("balance")) ;

                                        editor.commit();

                                        Intent i = new Intent(login.this, Home.class);

                                        Toast.makeText(login.this, " Succesfully Logged in", Toast.LENGTH_LONG).show();

                                        startActivity(i);

                                    }else{
                                        Toast.makeText(login.this, "Failed in verification ", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e){
                                    Toast.makeText(login.this, "Server Error ", Toast.LENGTH_SHORT).show();
                                }



//                                tv.setText(tv.getText()+"\n2 Success : "+response.toString());



                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //   Handle Error
                                tv.setText(tv.getText()+"\n2 Failure : "+error.toString());
                                Toast.makeText(login.this, "Error bhaya : "+error, Toast.LENGTH_LONG).show();
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

            }

        });




    }




}

//  Config decodeConfig, Response.ErrorListener errorListener
/*
StringRequest stringRequest = new StringRequest(Request.Method.GET, url3,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
//add request to queue
requestQueue.add(stringRequest);

*/
