package cn.yjxxclub.springboot.controller;

import cn.yjxxclub.springboot.entity.BmIndustry;
import cn.yjxxclub.springboot.mapper.IndustryMapper;
import cn.yjxxclub.springboot.util.PageBean;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-7
 * Time: 下午9:39
 * Describe: 业务控制层
 */
@RestController
@RequestMapping("/industry")
public class BusinessController {

    @Autowired
    IndustryMapper industryMapper;

    @GetMapping("/list")
    public Object list(@RequestParam(value = "page", required = false)String page,
                       @RequestParam(value = "size", required = false)String size){
        if (page==null||page==""){
            page = "1";
        }
        if (size==null||size==""){
            size = "10";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(size));
        Map<String,Object> map = new HashedMap();
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        List<BmIndustry> list = industryMapper.list(map);
        Integer total = industryMapper.countTotal();
        System.out.println(list==null);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status","200");
        result.put("tatal",total);
        result.put("data",list);
        return result;
    }
}
