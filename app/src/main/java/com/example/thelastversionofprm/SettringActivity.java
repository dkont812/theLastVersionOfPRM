package com.example.thelastversionofprm;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.example.thelastversionofprm.Utility.BottomNavigationViewHelper;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class SettringActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SettringActivity";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Log.d(TAG, "onCreate: started");

        Button button = (Button) findViewById(R.id.changeColor);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeColor();
            }
        });

//        setupBottomNavigationView();
    }

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting bottomNaviView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(SettringActivity.this, bottomNavigationViewEx);
    }

    private void changeColor() {
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(0xacacac)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        //TODO
                        //selectedColor;
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
