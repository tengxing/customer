package cn.yjxxclub.springboot.controller;

import cn.yjxxclub.springboot.entity.*;
import cn.yjxxclub.springboot.mapper.MemberMapper;
import cn.yjxxclub.springboot.util.DateJsonValueProcessor;
import cn.yjxxclub.springboot.util.PageBean;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-10
 * Time: 上午9:00
 * Describe: 客户控制器
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberMapper memberMapper;

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/list")
    public Object list(@RequestParam(value = "page", required = false)String page,
                       @RequestParam(value = "size", required = false)String size,
                       @RequestParam(value = "creator",required = false)String creator,
                       @RequestParam(value = "status",required = false)Integer status){
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
        if (creator!=null){
            User user = new User();
            user.setUsername("遇见小星");
            map.put("creator",user.getUsername());
        }
        if (status!=null){
            map.put("status",status);
        }
        List<Member> list = memberMapper.list(map);
        Integer total = memberMapper.countTotal();
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        net.sf.json.JSONArray jsonArray= net.sf.json.JSONArray.fromObject(list,jsonConfig);
        System.out.println(list);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status","200");
        result.put("total",total);
        result.put("data",list);
        return result;
    }

    /**
     * 通过id查找
     * @param id
     * @return
     */
    @PostMapping("/find")
    public Object find(@RequestParam(value = "id")Integer id){
        Member member = memberMapper.findById(id);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status",200);
        result.put("data",member);
        return result;
    }

    /**
     * 保存或者更新
     * @param member
     * @return
     */
    @PostMapping("/modifyOrSave")
    public Object modifyOrSave(Member member,
                               @RequestParam(value = "userId")Integer userId,
                               @RequestParam(value = "statusId")Integer statusId,
                               @RequestParam(value = "typeId")Integer typeId,
                               @RequestParam(value = "industryId",required = false)Integer industryId,
                               @RequestParam(value = "areaId",required = false)Integer areaId){

        User user = new User();
        user.setId(userId);
        BmStatus bmStatus = new BmStatus();
        bmStatus.setId(statusId);
        BmType bmType = new BmType();
        bmType.setId((typeId!=null)?typeId:1);
        BmIndustry bmIndustry = new BmIndustry();
        bmIndustry.setId((industryId!=null)?industryId:1);
        BmArea bmArea = new BmArea();
        bmArea.setId((areaId!=null)?areaId:1);
        member.setBmArea(bmArea);
        member.setBmIndustry(bmIndustry);
        member.setBmStatus(bmStatus);
        member.setBmType(bmType);
        member.setUser(user);
        Integer q;
        member.setStatus(1);//正常
        if (member.getId()==null){
            member.setCreateDate(new Date());
            q = memberMapper.save(member);
        }else {
            member.setUpdateDate(new Date());
            q = memberMapper.update(member);
        }
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status",200);
        if(q > 0){
            return result;
        }
        result.put("success",false);
        result.put("status",500);
        return result;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestParam(value = "id")Integer id){

        Integer q = memberMapper.deleteById(id);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status",200);
        if(q > 0){
            return result;
        }
        result.put("success",false);
        result.put("status",500);
        return result;
    }
}
