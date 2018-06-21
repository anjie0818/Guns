package com.stylefeng.guns.core.mutidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 *
 * @author fengshuonan
 * @date 2017年3月5日 上午9:11:49
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	//觉得当前使用哪个数据源
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType();
	}

}
