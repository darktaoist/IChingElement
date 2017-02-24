package com.naver.cafe.android.chemistry;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.co.taoists.android.util.dto.GpsDTO;
import kr.co.taoists.android.util.gps.GPSManager;
import kr.co.taoists.cmnn.dto.HttpConDTO;
import kr.co.taoists.cmnn.net.HttpConAdaptor;
import kr.co.taoists.cmnn.util.JsonUtil;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.naver.cafe.android.CmnnBtnUtil;
import com.naver.cafe.android.IChingDBMangger;
import com.naver.cafe.android.R;
import com.naver.cafe.android.ShareActivity;
import com.naver.cafe.android.chemistry.ChemistryResult;

public class ChemistrySub extends ListActivity implements SensorListener {

	public String imgIdx = "";
	public String idx = "0";
	public String idx2 = "0";

	private SensorManager sensorMgr;
	private long lastUpdate = -1;
	private long lastPickup = -1;
	private float x_new, y_new, z_new;
	private float x_old, y_old, z_old;
	private static final int SHAKE_THRESHOLD = 900;
	private boolean isShacked = false;
	private int maxShackCnt = 3;
	private int isShackCnt = 0;
	String phoneNumber = null;
	HttpConDTO httpConDTO = null;
	Resources resources = null;
	HttpConAdaptor adaptor = null;
	GPSManager gps = null;
	GpsDTO gpsDTO = null;
	ImageButton selectedTi2 = null;
	String ttStr = "";
	String ttStr2 = "";
	TextView titleTextView = null;
	public String sMale = "";
	public String sFemale = "";
	public String sex = "M";

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// setContentView(R.layout.harmony_fortune_sub);
//	    setBtn(R.layout.harmony_fortune_sub);
		CmnnBtnUtil btnUtil = new CmnnBtnUtil();
		btnUtil.setIChingCmnnBtn(this,R.layout.chemistry_sub);

		initSensor();

		initHttpInfo();

		ImageButton confirmButton = (ImageButton) findViewById(R.id.harmonry_confirm);
		confirmButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				callIntent(idx,idx2);
			}
		});

		Bundle extras = getIntent().getExtras();
		titleTextView = (TextView) findViewById(R.id.chemistryFortuneResultTitle);
		if (extras != null) {
			idx = extras.getString("num");
			imgIdx = extras.getString("imgIdx");
			Log.e("idx="+idx,"imgIdx="+imgIdx+":titleTextView="+titleTextView);

			titleTextView.setText(getTTi(Integer.parseInt(idx))+"띠 궁합은 ");
			ttStr = getTTi(Integer.parseInt(idx));

			final ImageButton btn1 = (ImageButton)findViewById(R.id.selectedTi);
			btn1.setImageResource(Integer.parseInt(imgIdx));
			selectedTi2 = (ImageButton)findViewById(R.id.selectedTi2);
			//// 에물 점검용
			btn1.setOnTouchListener(new View.OnTouchListener() {
				public boolean onTouch(View v, MotionEvent event) {
					pickHyo();
					Log.w("onTouch","touch");
					return false;
				}
			});
		}
		showToast("본인의 띠를 선택하세요");
		//drawPhoneList();
	}

	private void showToast(String str){
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	}

	private void drawPhoneList(){

		ArrayList<Map<String,String>> m_orders = getTestData();
		drawPhoneList(m_orders);
	}

	private ArrayList getTestData(){
		ArrayList<Map<String,String>> m_orders = new ArrayList();

		HashMap hm = new HashMap();
		hm.put("USER_ID","010-1");
		hm.put("USER_TTI","1");
		m_orders.add(hm);
		hm = new HashMap();
		hm.put("USER_ID","010-2");
		hm.put("USER_TTI","2");
		m_orders.add(hm);
		hm = new HashMap();
		hm.put("USER_ID","010-3");
		hm.put("USER_TTI","3");
		m_orders.add(hm);
		hm = new HashMap();
		hm.put("USER_ID","010-4");
		hm.put("USER_TTI","4");
		m_orders.add(hm);
		hm = new HashMap();
		hm.put("USER_ID","010-5");
		hm.put("USER_TTI","5");
		m_orders.add(hm);
		hm = new HashMap();
		hm.put("USER_ID","010-6");
		hm.put("USER_TTI","6");
		m_orders.add(hm);

		return m_orders;
	}

	private ArrayList converJsonToArryList(JSONArray jobjs){
		Log.e("converJsonToArryList","JSONArray="+jobjs);
		ArrayList m_orders = new ArrayList();
		try{
			for (int i=0; jobjs != null && i < jobjs.length(); i++) {
				JSONObject jobj = (JSONObject)jobjs.get(i);
				HashMap hm = new HashMap();
				String userId = "";
				String userTti = "";
				try{
					userId = jobj.getString("USER_ID");
				}catch(Exception e){}
				try{
					userTti = jobj.getString("USER_TTI");
				}catch(Exception e){}

				hm.put("USER_ID",userId);
				hm.put("USER_TTI",userTti);
				m_orders.add(hm);
			}
		} catch(Exception e){e.printStackTrace();}
		return m_orders;
	}
	private void drawPhoneList(ArrayList m_orders){

		String[] keys = new String[] { "USER_ID","USER_TTI"};

		PhoneListAdapter m_adapter = new PhoneListAdapter(this, R.layout.chemistry_sub, m_orders, keys); // 어댑터를 생성합니다.
		setListAdapter(m_adapter);
	}

	static class ViewHolder {
		TextView text;
		ImageView icon;
		int imgIdx;
		int imgIdx_;
		int tti;
	}

	private class PhoneListAdapter extends ArrayAdapter {

		private ArrayList items;
		private String[] keys;
		public PhoneListAdapter(Context context, int textViewResourceId, ArrayList items, String [] keys) {
			super(context, textViewResourceId, items);
			this.items = items;
			this.keys = keys;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {

			final ViewHolder holder;

			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.chemistry_sub_list, null);

				holder = new ViewHolder();

				Log.e("TextView", "="+v);

				holder.text = (TextView) v.findViewById(R.id.phone_no);
				holder.icon = (ImageView) v.findViewById(R.id.listTti);

				v.setTag(holder);
				v.setTag(holder.icon);

				v.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Log.e("text click data", "="+position);
						//callIntent(idx, jobj.getString("USER_TTI"));
					}
				});

				Map m = (HashMap)items.get(position);
				Log.e("position", "="+position);
				Log.e("items", "="+items);
				Log.e("m", "="+m);
				if (m != null) {

					ImageView t1 = (ImageView) v.findViewById(R.id.listTti);
					TextView t2 = (TextView) v.findViewById(R.id.phone_no);

					holder.imgIdx_ = getTTiImg((String)m.get(keys[1]));
					holder.imgIdx = getTTiImgClicked((String)m.get(keys[1]));
					holder.tti = Integer.parseInt((String)m.get(keys[1]));

					if(t1 != null){
						t1.setImageResource(holder.imgIdx);
						holder.icon.setOnTouchListener(new View.OnTouchListener() {
							public boolean onTouch(View v, MotionEvent event) {
								if (event.getAction() == MotionEvent.ACTION_DOWN) {
									holder.icon.setImageResource(holder.imgIdx_);
//                					selectedTi2.setImageResource(holder.imgIdx);
//
//                				    ttStr2 = getTTi(holder.tti);
//                				    titleTextView.setText(ttStr+" 과(의) "+ttStr2+" 띠 궁합은 ");
//                				    idx2 = ""+holder.tti;
//
									setChoice(holder.tti);
									Log.e("position", "down:"+position);
								} else if (event.getAction() == MotionEvent.ACTION_UP) {
									holder.icon.setImageResource(holder.imgIdx);
									Log.e("position", "up:"+position);
									//callIntent("3",R.drawable.tiger);
								}
								return false;
							}
						});
					}
					if(t2 != null){
						t2.setText(""+ (String)m.get(keys[0]));
					}
				}
			}

			return v;
		}
	}
	private void setChoice(int tti){
		selectedTi2.setImageResource(getTTiImg(""+tti));
		ttStr2 = getTTi(tti);
		titleTextView.setText(ttStr+" 과(의) "+ttStr2+" 띠 궁합은 ");
		idx2 = ""+tti;
	}

	private void callIntent(String tti, String tti2) {
		Intent i = new Intent(ChemistrySub.this,ChemistryResult.class);

		DecimalFormat dcf = new DecimalFormat("00");
		i.putExtra("male",dcf.format(Integer.parseInt(sMale)));
		i.putExtra("female",dcf.format(Integer.parseInt(sFemale)));
		startActivityForResult(i,1);
	}

	private String getTTi(int num) {
		String rtn = "";
		switch (num) {
			case 1 : rtn 	= "쥐"; 		break;
			case 2 : rtn 	= "소"; 		break;
			case 3 : rtn 	= "호랑이"; 	break;
			case 4 : rtn 	= "토끼"; 		break;
			case 5 : rtn 	= "용"; 		break;
			case 6 : rtn 	= "뱀"; 		break;
			case 7 : rtn 	= "말"; 		break;
			case 8 : rtn 	= "양"; 		break;
			case 9 : rtn 	= "원숭이"; 	break;
			case 10 : rtn 	= "닭"; 		break;
			case 11 : rtn 	= "개"; 		break;
			case 12 : rtn 	= "돼지"; 	break;
			default:rtn		=	"";
		}
		return rtn;
	}

	private int getTTiImgClicked(String num) {
		int rtn = 0;
		int n = Integer.parseInt(num);
		switch (n) {
			case 1 : rtn 	= R.drawable.mouse; 		break;
			case 2 : rtn 	= R.drawable.cow; 		break;
			case 3 : rtn 	= R.drawable.tiger; 		break;
			case 4 : rtn 	= R.drawable.rabbit; 		break;
			case 5 : rtn 	= R.drawable.dragon; 		break;
			case 6 : rtn 	= R.drawable.snake; 		break;
			case 7 : rtn 	= R.drawable.horse; 		break;
			case 8 : rtn 	= R.drawable.lamb; 		break;
			case 9 : rtn 	= R.drawable.monkey; 		break;
			case 10 : rtn 	= R.drawable.chicken; 		break;
			case 11 : rtn 	= R.drawable.dog; 		break;
			case 12 : rtn 	= R.drawable.pig; 		break;
			default:rtn		=	0;
		}
		return rtn;
	}

	private int getTTiImg(String num) {
		int rtn = 0;
		int n = Integer.parseInt(num);
		switch (n) {
			case 1 : rtn 	= R.drawable.mouse_; 		break;
			case 2 : rtn 	= R.drawable.cow_; 		break;
			case 3 : rtn 	= R.drawable.tiger_; 		break;
			case 4 : rtn 	= R.drawable.rabbit_; 		break;
			case 5 : rtn 	= R.drawable.dragon_; 		break;
			case 6 : rtn 	= R.drawable.snake_; 		break;
			case 7 : rtn 	= R.drawable.horse_; 		break;
			case 8 : rtn 	= R.drawable.lamb_; 		break;
			case 9 : rtn 	= R.drawable.monkey_; 		break;
			case 10 : rtn 	= R.drawable.chicken_; 		break;
			case 11 : rtn 	= R.drawable.dog_; 		break;
			case 12 : rtn 	= R.drawable.pig_; 		break;
			default:rtn		=	0;
		}
		return rtn;
	}

	protected void onPause() {
		if (sensorMgr != null) {
			sensorMgr.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
			sensorMgr = null;
		}
		super.onPause();
	}

	public void initSensor(){
		// motion sensor
		sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		boolean accelSupported = sensorMgr.registerListener(this, SensorManager.SENSOR_ACCELEROMETER, SensorManager.SENSOR_DELAY_GAME);
		if (!accelSupported) {
			sensorMgr.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
		}

	}

	public void onSensorChanged(int sensor, float[] values) {
		if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
			long curTime = System.currentTimeMillis();

			// pick it up one time each 1000ms
			if((curTime-lastPickup)<100) return;

			long diffTime = curTime - lastUpdate;
			if (diffTime>50) {
				lastUpdate = curTime;

				x_new = values[SensorManager.DATA_X];
				y_new = values[SensorManager.DATA_Y];
				z_new = values[SensorManager.DATA_Z];

				float speed = Math.abs( (x_new+y_new+z_new) - (x_old+y_old+z_old) ) / diffTime * 10000;
				if (speed > SHAKE_THRESHOLD) {
					// Shake !!
					lastPickup = curTime;
					//Toast.makeText(this, "Shake !!! " + speed, Toast.LENGTH_SHORT).show();

					if(!isShacked && isShackCnt++ > maxShackCnt){
						pickHyo();
					}
				}

				x_old = x_new;
				y_old = y_new;
				z_old = z_new;
			}
		}
	}

	public synchronized void pickHyo(){
		try{
			if(isShacked)
				return ;
			isShacked = true;
			Log.w("##### pickHyo =","calllend pickHyo");

			Toast.makeText(this,"조회중입니다...",Toast.LENGTH_LONG).show();

			Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

//			JSONObject jobj = getHarmonyPreInfo(dto);
			JSONObject jobj = null;
			vibe.vibrate(100);

			Log.e("##### pickHyo =","start JSONObject:"+jobj);
			jobj = delete();
			Log.e("##### pickHyo =","delete JSONObject"+jobj);
			jobj = insert();
			Log.e("##### pickHyo =","inserted JSONObject"+jobj);
			JSONArray jobjs = retrieve();
			vibe.vibrate(300);
			jobj = delete();

			Log.e("##### pickHyo =","after retrieve: "+jobjs);
			Log.e("##### pickHyo =","after retrieve jobjs : "+(jobjs == null));


			if(jobjs == null){
				AlertDialog.Builder adb = new AlertDialog.Builder(this);
				adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});

				adb.setTitle("연결오류");
				adb.setMessage("서버 연결에 문제가 발생했습니다.");
				adb.show();
				drawPhoneList();
			} else {

				AlertDialog.Builder adb = new AlertDialog.Builder(this);
				adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});

				Log.e("after show mag","jobjs: "+jobjs);
				ArrayList arr = converJsonToArryList(jobjs);
				if(arr.size() == 1){
					String tti = (String)((HashMap)arr.get(0)).get("USER_TTI");
					if("".equals(tti)){
						adb.setTitle(idx);
						adb.setMessage("대상자가 존재하지 않습니다.");
						Log.e("before show mag","jobjs: "+jobjs);
						adb.show();
					}
					setChoice(Integer.parseInt(tti));
				}else if(arr.size() > 1){
					drawPhoneList(arr);
				}else{

					adb.setTitle(idx);
					adb.setMessage("대상자가 존재하지 않습니다");
					Log.e("before show mag","jobjs: "+jobjs);
					adb.show();
					drawPhoneList();
				}
				//callIntent(idx, jobj.getString("USER_TTI"));
			}

		} catch (Exception e){
			e.printStackTrace();
		}
	}


	public void onAccuracyChanged(int sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	public void initHttpInfo(){
		resources = getResources();
		phoneNumber = getPhoneNum();
		httpConDTO = getHttpConDTO();
		adaptor = new HttpConAdaptor();
		gps = new GPSManager(this);
		gps.testLocation();
		gpsDTO = gps.getGpsDto();
	}



	public String getPhoneNum(){
		TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		return phoneNumber = manager.getLine1Number();
	}

	public HttpConDTO getHttpConDTO(){
		HttpConDTO dto = new HttpConDTO();

		dto.setUrl(resources.getString(R.string.harmonyUrl));
		dto.setCharSet(resources.getString(R.string.harmonyCharSet));
		dto.setBufSize(Integer.parseInt(resources.getString(R.string.harmonyBufSize)));
		dto.setTimeout(Integer.parseInt(resources.getString(R.string.harmonyTimeOut)));
		dto.setMethodType(resources.getString(R.string.harmonyMethodType));
		return dto;

	}

	public JSONObject delete() {
		JSONObject jobj = null;
		Properties prop = new Properties();
		prop.setProperty("USER_ID", ""+phoneNumber);
		httpConDTO.setProp(prop);
		JSONArray jarray = transfer("deleteHarmonyInfo");
		try{
			for (int i=0; i < jarray.length(); i++) {
				jobj = (JSONObject)jarray.get(i);
			}
		} catch(Exception e){e.printStackTrace();}
		return jobj;
	}

	public JSONObject insert() {
		JSONObject jobj = null;
		Properties prop = new Properties();
		prop.setProperty("USER_ID", ""+phoneNumber);
		prop.setProperty("LATITUDE", ""+gpsDTO.getLatitude());
		prop.setProperty("LONGITUDE", ""+gpsDTO.getLongitude());
		prop.setProperty("ALTITUDE", ""+gpsDTO.getAltitude());
		prop.setProperty("USER_TTI", idx);
		prop.setProperty("SEX", sex);

		httpConDTO.setProp(prop);
		JSONArray jarray = transfer("insertHarmonyInfo");
		try{
			for (int i=0; i < jarray.length(); i++) {
				jobj = (JSONObject)jarray.get(i);
			}
		} catch(Exception e){e.printStackTrace();}
		return jobj;
	}

	public JSONArray retrieve() {
		Properties prop = new Properties();
		prop.setProperty("USER_ID", ""+phoneNumber);
		prop.setProperty("LATITUDE", ""+gpsDTO.getLatitude());
		prop.setProperty("LONGITUDE", ""+gpsDTO.getLongitude());
		prop.setProperty("ALTITUDE", ""+gpsDTO.getAltitude());
		prop.setProperty("USER_TTI", idx);
		prop.setProperty("SEX", sex);
		httpConDTO.setProp(prop);
		JSONArray jarray = null;
		for(int i = 0 ; i< Integer.parseInt(resources.getString(R.string.harmonyMaxTryCnt)); i++){
			jarray = transfer("retrieveHarmonyInfo");
			try{
				Thread.sleep(Long.parseLong(resources.getString(R.string.harmonyTryTimeOut)));
			}catch(Exception e){}
		}
		return jarray;
	}

	public JSONArray transfer(String cmd) {
		Log.e("start transfer","cmd="+cmd);
		JSONArray jobjs = null;
		try{
			Properties prop = httpConDTO.getProp();
			prop.setProperty("cmd", ""+cmd);
			Log.e("transfer","url="+httpConDTO.getUrl());
			Log.e("transfer","dto="+httpConDTO);
			Log.e("transfer","data="+HttpConAdaptor.encodeString(httpConDTO.getProp()));
			Log.e("transfer","char="+httpConDTO.getCharSet());
			Log.e("transfer","tot:"+httpConDTO.getUrl()+":info="+HttpConAdaptor.encodeString(httpConDTO.getProp()).getBytes(httpConDTO.getCharSet()));

			String result = adaptor.transfer(httpConDTO,cmd,true);

			Log.e("transfer","result="+result);
			jobjs = JsonUtil.makeJsonToBeans(result);
			Log.e("transfer","after jobjs="+jobjs);
		} catch(Exception e){ Log.e("error","transfer");e.printStackTrace();}
		Log.e("end transfer","cmd="+cmd);
		return jobjs;
	}
}
