package cn.yjxxclub.springboot.controller;

import cn.yjxxclub.springboot.entity.Member;
import cn.yjxxclub.springboot.util.ExcelUtil.ExcelUtil;
import cn.yjxxclub.springboot.util.FileUtil;
import org.apache.commons.io.FileUtils;
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
            List<Member> list = ExcelUtil.importExcel(dir+"/"+filename);
            System.out.println(list.size());
        }
        return null;
    }

    @RequestMapping("/testExcel")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception{
        String filename="客户导入模板.xlsx";
        String basePath = request.getSession().getServletContext().getRealPath("excel");
        File file=new File(basePath+"/"+filename);
        HttpHeaders headers = new HttpHeaders();
        String fileName=new String("客户导入模板.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
