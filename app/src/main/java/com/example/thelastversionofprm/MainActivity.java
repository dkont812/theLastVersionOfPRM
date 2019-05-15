package com.example.thelastversionofprm;


import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.thelastversionofprm.Utility.BottomNavigationViewHelper;
import com.example.thelastversionofprm.Utility.RecyclerViewAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
//    ArrayList<File> list;
//    GridView gridView;
    private ArrayList<File> images = new ArrayList<>();
    private ArrayList<String> nameIm = new ArrayList<>();
    private File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "my_folder");

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting.");
        setupBottomNavigationView();
        createFolder(folder);
        images = imageReader(folder);
        nameIm = initImage(folder);

        initRecycleView();



    }

    private ArrayList<String> initImage(File externalStorageDirectory){
        ArrayList<String> b = new ArrayList<>();
        File[] files = externalStorageDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
            b.add(files[i].getName());
        }

        return b;

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ArrayList<File> imageReader(File externalStorageDirectory) {
        ArrayList<File> b = new ArrayList<>();
        File file = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "cat.png");
        //File [] file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.getExternalStorageState(folder.getAbsoluteFile())));
        File[] files = externalStorageDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
            b.add(files[i]);
        }
        return b;
    }
    private void initRecycleView(){
        RecyclerView recyclerView = findViewById(R.id.recycle_view) ;
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,nameIm,images);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void createFolder(File folder) {
        boolean bool = true;
        if (!folder.exists())
            bool = folder.mkdirs();

    }


    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting bottomNaviView");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(MainActivity.this, bottomNavigationViewEx);
    }



}

