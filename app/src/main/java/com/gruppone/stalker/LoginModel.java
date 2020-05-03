package com.gruppone.stalker;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;

public class LoginModel {

  // XXX needs to return void, because of all the callback stuff
  // XXX also, needs to be fixed to actually work
  @SuppressLint("UnknownNullness")
  public Boolean login(@NonNull String email, @NonNull String password) {
    return true;
    // WebSingleton.getInstance(App.getAppContext())
    //            .addToRequestQueue(new JsonObjectRequest(
    //              "https://pokeapi.co/api/v2/pokemon/ditto/", null,
    //              new Response.Listener<JSONObject>() {
    //                @Override
    //                public void onResponse(JSONObject jsonObject) {
    //                  //TODO
    //                }
    //              }, new Response.ErrorListener() {
    //              @Override
    //              public void onErrorResponse(VolleyError volleyError) {
    //                //TODO
    //              }
    //            }));
  }
}
