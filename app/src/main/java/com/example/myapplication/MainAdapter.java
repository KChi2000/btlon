package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewholder> {
    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public viewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_mainfood,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder( MainAdapter.viewholder holder, int position) {
        final MainModel model = list.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(model.getImage(), 0,model.getImage().length);
        holder.foodimage.setImageBitmap(bitmap);
        holder.mainName.setText(model.getName());
        holder.price.setText(String.valueOf(model.getPrice()));
        holder.description.setText(model.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra("class","MainActivity");
                i.putExtra("image", model.getImage());
                i.putExtra("name", model.getName());
                i.putExtra("price", model.getPrice());
                i.putExtra("descrip", model.getDescription());
                context.startActivity(i);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                foodhelper h = new foodhelper(context);
                int f =h.deletefood(model.getId());
                if(f>0){
                    Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView foodimage;
        TextView mainName,price,description;
        public viewholder( View itemView) {
            super(itemView);
            foodimage = itemView.findViewById(R.id.imageview);
            mainName=itemView.findViewById(R.id.namefood);
            price=itemView.findViewById(R.id.price);
            description=itemView.findViewById(R.id.description);
            

        }
    }
}
