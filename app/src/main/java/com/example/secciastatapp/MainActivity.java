package com.example.secciastatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;
    TextView InfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);

        loginButton = findViewById(R.id.LoginButton);

        InfoText = findViewById(R.id.textViewInfo);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!email.getText().toString().equals("") || !password.getText().toString().equals("")){

                    Map<String, String> paramV = new HashMap<>();
                    paramV.put("RequestType", "CheckLogin");
                    paramV.put("Email", email.getText().toString());
                    paramV.put("Password", password.getText().toString());

                    Query.DatabaseQuery(paramV, loginCheck(), getApplicationContext());
                }
                else {
                    String Info = "Please enter your credentials.";
                    InfoText.setText(Info);

                }
            }
        });

    }

    private Consumer<JSONArray> loginCheck (){

        return array ->{

           JSONObject object = null;
           boolean result;
           try {
               object = array.getJSONObject(0);
               result= object.getBoolean("result");
           } catch (JSONException e) {
               throw new RuntimeException(e);
           }

           if (result){

               Query.Email = email.getText().toString();

               Intent intent = new Intent(this, StatActivity.class);
               startActivity(intent);
           }else{
               String failText = "Login Failed !";
               InfoText.setText(failText);
           }

       };
    }
}