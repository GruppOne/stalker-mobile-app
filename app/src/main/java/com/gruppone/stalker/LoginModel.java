package com.gruppone.stalker;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

public class LoginModel {
  public void login(String email, String password) {
    WebSingleton.getInstance()
                .addToRequestQueue(new JsonObjectRequest(
                  "https://pokeapi.co/api/v2/pokemon/ditto/", null,
                  new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                      //TODO
                    }
                  }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError volleyError) {
                    //TODO
                  }
                }));
  }
}
