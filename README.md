CSDN博客：[https://blog.csdn.net/weixin_43734095/article/details/106594631](https://blog.csdn.net/weixin_43734095/article/details/106594631)

> Python 版本： [【数据库实验】《小型MIS的开发》PyQt5 开发 民航票务管理系统](https://blog.csdn.net/weixin_43734095/article/details/106773105)
---


~~这次的项目使用了 JavaFx + Spring + MyBatis，单纯的使用框架练练手，其实没有必要。~~

使用框架的好处是 **解耦合** 与 **便于维护**，对于一般的小型个人项目来说（尤其是这种实验，简直就是大材小用），使用框架其实是降低了开发效率。。。

---

修改了项目，采用 JDBC 连接数据库。。。。


# 需求描述
设计并实现一个C/S结构的 "**民航票务管理系统**"。

该系统具有录入、修改、删除和查询各航班机票信息的功能。

需录入的信息如下：航班号、起点、终点、日期、起飞时刻、到达时刻、票价、折扣票数、剩余座位数、航班所属航空公司。

录入、修改、删除只能由管理员进行。

---

以下是实验要求的界面：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200607190929625.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200607190934149.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200607190940946.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)


# 数据库建表


需要两张表：
* 用户表 user
* 航班信息表 flight

命令行登录 MySQL:
```bash
mysql -u root -p
> 输入密码: 1234
```

建立一个数据库：`sxn210224`，并进入：
```sql
CREATE DATABASE sxn210224;
USE sxn210224;
```

下面开始建表：
## 用户表的 SQL
```sql
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` varchar(8) NOT NULL,
  `u_username` varchar(50) DEFAULT NULL,
  `u_password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('17210224', 'luzhenyu', '123456');
INSERT INTO `user` VALUES ('17210309', 'hanlei', '123456');
```

## 航班信息表的 SQL
```sql
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flight
-- ----------------------------
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight` (
  `f_id` char(8) NOT NULL,
  `f_src` varchar(15) DEFAULT NULL,
  `f_des` varchar(15) DEFAULT NULL,
  `f_date` date NOT NULL,
  `f_start_time` char(6) DEFAULT NULL,
  `f_end_time` char(6) DEFAULT NULL,
  `f_remain_seats` int(4) DEFAULT NULL,
  `f_fares` float(8,0) DEFAULT NULL,
  `f_discount_nums` float(8,0) DEFAULT NULL,
  `f_discount` float(8,0) DEFAULT NULL,
  `f_subordinate_company` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`f_id`,`f_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flight
-- ----------------------------
INSERT INTO `flight` VALUES ('F001', '扬州', '苏州', '2012-12-12', '15:30', '14:20', '5', '99', '5', '1', '振宇有限公司');
INSERT INTO `flight` VALUES ('F002', '扬州', '苏州', '2012-12-13', '12:20', '15:50', '20', '99', '5', '1', '振宇有限公司');
INSERT INTO `flight` VALUES ('F003', '扬州', '北京', '2012-12-13', '12:20', '17:50', '4', '99', '5', '1', '振宇有限公司');
INSERT INTO `flight` VALUES ('F004', '扬州', '泰国', '2012-12-13', '7:20', '8:50', '2', '99', '5', '1', '振宇有限公司');
INSERT INTO `flight` VALUES ('F005', '扬州', '广州', '2012-12-14', '5:20', '14:50', '40', '99', '5', '1', '振宇有限公司');
```

# 项目演示
启动项目，进入一个主界面：选择票务管理则需要登陆，选择票务查询则直接进入查询界面。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200621093728850.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)
选了票务管理后进入登录界面：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200621093858919.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)
用户名密码输入错误则会产生提示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200621093927312.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)
进入操作界面后，我们输入航班号：`F001`、日期其实随便输，但是不能为空，建议点输入框右边的小按钮随便选一个日期。。。自己手输也可以，要按照他的格式。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200621095208471.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)成功查询出数据！
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200621095555749.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)
增加和修改功能后台逻辑写好了，但是没有去与界面做交互（还要多写个页面太麻烦了）。。以后有空补。。

# GitHub 网址获取源代码
GitHub：[https://github.com/szluyu99/database_exp](https://github.com/szluyu99/database_exp)

# 比较重要的知识点
## JavaFx 中 spring 工厂如何创建
JavaFx 利用 fxml 开发时控制器中无法获取 Spring 工厂帮你创建的对象（可能是我的姿势问题）。。

我的做法是利用一个 SpringHelper 类来辅助。。
```java
public class SpringHelper {
    public static ApplicationContext ctx;
    public SpringHelper() {
        ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    }
}
```
然后在整个类启动前创建好工厂：
```java
public class Launcher {

    public static void main(String args[]){
        SpringHelper springHelper = new SpringHelper();
        Application.launch(MainStage.class, args);
    }
}
```
后面的控制类中就可以这样来使用工厂了。。。
```java
private FlightService flightService = (FlightService) SpringHelper.ctx.getBean("flightService");
```
## mysql 根据日期类型查询数据
其实可以转成字符串再操作。。。
```sql
select * from flight where 
DATE_FORMAT(f_date,'%Y-%m-%d') = '2012-12-12'
```
```sql
SELECT * FROM flight WHERE
f_date = '2012-12-12';
```

## 在控制器 Controller 中关闭当前 fxml 界面
```java
public void close(ActionEvent event) {
    ((Node)event.getSource()).getScene().getWindow().hide();
}
```

## Navicat for MySQL 显示 ER 图
右击某个表，选择 **逆向到模型...** ：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200615135710218.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200615135727911.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)
## Delphi 的 DBNavigator 组件 
Delphi 中有个 DBNavigator 组件：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200607123935288.png)
* First按钮  将指针移到数据表的第一条记录。
* Previous按钮  将指针前移一条记录o
* Next按钮  将指针后移一条记录。
* Last按钮  将指针移到数据表的最后一条记录。
* Insert按钮  在当前记录前插入一条新记录，并将数据集组件置于插人状态。
* Delete按钮  删除当前记录。
* Edit按钮  将数据集置于编辑状态。
* Post按钮  提交对当前记录的修改。
* Cancel 按钮  取消对当前记录的修改。
* Refresh按钮  清除数据浏览组件的显示缓冲区，并用与其相连的数据集组件中的记录刷新显示缓冲区。

这个有点牛逼嗷，JavaFx 没有自带，我也懒得自己写一个这种功能的组件，用几个按钮代替，实现增删改查的功能就行了。。

## JavaFx 给组件添加背景图片
我们利用 css 样式可以给 JavaFx 的组件添加背景图片。
```css
style="-fx-background-image: url('/pictures/s4.png'); -fx-background-size: 100% 100%"
```
当然也可以给组件添加背景颜色：
```css
style="-fx-background-image: url('/pictures/s4.png'); -fx-background-size: 100% 100%"
```
我们利用 Idea 在 .fxml 文件中任意一个组件的标签中打 `style="-fx"` 即可提示出样式。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200621092639108.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzczNDA5NQ==,size_16,color_FFFFFF,t_70)
## 弹框方法的封装
```java
private void alert(String title, String content, String header, Alert.AlertType type){
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}
```

## JDBC 连接数据库
JdbcUtil 工具类：
```java
package com.yusael.util;

import javax.sql.DataSource;
import java.sql.*;

// Java Data Base Connect
public class JdbcUtil { // 连接数据库

    public static DataSource ds = null;
    //    static {
//        try {
//            //1.加载配置文件
//            Properties p = new Properties();
//            FileInputStream in = new FileInputStream("/db.properties");
//            // InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
//            p.load(in);
//            //ds = BasicDataSourceFactory.createDataSource(p);
//            ds = DruidDataSourceFactory.createDataSource(p);
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static Connection getConn() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/sxn210224?useSSL=false";
            String user = "root";
            String password = "1234";
            // 2.连接数据
            // return ds.getConnection();
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            System.out.println("报错了！");
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```
数据库操作：
```java
package com.yusael.dao.impl;

import com.yusael.dao.IFlightDAO;
import com.yusael.entity.Flight;
import com.yusael.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightDAOImpl implements IFlightDAO {
    public int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            ps = conn.prepareStatement(sql);
            // 遍历参数
            for (int i = 0; i < params.length; i++) {
                // ps.setString(1, stu.getName());
                // ps.setInt(2, stu.getAge());
                ps.setObject(i + 1, params[i]);

            }
            // 4.执行语句
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, null);
        }
        return 0;
    }

    public void add(Flight flight) {
        String sql = "INSERT INTO flight(f_id, f_src, f_des, f_date, f_start_time, f_end_time, " +
                "f_remain_seats, f_fares, f_discount_nums, f_discount, f_subordinate_company) VALUES " +
                "(?,?,?,?,?,?,?,?,?,?,?)";
        executeUpdate(sql, flight.getF_id(), flight.getF_src(), flight.getF_des(), flight.getF_date(),
                flight.getF_start_time(), flight.getF_end_time(), flight.getF_remain_seats(), flight.getF_fares(),
                flight.getF_discount_nums(), flight.getF_discount_nums(), flight.getF_subordinate_company());
    }

    public void delete(String id) {
        String sql = "DELETE FROM flight WHERE f_id = ?";
        executeUpdate(sql, id);
    }

    public void update(String id, Flight flight) {
        String sql = "UPDATE flight SET f_id=?, f_src=?, f_des=?, f_date=?, f_start_time=?, f_end_time=?," +
                "f_remain_seats=?, f_fares=?, f_discount_nums=?, f_discount=?, f_subordinate_company=?" +
                "WHERE f_id=?";
        executeUpdate(sql, flight.getF_id(), flight.getF_src(), flight.getF_des(), flight.getF_date(),
                flight.getF_start_time(), flight.getF_end_time(), flight.getF_remain_seats(), flight.getF_fares(),
                flight.getF_discount_nums(), flight.getF_discount_nums(), flight.getF_subordinate_company(), id);
    }

    public List<Flight> queryAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "select * from flight";
            ps = conn.prepareStatement(sql);
            // 4.执行语句
            rs = ps.executeQuery();
            List<Flight> list = new ArrayList<Flight>();
            while (rs.next()) {
                Flight flight = new Flight(rs.getString("f_id"),rs.getString("f_src"),
                        rs.getString("f_des"),rs.getString("f_date"),rs.getString("f_start_time"),
                        rs.getString("f_end_time"), rs.getString("f_remain_seats"), rs.getString("f_fares"),
                        rs.getString("f_discount_nums"), rs.getString("f_discount"), rs.getString("f_subordinate_company"));
                list.add(flight);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }

    public Flight queryById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.加载驱动
            // 2.连接数据库
            conn = JdbcUtil.getConn();
            // 3.创建语句
            String sql = "select * from flight where f_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            // 4.执行语句
            rs = ps.executeQuery();

            if (rs.next()) {
                Flight flight = new Flight(rs.getString("f_id"),rs.getString("f_src"),
                        rs.getString("f_des"),rs.getString("f_date"),rs.getString("f_start_time"),
                        rs.getString("f_end_time"), rs.getString("f_remain_seats"), rs.getString("f_fares"),
                        rs.getString("f_discount_nums"), rs.getString("f_discount"), rs.getString("f_subordinate_company"));
                return flight;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.释放资源
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }
}
```
