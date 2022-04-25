package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewholder> {

   ArrayList<OrdersModel> list;
   Context context;

    public OrderAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.viewholder holder, int position) {
        OrdersModel model = list.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(model.getImage(), 0,model.getImage().length);
        holder.image.setImageBitmap(bitmap);
        holder.namefood.setText(model.getSoldItemName());
        holder.orderNum.setText(String.valueOf(model.getOrderNum()));
        holder.price.setText(String.valueOf(model.getPrice()));
        holder.quantity.setText(String.valueOf(model.getQuantity()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,UpdatefoodActivity.class);
                i.putExtra("class","OrderActivity");
                i.putExtra("imageOrder", model.getImage());
                i.putExtra("namefoodOrder",model.getSoldItemName());
//                i.putExtra("ordernumOrder",model.getOrderNum());
                i.putExtra("priceOrder",model.getPrice());
                i.putExtra("quantityOrder",model.getQuantity());
                i.putExtra("descripOrder",model.getDes());
                i.putExtra("username",model.getUsername());
                i.putExtra("phone",model.getPhone());
                i.putExtra("address",model.getAddress());
                i.putExtra("id",model.getOrderNum());
               context.startActivity(i);
            }
        });
    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            foodhelper helper = new foodhelper(context);
            new AlertDialog.Builder(context)
                    .setTitle("Delete item orders")
                    .setMessage("Are you sure to delete "+model.getSoldItemName()+" from Orders")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            helper.deleteorder(model.getOrderNum());
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            OrderActivity o = new OrderActivity();
                            o.getChange(position);
//                                context.startActivity(new Intent(context,OrderActivity.class));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

            return true;
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView orderNum,price,quantity,namefood;
        public viewholder( View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.imageview);
            orderNum= itemView.findViewById(R.id.ordernum);
            price=itemView.findViewById(R.id.price);
            quantity=itemView.findViewById(R.id.quantity);
            namefood=itemView.findViewById(R.id.namefood);
        }
    }
}
