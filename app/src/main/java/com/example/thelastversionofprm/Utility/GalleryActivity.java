package com.example.thelastversionofprm.Utility;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thelastversionofprm.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started");
getIncomingIntent();
        
    }



    private void getIncomingIntent(){

        Log.d(TAG, "getIncomingIntent: cheking");
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){
            Log.d(TAG, "getIncomingIntent:  extra not null");

            //String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");
            File file = (File)getIntent().getExtras().get("image_url");
            setImage(file,imageName);
        }
    }
    
    private void setImage(File imageUrl, String imageName){
        Log.d(TAG, "setImage: widget get on");
        File file = new File("settingColor.txt");
        String tepm = "";



        TextView name = findViewById(R.id.image_description);
        name.setText(imageName);
        TextView text = findViewById(R.id.tex_location);
        String[] tmp = imageName.split("_");
        text.setText(tmp[2] + "," +tmp[3]);

        text.setTextColor(Integer.valueOf(readFromFile(getApplicationContext(),"colorSet.txt")));
        text.setTextSize(Integer.valueOf(readFromFile(getApplicationContext(),"sizeSet.txt")));
        ImageView image = findViewById(R.id.image_view_rec);

        Glide.with(this)
.asBitmap()
                .load(imageUrl)
                .into(image);
    }
//colorSet.txt
    private String readFromFile(Context context, String filePath) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(filePath);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
