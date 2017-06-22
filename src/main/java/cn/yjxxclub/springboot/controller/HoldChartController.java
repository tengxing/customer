package cn.yjxxclub.springboot.controller;

import cn.yjxxclub.springboot.entity.Group;
import cn.yjxxclub.springboot.entity.Member;
import cn.yjxxclub.springboot.mapper.MemberMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-17
 * Time: 下午5:22
 * Describe: 持有客户图表控制器
 */
@RestController
@RequestMapping("/holdChart")
public class HoldChartController {

    @Autowired
    MemberMapper memberMapper;

    /**
     * 按状态分类客户
     * @return
     */
    @PostMapping("/memberByStatus")
    public Object memberByStatus(){
        List<Group> list = memberMapper.memberByStatus();
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status","200");
        result.put("data",list);
        return result;
    }

    /**
     * 按地区分类客户
     * @return
     */
    @PostMapping("/memberByArea")
    public Object memberByArea(){
        List<Group> list = memberMapper.memberByArea();
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status","200");
        result.put("data",list);
        return result;
    }

    /**
     * 按类型分类客户
     * @return
     */
    @PostMapping("/memberByType")
    public Object memberByType(){
        List<Group> list = memberMapper.memberByType();
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status","200");
        result.put("data",list);
        return result;
    }

    /**
     * 按行业分类客户
     * @return
     */
    @PostMapping("/memberByIndustry")
    public Object memberByIndustry(){
        List<Group> list = memberMapper.memberByIndustry();
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status","200");
        result.put("data",list);
        return result;
    }


}
