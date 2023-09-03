package com.example.secciastatapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Map;
import java.util.function.Consumer;

public class Query {

    public static String Email = "";

    public static void DatabaseQuery(Map<String, String> paramV, Consumer<JSONArray> Method, Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://192.168.1.103/Mobile/API.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray QueryResponse = new JSONArray(response);
                            Method.accept(QueryResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getLocalizedMessage());
            }
        }){
            protected Map<String, String> getParams(){

                return paramV;
            }
        };
        queue.add(stringRequest);
    }

}
