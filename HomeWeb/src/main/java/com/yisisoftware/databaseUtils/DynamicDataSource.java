package com.yisisoftware.databaseUtils;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		System.out.println("curDB:"+DatabaseHolder.getInstance().getDataBaseSource());
		return DatabaseHolder.getInstance().getDataBaseSource();
	}
	
	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
//		super.setTargetDataSources(targetDataSources);
		for (Object key : targetDataSources.keySet()) {
			System.out.println("key:"+key.toString());
		}
		DatabaseHolder.setDbKeys(targetDataSources.keySet());
		super.setTargetDataSources(targetDataSources);
	}

}
