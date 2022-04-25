package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityAddFoodBinding;

import java.io.ByteArrayOutputStream;

public class addFood extends AppCompatActivity {
    ImageView img;
    EditText ed1,ed2,ed3;
    ActivityAddFoodBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        foodhelper helper = new foodhelper(this);
        binding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pizza1);
                ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                byte[] img = byteArrayOutputStream.toByteArray();
                boolean g = helper.insertFood(binding.namef.getText().toString(),Integer.parseInt(binding.pricef.getText().toString()),
                        img,binding.desf.getText().toString());
                if(g){
                    Toast.makeText(addFood.this, "inserted", Toast.LENGTH_SHORT).show();
                }
                 else {
                    Toast.makeText(addFood.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}