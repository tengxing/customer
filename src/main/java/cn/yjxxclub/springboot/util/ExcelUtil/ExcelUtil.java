package cn.yjxxclub.springboot.util.ExcelUtil;

import cn.yjxxclub.springboot.entity.Member;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-23
 * Time: 下午3:53
 * Describe: Excel工具类
 */
public class ExcelUtil {

    /**
     * 导入Excel
     * @param path
     * @return
     * @throws IOException
     */
    public static List importExcel(String path)throws IOException{
        return new ReadExcel().readExcel(path);
    }

    /**
     *
     */
    public static void exportExcel(){
        System.out.println("还没有完善");
    }

    /**
     * get postfix of the path
     * @param path
     * @return
     */
    public static String getPostfix(String path) {
        if (path == null || ExcelCommon.EMPTY.equals(path.trim())) {
            return ExcelCommon.EMPTY;
        }
        if (path.contains(ExcelCommon.POINT)) {
            return path.substring(path.lastIndexOf(ExcelCommon.POINT) + 1, path.length());
        }
        return ExcelCommon.EMPTY;
    }
}

