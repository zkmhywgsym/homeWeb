package com.yisisoftware.databaseUtils;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseHolder.getInstance().getDataBaseSource();
	}
	
	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		DatabaseHolder.setDbKeys(targetDataSources.keySet());
		super.setTargetDataSources(targetDataSources);
	}

}
