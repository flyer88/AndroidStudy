package com.holyboom.flyer.coolweather.util;

import android.text.TextUtils;

import com.holyboom.flyer.coolweather.db.CoolWeatherDB;
import com.holyboom.flyer.coolweather.model.City;
import com.holyboom.flyer.coolweather.model.County;
import com.holyboom.flyer.coolweather.model.Province;

/**
 * Created by flyer on 15/2/4.
 */
public class Utility {
    /**
     * 解析服务器返回数据
     * @param coolWeatherDB 数据库实例，方便保存数据到数据库
     * @param response 服务器返回数据
     * @return
     */
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length>0){
                for (String p : allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length>0){
                for (String c :allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return  true;
            }
        }
        return  false;
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
