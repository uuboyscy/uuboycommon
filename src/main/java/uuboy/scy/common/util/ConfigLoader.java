package uuboy.scy.common.util;

/**
 * Project: uuboycommon
 * Package: uuboy.scy.common.util
 * ConfigLoader.java
 * Description:
 * User: uuboyscy
 * Created Date: 1/25/22
 * Version: 0.0.1
 */

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class ConfigLoader {

    public ConfigLoader() {
    }

    /**
     *
     * @param config
     * @return
     */
    public static Object loadConfig(String config) {
        InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(config);
        Yaml yaml = new Yaml();
        return yaml.load(input);
    }

    public static void main(String[] args) {
        String filename = "test.yaml";
        Map<String, Map<String, String>> baseConfig = (Map<String, Map<String, String>>) ConfigLoader.loadConfig(filename);
        System.out.println(baseConfig.get("test1").get("a"));
        System.out.println(baseConfig.get("test2").get("cc"));
    }
}