package com.bliss.APITestingFramework.utilities;

import com.bliss.APITestingFramework.Setup.BaseTest;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class DataUtil extends BaseTest {

    @DataProvider(name="TestData",parallel=true)
    public Object[][] getData(Method m) {
        //excel = new ExcelReader(config.getProperty("DataSheetPath"));
        //System.out.println(config.getProperty("DataSheetPath"));
        int rows = excel.getRowCount(prop.getProperty("DataSheetName"));
        System.out.println(prop.getProperty("DataSheetName"));
        System.out.println("total rows are "+rows);

        String testName = m.getName();
// Finding Test Case row number
        int testcaseRowNum;
        for(testcaseRowNum=1; testcaseRowNum<=rows; testcaseRowNum++){
            String testCaseName = excel.getCellData(prop.getProperty("DataSheetName"),0,testcaseRowNum);
            System.out.println(testCaseName+" ------ "+testName);
            if(testCaseName.equalsIgnoreCase(testName)){
                break;
            }
        }
        System.out.println("Testcase starts from "+testcaseRowNum);

// Finding data rows in testcase

        int dataStartRowNum = testcaseRowNum+2;
        int dataRows=0;
        while(!excel.getCellData(prop.getProperty("DataSheetName"),0,dataStartRowNum+dataRows).equals("")){
            dataRows++;
        }
        System.out.println(" test data rows number is "+dataRows);

        // Finding data columns in test case
        int ColNameStartRowNum= testcaseRowNum+1;
        int dataCols=0;
        while(!excel.getCellData(prop.getProperty("DataSheetName"),dataCols,ColNameStartRowNum).equals("")){

            dataCols++;
        }
        System.out.println("total columns in testcase are "+dataCols);

        Object[][] data = new Object[dataRows][1];

// Printing data
        int i=0;
        for(int rNum=dataStartRowNum;rNum<dataStartRowNum+dataRows;rNum++){
            Hashtable<String,String> table = new Hashtable<String,String>();
            for(int cNum=0; cNum<dataCols; cNum++){

                String testdata =excel.getCellData(prop.getProperty("DataSheetName"),cNum,rNum);
                String colName = excel.getCellData(prop.getProperty("DataSheetName"),cNum,ColNameStartRowNum);
                table.put(colName,testdata);
            }

            data[i][0] = table;
            i++;
        }
        return data;

    }
    }
