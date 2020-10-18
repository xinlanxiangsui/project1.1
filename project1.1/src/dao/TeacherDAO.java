package dao;

import bean.Teacher;

import java.sql.Connection;
import java.util.List;

/*
 * 此接口用于规范对于TEACHER表的通用操作
 * */
public interface TeacherDAO {
    /*
     * 登陆方法：查询表中是否有该用户
     * */
    String login(Connection conn);

    /*
     * 注册方法
     * */
    boolean register(Connection conn);

    /*
     * 查询表中所有教师打卡记录并返回一个教师集合
     * */
    List<Teacher> getAll(Connection conn);

    /*
     * 查询方法，根据工号，查询得到对应的Teacher对象
     * */
    Teacher getTeacherByTeaNum(Connection conn);

    /*
     * 删除方法，根据指定的工号，删除对应教师信息
     * */
    void deleteByTeaNum(Connection conn);


    /*
     * 修改方法，根据指定工号，修改对应教师信息
     * */
    void update(Connection conn);

    /*
     * 打卡方法
     * */
    void clock(Connection conn, String account);
}
