/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:16 PM yiliang.gyl Exp $
 */
public class AccountControllerTests extends ControllerBaseTest{


    @Test
    public void testLogin() throws  Exception{
        System.out.println("test");
        MvcResult mvcResult = mockMvc.perform(post("/login")).andExpect(status().isOk()).andReturn();
        if(mvcResult != null){
            System.out.println(mvcResult.getResponse().getContentAsString());
        }

    }

}
