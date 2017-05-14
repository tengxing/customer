package cn.yjxxclub.springboot.config;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-5-11
 * Time: 下午8:06
 * Describe: 工具尅
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
