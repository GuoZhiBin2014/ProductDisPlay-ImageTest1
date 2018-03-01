package com.cambricon.productdisplay.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell on 18-2-5.
 */

public class CommDB {
    public static final String DATABASE_NAME="BenchMarkDB";
    public static final int DATABASE_VERSION=1;
    //创建图片分类表
    private static final String CREATE_TABLE_Classification="CREATE TABLE if not exists " + ClassificationDB.SQLITE_TABLE + " (" +
            ClassificationDB.KEY_ROWID + " integer PRIMARY KEY autoincrement," +
            ClassificationDB.KEY_NAME + "," +
            ClassificationDB.KEY_TIME + "," +
            ClassificationDB.KEY_FPS + "," +
            ClassificationDB.KEY_RESULT + "," +
           " UNIQUE (" + ClassificationDB.KEY_NAME +")"+"ON CONFLICT REPLACE"+");";

    //创建目标检测分类表
    private static final String CREATE_TABLE_Detection="CREATE TABLE if not exists " + DetectionDB.SQLITE_TABLE + " (" +
            DetectionDB.KEY_ROWID + " integer PRIMARY KEY autoincrement," +
            DetectionDB.KEY_NAME + "," +
            DetectionDB.KEY_TIME + "," +
            DetectionDB.KEY_FPS + "," +
            DetectionDB.KEY_RESULT + "," +
            DetectionDB.KEY_NETTYPE + "," +
            " UNIQUE (" + DetectionDB.KEY_NAME +")"+"ON CONFLICT REPLACE"+");";

    private final Context context;
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase db;

    public CommDB(Context context){
        this.context=context;
        this.dataBaseHelper=new DataBaseHelper(this.context);
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {

        DataBaseHelper(Context context) {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_Classification);
            sqLiteDatabase.execSQL(CREATE_TABLE_Detection);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    /**
     * open db
     * @return
     * @throws SQLException
     */

    public CommDB open() throws SQLException{
        this.db=this.dataBaseHelper.getWritableDatabase();
        return this;
    }

    /**
     * close db
     */
    public void close(){
        if(this.dataBaseHelper!=null){
            this.dataBaseHelper.close();
        }
    }
}
