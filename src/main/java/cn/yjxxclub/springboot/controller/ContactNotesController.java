package cn.yjxxclub.springboot.controller;

import cn.yjxxclub.springboot.entity.BmType;
import cn.yjxxclub.springboot.entity.ContactNotes;
import cn.yjxxclub.springboot.mapper.ContactNotesMapper;
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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-16
 * Time: 上午9:30
 * Describe: 联系记录控制器
 */
@RestController
@RequestMapping("/contactNotes")
public class ContactNotesController {


    @Autowired
    ContactNotesMapper contactNotesMapper;

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/list")
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
        List<ContactNotes> list = contactNotesMapper.list(map);
        Integer total = contactNotesMapper.countTotal();
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        net.sf.json.JSONArray jsonArray= net.sf.json.JSONArray.fromObject(list,jsonConfig);
        System.out.println(list);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status","200");
        result.put("total",total);
        result.put("data",jsonArray);
        return result;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestParam(value = "id")Integer id){

        Integer q = contactNotesMapper.deleteById(id);
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

    @PostMapping("/modifyOrSave")
    public Object saveOrupdate(ContactNotes contactNotes,
                               @RequestParam(value = "time",required = false)String strTime) throws ParseException{

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strTime);
        contactNotes.setName("小星");
        if (date==null){
            date = new Date();
        }
        Integer q;
        if (contactNotes.getId()==null){
            contactNotes.setContactTime(date);
            System.out.println(contactNotes);
            q = contactNotesMapper.save(contactNotes);
        }else{
            q = contactNotesMapper.update(contactNotes);
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

}
