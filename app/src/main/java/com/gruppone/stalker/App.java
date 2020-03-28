package com.gruppone.stalker;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import com.beardedhen.androidbootstrap.TypefaceProvider;

public class App extends Application {
  //It's not a leak because the reference is bound to the application context
  @SuppressLint("StaticFieldLeak")
  private static Context context;

  public void onCreate() {
    super.onCreate();
    context = getApplicationContext();
    TypefaceProvider.registerDefaultIconSets();
  }

  public static Context getAppContext() {
    return context;
  }
}