import java.sql.*;

/**
 * @program: fpga_monitor
 * @description: 数据库操作工具类
 * @author: yanweian
 * @create: 2018-08-01 12:08
 **/
public class DBUtil {
    static Connection conn = null;
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.10.13:3306/fpga_monitor";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "Mysql111111!";

    public static Connection getConn() {
        if (conn == null) {
            // 注册 JDBC 驱动
            try {
                Class.forName(JDBC_DRIVER);
                // 打开链接
                System.out.println("连接数据库...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    //关闭连接
    public static void closeConn(){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn=null;
        }
    }

    //插入数据测试
    public static int insert(Monitor monitor) {
        if (conn == null) {
            getConn();
        }
        int rs = -1;
        PreparedStatement preparedStatement = null;
        try {
            String sql;
            sql = "INSERT INTO monitor (usb,time,core_power,ddr_power,board_power,core_tem,board_tem) VALUES (?,?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,monitor.getUsb());
            preparedStatement.setString(2,monitor.getTime()+"");
            preparedStatement.setInt(3,monitor.getCore_power());
            preparedStatement.setInt(4,monitor.getDdr_power());
            preparedStatement.setInt(5,monitor.getBoard_power());
            preparedStatement.setInt(6,monitor.getCore_tem());
            preparedStatement.setInt(7,monitor.getBoard_tem());
            //执行
            rs=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

}
