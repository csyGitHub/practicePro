package test;

/**
 * Created with IntelliJ IDEA.
 * User:juzi
 * Date:2015/11/3.
 * Time:0:44.
 */
/*import com.ms.com.*;
import com.ms.activeX.*;*/
/*import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;*/

public class DispatchTest
{
    public static void main(String[] args)
    {
       /* ActiveXComponent xl = new ActiveXComponent("Excel.Application");
        Object xlo = xl.getObject();
        System.out.println("version="+xl.getProperty("Version"));
       /* try {
            System.out.println("version="+xl.getProperty("Version"));
            System.out.println("version="+Dispatch.get(xlo, "Version"));
            xl.setProperty("Visible", new Variant(true));
            Object workbooks = xl.getProperty("Workbooks").toDispatch();
            Object workbook = Dispatch.get(workbooks,"Add").toDispatch();
            Object sheet = Dispatch.get(workbook,"ActiveSheet").toDispatch();
            Object a1 = Dispatch.invoke(sheet, "Range", Dispatch.Get,
                    new Object[] {"A1"},
                    new int[1]).toDispatch();
            Object a2 = Dispatch.invoke(sheet, "Range", Dispatch.Get,
                    new Object[] {"A2"},
                    new int[1]).toDispatch();
            Dispatch.put(a1, "Value", "123.456");
            Dispatch.put(a2, "Formula", "=A1*2");
            System.out.println("a1 from excel:"+Dispatch.get(a1, "Value"));
            System.out.println("a2 from excel:"+Dispatch.get(a2, "Value"));
            Variant f = new Variant(false);
            Dispatch.call(workbook, "Close", f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            xl.invoke("Quit", new Variant[] {});
        }*/
    }
}
