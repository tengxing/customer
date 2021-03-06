package cn.yjxxclub.springboot.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-8
 * Time: 下午9:47
 * Describe: 时间处理
 */
public class DateJsonValueProcessor implements JsonValueProcessor{

    private String format;

    public DateJsonValueProcessor(String format) {
        this.format = format;
    }

    public DateJsonValueProcessor(){
        this.format="yyyy-MM-dd HH:mm:ss";
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        // TODO Auto-generated method stub

        return null;
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if(value == null)
        {
            return "";
        }
        if(value instanceof java.sql.Timestamp)
        {
            String str = new SimpleDateFormat(format).format((java.sql.Timestamp)value);
            return str;
        }
        if (value instanceof java.util.Date)
        {
            String str = new SimpleDateFormat(format).format((java.util.Date) value);
            return str;
        }

        return value.toString();
    }
}
