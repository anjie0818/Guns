package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.Test;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-23
 */
public interface ITestService extends IService<Test> {
    //测试第二个数据源
    void testBiz();
    //测试第三个数据源
    void testBiz2();
    //测试本身数据源
    void testGuns();

}
