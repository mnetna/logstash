package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(TestController.class)
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void firstTest() throws Exception {
        for (int i=0; i < 10000; i++) {
            mockMvc.perform(get("/firstTest").param("resCode", "RP1234567890"));
        }
    }

    @Test
    public void secondTest() throws Exception {
        for (int i=0; i < 10000; i++) {
            mockMvc.perform(get("/secondTest").param("resCode", "RP1234567890"));
        }
    }

}
