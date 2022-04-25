package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    byte[] image;
    Bitmap bitmap;
    int price;
    String name;
    String descrip;
    int quantity;
    String startActivity="";
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yellow)));
        startActivity = getIntent().getStringExtra("class");
       setContentfromMain();

        binding.nameuser.setText(getIntent().getStringExtra("username"));
        binding.phone.setText(getIntent().getStringExtra("phone"));
        binding.address.setText(getIntent().getStringExtra("address"));
        binding.image.setImageBitmap(bitmap);
        binding.foodprice.setText(String.valueOf(price));
        binding.foodname.setText(name);
        binding.descrip.setText(descrip);
        int sl = Integer.parseInt(binding.calculate.getText().toString());

        binding.subprice.setText(String.valueOf(sl*price));
        binding.cost.setText(String.valueOf((sl*price)+7));
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count= Integer.parseInt(binding.calculate.getText().toString());
                count++;
                binding.calculate.setText(String.valueOf(count));
                binding.subprice.setText(String.valueOf(count*price));
                binding.cost.setText(String.valueOf((count*price)+7));
            }
        });
        binding.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count= Integer.parseInt(binding.calculate.getText().toString());

                if(count == 1){
                    Toast.makeText(DetailActivity.this, "Product's quantity must not equal 0", Toast.LENGTH_SHORT).show();
                } else {
                    count--;
                    binding.calculate.setText(String.valueOf(count));
                }
                binding.subprice.setText(String.valueOf(count*price));
                binding.cost.setText(String.valueOf((count*price)+7));
            }
        });
       final foodhelper helper = new foodhelper(this);

        binding.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.nameuser.getText().toString().isEmpty() || binding.phone.getText().toString().isEmpty() || binding.address.getText().toString().isEmpty()){
                    Toast.makeText(DetailActivity.this, "Fill all your information please !", Toast.LENGTH_SHORT).show();
                }
                else {
                    int sl = Integer.parseInt(binding.calculate.getText().toString());
                    int total = Integer.parseInt(binding.cost.getText().toString());
                    boolean isInserted= helper.insertorder(binding.nameuser.getText().toString(),binding.phone.getText().toString(),
                            binding.address.getText().toString(),price,image,sl,descrip,name,total);
                    if(isInserted){
                        Toast.makeText(DetailActivity.this, "Order Successfully !", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DetailActivity.this, "oops ! somthing went wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
    public void setContentfromMain(){
        image = getIntent().getByteArrayExtra("image");
        bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
        price = getIntent().getIntExtra("price",0);
        name = getIntent().getStringExtra("name");
        descrip = getIntent().getStringExtra("descrip");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}