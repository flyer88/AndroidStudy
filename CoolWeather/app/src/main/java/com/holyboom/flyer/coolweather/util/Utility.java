package com.holyboom.flyer.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.holyboom.flyer.coolweather.db.CoolWeatherDB;
import com.holyboom.flyer.coolweather.model.City;
import com.holyboom.flyer.coolweather.model.County;
import com.holyboom.flyer.coolweather.model.Province;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

/**
 * Created by flyer on 15/2/4.
 */
public class Utility {

//    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
//        if (!TextUtils.isEmpty(response)){
//            String[] allProvinces = response.split(",");
//            if (allProvinces != null && allProvinces.length>0){
//                for (String p : allProvinces){
//                    String[] array = p.split("\\|");
//                    Province province = new Province();
//                    province.setProvinceCode(array[0]);
//                    province.setProvinceName(array[1]);
//                    coolWeatherDB.saveProvince(province);
//                }
//                return true;
//            }
//        }
//        return false;
//    }

    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
        if (response!=null) {
            try {
                Log.e("handleProvincesResponse", "start handle");
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setInput(new StringReader(response));
                int eventType = xmlPullParser.getEventType();
                while (eventType != xmlPullParser.END_DOCUMENT) {
                    String nodeName = xmlPullParser.getName();
                    //Log.e("handleProvincesResponse", "node name is " + nodeName);
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if ("city".equals(nodeName)) {
                                Province province = new Province();
                                province.setProvinceCode(xmlPullParser.getAttributeValue(0));
                                province.setProvinceName(xmlPullParser.getAttributeValue(1));
                                coolWeatherDB.saveProvince(province);
                                //Log.e("city", "City name is  " + xmlPullParser.getAttributeValue(0));
                                //Log.e("city", "City pyName is " + xmlPullParser.getAttributeValue(1));
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            Log.e("xmlPullParse", "End of xml");
                            break;
                        default:
                            break;
                    }
                    eventType = xmlPullParser.next();
                }
            } catch (Exception e) {
                Log.e("handleProvincesResponse", "handle exception!!");
            }
            return true;
        }else{
            return false;
        }
    }

//    public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
//        if (!TextUtils.isEmpty(response)){
//            String[] allCities = response.split(",");
//            if (allCities != null && allCities.length>0){
//                for (String c :allCities){
//                    String[] array = c.split("\\|");
//                    City city = new City();
//                    city.setCityCode(array[0]);
//                    city.setCityName(array[1]);
//                    city.setProvinceId(provinceId);
//                    coolWeatherDB.saveCity(city);
//                }
//                return  true;
//            }
//        }
//        return  false;
//    }

    public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int prvinceId){
        if (response !=null){
            try {
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setInput(new StringReader(response));
                int eventType = xmlPullParser.getEventType();
                while (eventType != xmlPullParser.END_DOCUMENT) {
                    String nodeName = xmlPullParser.getName();
                    //Log.e("handleProvincesResponse", "node name is " + nodeName);
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if ("city".equals(nodeName)) {
                                City city = new City();
                                city.setCityCode(xmlPullParser.getAttributeValue(0));
                                city.setCityName(xmlPullParser.getAttributeValue(1));
                                city.setProvinceId(prvinceId);
                                coolWeatherDB.saveCity(city);
                                //Log.e("city", "City name is  " + xmlPullParser.getAttributeValue(0));
                                //Log.e("city", "City pyName is " + xmlPullParser.getAttributeValue(1));
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            Log.e("xmlPullParse", "End of xml");
                            break;
                        default:
                            break;
                    }
                    eventType = xmlPullParser.next();
                }

            }catch (Exception e){
                Log.e("handleCitiesResponse","Exception");
            }
            return true;
        }else {
            return false;
        }

    }

    public  synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            String [] allCounties = response.split(",");
            if (allCounties != null && allCounties.length>0){
                for (String c :allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    coolWeatherDB.savecounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
