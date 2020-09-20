package com.icegone.spring.boot.blog.controller.knowledge;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前端 vue.js 框架
 * @author Icegone
 * @date 2019/5/27
 */
@Controller
@RequestMapping(value = "/knowledge/vueJS")
public class VueController {
    /**
     * @description: 跳转vue主页
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/28
     */
    @RequestMapping(value = "/")
    public String goVueJS(){
        return "knowledge/vueJS/vueIndex";
    }

    /**
     * @description: 起步
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/01_start")
    public String start(){
        return "knowledge/vueJS/01_start";
    }

    /**
     * @description: 模板语法
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/02_templateSyntax")
    public String templateSyntax(){
        return "knowledge/vueJS/02_templateSyntax";
    }

    /**
     * @description: 条件语句
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/03_conditionalStatements")
    public String conditionalStatements(){
        return "knowledge/vueJS/03_conditionalStatements";
    }

    /**
     * @description: 循环语句
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/04_cycle")
    public String cycle(){
        return "knowledge/vueJS/04_cycle";
    }

    /**
     * @description: 计算属性
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/05_calculateAttribute")
    public String calculateAttribute(){
        return "knowledge/vueJS/05_calculateAttribute";
    }

    /**
     * @description: 监听属性
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/06_listenerAttr")
    public String listenerAttr(){
        return "knowledge/vueJS/06_listenerAttr";
    }

    /**
     * @description: 样式绑定
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/07_styleBind")
    public String styleBind(){
        return "knowledge/vueJS/07_styleBind";
    }

    /**
     * @description: 事件监听
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/08_eventDealer")
    public String eventDealer(){
        return "knowledge/vueJS/08_eventDealer";
    }

    /**
     * @description: 表单
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/09_form")
    public String form(){
        return "knowledge/vueJS/09_form";
    }

    /**
     * @description: 组件
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/10_component")
    public String component(){
        return "knowledge/vueJS/10_component";
    }

    /**
     * @description: 路由
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/11_route")
    public String route(){
        return "knowledge/vueJS/11_route";
    }

    /**
     * @description: 自定义指令
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/12_selfDefideOrder")
    public String selfDefideOrder(){
        return "knowledge/vueJS/12_selfDefideOrder";
    }

    /**
     * @description: 动画
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/13_annimate")
    public String annimate(){
        return "knowledge/vueJS/13_annimate";
    }

    /**
     * @description: 混入
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/14_mixIn")
    public String mixIn(){
        return "knowledge/vueJS/14_mixIn";
    }

    /**
     * @description: ajax_axios
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/15_ajax_axios")
    public String ajax_axios(){
        return "knowledge/vueJS/15_ajax_axios";
    }

    /**
     * @description: ajax_vus_resource
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/16_ajax_vus_resource")
    public String ajax_vus_resource(){
        return "knowledge/vueJS/16_ajax_vus_resource";
    }

    /**
     * @description: 响应接口
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/17_responsiveInterface")
    public String responsiveInterface(){
        return "knowledge/vueJS/17_responsiveInterface";
    }

    /**
     * @description: 实例
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/5/30
     */
    @RequestMapping(value = "/18_instance")
    public String instance(){
        return "knowledge/vueJS/18_instance";
    }



}
