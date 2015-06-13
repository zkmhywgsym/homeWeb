package com.yisisoftware.action.location;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yisisoftware.action.base.BaseAction;
import com.yisisoftware.service.base.ICrashServiceI;

@SuppressWarnings("serial")
@Controller
public class CrashAction extends BaseAction {
	@Autowired
	private ICrashServiceI crashService;
	private File crashFile;

	public void saveCrash() {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = false;
		String msg = "";
		try {
			if (!crashFile.exists()) {
				map.put("result", flag);
				map.put("msg", "文件上传失败");
				writeJson(map);
				return;
			}
			BufferedReader reader=new BufferedReader(new FileReader(crashFile));
			String line="";
			StringBuffer sb=new StringBuffer();
			while ((line=reader.readLine())!=null) {
				sb.append(line);
			}
			reader.close();
			//TODO 保存日志
			System.out.println("file content:"+sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			msg = "保存失败！";
		} finally {
			map.put("result", flag);
			map.put("msg", msg);
			writeJson(map);
		}
	}

	public File getCrashFile() {
		return crashFile;
	}

	public void setCrashFile(File crashFile) {
		this.crashFile = crashFile;
	}





	
}
