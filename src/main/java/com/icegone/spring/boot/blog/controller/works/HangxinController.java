package com.icegone.spring.boot.blog.controller.works;

import com.icegone.spring.boot.blog.domain.User;
import com.icegone.spring.boot.blog.service.UserService;
import com.icegone.spring.boot.blog.vo.general.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 航信记录
 * @author Icegone
 * @date 2019/6/3
 */

@Controller
@RequestMapping(value = "works/hangxin")
public class HangxinController {

    @Autowired
    private UserService userService;

    /**
     * @description:跳转 到航信主页
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/6/3
     */
    @RequestMapping(value = "/")
    public String goIndex(){
        return "works/hangxin/hangxinIndex";
    }

    /**
     * @description:跳转 跨站点攻击链接
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/6/3
     */
    @RequestMapping(value = "/02_Amap")
    public String Amap(){
        return "02_Amap";
    }

    /**
     * @description:跳转 select2 分页查询页面
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/6/13
     */
    @RequestMapping(value = "/03_select2PageQuery")
    public String select2PageQuery(){
        return "works/hangxin/03_select2PageQuery";
    }

    /**
     * @description: select2 分页查询
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/6/13
     */
    @ResponseBody
    @RequestMapping(value = "/selectTest")
    public ResultMap selectTest(@RequestParam(value="curPage",required=false,defaultValue="0") int pageIndex,
                                @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                                @RequestParam(value="selectInput",required=false,defaultValue="") String selectInput,
                                Model model){
        /**可分页设置*/
        Pageable pageable = new PageRequest(--pageIndex,pageSize);
        Page<User> page = userService.listUsersByNameLike(selectInput, pageable);
        List<User> userList = page.getContent();
         ResultMap resultMap = new ResultMap();
        resultMap.setSuccess(1);
        resultMap.setData(page);
        resultMap.setMessage("分页查询成功！");
        return resultMap;
    }
}
