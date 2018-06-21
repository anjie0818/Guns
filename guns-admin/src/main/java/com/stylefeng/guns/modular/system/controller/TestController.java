package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Test;
import com.stylefeng.guns.modular.system.service.ITestService;

/**
 * 测试控制器
 *
 * @author fengshuonan
 * @Date 2018-05-23 10:21:37
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    private String PREFIX = "/system/test/";

    @Autowired
    private ITestService testService;

    /**
     * 跳转到测试首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "test.html";
    }

    /**
     * 跳转到添加测试
     */
    @RequestMapping("/test_add")
    public String testAdd() {
        return PREFIX + "test_add.html";
    }

    /**
     * 跳转到修改测试
     */
    @RequestMapping("/test_update/{testId}")
    public String testUpdate(@PathVariable Integer testId, Model model) {
        Test test = testService.selectById(testId);
        model.addAttribute("item",test);
        LogObjectHolder.me().set(test);
        return PREFIX + "test_edit.html";
    }

    /**
     * 获取测试列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return testService.selectList(null);
    }

    /**
     * 新增测试
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Test test) {
        testService.insert(test);
        return SUCCESS_TIP;
    }

    /**
     * 删除测试
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer testId) {
        testService.deleteById(testId);
        return SUCCESS_TIP;
    }

    /**
     * 修改测试
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Test test) {
        testService.updateById(test);
        return SUCCESS_TIP;
    }

    /**
     * 测试详情
     */
    @RequestMapping(value = "/detail/{testId}")
    @ResponseBody
    public Object detail(@PathVariable("testId") Integer testId) {
        return testService.selectById(testId);
    }
}
