package com.naver.cafe.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class IChingDBMangger {
	public static final String KEY_IDX = "idx";
	public static final String KEY_TITLE = "title";
    public static final String KEY_ASK = "ask";
    public static final String KEY_ANSWER = "answer";
    public static final String KEY_REAL_RESULT = "real_result";
    public static final String KEY_ACCURACY = "accuracy";
    public static final String KEY_ASK_DATE = "ask_date";
    public static final String KEY_REAL_DATE = "real_date";

    public static IChingDBMangger mIChingDBMangger = null;
    public static SQLiteDatabase mSQLiteDatabase;
    
	public static final String KEYS[] = {KEY_IDX,KEY_TITLE,KEY_ASK,KEY_ANSWER,KEY_REAL_RESULT,KEY_ACCURACY,KEY_ASK_DATE,KEY_REAL_DATE};    

    private IChingDBAdapter mIChingDBAdapter;

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "iching_history";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
            "create table "+DATABASE_TABLE+" ( "+
				KEY_IDX+" integer primary key AUTOINCREMENT, "+
				KEY_TITLE+" text not null, "+
				KEY_ASK+" text not null, "+
				KEY_ANSWER+" VARCHAR, "+
				KEY_REAL_RESULT+" VARCHAR, "+
				KEY_ACCURACY+" VARCHAR, "+
				KEY_ASK_DATE+" VARCHAR, "+
				KEY_REAL_DATE+" VARCHAR  "+
			");";

    private final Context mContext;

    private int totalCnt = 0;
    

   private static class IChingDBAdapter  extends SQLiteOpenHelper {

        IChingDBAdapter (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            database.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(database);
        }
    }

   public static IChingDBMangger getInstance(Context context) throws Exception {
       if (mIChingDBMangger == null) {
           synchronized (IChingDBMangger.class) {
               if (mIChingDBMangger == null) {
Log.d("init db","init....getInstance ");
					mIChingDBMangger = new IChingDBMangger(context);
					mIChingDBMangger.mIChingDBAdapter= new IChingDBAdapter (context);
               }
           }
       }
       return mIChingDBMangger;
   }   
    public IChingDBMangger(Context context) {
        mContext= context;
    }
    
    public IChingDBMangger open() throws SQLException {

        mIChingDBAdapter= new IChingDBAdapter (mContext);
        mSQLiteDatabase= mIChingDBAdapter.getWritableDatabase();
    	
    	return this;
    }
    
    public static IChingDBMangger setDBManager(Context context){
    	try{
    		mIChingDBMangger =getInstance(context); 
    		mSQLiteDatabase = mIChingDBMangger.mIChingDBAdapter.getWritableDatabase();
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	return mIChingDBMangger;
    }
    public void close() {
        mIChingDBAdapter.close();
    }
    
    public long insertIChingHist(String idx, String title, String ask, String answer, String real_result, String accuracy, String ask_date, String real_date) {
        
    	long result = -1;
    	try{
	    	
	    	ContentValues values= new ContentValues();
			values.put(KEY_IDX, idx);
	        values.put(KEY_TITLE, title);
	        values.put(KEY_ASK, ask); 
	        values.put(KEY_ANSWER, answer); 
	        values.put(KEY_REAL_RESULT, real_result); 
	        values.put(KEY_ACCURACY, accuracy); 
			values.put(KEY_ASK_DATE, ask_date); 
	        values.put(KEY_REAL_DATE, real_date); 
	
	        result = mSQLiteDatabase.insert(DATABASE_TABLE, null, values);
    	} finally{
    		close();
    	}
        return result;
    }

    public boolean updateIChingHist(String idx, String title, String ask, String answer, String real_result, String accuracy, String ask_date, String real_date) {
        
    	boolean result = false;
    	try{
    		
    		ContentValues values= new ContentValues();
			values.put(KEY_IDX, idx);
	        values.put(KEY_TITLE, title);
	        values.put(KEY_ASK, ask); 
	        values.put(KEY_ANSWER, answer); 
	        values.put(KEY_REAL_RESULT, real_result); 
	        values.put(KEY_ACCURACY, accuracy); 
			values.put(KEY_ASK_DATE, ask_date); 
	        values.put(KEY_REAL_DATE, real_date); 
	        
	        result = mSQLiteDatabase.update(DATABASE_TABLE, values, KEY_IDX + "=" + idx, null) > 0;
		} finally{
			close();
		}
	    return result;
    }

    public boolean deleteIChingHist(String idx) { 
        
    	boolean result = false;
    	try{
    		result = mSQLiteDatabase.delete(DATABASE_TABLE, KEY_IDX + "=" + idx, null) > 0;
		} finally{
			close();
		}
	    return result;
    }

    public boolean deleteAllIChingHist() { 
        
    	boolean result = false;
    	try{
	    	
    	 result = mSQLiteDatabase.delete(DATABASE_TABLE, "1=1", null) > 0;
		} finally{
			close();
		}
	    return result;
    }

    public Cursor getAllIChingHists() { 

        return mSQLiteDatabase.query(DATABASE_TABLE, 
				new String[] {KEY_IDX, KEY_TITLE, KEY_ASK, KEY_ANSWER, 
							KEY_REAL_RESULT, KEY_ACCURACY, KEY_ASK_DATE, KEY_REAL_DATE}, null, null, null, null, null);
    }

//    public ArrayList getAllIChingHistsMap() { 
//    	Cursor cursor = null;
//        ArrayList arr = new ArrayList();    	
//    	try{
//	    	cursor = mSQLiteDatabase.query(DATABASE_TABLE, 
//					new String[] {
//	    				KEY_IDX,
//	    				KEY_TITLE,
//	    				KEY_ASK,
//	    				KEY_ANSWER, 
//						KEY_REAL_RESULT,
//						KEY_ACCURACY, 
//						KEY_ASK_DATE, 
//						KEY_REAL_DATE
//					}, null, null, null, null, null);
//	        
//	        if (cursor != null) {
//	        	cursor.moveToFirst();
//	        }
//
//	    	if (! cursor.isAfterLast()) {
//	    		do {
//	    			IChingHistVO vo = new IChingHistVO();
//	    			vo.setIdx(cursor.getInt(0));
//	    			vo.setTitle(cursor.getString(1));
//	    			vo.setAsk(cursor.getString(2));
//	    			vo.setAnswer(cursor.getString(3));
//	    			vo.setRealResult(cursor.getString(4));
//	    			vo.setAccuracy(cursor.getInt(5));
//	    			vo.setAskDate(cursor.getString(6));
//	    			vo.setRealDate(cursor.getString(7));
//	    			arr.add(vo);
//	    		} while(cursor.moveToNext());
//	    	}
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}finally{
//    		cursor.close();
//    	}	    	
//        return arr;
//    }

    public Cursor getIChingHist(String idx) throws SQLException {

        Cursor cursor = mSQLiteDatabase.query(true, DATABASE_TABLE, new String[] {KEY_IDX, KEY_TITLE, KEY_ASK, KEY_ANSWER, 
				KEY_REAL_RESULT, KEY_ACCURACY, KEY_ASK_DATE, KEY_REAL_DATE}, KEY_IDX + "=" + idx, null,
                null, null, null, null);        
        if (cursor != null) {
        	cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<Map<String,String>> getIChingHistFetch(Cursor c, int position, int size) {
    	ArrayList<Map<String,String>> todoItems = new ArrayList<Map<String,String>>();
    	try{
	    	
	    	
	    	if(c != null && c.getCount() > 0){
	    		for(int i = 0 ; i < size && i < c.getCount(); i++){
		    		c.moveToPosition(position+i);

	        		Map<String,String> map = new HashMap<String,String>();
Log.d("getIChingHistFetch", "c.getInt(c.getColumnIndex(KEY_IDX))="+c.getInt(c.getColumnIndex(KEY_IDX)));
	        		
					map.put(KEY_IDX,  ""+c.getInt(c.getColumnIndex(KEY_IDX)));
					map.put(KEY_TITLE, c.getString(c.getColumnIndex(KEY_TITLE)));
					map.put(KEY_ASK, c.getString(c.getColumnIndex(KEY_ASK))); 
					map.put(KEY_ANSWER, c.getString(c.getColumnIndex(KEY_ANSWER))); 
					map.put(KEY_REAL_RESULT, c.getString(c.getColumnIndex(KEY_REAL_RESULT))); 
					map.put(KEY_ACCURACY, ""+c.getInt(c.getColumnIndex(KEY_ACCURACY))); 
					map.put(KEY_ASK_DATE, c.getString(c.getColumnIndex(KEY_ASK_DATE))); 
					map.put(KEY_REAL_DATE, c.getString(c.getColumnIndex(KEY_REAL_DATE)));
					todoItems.add(map);
	    		}
	    	} else {
	    		Log.e("조회결과","없습니다.");
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		close();
    	}
        return todoItems;
    }

    public ArrayList<Map<String,String>> getIChingHistByIdx(Cursor c, String position) {
    	ArrayList<Map<String,String>> todoItems = new ArrayList<Map<String,String>>();
    	try{
	    	if(c != null && c.getCount() > 0){
	    		for(int i = 0 ; i < c.getCount(); i++){
		    		c.moveToFirst();

	        		Map<String,String> map = new HashMap<String,String>();
					map.put(KEY_IDX,  ""+c.getInt(c.getColumnIndex(KEY_IDX)));
					map.put(KEY_TITLE, c.getString(c.getColumnIndex(KEY_TITLE)));
					map.put(KEY_ASK, c.getString(c.getColumnIndex(KEY_ASK))); 
					map.put(KEY_ANSWER, c.getString(c.getColumnIndex(KEY_ANSWER))); 
					map.put(KEY_REAL_RESULT, c.getString(c.getColumnIndex(KEY_REAL_RESULT))); 
					map.put(KEY_ACCURACY, ""+c.getInt(c.getColumnIndex(KEY_ACCURACY))); 
					map.put(KEY_ASK_DATE, c.getString(c.getColumnIndex(KEY_ASK_DATE))); 
					map.put(KEY_REAL_DATE, c.getString(c.getColumnIndex(KEY_REAL_DATE)));
					todoItems.add(map);
	    		}
	    	} else {
	    		Log.e("조회결과","없습니다.");
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		close();
    	}
        return todoItems;
    }    
    public int getTotalCnt(){
   	   return totalCnt;    	
    }
}
