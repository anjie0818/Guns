package com.stylefeng.guns.core.common.annotion;

import com.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;
import com.stylefeng.guns.core.common.constant.dictmap.base.SystemDict;

import java.lang.annotation.*;

/**
 * 标记需要做业务日志的方法
 *
 * @author fengshuonan
 * @date 2017-03-31 12:46
 */
@Inherited  //允许子类继承父类的注解
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})//注解的使用范围
public @interface BussinessLog {

    /**
     * 业务的名称,例如:"修改菜单"
     */
    String value() default "";

    /**
     * 被修改的实体的唯一标识,例如:菜单实体的唯一标识为"id"；可以是多个字段用“，”分割
     */
    String key() default "id";

    /**
     * 字典(用于查找key的中文名称和字段的中文名称)
     */
    Class<? extends AbstractDictMap> dict() default SystemDict.class;
}
