package com.test.coolweather.util;

import android.text.TextUtils;

import com.test.coolweather.db.CoolWeatherDB;
import com.test.coolweather.model.City;
import com.test.coolweather.model.County;
import com.test.coolweather.model.Province;


/**
 * 解析服务器返回来的字符串数据
 * @author sungang
 *
 */
public class Utility {
	// 解析并处理省级数据,将数据存入数据库
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response){
//		System.out.println("#############");
//		System.out.println("#############");
//		System.out.println("#############");
		
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0){
				for(String p : allProvinces){
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
	
	// 解析并处理市级数据,将数据存入数据库
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allCities = response.split(",");
			if(allCities != null && allCities.length > 0){
				for(String p : allCities){
					String[] array = p.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		
		return false;
	}
	
	// 解析并处理县级数据,将数据存入数据库
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allCounties = response.split(",");
			if(allCounties != null && allCounties.length > 0){
				for(String p : allCounties){
					String[] array = p.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					
					coolWeatherDB.saveCounty(county);
				}
			}
			return true;
		}
		
		return false;
	}
}
