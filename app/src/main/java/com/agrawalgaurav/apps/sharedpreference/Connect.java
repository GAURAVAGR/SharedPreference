package com.agrawalgaurav.apps.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GAURAV on 05-Jan-17.
 */

public class Connect extends Activity {


    public static final String MyPREFERENCES = "MyPrefs" ;
//    SharedPreferences sharedpreferences;
    RequestQueue requestQueue;
    JSONObject resp ;

    public JSONObject getData(JSONObject jsonObj,final Context context, final TextView balance_view){
        requestQueue = Volley.newRequestQueue(context);
//        Home.sharedpreferences = getSharedPreferences(Home.MyPREFERENCES, context.MODE_PRIVATE);
      /*  final  TextView balance_view  = (TextView) findViewById(R.id.balance_view) ;
*/
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
                                Toast.makeText(context, ""+jsonObj2.getString("message"), Toast.LENGTH_SHORT).show();
                            }else if(jsonObj2.getString("result").equals("Successful")){
                                Toast.makeText(context, ""+jsonObj2.getString("message"), Toast.LENGTH_SHORT).show();
                                balance_view.setText("Hello "+jsonObj2.get("fromuser")+", your balance is : "+jsonObj2.getString("from_user_updated_balance"));

                            } }catch(Exception e){}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //   Handle Error

                        Toast.makeText(context, "Error Hua : "+error, Toast.LENGTH_LONG).show();
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


    } // end method body




}
