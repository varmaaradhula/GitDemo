package com.bliss.APITestingFramework.Setup;

import com.bliss.APITestingFramework.utilities.ExcelReader;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public static Properties prop = new Properties();
    private FileInputStream fis;
    public ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"/src/main/Resource/mydata/testdata.xlsx");
   // public File f = new File(System.getProperty("user.dir")+"/src/main/java/resources/config.properties");

    @BeforeSuite
    public void setUP(){
            try {
                fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/Resource/mydata/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAssured.baseURI =prop.getProperty("baseURI");
        RestAssured.basePath=prop.getProperty("basePath");

    }

    @AfterSuite
    public void tearDown(){


    }


}
