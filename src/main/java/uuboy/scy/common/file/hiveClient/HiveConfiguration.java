package uuboy.scy.common.file.hiveClient;

/**
 * Project: uuboycommon
 * Package: uuboy.scy.common.file.hiveClient
 * HiveConfiguration.java
 * Description:
 * User: uuboyscy
 * Created Date: 2/1/22
 * Version: 0.0.1
 */

public class HiveConfiguration {
    public static String host = "jdbc:hive2://192.168.1.168:10000";
    public static String db = "default";
    public static String uri = host + "/" + db;
    public static String user = "hive";
    public static String passwd = "";
}
