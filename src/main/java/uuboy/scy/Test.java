package uuboy.scy;

import uuboy.scy.common.util.*;
import uuboy.scy.common.api.*;

import java.text.ParseException;
import java.util.Map;

/**
 * Project: uuboycommon
 * Package: uuboy.scy
 * Test.java
 * Description:
 * User: uuboyscy
 * Created Date: 1/25/22
 * Version: 0.0.1
 */

public class Test {
    public static void main(String[] args) {
        ApiHelper apiHelper = new ApiHelper();
        String filename = "test.yaml";
        Map<String, Map<String, String>> baseConfig = (Map<String, Map<String, String>>) ConfigLoader.loadConfig(filename);
        System.out.println(baseConfig.get("test1").get("a"));
        System.out.println(baseConfig.get("test2").get("cc"));
        ApiHelper.main(null);
    }
}
