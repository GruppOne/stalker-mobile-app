package com.gruppone.stalker;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Looper;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class LocationNotifier extends JobIntentService {
  static int JOB_ID = 1000;

  public static void enqueue(Context ctx, Intent work) {
    enqueueWork(ctx, LocationNotifier.class, JOB_ID, work);
  }

  @Override
  protected void onHandleWork(@NonNull Intent intent) {
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setComponent(new ComponentName(this, Display.class));
    startActivity(intent);
  }
}
