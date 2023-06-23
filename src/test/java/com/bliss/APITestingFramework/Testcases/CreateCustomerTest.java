package com.bliss.APITestingFramework.Testcases;

import com.bliss.APITestingFramework.Setup.BaseTest;
import com.bliss.APITestingFramework.utilities.DataUtil;
import com.bliss.APITestngFramework.APIs.CreateCustomerAPI;
import com.bliss.TestNgFramework.listners.ExtentListeners;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class CreateCustomerTest extends BaseTest {

    @Test(dataProviderClass = DataUtil.class,dataProvider = "TestData",priority = 1)
    public void validateCreateCustomerAPI(Hashtable<String,String> data) {

       // System.out.println(name);
        Response response = CreateCustomerAPI.sendPostRequestWithValidKaey(data);
        ExtentListeners.testReport.get().info(data.toString());
        response.prettyPrint();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);
    }

   @Test(dataProviderClass = DataUtil.class,dataProvider = "TestData",priority = 2)
    public void InvalidateCreateCustomerAPI(Hashtable<String,String> data) {
        Response response = CreateCustomerAPI.sendPostRequestWithInValidKaey(data);
        response.prettyPrint();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);
    }



}

