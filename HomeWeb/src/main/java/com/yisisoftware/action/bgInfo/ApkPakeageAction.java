package com.yisisoftware.action.bgInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import test.AXMLPrinter;
import android.content.res.AXmlResourceParser;
import android.util.TypedValue;

import com.alibaba.fastjson.JSON;
import com.yisisoftware.action.base.BaseAction;
import com.yisisoftware.entity.ApkPakeage;
import com.yisisoftware.service.business.bginfo.ApkPakeageServiceI;
import com.yisisoftware.util.base.HqlFilter;

/**
 * apk版本维护action
 * 
 * @author Administrator
 *
 */
@Controller
public class ApkPakeageAction extends BaseAction {

	@Autowired
	private ApkPakeageServiceI  apkPakeageService;
	
	/**
	 * 上传文件
	 */
	private File uploadfile;
	
	/**
	 * 文件名
	 */
	private String filename;
	
	
	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
	
	/**
	 * 保存apk信息
	 */
	public void saveApkInfo(){
		String submitData = getRequest().getParameter("submitData");	//获得表单数据
		String downloadFileName = getRequest().getParameter("fileName");	//获得下载文件名
		boolean result = false;
		String msg = "";
		
		
		try {
			ApkPakeage apkPakeage = JSON.parseObject(submitData, ApkPakeage.class);
			
			HqlFilter hqlFilter = new HqlFilter();
			
			hqlFilter.addFilter("QUERY_t#verCode_L_EQ", apkPakeage.getVerCode().toString());
			List<ApkPakeage>  apkPakeages = this.apkPakeageService.findByFilter(hqlFilter);
			
			if(apkPakeages.size() == 0){
				
				//获得当前时间
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		apkPakeage.setUpdateTime(sf.format(new Date()));	//设置更新时间
				apkPakeage.setUpdateTime(new Date());	//设置更新时间
				
				apkPakeage.setApkname(filename); //设置apk名称
				
				//获得项目url路径
				String basePath = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()
						+getRequest().getContextPath()+"/";
				apkPakeage.setApkUrl(basePath+"inventory/apkFileDownload.action?fileName="+downloadFileName);
				
				this.apkPakeageService.save(apkPakeage);
				result = true;
				msg = "保存成功!";
				
			}else{
				msg = "系统已存在相同版本号";
			}
		} catch (com.alibaba.fastjson.JSONException e) {
			// TODO Auto-generated catch block
			msg = "版本号只能是数字";
		}finally{
			
			Map<String, Object> map = new HashMap<>();
			map.put("result", result);
			map.put("msg", msg);
			writeJson(map);
		}
		
	}
	
	/**
	 * 分析android应用中AndroidManifest
	 */
	
	public Map<String, Object> readAndroidManifest(String path){
		Map<String, Object> backMap = new HashMap<>();
		try {
			ZipFile zip = new ZipFile(path);
			ZipEntry entry = zip.getEntry("AndroidManifest.xml");
			 
			InputStream inputStream = zip.getInputStream(entry);
			AXmlResourceParser parser=new AXmlResourceParser();
			parser.open(inputStream);

			 while (true) {
			     int type = parser.next();
			     if (type == XmlPullParser.END_DOCUMENT) {
			         break;
			     }
			     switch (type) {
			     case XmlPullParser.START_TAG:
			         for (int i = 0; i != parser.getAttributeCount(); ++i) {
//			        	 System.out.println(parser.getAttributeName(i));
//			        	 System.out.println(getAttributeValue(parser, i));
			             if (parser.getAttributeName(i).equals("versionCode")) {
			                 backMap.put("versionCode", getAttributeValue(parser, i));
			             }
			             if (parser.getAttributeName(i).equals("versionName")) {
			            	 backMap.put("versionName", getAttributeValue(parser, i));
			             }
			         }
			         break;
			     }
			 }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}finally{
			return backMap;
		}
		
	}
	
	
	private String getAttributeValue(AXmlResourceParser parser, int index) {  
        int type = parser.getAttributeValueType(index);  
        int data = parser.getAttributeValueData(index);  
        if (type == TypedValue.TYPE_STRING) {  
            return parser.getAttributeValue(index);  
        }  
        if (type == TypedValue.TYPE_ATTRIBUTE) {  
            return String.format("?%s%08X", getPackage(data), data);  
        }  
        if (type == TypedValue.TYPE_REFERENCE) {  
            return String.format("@%s%08X", getPackage(data), data);  
        }  
        if (type == TypedValue.TYPE_FLOAT) {  
            return String.valueOf(Float.intBitsToFloat(data));  
        }  
        if (type == TypedValue.TYPE_INT_HEX) {  
            return String.format("0x%08X", data);  
        }  
        if (type == TypedValue.TYPE_INT_BOOLEAN) {  
            return data != 0 ? "true" : "false";  
        }  
        if (type == TypedValue.TYPE_DIMENSION) {  
            return Float.toString(complexToFloat(data))  
                    + DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];  
        }  
        if (type == TypedValue.TYPE_FRACTION) {  
            return Float.toString(complexToFloat(data))  
                    + FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];  
        }  
        if (type >= TypedValue.TYPE_FIRST_COLOR_INT  
                && type <= TypedValue.TYPE_LAST_COLOR_INT) {  
            return String.format("#%08X", data);  
        }  
        if (type >= TypedValue.TYPE_FIRST_INT  
                && type <= TypedValue.TYPE_LAST_INT) {  
            return String.valueOf(data);  
        }  
        return String.format("<0x%X, type 0x%02X>", data, type);  
    }  
	
	private String getPackage(int id) {  
        if (id >>> 24 == 1) {  
            return "android:";  
        }  
        return "";  
    }  
	
	private float complexToFloat(int complex) {  
        return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];  
    }  
	
	private final float RADIX_MULTS[] = { 0.00390625F, 3.051758E-005F,  
	            1.192093E-007F, 4.656613E-010F };  
    private final String DIMENSION_UNITS[] = { "px", "dip", "sp", "pt", "in",  
            "mm", "", "" };  
    private final String FRACTION_UNITS[] = { "%", "%p", "", "", "", "", "", "" };
	
    
    /**
     * 获取apk应用程序名称
     */
    public String getApkName(String apkPath, String aaptPath){
    	String apkName = "";
		try {
			Runtime rt = Runtime.getRuntime();
			String order = aaptPath + "aapt.exe" + " d badging \"" + apkPath + "\"";
			Process proc = rt.exec(order);
			InputStream inputStream = proc.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				if(line.contains("application:")){
					String str1 = line.substring(line.indexOf("'")+1);
					String str2 = str1.substring(0, str1.indexOf("'"));
					apkName = str2;
					break;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return apkName;
    }
	
	/**
	 * 保存apk文件
	 */
	public void saveApkFile(){
		
		//设置上传文件目录    
        String realpath = ServletActionContext.getServletContext().getRealPath("/")+"apkFile";
        
        //重新设置文件名称
        filename = filename.substring(0, filename.lastIndexOf(".")) + 
        			System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."), filename.length());
        
        //设置目标文件
        File saveFile = new File(realpath, filename);
        
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = false;
        String msg = "";
        try {
			FileUtils.copyFile(uploadfile, saveFile);
			flag = true;
			msg = "保存成功!";
			map.put("fileName", filename);
			
			////////////////
		
			Map<String, Object> tmpMap = new HashMap<>();
			tmpMap = readAndroidManifest(realpath+"/"+filename);
			
			String apkNa = getApkName(realpath+"/"+filename, ServletActionContext.getServletContext().getRealPath("/")+"aapt/");
			map.put("apkName", apkNa);
			map.put("apkInfo", tmpMap);	
			
		} catch (IOException e) {
			e.printStackTrace();
			msg = "保存失败！";
		}finally{
			map.put("result", flag);
			map.put("msg", msg);
			writeJson(map);
		}
	}
	
	
	public String fileDownload(){
		filename = getRequest().getParameter("fileName");	//获得下载文件名
		
		try {
			filename = new String(filename.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "download_success";
	}
	
	 /*  
     * 获取输入流资源  
     */  
    public InputStream getInputStream() throws Exception {  
//    	String path = ServletActionContext.getServletContext().getRealPath("/") + "apkFile\\" + getFilename();
    	String path = "/apkFile/" + getFilename();  
        InputStream in = ServletActionContext.getServletContext().getResourceAsStream(path);
        return in;  
    }  
    
    
    /*  
     * 文件名 转换编码 防止中文乱码
     */  
    public String getDownloadFileName() {  
        String downloadFileName = filename;  
        try {  
            downloadFileName = new String(downloadFileName.getBytes(), "ISO8859-1");  
        } catch (UnsupportedEncodingException e) {  
            e.getMessage();  
            e.printStackTrace();  
        }  
        return downloadFileName;  
    }  
    
    
    /**
     * 对比文件版本信息
     * 当发现数据库中不存在或者有新版本时 提供下载信息
     */
    public void contrastApkVersion(){
    	String apkVersion = getRequest().getParameter("verCode");	//获得版本号
    	String appName = getRequest().getParameter("appName");	//获得版本号
    	
    	//首先判断版本号是否为空
    	if(StringUtils.isNotEmpty(apkVersion)&&StringUtils.isNotEmpty(appName)){
    		Map<String , Object> bakMap = new HashMap<>();
    		
//    		HqlFilter hqlFilter = new HqlFilter();
//    		hqlFilter.addFilter("QUERY_t#verCode_L_EQ", apkVersion);
//    		hqlFilter.addFilter("QUERY_t#appname_S_EQ", appName);
//    		List<ApkPakeage>  apkPakeages = this.apkPakeageService.findByFilter(hqlFilter);
    		
			HqlFilter hqlFilter2 = new HqlFilter();
//    		hqlFilter2.addFilter("QUERY_t#appname_S_EQ", appName);
			hqlFilter2.addOrder("desc");
			hqlFilter2.addSort("verCode");
			String hql = "from ApkPakeage t";
//			String sql="select * from ApkPakeage t where 1=1  and t.appname =  :parama4db03a9b59745208a538edc61d30c37  order by t.verCode desc ";
//			apkPakeageService.getByHql(sql, bakMap);
			List<ApkPakeage>  apkPakeages2 = this.apkPakeageService.find(hql+hqlFilter2.getWhereAndOrderHql());
    			
			if(apkPakeages2.size() != 0){
				ApkPakeage apk = apkPakeages2.get(0);
				writeJson(apk);
			}else{
				bakMap.put("verCode", apkVersion);
				writeJson(bakMap);
			}
    		
    	}
    }
}
