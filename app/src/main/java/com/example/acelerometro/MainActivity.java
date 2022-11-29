package com.example.acelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvValorX;
    private TextView tvValorY;
    private TextView tvValorZ;

    private SensorManager sensorManager;
    private Sensor acelerometro;
    private SensorEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvValorX = (TextView) findViewById(R.id.tvValorX);
        tvValorY = (TextView) findViewById(R.id.tvValorY);
        tvValorZ = (TextView) findViewById(R.id.tvValorZ);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (acelerometro == null) {
            Toast.makeText(this, "O dispositivo não possui acelerômetro!",
                    Toast.LENGTH_LONG).show();
            finish();
        }

        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                tvValorX.setText( String.valueOf( x ) );
                tvValorY.setText( String.valueOf( y ) );
                tvValorZ.setText( String.valueOf( z ) );
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, acelerometro,
                SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
    }
}