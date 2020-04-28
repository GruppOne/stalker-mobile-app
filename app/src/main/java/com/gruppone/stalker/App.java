package com.gruppone.stalker;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import com.beardedhen.androidbootstrap.TypefaceProvider;

public class App extends Application {

  //It's not a leak because the reference is bound to the application context
  @SuppressLint("StaticFieldLeak")
  private static Context context;

  public void onCreate() {
    super.onCreate();
    context = getApplicationContext();
  }

  @NonNull
  public static Context getAppContext() {
    return context;
  }
}
