package com.agrawalgaurav.apps.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.agrawalgaurav.apps.sharedpreference.customlist.CustomList;

import java.util.ArrayList;

public class send_sms extends AppCompatActivity{
Button send;
//    private static CustomAdapter adapter;

    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        send = (Button) findViewById(R.id.sendsms);

        final String[] listArray = {"Android ListView Example","ListVIew Android","Simple Android ListView","ListView in Android","Android ListView Example","ListVIew Android","Simple Android ListView","ListView in Android","Android ListView Example","ListVIew Android","Simple Android ListView","ListView in Android"};


//        Adapter a = new Adapter(
        ArrayAdapter adapter1 = new ArrayAdapter<>(this,R.layout.listview_row, listArray);
        ListView mlistView = (ListView)findViewById(R.id.smslist);
        mlistView.setAdapter(adapter1);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(send_sms.this, listArray[position], Toast.LENGTH_SHORT).show();
            }
        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(send_sms.this, "Clicked ..", Toast.LENGTH_SHORT).show();
            }
        });





//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.list);

        dataModels= new ArrayList<>();
        Context context;
        context = send_sms.this ;
        View row ;
        LayoutInflater inflater =  (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.row_item, null);
        row.setTag(row.findViewById(R.id.item_info));
        Bitmap img = (Bitmap) row.getTag();

        dataModels= new ArrayList<>();

        dataModels.add(new DataModel("Apple Pie", "Android 1.0", "1","September 23, 2008",img  ));
        dataModels.add(new DataModel("Banana Bread", "Android 1.1", "2","February 9, 2009",img  ));
        dataModels.add(new DataModel("Cupcake", "Android 1.5", "3","April 27, 2009",img  ));
        dataModels.add(new DataModel("Donut","Android 1.6","4","September 15, 2009",img  ));
        dataModels.add(new DataModel("Eclair", "Android 2.0", "5","October 26, 2009",img  ));
        dataModels.add(new DataModel("Froyo", "Android 2.2", "8","May 20, 2010",img  ));
        dataModels.add(new DataModel("Gingerbread", "Android 2.3", "9","December 6, 2010",img  ));
        dataModels.add(new DataModel("Honeycomb","Android 3.0","11","February 22, 2011",img  ));
        dataModels.add(new DataModel("Ice Cream Sandwich", "Android 4.0", "14","October 18, 2011",img  ));
        dataModels.add(new DataModel("Jelly Bean", "Android 4.2", "16","July 9, 2012",img  ));
        dataModels.add(new DataModel("Kitkat", "Android 4.4", "19","October 31, 2013",img  ));
        dataModels.add(new DataModel("Lollipop","Android 5.0","21","November 12, 2014",img  ));
        dataModels.add(new DataModel("Marshmallow", "Android 6.0", "23","October 5, 2015",img  ));


        adapter= new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataModel dataModel= dataModels.get(position);

                Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


}
