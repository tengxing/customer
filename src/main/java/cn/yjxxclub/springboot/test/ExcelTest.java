package cn.yjxxclub.springboot.test;

import cn.yjxxclub.springboot.entity.Member;
import cn.yjxxclub.springboot.util.ExcelUtil.ExcelUtil;

import java.io.IOException;
import java.util.List;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-25
 * Time: 下午4:29
 * Describe: 测试类
 */
public class Test {
    private static final String excel2010 = "/home/tengxing/下载/客户导入模板.xlsx";
    public static void main(String[] args) throws IOException{
        List<Member> list = ExcelUtil.importExcel(excel2010);
        for (Member member : list){
            System.out.println(member);
        }
    }
}
