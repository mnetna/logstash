package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);
    private final Logger errorLogger = LoggerFactory.getLogger("ERROR_LOG");
    private final Logger stashLogger = LoggerFactory.getLogger("STASH_LOG");

    @GetMapping("/fail")
    public String fail() {
        try {
            throw new Exception();
        } catch (Exception e) {
            MDC.put("errorcode", "10000");
            errorLogger.error("Test Controller Fail", e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/success")
    public String success() {
        logger.info("Test Controller : {}", "Success");
        return "success";
    }

    @GetMapping("/firstTest")
    public String firstTest(@RequestParam(value = "resCode") String resCode) throws JsonProcessingException {
        String msg = "FIRST TEST ING...";

        LinkedHashMap map = new LinkedHashMap();
        map.put("resCode", resCode);
        map.put("msg", msg);

        logger.info("ELK PARSE TARGET : {}", new ObjectMapper().writeValueAsString(map));

        return "firstTest";
    }

    @GetMapping("/secondTest")
    public String secondTest(@RequestParam(value = "resCode") String resCode) {
        String msg = "SECOND TEST ING...";

//        logger.info("ELK PARSE TARGET : {}", msg);
        stashLogger.info("ELK PARSE TARGET : {}", msg);

        return "secondTest";
    }
}