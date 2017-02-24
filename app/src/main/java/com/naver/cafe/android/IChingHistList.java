package com.naver.cafe.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

public class IChingHistList extends ListActivity {

	private IChingDBMangger mIChingDBManger;
	private Cursor mNotesCursor;

	private Map idxs;
	private String lastIdx = "0";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.iching_hist_main);
//		Button newBtn = (Button) findViewById(R.id.hist_new);        
//		newBtn.setOnClickListener(new View.OnClickListener() {
//		    public void onClick(View view) {
//		    	showNewForm();
//		    	
//		  }
//		}); 
		
		CmnnBtnUtil btnUtil = new CmnnBtnUtil();
		btnUtil.setIChingCmnnBtn(this,R.layout.iching_hist_main);
        updateListView();
    }
    
    private void updateListView() {
    	
/*
        ArrayList m_orders = new ArrayList();
        
        IChingHistVO p1 = new IChingHistVO("안드로이드", "011-123-4567"); // 리스트에 추가할 객체입니다.
        IChingHistVO p2 = new IChingHistVO("구글", "02-123-4567"); // 리스트에 추가할 객체입니다.
        m_orders.add(p1); // 리스트에 객체를 추가합니다.
        m_orders.add(p2); // 리스트에 객체를 추가합니다.
        
        IChingHistAdapter m_adapter = new IChingHistAdapter(this, R.layout.iching_hist_list, m_orders); // 어댑터를 생성합니다.
        setListAdapter(m_adapter);  
*/    	
    	idxs = new HashMap();
//		mIChingDBManger = new IChingDBMangger(this);
//		mIChingDBManger.open();
		
    	mIChingDBManger = IChingDBMangger.setDBManager(this);
		
        Log.e("List getIChingHistFetch"," before");
        Cursor c = mIChingDBManger.getAllIChingHists();
        startManagingCursor(c);
		ArrayList<Map<String,String>> m_orders = mIChingDBManger.getIChingHistFetch(c,0,10);
        Log.e("List getIChingHistFetch"," after");
        
		String[] from = new String[] { IChingDBMangger.KEY_TITLE,IChingDBMangger.KEY_ASK_DATE,IChingDBMangger.KEY_ACCURACY};
		int[] to = new int[] { R.id.iching_hist_list_title,R.id.iching_hist_list_ask_date ,R.id.iching_hist_list_accuracy};
	
        IChingHistAdapter m_adapter = new IChingHistAdapter(this, R.layout.iching_hist_list, m_orders, IChingDBMangger.KEYS); // 어댑터를 생성합니다.
        setListAdapter(m_adapter); 
/*        
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
				R.layout.notes_list,
				mNotesCursor, from, to);

		setListAdapter(notes);
*/		
     }
    
    private class IChingHistAdapter extends ArrayAdapter {

        private ArrayList items;
        private String[] keys;
        public IChingHistAdapter(Context context, int textViewResourceId, ArrayList items, String [] keys) {
                super(context, textViewResourceId, items);
                this.items = items;
                this.keys = keys;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
        	
        	final ViewHolder holder;

            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.iching_hist_list, null);
                
                holder = new ViewHolder();
                
                Log.e("TextView", "="+v);
                
                holder.text = (TextView) v.findViewById(R.id.iching_hist_list_title);
//                holder.icon = (ImageView) v.findViewById(R.id.icon);
                holder.updateBtn = (ImageButton) v.findViewById(R.id.update);
                holder.deleteBtn = (ImageButton) v.findViewById(R.id.delete);
                 
                v.setTag(holder);
                v.setTag(holder.updateBtn);
                v.setTag(holder.deleteBtn);

                v.setOnClickListener(new OnClickListener() {  
                    public void onClick(View v) {  
                     Log.e("text click data", "="+position);
                     showForm((String)idxs.get(""+position));
                    }  
                }); 
                
                holder.updateBtn.setOnClickListener(new OnClickListener() {  
                    public void onClick(View v) {
                     Log.e("updateBtn click data", "="+position);
                     showEditForm((String)idxs.get(""+position));
                    }
                });                
                
                holder.deleteBtn.setOnClickListener(new OnClickListener() {  

                    public void onClick(View v) {
                     Log.e("deleteBtn click data", "="+position);
                     mIChingDBManger = IChingDBMangger.setDBManager(null);
                     mIChingDBManger.deleteIChingHist((String)idxs.get(""+position));
                     updateListView();
        			
                    }
                });                
                
            }
            
/*            
            IChingHistVO p = (IChingHistVO)items.get(position);
            if (p != null) {
           	
                TextView t1 = (TextView) v.findViewById(R.id.iching_hist_list_title);
                TextView t2 = (TextView) v.findViewById(R.id.iching_hist_list_ask_date);
                if(t1 != null){
                	t1.setText(p.getTitle());                            
                }
                if(t2 != null){
                	t2.setText("요청일시 : "+ p.getAskDate());
                }
                  
            }
*/
            Map m = (HashMap)items.get(position);
            if (m != null) {
           	
                TextView t1 = (TextView) v.findViewById(R.id.iching_hist_list_title);
                TextView t2 = (TextView) v.findViewById(R.id.iching_hist_list_ask_date);
                if(t1 != null){
                	t1.setText((String)m.get(keys[1]));                            
                }
                if(t2 != null){
                	t2.setText("요청일시 : "+ (String)m.get(keys[6]));                            
                }
                RatingBar mIndicatorRatingBar = (RatingBar) v.findViewById(R.id.iching_hist_list_accuracy);
//                mIndicatorRatingBar.setNumStars(Integer.parseInt((String)m.get(keys[5])));
                mIndicatorRatingBar.setRating(Float.parseFloat((String)m.get(keys[5])));
                idxs.put(""+position,(String)m.get(keys[0]));
                lastIdx = ""+position;
            }
            
            return v;
        }
	}

    static class ViewHolder {
        TextView text;
//        ImageView icon;
        ImageButton updateBtn;
        ImageButton deleteBtn;
    }
    
	private void showNewForm() {
 		Intent i = new Intent(this, IChingHistEdit.class);
 		Bundle bundle = new Bundle();
 		bundle.putString("CheoriMode", "I");
 		bundle.putString("num", "2");

 		i.putExtras(bundle);
//		startActivity(i);
		startActivityForResult(i, 2);		
	}
	private void showEditForm(String position) {
 		Intent i = new Intent(this, IChingHistEdit.class);
 		Bundle bundle = new Bundle();
 		bundle.putString("CheoriMode", "U");
 		Log.d("idxs=",idxs.toString());
 		bundle.putString(IChingDBMangger.KEY_IDX, position);

 		i.putExtras(bundle);
//		startActivity(i);
		startActivityForResult(i, 2);		
	}
	private void showForm(String position) {
 		Intent i = new Intent(this, IChingHistView.class);
 		Bundle bundle = new Bundle();
 		bundle.putString(IChingDBMangger.KEY_IDX, position);
 		i.putExtras(bundle);
 		startActivity(i);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,Intent intent) {
		try{
			super.onActivityResult(requestCode, resultCode, intent);
			Bundle extras = intent.getExtras();
			
			Log.d("onActivityResult",requestCode+":"+resultCode);
	
			switch (resultCode) {
			case RESULT_CANCELED:
				break;
			case RESULT_OK:
				break;
			}
			updateListView();
		}catch (Exception e ) {}
	}		
}
