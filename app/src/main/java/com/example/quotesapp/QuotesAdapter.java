package com.example.quotesapp;


import static android.content.Context.CLIPBOARD_SERVICE;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.DataViewHolder> {

    Context context;
    String[] quote_list;

    public QuotesAdapter(Context context, String[] quotesList) {
        this.context = context;
        this.quote_list = quotesList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_list_view, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.textView.setText(quote_list[position]);
        holder.likeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.likeTextView.getText() == "Like") {
                    holder.likeTextView.setText("Liked");
                    holder.likeImageView.setBackgroundResource(R.drawable.baseline_favorite_24);
                } else {
                    holder.likeTextView.setText("Like");
                    holder.likeImageView.setBackgroundResource(R.drawable.baseline_favorite_border_24);
                }
            }
        });

        holder.copyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(holder.textView.getText());
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();

            }
        });

        holder.saveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cardLayout.setDrawingCacheEnabled(true);

                Bitmap bitmap = Bitmap.createBitmap(holder.cardLayout.getDrawingCache());
                storeImage(bitmap);
            }
        });

        holder.shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cardLayout.setDrawingCacheEnabled(true);
                Bitmap b = Bitmap.createBitmap(holder.cardLayout.getDrawingCache());
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                        b, "Title", null);
                Uri imageUri = Uri.parse(path);
                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                context.startActivity(Intent.createChooser(share, "Select"));

            }
        });

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.green));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView likeTextView;
        ImageView likeImageView;
        CardView cardView;
        LinearLayout likeLayout;
        LinearLayout saveLayout;
        RelativeLayout cardLayout;
        LinearLayout copyLayout;
        LinearLayout shareLayout;


        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.quoteText);
            cardView = itemView.findViewById(R.id.cardView);
            likeLayout = itemView.findViewById(R.id.likeLayout);
            saveLayout = itemView.findViewById(R.id.saveLayout);
            copyLayout = itemView.findViewById(R.id.copyLayout);
            shareLayout = itemView.findViewById(R.id.shareLayout);
            likeImageView = itemView.findViewById(R.id.likeImage);
            likeTextView = itemView.findViewById(R.id.likeText);
            cardLayout = itemView.findViewById(R.id.quoteLayout);
        }
    }

    private void storeImage(Bitmap image) {
        File pictureFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/quotes");
        if (!pictureFile.exists()) {
            if (!pictureFile.mkdirs()) {
                Log.d("TAG", "Error creating media file, check storage permissions: ");// e.getMessage());
                return;
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(new File(pictureFile.getPath() + File.separator + "photo.png"));
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }
}
