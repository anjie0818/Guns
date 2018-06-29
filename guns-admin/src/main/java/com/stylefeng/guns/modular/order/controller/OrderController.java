package com.stylefeng.guns.modular.order.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.Const;
import com.stylefeng.guns.core.common.constant.dictmap.OrderDict;
import com.stylefeng.guns.core.common.constant.dictmap.UserDict;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.order.service.IOrderService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-05-22 10:44:37
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private String PREFIX = "/order/order/";

    @Autowired
    private IOrderService orderService;

    /**
     * 跳转到订单管理首页
     */
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), true));

    }
    @RequestMapping("")
    public String index() {
        return PREFIX + "order.html";
    }

    /**
     * 跳转到添加订单管理
     */
    @RequestMapping("/order_add")
    public String orderAdd() {
        return PREFIX + "order_add.html";
    }

    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/order_update/{orderId}")
    public String orderUpdate(@PathVariable Integer orderId, Model model) {
        Order order = orderService.selectById(orderId);
        model.addAttribute("item",order);
        LogObjectHolder.me().set(order);
        return PREFIX + "order_edit.html";
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return orderService.selectList(null);
    }

    /**
     * 新增订单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "添加订单", key = "goodsName", dict = OrderDict.class)
    public Object add(Order order) {
        orderService.insert(order);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer orderId) {
        orderService.deleteById(orderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改订单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改订单", key = "goodsName", dict = OrderDict.class)
    public Object update(Order order) {
        orderService.updateById(order);
        return SUCCESS_TIP;
    }

    /**
     * 订单管理详情
     */
    @RequestMapping(value = "/detail/{orderId}")
    @ResponseBody
    public Object detail(@PathVariable("orderId") Integer orderId) {
        return orderService.selectById(orderId);
    }
}
