package cn.yjxxclub.springboot.controller;

import cn.yjxxclub.springboot.entity.BmArea;
import cn.yjxxclub.springboot.mapper.AreaMapper;
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
 * Date: 17-6-9
 * Time: 上午10:11
 * Describe: 地区控制器
 */
@RestController
@RequestMapping("/area")
public class BmAreaController {

    @Autowired
    AreaMapper areaMapper;

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
        List<BmArea> list = areaMapper.list(map);
        Integer total = areaMapper.countTotal();
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
     * 通过id查找
     * @param id
     * @return
     */
    @PostMapping("/find")
    public Object find(@RequestParam(value = "id")Integer id){
        BmArea bmIndustry = areaMapper.findById(id);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status",200);
        result.put("data",bmIndustry);
        return result;
    }

    /**
     * 保存或者更新
     * @param bmIndustry
     * @return
     */
    @PostMapping("/modifyOrSave")
    public Object modifyOrSave(BmArea bmIndustry){
        Integer q;
        bmIndustry.setStatus(1);//正常
        if (bmIndustry.getId()==null){
            bmIndustry.setCreateDate(new Date());
            q = areaMapper.save(bmIndustry);
        }else {
            bmIndustry.setUpdateDate(new Date());
            System.out.print(bmIndustry);
            q = areaMapper.update(bmIndustry);
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

        Integer q = areaMapper.deleteById(id);
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
