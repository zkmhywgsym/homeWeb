package com.yisisoftware.databaseUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class DatabaseHolder {
	private static DatabaseHolder instance;
	private static List<Object> dbKeys=new ArrayList<Object>();
	
	private final ThreadLocal<String>  dataBase=new ThreadLocal<String>();
	private final String Default=null;

	private DatabaseHolder(){
		
	}
	public static DatabaseHolder getInstance(){
		if (instance==null) {
			instance=new DatabaseHolder();
		}
		return instance;
	}
	public String getDataBaseSource() {
		return dataBase.get();
	}
	public void setDataBaseSource(String databaseSource) {
		dataBase.set(databaseSource);
	}
	public void clearDataBaseSource() {
		dataBase.remove();
	}
	public void restore(){
		dataBase.remove();
		dataBase.set(Default);
	}
	public static Object getDbKeys(int index) {
		return dbKeys.get(index);
	}
	public static void setDbKeys(Collection<Object> keys) {
		dbKeys.addAll(keys);
	}
	
	
}
