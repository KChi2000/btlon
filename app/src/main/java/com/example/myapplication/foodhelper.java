package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class foodhelper extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    final static String DBNAME= "myfood.db";
    final static int DBVERSION=3;
    public foodhelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table food " +
                "(id integer primary key autoincrement,"+
                "namefood text,"+
                "price int,"+
                "image blob,"+
                "descrip text)"


        );
        sqLiteDatabase.execSQL("create table orders " +
                "(id integer primary key autoincrement,"+
                "name text,"+
                "phone text,"+
                "address text,"+
                "price int,"+
                "image blob,"+
                "quantity int,"+
                "descrip text,"+
                "foodname text,"+
                "total int)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists food");
        sqLiteDatabase.execSQL("drop table if exists orders");
        onCreate(sqLiteDatabase);
//        createOrder(sqLiteDatabase);
    }
    public boolean insertFood(String name,int price,byte [] image,String descrip ){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("namefood",name);
        contentValues.put("price",price);
        contentValues.put("image",image);
        contentValues.put("descrip",descrip);
        long flag = database.insert("food",null,contentValues);
        if(flag<=0){
            return false;
        } else return true;
    }
    public void createOrder(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table orders " +
                "(id integer primary key autoincrement,"+
                "name text,"+
                "phone text,"+
                "address text,"+
                "price int,"+
                "image blob,"+
                "quantity int,"+
                "descrip text,"+
                "foodname text,"+
                "total int)"
        );
    }
    public boolean insertorder(String name, String phone, String address, int price, byte[] image,int quantity,String descrip, String foodname,int total){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("address",address);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("descrip",descrip);
        values.put("foodname",foodname);
        values.put("total",total);
        long flag= database.insert("orders",null,values);
        if(flag<=0){
            return false;
        } else return true;
    }
    public boolean updateorder(int id,String name, String phone, String address, int price, byte[] image,int quantity,String descrip, String foodname,int total){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("address",address);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("descrip",descrip);
        values.put("foodname",foodname);
        values.put("total",total);
        long flag= database.update("orders",values,"id="+id,null);
        if(flag<=0){
            return false;
        } else return true;
    }
    public List<OrdersModel> getOrders(){
        ArrayList<OrdersModel> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor= database.rawQuery("select * from orders",null);


            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    OrdersModel model = new OrdersModel();
                model.setOrderNum(cursor.getInt(0));
                model.setPrice(cursor.getInt(4));
                System.out.println("ahahahahhah "+cursor.getInt(4) );
                model.setImage(cursor.getBlob(5));
                model.setQuantity(cursor.getInt(6));
                model.setSoldItemName(cursor.getString(8));
                model.setDes(cursor.getString(7));
                model.setUsername(cursor.getString(1));
                model.setPhone(cursor.getString(2));
                model.setAddress(cursor.getString(3));
                    list.add(model);
                    cursor.moveToNext();
                }
            }


        cursor.close();
        database.close();
        return list;
    }
    public List<MainModel> getFood() {
        ArrayList<MainModel> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor= database.rawQuery("select * from food",null);

            if( cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
//                    MainModel model = new MainModel(cursor.getBlob(3),cursor.getString(1),cursor.getInt(2),
//                            cursor.getString(4));
                    MainModel model = new MainModel();
                    model.setImage(cursor.getBlob(3));

                    model.setName(cursor.getString(1));
                    System.out.println("in data: "+model.getName());
                    Log.d("aa"," in data "+cursor.getString(1));
                    model.setPrice(cursor.getInt(2));
                    model.setDescription( cursor.getString(4));
                    model.setId(cursor.getInt(0));
                    list.add(model);
                    cursor.moveToNext();
                }
            }


        cursor.close();
        database.close();
        return list;
    }
    public int deleteorder(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("orders","id="+id,null);
    }
    public int deletefood(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("food","id="+id,null);
    }
}
