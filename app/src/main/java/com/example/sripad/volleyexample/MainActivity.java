package com.example.sripad.volleyexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText RName,RMobile,REmail,RPass;
    private RequestQueue jQueue;

    ProgressDialog progressDialog;
    JSONObject jsonObject = new JSONObject();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        jQueue = Volley.newRequestQueue(getApplicationContext());

        button = findViewById(R.id.reg);
        RName = findViewById(R.id.name);
        RMobile = findViewById(R.id.mob);
        REmail = findViewById(R.id.email);
        RPass = findViewById(R.id.pass);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jSonParse();


            }
        });

    }

    private void jSonParse(){
        String URL = "http://androindian.com/apps/example_app/api.php";

        try {
            jsonObject.put("name",RName.getText().toString().trim());
            jsonObject.put("mobile",RMobile.getText().toString().trim());
            jsonObject.put("email",REmail.getText().toString().trim());
            jsonObject.put("pswrd",RPass.getText().toString().trim());
            jsonObject.put("baction","register_user");



        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Content Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        try {

                            /*String r = response.getString("response");
                            if(r.equalsIgnoreCase("failed")){

                                String r1=response.getString("user");

                                Toast.makeText(MainActivity.this, ""+r1, Toast.LENGTH_SHORT).show();

                            }else if(r.equalsIgnoreCase("success")){

                                String r2=response.getString("user");

                                Toast.makeText(MainActivity.this, ""+r2, Toast.LENGTH_SHORT).show();


                            }else {

                                String r3=response.getString("user");

                                Toast.makeText(MainActivity.this, ""+r3, Toast.LENGTH_SHORT).show();


                            }*/
                            Toast.makeText(getApplicationContext(),response.getString("response").concat("\n"+response.getString("user")), Toast.LENGTH_LONG).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_LONG).show();

            }
        });

        jQueue.add(jsonObjectRequest);
    }
}
