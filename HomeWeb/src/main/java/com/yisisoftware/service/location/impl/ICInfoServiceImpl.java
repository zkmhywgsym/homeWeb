package com.yisisoftware.service.location.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.yisisoftware.entity.EntityICCard;
import com.yisisoftware.service.base.impl.BaseServiceImpl;
import com.yisisoftware.service.location.IICInfoServiceI;

@Service("icInfoService")
public class ICInfoServiceImpl extends BaseServiceImpl<EntityICCard>
		implements IICInfoServiceI {


	@Override
	public void saveFile(String imagePath, EntityICCard ic) {
		File dir=new File(imagePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		writeFile(dir, ic.getPic1Name(), ic.getPic1());
		writeFile(dir, ic.getPic2Name(), ic.getPic2());
		writeFile(dir, ic.getPic3Name(), ic.getPic3());
	}
	private void writeFile(File dir,String fileName,byte[] byteArray){
		try {
			FileOutputStream fos=new FileOutputStream(new File(dir, fileName));
			fos.write(byteArray);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
