package cn.yjxxclub.springboot.util.ExcelUtil;

import cn.yjxxclub.springboot.entity.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-25
 * Time: 下午2:49
 * Describe: 阅读Excel
 */
public class ReadExcel {

    /**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<Member> readExcel(String path) throws IOException {
        if (path == null || ExcelCommon.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = ExcelUtil.getPostfix(path);
            System.out.println(postfix);
            if (!ExcelCommon.EMPTY.equals(postfix)) {
                if (ExcelCommon.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (ExcelCommon.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                System.out.println(path + ExcelCommon.NOT_EXCEL_FILE);
            }
        }
        return null;
    }

    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public List<Member> readXlsx(String path) throws IOException {
        System.out.println(ExcelCommon.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Member member = null;
        User user = null;
        BmArea area = null;
        BmIndustry industry = null;
        BmStatus status = null;
        BmType type = null;
        List<Member> list = new ArrayList<Member>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    member = new Member();
                    user = new User();
                    industry = new BmIndustry();
                    area = new BmArea();
                    status = new BmStatus();
                    type = new BmType();
                    XSSFCell name = xssfRow.getCell(0);
                    XSSFCell userId = xssfRow.getCell(1);
                    XSSFCell statusId = xssfRow.getCell(2);
                    XSSFCell phoneNumber = xssfRow.getCell(3);
                    XSSFCell typeId = xssfRow.getCell(4);
                    XSSFCell industryId = xssfRow.getCell(5);
                    XSSFCell areaId = xssfRow.getCell(6);
                    XSSFCell address = xssfRow.getCell(7);

                    user.setUsername(getValue(userId));
                    status.setName(getValue(statusId));
                    industry.setName(getValue(industryId));
                    area.setName(getValue(areaId));
                    type.setName(getValue(typeId));

                    member.setName(getValue(name));
                    member.setAddress(getValue(address));
                    member.setPhoneNumber(getValue(phoneNumber));
                    member.setUser(user);
                    member.setBmIndustry(industry);
                    member.setBmArea(area);
                    member.setBmStatus(status);
                    member.setBmType(type);
                    list.add(member);
                }
            }
        }
        return list;
    }

    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<Member> readXls(String path) throws IOException {
        System.out.println(ExcelCommon.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Member member = null;
        User user = null;
        BmArea area = null;
        BmIndustry industry = null;
        BmStatus status = null;
        BmType type = null;
        List<Member> list = new ArrayList<Member>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                System.out.println(hssfRow.getCell(1));
                if (hssfRow != null) {
                    member = new Member();
                    user = new User();
                    industry = new BmIndustry();
                    area = new BmArea();
                    status = new BmStatus();
                    type = new BmType();
                    HSSFCell name = hssfRow.getCell(0);
                    HSSFCell userId = hssfRow.getCell(1);
                    HSSFCell statusId = hssfRow.getCell(2);
                    HSSFCell phoneNumber = hssfRow.getCell(3);
                    HSSFCell typeId = hssfRow.getCell(4);
                    HSSFCell industryId = hssfRow.getCell(5);
                    HSSFCell areaId = hssfRow.getCell(6);
                    HSSFCell address = hssfRow.getCell(7);

                    user.setUsername(getValue(userId));
                    status.setName(getValue(statusId));
                    industry.setName(getValue(industryId));
                    area.setName(getValue(areaId));
                    type.setName(getValue(typeId));

                    member.setName(getValue(name));
                    member.setAddress(getValue(address));
                    member.setPhoneNumber(getValue(phoneNumber));
                    member.setUser(user);
                    member.setBmIndustry(industry);
                    member.setBmArea(area);
                    member.setBmStatus(status);
                    member.setBmType(type);
                    list.add(member);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}