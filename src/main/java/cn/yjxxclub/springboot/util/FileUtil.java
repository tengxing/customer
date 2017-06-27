package cn.yjxxclub.springboot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-26
 * Time: 上午8:41
 * Describe:文件工具类
 */
public class FileUtil {


    public static File writeByteArrayToFile(String filePath, byte[] byteArray) throws IOException {
        return writeByteArrayToFile(new File(filePath), byteArray);
    }

    public static File writeByteArrayToFile(File file, byte[] byteArray) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(byteArray);
            fileOutputStream.flush();
        }
        return file;
    }
}
