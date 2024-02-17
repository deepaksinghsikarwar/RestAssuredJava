package api.utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider(name="data")
    public Object[][] getdata() throws IOException {
        String path = System.getProperty("user.dir")+"\\userdata.xlsx";
        XLUtility xlUtility = new XLUtility(path);
        int rownum = xlUtility.getRowCount("Sheet1");
        int colnum = xlUtility.getCellCount("Sheet1", 1);

        String [][] apiData = new String [rownum][colnum];
        for (int i=1;i<=rownum;i++) {
            for (int j=0; j<colnum;j++) {
                apiData[i-1][j] = xlUtility.getCellData("Sheet1", i, j);
            }
        } return apiData;
    }

    @DataProvider(name="usernames")
    public Object[] getDataByUsername() throws IOException {
        String path = System.getProperty("user.dir")+"\\userdata.xlsx";
        XLUtility xlUtility = new XLUtility(path);
        int rownum = xlUtility.getRowCount("Sheet1");
        String [] apiData = new String [rownum];
        for (int i=1;i<=rownum;i++) {
            apiData[i-1] = xlUtility.getCellData("Sheet1", i,1);
        } return apiData;
    }
}
