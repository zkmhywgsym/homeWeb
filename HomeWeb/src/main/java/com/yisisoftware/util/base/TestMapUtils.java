package com.yisisoftware.util.base;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yisisoftware.entity.MapPoint;
//坐标转换
public class TestMapUtils {
	private static String url = "http://api.map.baidu.com/geoconv/v1/?";
	private static String ak = "nMbwPGhLNAptpWmfmWTUv8dP";

	// coords=114.21892734521,29.575429778924;114.21892734521,29.575429778924&from=1&to=5&ak=nMbwPGhLNAptpWmfmWTUv8dP";
	public static MapPoint gps2BaiDuPoint(MapPoint p) {
		try {

			if (p != null) {
				url += "coords=" + p.getLongitude() + "," + p.getLatitude();
				url += "&from=1&to=5&";
				url += "ak=" + ak;
			}
			//http://api.map.baidu.com/geoconv/v1/?coords=114.21892734521,29.575429778924from=1&to=5&ak=nMbwPGhLNAptpWmfmWTUv8dP
			//http://api.map.baidu.com/geoconv/v1/?coords=29.575429778924,114.21892734521from=1&to=5&ak=nMbwPGhLNAptpWmfmWTUv8dP
			//http://api.map.baidu.com/geoconv/v1/?coords=114.21892734521,29.575429778924;114.21892734521,29.575429778924&from=1&to=5&ak=nMbwPGhLNAptpWmfmWTUv8dP
			System.out.println("url:"+url);
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setAllowUserInteraction(false);
			con.setUseCaches(false);
			con.setRequestProperty("Accept-Charset", "GBK");
			BufferedOutputStream bufOutPut = new BufferedOutputStream(
					con.getOutputStream());

			byte[] bdat = new byte[1024];//input.getBytes("UTF-8");// 解决中文乱码问题
			bufOutPut.write(bdat, 0, bdat.length);
			bufOutPut.flush();
			BufferedInputStream inp = new BufferedInputStream(
					con.getInputStream());
			InputStreamReader in = new InputStreamReader(inp,
					Charset.forName("GBK"));
			BufferedReader bufReador = new BufferedReader(in);
			String str="";
			StringBuilder sb=new StringBuilder();
			while ((str=bufReador.readLine())!=null) {
				sb.append(str);
			}
			String json=JSON.parseObject(sb.toString()).getString("result");
			JSONObject obj=JSON.parseArray(json).getJSONObject(0);
			
			p.setLatitude(obj.getDoubleValue("y"));
			p.setLongitude(obj.getDoubleValue("x"));
			System.out.println(sb.toString());
		} catch (Exception e) {
		}

		return p;
	}
	public static void main(String[] args) {
		MapPoint point=gps2BaiDuPoint(new MapPoint(0, 114.21892734521,29.575429778924, new Date(), "15034010946", "e_01", "wf"));//(114.21892734521,29.575429778924));
		System.out.println("longitude:"+point.getLongitude()+"latitude:"+point.getLatitude());
	}
}
