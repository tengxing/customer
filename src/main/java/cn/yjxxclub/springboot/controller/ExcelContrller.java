package cn.yjxxclub.springboot.controller;

import cn.yjxxclub.springboot.entity.*;
import cn.yjxxclub.springboot.mapper.*;
import cn.yjxxclub.springboot.util.ExcelUtil.ExcelUtil;
import cn.yjxxclub.springboot.util.FileUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-26
 * Time: 下午2:39
 * Describe:
 */
@Controller
@RequestMapping("/member")
public class ExcelContrller {

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    StatusMapper statusMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    IndustryMapper industryMapper;
    @Autowired
    AreaMapper areaMapper;

    /**
     * 批量导入客户
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadExcel")
    @ResponseBody
    public Object uploadExcel(@RequestParam(value = "file", required = false)MultipartFile file,
                              HttpServletRequest request) throws IOException {
        //List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        if (!file.isEmpty()){
            ServletContext sc = request.getSession().getServletContext();
            String dir = sc.getRealPath("excel");

            String filename = file.getOriginalFilename();
            String finalName = UUID.randomUUID()+ filename.substring(filename.lastIndexOf("."));
            File dest =new File(dir,filename);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            FileUtil.writeByteArrayToFile(dest,file.getBytes());
            List<Member> list=null;
            try {
                saveToDatabase(dir+"/"+filename);//保存数据到数据库
            } catch (Exception e){
                e.printStackTrace();
                JSONObject result = new JSONObject();
                result.put("success",false);
                result.put("status",500);
                return result;
            }
        }
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("status",200);
        return result;
    }

    private void saveToDatabase(String path) throws IOException{
        List<Member> list = ExcelUtil.importExcel(path);
        BmStatus bmStatus=null;
        BmType bmType=null;
        BmIndustry bmIndustry=null;
        BmArea bmArea=null;
        Integer statusId;
        Integer areaId;
        Integer industryId;
        Integer typeId;
        Integer i=0;
        for (Member member : list){
            System.out.println(member);
            Integer q=0;
            bmStatus = statusMapper.findByUsername(member.getBmStatus().getName());
            if (bmStatus==null){
                q = statusMapper.save(member.getBmStatus());
                if(q>0) {
                    bmStatus = statusMapper.findByUsername(member.getBmStatus().getName());
                }
                q=0;
            }
            System.out.println(1);
            bmArea = areaMapper.findByUsername(member.getBmArea().getName());
            if (bmArea==null){
                q = areaMapper.save(member.getBmArea());
                if(q>0) {
                    bmArea = areaMapper.findByUsername(member.getBmArea().getName());
                }
                q=0;
            }

            System.out.println(2);
            bmIndustry = industryMapper.findByUsername(member.getBmIndustry().getName());
            if (bmIndustry==null){
                q = industryMapper.save(member.getBmIndustry());
                if(q>0) {
                    bmIndustry = industryMapper.findByUsername(member.getBmIndustry().getName());
                }
                q=0;
            }
            System.out.println(3);

            bmType =typeMapper.findByUsername(member.getBmType().getName());
            if (bmType==null){
                q = typeMapper.save(member.getBmType());
                if(q>0) {
                    bmType = typeMapper.findByUsername(member.getBmType().getName());
                }
            }
            System.out.println(4);
            member.setBmStatus(bmStatus);
            member.setBmArea(bmArea);
            member.setBmIndustry(bmIndustry);
            member.setBmType(bmType);
            System.out.println(member);
            q = memberMapper.save(member);
            if (q>0){
                i++;
            }
        }
    }

    /**
     * 客户模板下载
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/testExcel")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception{
        String filename="客户导入模板.xlsx";
        String basePath = request.getSession().getServletContext().getRealPath("excel");
        File file=new File(basePath+"/"+filename);
        HttpHeaders headers = new HttpHeaders();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("load time :" + time);
        String fileName=new String("客户导入模板.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
