package com.bliss.APITestingFramework.Testcases;

import com.bliss.APITestingFramework.Setup.BaseTest;
import com.bliss.APITestingFramework.utilities.DataUtil;
import com.bliss.APITestingFramework.utilities.TestUtil;
import com.bliss.APITestngFramework.APIs.CreateCustomerAPI;
import com.bliss.APITestngFramework.APIs.DeleteCustomerAPI;
import com.bliss.TestNgFramework.listners.ExtentListeners;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.Hashtable;

public class DeleteCustomerTest extends BaseTest {

    @Test(dataProviderClass = DataUtil.class,dataProvider = "TestData")
    public void validateDeleteCustomerAPI(Hashtable<String,String> data) {
       // System.out.println(data.get("customerid"));
        Response response = DeleteCustomerAPI.sendDeleteRequestWithValidID(data);
        ExtentListeners.testReport.get().info(data.toString());
        response.prettyPrint();
        System.out.println(response.statusCode());
        //String actual_id = response.jsonPath().get("id").toString();
       // System.out.println(" getting actual id from response is "+actual_id);
        Assert.assertEquals(response.statusCode(), 200);
       // Assert.assertEquals(actual_id,data.get("customerId"),"ID not match");

        JSONObject jsonobject = new JSONObject(response.asString());
        //System.out.println(jsonobject.has("id"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(),"id"));

        String actual_id = TestUtil.getJsonKeyValue(response.asString(),"id");

        Assert.assertEquals(actual_id,data.get("customerId"),"ID not matching");

        System.out.println(" object key value is "+TestUtil.getJsonKeyValue(response.asString(),"object"));
        System.out.println(" deleted key value is "+TestUtil.getJsonKeyValue(response.asString(),"deleted"));

    }


}
