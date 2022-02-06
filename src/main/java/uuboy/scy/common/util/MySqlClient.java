package uuboy.scy.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;

/**
 * Project: uuboycommon
 * Package: uuboy.scy.common.util
 * MySqlClient.java
 * Description:
 * User: uuboyscy
 * Created Date: 2/2/22
 * Version: 0.0.1
 */

public class MySqlClient {
    private String dbName;
    private String user;
    private String password;
    private String uri;
    public static Connection mysqlConnect;

    public MySqlClient(String host, String port, String dbName, String user, String password) {
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.uri = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?characterEncoding=utf8&useSSL=false";
//        this.uri = "jdbc:mysql://" + host + ":" + port + "/" + dbName +
//                "?useSSL=false" +
//                "&useUnicode=true" +
//                "&characterEncoding=UTF-8" +
//                "&zeroDateTimeBehavior=convertToNull" +
//                "&serverTimezone=GMT";
    }

    public void connectDB() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");
        mysqlConnect = DriverManager.getConnection(this.uri, this.user, this.password);
    }

    public void closeDB() throws SQLException {
        if (mysqlConnect != null) {
            mysqlConnect.close();
        }

    }

    public void updateDB(String sql) throws SQLException {
            Statement mysqlStatement = mysqlConnect.createStatement();
            mysqlStatement.executeUpdate(sql);
    }

    public ResultSet queryDB(String sql) throws SQLException {
        ResultSet rs = null;
        Statement mysqlStatement = mysqlConnect.createStatement();
        rs = mysqlStatement.executeQuery(sql);
        return rs;
    }

    public List<String> getColumns(String dbName, String tableName) {
        return null;
    }

    public List<String> getColumns(String tableName) {
        String sql = "DESCRIBE " + this.dbName + "." + tableName;
        return null;
    }

    public static void main(String[] args) {
        String host = "127.0.0.1";
        String port = "33060";
        port = "3306";
        String dbName = "testdb";
        dbName = "TESTDB";
        String user = "root";
        String password = "root";
        String sql = "SELECT * FROM testdb.membersInfo";
        sql = "SELECT * FROM TESTDB.Staff;";

        MySqlClient mySqlClient = new MySqlClient(
                host,
                port,
                dbName,
                user,
                password
        );

        try {
            mySqlClient.connectDB();
            ResultSet rs = mySqlClient.queryDB(sql);
            System.out.println(rs.next());
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
