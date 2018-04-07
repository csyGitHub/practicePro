package test;

/**
 * Created with IntelliJ IDEA.
 * User:juzi
 * Date:2015/11/2.
 * Time:20:31.
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiTest {
    //ʹ��POI����excel������
    public static void createWorkBook() throws IOException {
        //����excel������
        Workbook wb = new HSSFWorkbook();
        //������һ��sheet��ҳ��������Ϊ new sheet
        Sheet sheet = wb.createSheet("new sheet");
        //Row ��
        //Cell ����
        // Row �� Cell ���Ǵ�0��ʼ������

        // ����һ�У���ҳsheet��
        Row row = sheet.createRow((short) 0);
        // ��row���ϴ���һ������
        Cell cell = row.createCell(0);
        //���÷������ʾ
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue("This is a string �ٶȷ�������");
        row.createCell(3).setCellValue(true);

        //����һ���ļ� ����Ϊworkbook.xls
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        // �����洴���Ĺ�����������ļ���
        wb.write(fileOut);
        //�ر������
        fileOut.close();
    }

    //ʹ��POI����excel�������ļ�
    public static void readWorkBook() throws Exception {
        // poi��ȡexcel
        //����Ҫ������ļ���������
        InputStream inp = new FileInputStream("workbook.xls");

        //�������������������� ��������������
        Workbook wb = null;
                //WorkbookFactory.create(inp);
        //�õ���һҳ sheet
        //ҳSheet�Ǵ�0��ʼ������
        Sheet sheet = wb.getSheetAt(0);
        //����foreachѭ�� ����sheet�е�������
        for (Row row : sheet) {
            //����row�е����з���
            for (Cell cell : row) {
                //��������е����ݣ��Կո���
                System.out.print(cell.toString() + "  ");
            }
            //ÿһ�������֮����
            System.out.println();
        }
        //�ر�������
        inp.close();
    }

    public static void main(String[] args) throws Exception {
        PoiTest.createWorkBook();
        //PoiTest.readWorkBook();
    }
}