package com.example.thelastversionofprm;


import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.thelastversionofprm.Utility.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ArrayList<File> list;
    GridView gridView;
    private File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "my_folder");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting.");
        setupBottomNavigationView();
        createFolder(folder);
        gridView = findViewById(R.id.gridView);
        list = imageReader(folder);
        gridView.setAdapter(new gridAdapter());

    }

    private ArrayList<File> imageReader(File externalStorageDirectory) {
        ArrayList<File> b = new ArrayList<>();
        File[] files = externalStorageDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
            b.add(files[i]);
        }
        return b;
    }

    private void createFolder(File folder) {
        boolean bool = true;
        if (!folder.exists())
            bool = folder.mkdirs();

    }


    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting bottomNaviView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(MainActivity.this, bottomNavigationViewEx);
    }


    private class gridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View convertView = null;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.layout_grid_imageview, viewGroup, false);
                ImageView myImage = (ImageView) convertView.findViewById(R.id.gridImageView);
                myImage.setImageURI(Uri.parse(list.get(i).toString()));
            }
            return convertView;
        }
    }
}

