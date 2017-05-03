package com.github.duinodu.motionsound;

/**
 * Created by duino on 17-5-3.
 */

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class MotionSensor implements SensorEventListener {
    private static final String TAG = "MotionSensor";
    private Sensor accelerateSensor;
    private SensorManager sensorManager;
    private MotionListener motionListener;

    public MotionSensor(MotionListener motionListener) {
        Log.i(TAG, "start...");
        this.motionListener = motionListener;
    }

    public void init(Context context){
        if (this.sensorManager == null){
            this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            this.accelerateSensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Log.i(TAG, "Registered sensors...");
            sensorManager.registerListener(this, this.accelerateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.motionListener.update(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    // declaration
    public interface MotionListener {
        void update(float x, float y, float z);
    }
}
