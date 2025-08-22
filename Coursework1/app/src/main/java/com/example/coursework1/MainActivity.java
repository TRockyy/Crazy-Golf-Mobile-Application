package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener = new MyLocationListener();

    TextView highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting device location
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        try {
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
        } catch (SecurityException e) {
            System.out.println(e.toString());
        }

        //Display highest score
        highscore = findViewById(R.id.highscore);
        Database mydb = new Database(this);

        String highestscore = mydb.getHighest();
        highscore.setText("H I G H E S T   S C O R E - " + highestscore);
    }

    //START GAME BUTTON
    public void startgame(View view) {
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
    }

    //VIEW HISTORY BUTTON
    public void History(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

    //Get latitude and logtitude - Location must be enabled in settings
    public class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            final TextView latitude = (TextView) findViewById(R.id.latitude);
            final TextView longitude = (TextView) findViewById(R.id.longitude);
            double latitudevalue = location.getLatitude();
            double longitudevalue = location.getLongitude();
            latitude.setText(String.valueOf(latitudevalue));
            longitude.setText(String.valueOf(longitudevalue));
            Log.d("lattest", "Value is - " + latitudevalue);
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {}
        public void onProviderEnabled(String provider) {}
        public void onProviderDisabled(String provider) {}
    }

    //Destory - So locationd doesn't drain battery.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);  // Must stay here otherwise the GPS receiver will not stop working and will quickly consume the battery of the device.
    }





}
