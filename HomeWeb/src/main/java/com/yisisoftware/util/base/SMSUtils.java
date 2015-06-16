package com.yisisoftware.util.base;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.druid.util.StringUtils;

public class SMSUtils {
	private String BASE_URL="https://api.sms.mob.com/sms/verify";
	private String APP_KEY="6f994a4ba6c4";

	
	public SMSUtils() {
		super();
		this.BASE_URL=ConfigUtil.getSmsURL();
		this.APP_KEY=ConfigUtil.getSmsAppKey();
	}


	/**
	 * 请求校验短信验证码
	 * 
	 * @param phone eg.15224658754
	 * @param code eg.1234
	 * @param zone eg.86
	 * @return 返回值 错误描述 200 短信验证成功 512 服务器拒绝访问，或者拒绝操作 513 求Appkey不存在或被禁用。 514
	 *         权限不足 515 服务器内部错误 517 缺少必要的请求参数 518 请求中用户的手机号格式不正确（包括手机的区号） 519
	 *         请求发送验证码次数超出限制 520 无效验证码。 526 余额不足
	 */
	public String requestData(String phone,String code,String zone) {
		if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(code)){
			return "";
		}
		if(zone==null){
			zone="86";
		}
		Map<String, String> ps=new HashMap<String, String>();
		ps.put("appkey", APP_KEY);
		ps.put("phone", phone);
		ps.put("code", code);
		ps.put("zone", zone);
		String params=getParams(ps);
		HttpURLConnection conn = null;
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs,
						String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs,
						String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());

			// ip host verify
			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify(String urlHostName, SSLSession session) {
					return urlHostName.equals(session.getPeerHost());
				}
			};

			// set ip host verify
			HttpsURLConnection.setDefaultHostnameVerifier(hv);

			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());

			URL url = new URL(BASE_URL);
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");// POST
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			// set params ;post params
			if (params != null) {
				conn.setDoOutput(true);
				DataOutputStream out = new DataOutputStream(
						conn.getOutputStream());
				System.out.println("params:"+params);
				out.write(params.getBytes(Charset.forName("UTF-8")));
				out.flush();
				out.close();
			}
			conn.connect();
			// get result
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				String result = parsRtn(conn.getInputStream());
				return result;
			} else {
				System.out.println(conn.getResponseCode() + " "
						+ conn.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return null;
	}

	private String getParams(Map<String, String> params) {
		StringBuffer sb=new StringBuffer();
		if(params==null||params.isEmpty()){
			return "";
		}
		for (String key : params.keySet()) {
			sb.append(key+"="+params.get(key)+"&");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	/**
	 * 获取返回数据
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private String parsRtn(InputStream is) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = null;
		boolean first = true;
		while ((line = reader.readLine()) != null) {
			if (first) {
				first = false;
			} else {
				buffer.append("\n");
			}
			buffer.append(line);
		}
		return buffer.toString();
	}


	public String getBASE_URL() {
		return BASE_URL;
	}


	public String getAPP_KEY() {
		return APP_KEY;
	}
	
}
