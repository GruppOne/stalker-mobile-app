package com.gruppone.stalker;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Display extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display);

    Intent intent = getIntent();
    Location location = intent.getParcelableExtra("lastLocation");

    ((TextView) findViewById(R.id.latitude)).setText(
      String.valueOf(location.getLatitude()));

    ((TextView) findViewById(R.id.longitude)).setText(
      String.valueOf(location.getLongitude()));
  }
}
