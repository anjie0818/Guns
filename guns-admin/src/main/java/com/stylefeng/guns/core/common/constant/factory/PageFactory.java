package com.stylefeng.guns.core.common.constant.factory;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.common.constant.state.Order;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.ToolUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author fengshuonan
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));     //每页多少条数据
        int offset = Integer.valueOf(request.getParameter("offset"));   //每页的偏移量(本页当前有多少条)
        String sort = request.getParameter("sort");         //排序字段名称
        String order = request.getParameter("order");       //asc或desc(升序或降序)
        if (ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>((offset / limit + 1), limit);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>((offset / limit + 1), limit, filterSort(sort));
            if (Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }
    /**
     *过滤前后台异常字段
     */
    public String filterSort(String oldsort){
        String newsort="";
        List<String> lists=new ArrayList<>();
        lists.add("sexName");
        lists.add("roleName");
        lists.add("deptName");
        lists.add("statusName");
        if (lists.contains(oldsort)){
            newsort=oldsort.substring(0,oldsort.length()-4);
            if (newsort.equals("role")) newsort=newsort+"id";
            if (newsort.equals("dept")) newsort=newsort+"id";
        }
        return newsort;
    }
}
