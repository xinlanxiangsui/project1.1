package dao;

import bean.Student;

import java.sql.Connection;
import java.util.List;

/*
 * 此接口用于规范对于STUDENT表的通用操作
 * */
public interface StudentDAO {
    /*
     * 登陆方法：查询表中是否有该用户
     * */
    String login(Connection conn);

    /*
     * 注册方法
     * */
    boolean register(Connection conn);

    /*
     * 查询表中所有学生打卡记录并返回一个学生集合
     * */
    List<Student> getAll(Connection conn);

    /*
     * 查询方法，根据学号，查询得到对应的Student对象
     * */
    Student getStudentByStuNum(Connection conn);

    /*
     * 删除方法，根据指定的学号，删除对应学生信息
     * */
    void deleteByStuNum(Connection conn);


    /*
     * 修改方法，根据指定学号，修改对应学生信息
     * */
    void update(Connection conn);

    /*
     * 打卡方法
     * */
    void clock(Connection conn, String account);
}
