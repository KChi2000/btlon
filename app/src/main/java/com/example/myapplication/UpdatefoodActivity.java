package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityAddFoodBinding;
import com.example.myapplication.databinding.ActivityDetailBinding;
import com.example.myapplication.databinding.ActivityUpdatefoodBinding;

public class UpdatefoodActivity extends AppCompatActivity {
    ActivityUpdatefoodBinding binding;
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
        binding = ActivityUpdatefoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_updateorder);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yellow)));
        startActivity = getIntent().getStringExtra("class");
        setContentfromOrder();
        binding.calculate.setText(String.valueOf(quantity));
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
                    Toast.makeText(UpdatefoodActivity.this, "Product's quantity must not equal 0", Toast.LENGTH_SHORT).show();
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
                int sl = Integer.parseInt(binding.calculate.getText().toString());
                int total = Integer.parseInt(binding.cost.getText().toString());
                boolean isUpdated =helper.updateorder(getIntent().getIntExtra("id",0),binding.nameuser.getText().toString(),binding.phone.getText().toString(),
                        binding.address.getText().toString(),price,image,sl,descrip,name,total);
                if(isUpdated){
                    Toast.makeText(UpdatefoodActivity.this, "Update Successfully !", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UpdatefoodActivity.this, "oops ! somthing went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void setContentfromOrder(){
        image = getIntent().getByteArrayExtra("imageOrder");
        bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
        price = getIntent().getIntExtra("priceOrder",0);
        name = getIntent().getStringExtra("namefoodOrder");
        descrip = getIntent().getStringExtra("descripOrder");
        quantity = getIntent().getIntExtra("quantityOrder",0);
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