package com.example.thelastversionofprm.Utility;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thelastversionofprm.R;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private static final String TAG = "RecyclerViewAdapter";
    private RecyclerView recyclerView;
    private ArrayList<String> imageNames = new ArrayList<>();
    private ArrayList<File> images = new ArrayList<>();
    private Context context;



    public RecyclerViewAdapter ( Context context, ArrayList<String> imageNames, ArrayList<File> images) {
        this.imageNames = imageNames;
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listern,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder:  called");

        Glide.with(context)
                .asBitmap()
                .load(images.get(position))


                .into(holder.image);


            holder.imageName.setText(imageNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked");
                Toast.makeText(context, holder.imageName.toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,GalleryActivity.class);
                intent.putExtra("image_url",images.get(position));
                intent.putExtra("image_name", imageNames.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return imageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);


        }


    }
}
