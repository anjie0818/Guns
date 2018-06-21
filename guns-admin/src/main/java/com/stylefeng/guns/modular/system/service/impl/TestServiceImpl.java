package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.modular.system.model.Test;
import com.stylefeng.guns.modular.system.dao.TestMapper;
import com.stylefeng.guns.modular.system.service.ITestService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-23
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {
    @Autowired
    TestMapper testMapper;
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    public void testBiz() {
        Test test1 = new Test();
        test1.setValue("111");
        testMapper.insert(test1);
    }
    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ2)
    public void testBiz2(){
        Test test1 = new Test();
        test1.setValue("111");
        testMapper.insert(test1);
    }
    @DataSource(name = DatasourceEnum.DATA_SOURCE_GUNS)
    public void testGuns() {
        Test test1 = new Test();
        test1.setValue("222");
        testMapper.insert(test1);

    }

}
