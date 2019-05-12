package com.example.thelastversionofprm.Utility;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.example.thelastversionofprm.CameraActivity;
import com.example.thelastversionofprm.MainActivity;
import com.example.thelastversionofprm.R;
import com.example.thelastversionofprm.SettringActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: setting u BottomNavigationiew");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }


    public static void enableNavigation(final Context context,BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.ic_house:
                        Intent intent1 = new Intent(context, MainActivity.class);
                        context.startActivity(intent1);
                    break;
                    case R.id.ic_setting:
                        Intent intent2 = new Intent(context, SettringActivity.class);
                        context.startActivity(intent2);

                    break;
                    case R.id.ic_camera:
                        Intent intent3 = new Intent(context, CameraActivity.class);
                        context.startActivity(intent3);
                    break;
                }
                return false;
            }
        });

    }
}
