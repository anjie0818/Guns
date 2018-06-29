package com.stylefeng.guns.core.common.constant.dictmap;

import com.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 用户的字典
 *
 * @author fengshuonan
 * @date 2017-05-06 15:01
 */
public class OrderDict extends AbstractDictMap {

    @Override
    public void init() {
        put("id","订单主键");
        put("goodsName","商品名称");
        put("place","下单地点");
        put("createTime","下单时间");
        put("userName","下单用户名称");
        put("userPhone","下单用户电话");
    }

    @Override
    protected void initBeWrapped() {

    }


}
