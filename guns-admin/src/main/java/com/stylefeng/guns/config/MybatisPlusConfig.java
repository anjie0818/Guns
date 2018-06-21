package com.stylefeng.guns.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.datascope.DataScopeInterceptor;
import com.stylefeng.guns.core.datasource.DruidProperties;
import com.stylefeng.guns.core.mutidatasource.DynamicDataSource;
import com.stylefeng.guns.core.mutidatasource.config.MutiDataSourceProperties;
import com.stylefeng.guns.core.mutidatasource.config.MutiDataSourceProperties2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * MybatisPlus配置
 *
 * @author stylefeng
 * @Date 2017/5/20 21:58
 */
@Configuration
@EnableTransactionManagement(order = 2)//由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
//先拦截是哪个数据源，然后执行对应的事务
@MapperScan(basePackages = {"com.stylefeng.guns.modular.*.dao"})
public class MybatisPlusConfig {

    @Autowired
    DruidProperties druidProperties;

    @Autowired
    MutiDataSourceProperties mutiDataSourceProperties;

    @Autowired
    MutiDataSourceProperties2 mutiDataSourceProperties2;
    /**
     * 另一个数据源
     */
    private DruidDataSource bizDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //这儿为啥要将dataSource进行多次赋值
        druidProperties.config(dataSource);
        mutiDataSourceProperties.config(dataSource);
        return dataSource;
    }

    private DruidDataSource biz2DataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //这儿为啥要将dataSource进行多次赋值
        druidProperties.config(dataSource);
        mutiDataSourceProperties2.config(dataSource);
        return dataSource;
    }

    /**
     * guns的数据源
     */
    private DruidDataSource dataSourceGuns() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return dataSourceGuns();
    }

    /**
     * 多数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "muti-datasource-open", havingValue = "true")
    public DynamicDataSource mutiDataSource() {

        DruidDataSource dataSourceGuns = dataSourceGuns();
        DruidDataSource bizDataSource = bizDataSource();
        DruidDataSource biz2DataSource = biz2DataSource();


        try {
            dataSourceGuns.init();
            bizDataSource.init();
            biz2DataSource.init();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        //决定当前使用哪个数据源：DynamicDataSouce
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap.put(DatasourceEnum.DATA_SOURCE_GUNS, dataSourceGuns);
        hashMap.put(DatasourceEnum.DATA_SOURCE_BIZ, bizDataSource);
        hashMap.put(DatasourceEnum.DATA_SOURCE_BIZ2,biz2DataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceGuns);
        return dynamicDataSource;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

    /**
     * 乐观锁mybatis插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
