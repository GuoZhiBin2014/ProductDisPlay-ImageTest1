package com.cambricon.productdisplay.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.cambricon.productdisplay.bean.ClassificationImage;

import java.util.ArrayList;

/**
 * Created by dell on 18-2-5.
 */

public class ClassificationDB {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TIME = "time";
    public static final String KEY_FPS = "fps";
    public static final String KEY_RESULT = "result";

    private static final String LOG_TAG = "ClassificationDB";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    static final String SQLITE_TABLE = "ClassificationTable";
    private final Context mContext;

    public ClassificationDB(Context context) {
        this.mContext = context;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, CommDB.DATABASE_NAME, null, CommDB.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public ClassificationDB open() throws SQLException {
        databaseHelper = new DatabaseHelper(mContext);
        db = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

    /**
     * 创建分类表字段
     *
     * @param name
     * @param time
     * @param fps
     * @param result
     * @return
     */

    public long addClassification(String name, String time, String fps, String result) {
        long createResult = 0;
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_TIME, time);
        initialValues.put(KEY_FPS, fps);
        initialValues.put(KEY_RESULT, result);
        try {
            createResult = db.insert(SQLITE_TABLE, null, initialValues);
        } catch (Exception e) {
            //handle exception
        }
        return createResult;
    }

    /**
     * 删除所有字段
     *
     * @return
     */

    public boolean deleteAllClassification() {
        int doneDelete = 0;
        try {
            doneDelete = db.delete(SQLITE_TABLE, null, null);
        } catch (Exception e) {

        }
        return doneDelete > 0;
    }

    /**
     * 删除表中字段
     *
     * @param name
     * @return
     */

    public boolean deleteTicketByName(String name) {
        int isDelete;
        String[] tname;
        tname = new String[]{name};
        isDelete = db.delete(SQLITE_TABLE, KEY_NAME + "=?", tname);
        return isDelete > 0;
    }

    /**
     * 获取表中所有
     *
     * @return
     */
    public ArrayList<ClassificationImage> fetchAll() {
        ArrayList<ClassificationImage> allTicketsList = new ArrayList<>();
        Cursor mCursor = null;
        mCursor = db.query(SQLITE_TABLE, new String[]{KEY_ROWID, KEY_NAME,KEY_TIME, KEY_FPS, KEY_RESULT},
                null, null, null, null, null);
        if (mCursor.moveToFirst()) {
            do {
                ClassificationImage cfimage = new ClassificationImage();
                cfimage.setName(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_NAME)));
                cfimage.setFps(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_FPS)));
                cfimage.setResult(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_RESULT)));
                cfimage.setTime(mCursor.getString(mCursor.getColumnIndexOrThrow(KEY_TIME)));
                allTicketsList.add(cfimage);
            } while (mCursor.moveToNext());
        }

        if(mCursor!=null||!mCursor.isClosed()){
            mCursor.close();
        }
        for(int i=0;i<allTicketsList.size();i++){
            Log.d("huangyaling","allTicketsList="+allTicketsList.get(i).getName());
        }
        return allTicketsList;
    }
}
