package com.stylefeng.guns.system;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.system.service.ITestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *业务测试
 */
public class BizTest extends BaseJunit{
    @Autowired
    ITestService iTestService;
    @Test
    public void test(){
       iTestService.testBiz2();
        //iTestService.testGuns();
    }
}
