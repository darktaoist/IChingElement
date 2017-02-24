package com.naver.cafe.android;

import java.io.IOException;
import java.util.Random;

import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;

/**
 * 주역 괘상에 관련한 작업 전체를 수행하는 클래스임.
 * @author taoist
 *
 */
public class IChingElement extends IChing {
	
	/**
	 * 랜덤으로 음양값을 구한다.
	 * @return
	 * 1 : 양효, IChing.YANG
	 * 2 : 음효, IChing.YIN
	 */
	public int getRandom() {
    	int rtn = 0;
    	Random random = new Random();
    	rtn = random.nextInt(2) + 1;
    	if(rtn == 1) return IChing.YANG;
    	else return IChing.YIN;
    }
	
	/**
	 * 각 효에 대해 음양값을 셋팅한다.
	 * @param currenthyo 현재 선택된 효
	 * @param yinyang 현재 선택된 효에 할당된 음양값
	 */
	public void setHYO(int currenthyo, int yinyang) {
		switch (currenthyo) {
			case IChing.FIRST_HYO : FIRST_YIN_YANG = yinyang; break;
			case IChing.SECOND_HYO : SECOND_YIN_YANG = yinyang; break;
			case IChing.THIRD_HYO : THIRD_YIN_YANG = yinyang; break;
			case IChing.FOURTH_HYO : FOURTH_YIN_YANG = yinyang; break;
			case IChing.FIFTH_HYO : FIFTH_YIN_YANG = yinyang; break;
			case IChing.SIXTH_HYO : SIXTH_YIN_YANG = yinyang; break;
		}
	}
	
	/**
	 * 현재 효의 값을 계산한다.
	 * 효를 아래서부터 이진수로 해서 계산
	 * @return
	 */
	public int getSelectedHyoNum() {
		int rtn = 0;
		int first = FIRST_YIN_YANG * 1;
		int second = SECOND_YIN_YANG * 2;
		int third = THIRD_YIN_YANG * 4;
		int fourth = FOURTH_YIN_YANG * 8;
		int fifth = FIFTH_YIN_YANG * 16;
		int sixth = SIXTH_YIN_YANG * 32;
		return (first+second+third+fourth+fifth+sixth)+1; 
	} 
	
//	public SimpleIchingDataVO getIchingDataVO(XmlResourceParser data) {
//		SimpleIchingDataVO idvo = new SimpleIchingDataVO();
//		try{
//			inspectIchingXml(data,idvo);
//		} catch (Exception e){
//		}
//		return idvo;
//	}
	
//	public  void inspectIchingXml(XmlResourceParser xdata,SimpleIchingDataVO idvo)
//			throws XmlPullParserException, IOException {
//		int eventType = -1;
//		String currentTag = "";
//		while (eventType != XmlResourceParser.END_DOCUMENT) {
//			if (eventType == XmlResourceParser.START_DOCUMENT) {
//			} else if (eventType == XmlResourceParser.START_TAG) {
//				String strName = xdata.getName();
//				if (strName.equals("idata")) {
//					currentTag = xdata.getAttributeValue(null, "name").trim();
//				}
//			} else if (eventType == XmlResourceParser.TEXT) {
//				String currentData = xdata.getText();
//				if(currentTag.equals("title")){
//					idvo.TITLE = currentData;
//				} else if (currentTag.equals("titlecn")){
//					idvo.TITLECN = currentData;
//				} else if(currentTag.equals("origin")){
//					idvo.ORIGIN = currentData;
//				} else if (currentTag.equals("solv")){
//					idvo.SOLV = currentData;
//				} else if (currentTag.equals("desc")){
//					idvo.DESC = currentData;
//				} else if (currentTag.equals("wheather")){
//					idvo.WHEATHER = currentData;
//				} else if (currentTag.equals("wish")){
//					idvo.WISH = currentData;
//				} else if (currentTag.equals("job")){
//					idvo.JOB = currentData;
//				} else if (currentTag.equals("sell")){
//					idvo.SELL = currentData;
//				} else if (currentTag.equals("debt")){
//					idvo.DEBT = currentData;
//				} else if (currentTag.equals("invest")){
//					idvo.INVEST = currentData;
//				} else if (currentTag.equals("goodbad")){
//					idvo.GOODBAD = currentData;
//				} else if (currentTag.equals("trouble")){
//					idvo.TROUBLE = currentData;
//				} else if (currentTag.equals("health")){
//					idvo.HEALTH = currentData;
//				} else if (currentTag.equals("preg")){
//					idvo.PREG = currentData;
//				} else if (currentTag.equals("lucknum")){
//					idvo.LUCKNUM = currentData;
//				}
//			}
//			eventType = xdata.next();
//		}
//	}

	
	public int getIchingTitle(int num) {
		int rtn = -1;
		switch (num) {
			case 64  : rtn=R.string.ich64; break;
			case 32  : rtn=R.string.ich32; break;
			case 48  : rtn=R.string.ich48; break;
			case 16  : rtn=R.string.ich16; break;
			case 56  : rtn=R.string.ich56; break;			
			case 24  : rtn=R.string.ich24; break;
			case 40  : rtn=R.string.ich40; break;
			case 8   : rtn=R.string.ich8 ; break;
			case 60  : rtn=R.string.ich60; break;
			case 28  : rtn=R.string.ich28; break;
			case 44  : rtn=R.string.ich44; break;
			case 12  : rtn=R.string.ich12; break;
			case 52  : rtn=R.string.ich52; break;
			case 20  : rtn=R.string.ich20; break;
			case 36  : rtn=R.string.ich36; break;			
			case 4   : rtn=R.string.ich4 ; break;
			case 62  : rtn=R.string.ich62; break;
			case 30  : rtn=R.string.ich30; break;
			case 46  : rtn=R.string.ich46; break;
			case 14  : rtn=R.string.ich14; break;
			case 54  : rtn=R.string.ich54; break;
			case 22  : rtn=R.string.ich22; break;
			case 38  : rtn=R.string.ich38; break;
			case 6   : rtn=R.string.ich6 ; break;
			case 58  : rtn=R.string.ich58; break;			
			case 26  : rtn=R.string.ich26; break;
			case 42  : rtn=R.string.ich42; break;
			case 10  : rtn=R.string.ich10; break;
			case 50  : rtn=R.string.ich50; break;
			case 18  : rtn=R.string.ich18; break;
			case 34  : rtn=R.string.ich34; break;
			case 2   : rtn=R.string.ich2 ; break;
			case 63  : rtn=R.string.ich63; break;
			case 31  : rtn=R.string.ich31; break;
			case 47  : rtn=R.string.ich47; break;			
			case 15  : rtn=R.string.ich15; break;
			case 55  : rtn=R.string.ich55; break;
			case 23  : rtn=R.string.ich23; break;
			case 39  : rtn=R.string.ich39; break;
			case 7   : rtn=R.string.ich7 ; break;
			case 59  : rtn=R.string.ich59; break;
			case 27  : rtn=R.string.ich27; break;
			case 43  : rtn=R.string.ich43; break;
			case 11  : rtn=R.string.ich11; break;
			case 51  : rtn=R.string.ich51; break;			
			case 19  : rtn=R.string.ich19; break;
			case 35  : rtn=R.string.ich35; break;
			case 3   : rtn=R.string.ich3 ; break;
			case 61  : rtn=R.string.ich61; break;
			case 29  : rtn=R.string.ich29; break;
			case 45  : rtn=R.string.ich45; break;
			case 13  : rtn=R.string.ich13; break;
			case 53  : rtn=R.string.ich53; break;
			case 21  : rtn=R.string.ich21; break;
			case 37  : rtn=R.string.ich37; break;			
			case 5   : rtn=R.string.ich5 ; break;
			case 57  : rtn=R.string.ich57; break;
			case 25  : rtn=R.string.ich25; break;
			case 41  : rtn=R.string.ich41; break;
			case 9   : rtn=R.string.ich9 ; break;
			case 49  : rtn=R.string.ich49; break;
			case 17  : rtn=R.string.ich17; break;
			case 33  : rtn=R.string.ich33; break;
			case 1   : rtn=R.string.ich1 ; break;
		}
		return rtn;
	}
	
	public String getHtmlFile(int num) {
		String rtnStr = "file:///android_asset/iching/data";
		int rtn = 0;
		switch (num) {
		case 64  : rtn=1 		;break;		  
		case 32  : rtn=43		;break;		 
		case 48  : rtn=14		;break;		 
		case 16  : rtn=34		;break;		  
		case 56  : rtn=9 		;break;		 
		case 24  : rtn=5 		;break;		  
		case 40  : rtn=26		;break;		 
		case 8   : rtn=11		;break;		 
		case 60  : rtn=10		;break;		 
		case 28  : rtn=58		;break;		  
		case 44  : rtn=38		;break;		 
		case 12  : rtn=54		;break;		  
		case 52  : rtn=61		;break;		 
		case 20  : rtn=60		;break;		 
		case 36  : rtn=41		;break;		 
		case 4   : rtn=19		;break;		 
		case 62  : rtn=13		;break;		 
		case 30  : rtn=49		;break;		 
		case 46  : rtn=30		;break;		 
		case 14  : rtn=55		;break;		  
		case 54  : rtn=37		;break;		 
		case 22  : rtn=63		;break;		 
		case 38  : rtn=22		;break;		 
		case 6   : rtn=36		;break;		 
		case 58  : rtn=25		;break;		  
		case 26  : rtn=17		;break;		  
		case 42  : rtn=21		;break;		  
		case 10  : rtn=51		;break;		 
		case 50  : rtn=42		;break;		 
		case 18  : rtn=3 		;break;		 
		case 34  : rtn=27		;break;		 
		case 2   : rtn=24		;break;		 
		case 63  : rtn=44		;break;		 
		case 31  : rtn=28		;break;		 
		case 47  : rtn=50		;break;		 
		case 15  : rtn=32		;break;		 
		case 55  : rtn=57		;break;		 
		case 23  : rtn=48		;break;		 
		case 39  : rtn=18		;break;		 
		case 7   : rtn=46		;break;		 
		case 59  : rtn=6 		;break;		  
		case 27  : rtn=47		;break;		 
		case 43  : rtn=64		;break;		 
		case 11  : rtn=40		;break;		 
		case 51  : rtn=59		;break;		 
		case 19  : rtn=29		;break;		 
		case 35  : rtn=4 		;break;		 
		case 3   : rtn=7 		;break;		 
		case 61  : rtn=33		;break;		 
		case 29  : rtn=31		;break;		 
		case 45  : rtn=56		;break;		 
		case 13  : rtn=62		;break;		 
		case 53  : rtn=53		;break;		 
		case 21  : rtn=39		;break;		 
		case 37  : rtn=52		;break;		 
		case 5   : rtn=15		;break;		 
		case 57  : rtn=12		;break;		 
		case 25  : rtn=45		;break;		 
		case 41  : rtn=35		;break;		 
		case 9   : rtn=16		;break;		 
		case 49  : rtn=20		;break;		 
		case 17  : rtn=8 		;break;		 
		case 33  : rtn=23		;break;		 
		case 1   : rtn=2 		;break;		 

		
		
		}
		

		return rtnStr+rtn+".htm"; 
	}
	
		 
		
//	public int getLayout(int num) {
//		int rtn = 0;
//		switch (num) {
//		case 64  : rtn=R.xml.data1 		;break;		  
//		case 32  : rtn=R.xml.data43		;break;		 
//		case 48  : rtn=R.xml.data14		;break;		 
//		case 16  : rtn=R.xml.data34		;break;		  
//		case 56  : rtn=R.xml.data9 		;break;		 
//		case 24  : rtn=R.xml.data5 		;break;		  
//		case 40  : rtn=R.xml.data26		;break;		 
//		case 8   : rtn=R.xml.data11		;break;		 
//		case 60  : rtn=R.xml.data10		;break;		 
//		case 28  : rtn=R.xml.data58		;break;		  
//		case 44  : rtn=R.xml.data38		;break;		 
//		case 12  : rtn=R.xml.data54		;break;		  
//		case 52  : rtn=R.xml.data61		;break;		 
//		case 20  : rtn=R.xml.data60		;break;		 
//		case 36  : rtn=R.xml.data41		;break;		 
//		case 4   : rtn=R.xml.data19		;break;		 
//		case 62  : rtn=R.xml.data13		;break;		 
//		case 30  : rtn=R.xml.data49		;break;		 
//		case 46  : rtn=R.xml.data30		;break;		 
//		case 14  : rtn=R.xml.data55		;break;		  
//		case 54  : rtn=R.xml.data37		;break;		 
//		case 22  : rtn=R.xml.data63		;break;		 
//		case 38  : rtn=R.xml.data22		;break;		 
//		case 6   : rtn=R.xml.data36		;break;		 
//		case 58  : rtn=R.xml.data25		;break;		  
//		case 26  : rtn=R.xml.data17		;break;		  
//		case 42  : rtn=R.xml.data21		;break;		  
//		case 10  : rtn=R.xml.data51		;break;		 
//		case 50  : rtn=R.xml.data42		;break;		 
//		case 18  : rtn=R.xml.data3 		;break;		 
//		case 34  : rtn=R.xml.data27		;break;		 
//		case 2   : rtn=R.xml.data24		;break;		 
//		case 63  : rtn=R.xml.data44		;break;		 
//		case 31  : rtn=R.xml.data28		;break;		 
//		case 47  : rtn=R.xml.data50		;break;		 
//		case 15  : rtn=R.xml.data32		;break;		 
//		case 55  : rtn=R.xml.data57		;break;		 
//		case 23  : rtn=R.xml.data48		;break;		 
//		case 39  : rtn=R.xml.data18		;break;		 
//		case 7   : rtn=R.xml.data46		;break;		 
//		case 59  : rtn=R.xml.data6 		;break;		  
//		case 27  : rtn=R.xml.data47		;break;		 
//		case 43  : rtn=R.xml.data64		;break;		 
//		case 11  : rtn=R.xml.data40		;break;		 
//		case 51  : rtn=R.xml.data59		;break;		 
//		case 19  : rtn=R.xml.data29		;break;		 
//		case 35  : rtn=R.xml.data4 		;break;		 
//		case 3   : rtn=R.xml.data7 		;break;		 
//		case 61  : rtn=R.xml.data33		;break;		 
//		case 29  : rtn=R.xml.data31		;break;		 
//		case 45  : rtn=R.xml.data56		;break;		 
//		case 13  : rtn=R.xml.data62		;break;		 
//		case 53  : rtn=R.xml.data53		;break;		 
//		case 21  : rtn=R.xml.data39		;break;		 
//		case 37  : rtn=R.xml.data52		;break;		 
//		case 5   : rtn=R.xml.data15		;break;		 
//		case 57  : rtn=R.xml.data12		;break;		 
//		case 25  : rtn=R.xml.data45		;break;		 
//		case 41  : rtn=R.xml.data35		;break;		 
//		case 9   : rtn=R.xml.data16		;break;		 
//		case 49  : rtn=R.xml.data20		;break;		 
//		case 17  : rtn=R.xml.data8 		;break;		 
//		case 33  : rtn=R.xml.data23		;break;		 
//		case 1   : rtn=R.xml.data2 		;break;		 
//
//		
//		} 
//		return rtn;
//	}
}
