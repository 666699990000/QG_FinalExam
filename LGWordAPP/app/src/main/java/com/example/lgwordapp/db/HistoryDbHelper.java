package com.example.lgwordapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lgwordapp.info.HistoryInfo;


import java.util.ArrayList;
import java.util.List;

public class HistoryDbHelper extends SQLiteOpenHelper {
    private static HistoryDbHelper sHelper;
    private static final String DB_NAME = "history.db";
    private static final int VERSION = 1;

    //实现其中一个构造方法
    public HistoryDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例
    public synchronized static HistoryDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new HistoryDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建history_table表
        db.execSQL("create table history_table(history_id integer primary key autoincrement, " +
                "yword text," +      //原文
                "tword text" +       // 译文
                ")");

    }

    //判断是否添加过该数据，使数据不重复显示
    @SuppressLint("Range")
    public boolean isHistory(String yword) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        String sql= "select history_id,yword,tword from history_table where yword=?";
        String[] selectionArgs = {yword};
        Cursor cursor = db.rawQuery(sql,selectionArgs) ;
        return cursor.moveToNext();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //添加单词进入历史记录
    public int addHistory(String yword, String tword) {
        if (!isHistory(yword)){
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("yword", yword);
        values.put("tword", tword);
        String nullColumnHack = "values(null,?,?)";
        //执行
        int insert = (int) db.insert("history_table", nullColumnHack, values);
        db.close();
        return insert;
        }
        return 0;
    }

    //查询历史记录
    @SuppressLint("Range")
    public List<HistoryInfo> queryHistoryListData(String yword) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<HistoryInfo> list = new ArrayList<>();
        String sql;
        Cursor cursor ;
        if (yword == null) {
            sql = "select history_id,yword,tword from history_table";
            cursor = db.rawQuery(sql, null);
        }else {
            sql = "select history_id,yword,tword from history_table where yword=?";
            cursor = db.rawQuery(sql, new String[]{yword});
        }

        while (cursor.moveToNext()) {
            int history_id = cursor.getInt(cursor.getColumnIndex("history_id"));
            String yWord = cursor.getString(cursor.getColumnIndex("yword"));
            String tword = cursor.getString(cursor.getColumnIndex("tword"));
            list.add(new HistoryInfo(history_id,yWord, tword));
        }
        cursor.close();
        db.close();
        return list;
    }
    @SuppressLint("Range")
    public List<HistoryInfo> searchHistoryListData(String keyword) {
        SQLiteDatabase db = getReadableDatabase();
        List<HistoryInfo> list = new ArrayList<>();
        String sql = "select history_id, yword, tword from history_table";
        String[] selectionArgs = null;
        if (keyword != null && !keyword.isEmpty()) {
            sql += " where yword like ?";
            selectionArgs = new String[]{"%" + keyword + "%"};
        }
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int historyId = cursor.getInt(cursor.getColumnIndex("history_id"));
            String yword = cursor.getString(cursor.getColumnIndex("yword"));
            String tword = cursor.getString(cursor.getColumnIndex("tword"));
            list.add(new HistoryInfo(historyId, yword, tword));
        }
        cursor.close();
        return list;
    }

}

