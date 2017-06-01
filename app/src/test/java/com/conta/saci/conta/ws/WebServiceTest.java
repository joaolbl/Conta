package com.conta.saci.conta.ws;

import org.junit.Test;

/**
 * Created by JZLA on 31/05/2017.
 */

public class WebServiceTest {

    @Test
    public void sharedPurchaseWsTest(){
        String[] params = {"1", "10.50", "comments lalala", "30/05/2017"};
        new SharedPurchaseService().execute(params);
    }
}
