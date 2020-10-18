package dao;

import bean.Teacher;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class TeacherDAOImpl extends BaseDAO<Teacher> implements TeacherDAO {
    Scanner sc = new Scanner(System.in);

    /*
     * 登录方法
     * */
    @Override
    public String login(Connection conn) {
        System.out.println("请输入用户名：");
        String account = sc.next();
        System.out.println("请输入密码：");
        String pwd = sc.next();
        String sql = "SELECT TEA_ACCOUNT \"account\" ,TEA_PASSWORD \"pwd\" from TEACHER";
        Object loginState = login(conn, sql, account, pwd);
        if (loginState == null) {
            System.out.println("查无此人");
        } else {
            return (String) loginState;
        }
        return null;
    }

    /*
     * 注册方法
     * */
    @Override
    public boolean register(Connection conn) {
        System.out.println("请输入用户名：");
        String account = sc.next();
        System.out.println("请输入密码：");
        String pwd = sc.next();
        List<Teacher> list = getAll(conn);

        for (Teacher t : list) {
            if (account.equals(t.getAccount())) {
                System.out.println("该用户已存在");
                return false;
            }
        }
        String sql = "insert into TEACHER (TEA_ID,TEA_ACCOUNT,TEA_PASSWORD) values(teaId.nextval,?,?)";
        update(conn, sql, account, pwd);
        System.out.println("注册成功");
        return true;
    }

    /*
     * 查询所有教师打卡
     * */
    @Override
    public List<Teacher> getAll(Connection conn) {
        // 获取的结果集列名为大写，所以此处需加引号包裹以保持小写状态，避免为对象赋值错误
        String sql = "select tea.TEA_ID \"id\", tea.TEA_NUM \"num\",tea.TEA_NAME \"name\",tea.TEA_SEX \"sex\",tea.TEA_ACCOUNT \"account\",tea.TEA_PASSWORD \"password\", clo.CLO_TEMP \"temp\",clo.CLO_TIME \"time\",clo.CLO_PLACE \"place\" from TEACHER tea,CLOCk clo where tea.TEA_NUM = clo.CLO_NUM  and CLO_IDENTITY = 'T'";
        List<Teacher> list = getForList(conn, sql);
        return list;
    }

    /*
     * 查询一个教师打卡
     * */
    @Override
    public Teacher getTeacherByTeaNum(Connection conn) {
        System.out.println("请输入要查询的教师工号：");
        int teaNum = sc.nextInt();
        String sql = "select * from TEACHER where TEA_NUM = ?";
        Object vlaue = getVlaue(conn, sql, teaNum);
        if (vlaue != null) {
            String sql1 = "select tea.TEA_ID \"id\", tea.TEA_NUM \"num\",tea.TEA_NAME \"name\",tea.TEA_SEX \"sex\",tea.TEA_ACCOUNT \"account\",tea.TEA_PASSWORD \"password\", clo.CLO_TEMP \"temp\",clo.CLO_TIME \"time\",clo.CLO_PLACE \"place\" from TEACHER tea,CLOCk clo where tea.TEA_NUM = clo.CLO_NUM AND TEA_NUM = ? and CLO_IDENTITY = 'T'";
            Teacher tea = getInstance(conn, sql1, teaNum);
            if (tea != null) {
                System.out.println(tea);
            } else {
                System.out.println("该教师尚未打卡");
            }
        } else {
            System.out.println("查无此人");
        }
        return null;
    }

    /*
     * 删除教师
     * */
    @Override
    public void deleteByTeaNum(Connection conn) {
        System.out.println("请输入您要删除的教师工号：");
        int teaNum = sc.nextInt();
        String sq1 = "select * from TEACHER where TEA_NUM = ?";
        Object vlaue = getVlaue(conn, sq1, teaNum);
        if (vlaue != null) {
            String sql = "DELETE FROM CLOCK WHERE CLO_NUM = ? and CLO_IDENTITY='T'";
            String sql1 = "DELETE FROM TEACHER WHERE TEA_NUM = ?";
            update(conn, sql, teaNum);
            update(conn, sql1, teaNum);
            System.out.println("删除成功");
        } else {
            System.out.println("查无此人");
        }

    }


    /*
     * 修改信息
     * */
    @Override
    public void update(Connection conn) {
        boolean back = true;
        while (back) {
            System.out.println("请选择您的操作：1. 修改教师信息 2. 修改打卡记录 3. 返回上级");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("请输入教师工号：");
                    int teaNum = sc.nextInt();
                    String sq1 = "select * from TEACHER where TEA_NUM = ?";
                    Object vlaue = getVlaue(conn, sq1, teaNum);
                    if (vlaue != null) {
                        System.out.println("经费有限，只有修改名字的功能，请输入新名字");
                        String name = sc.next();
                        String sql = "update TEACHER set TEA_NAME = ? where TEA_NUM = ?";
                        update(conn, sql, name, teaNum);
                        System.out.println("修改成功");
                    } else {
                        System.out.println("查无此人");
                    }
                    break;
                case 2:
                    System.out.println("请输入教师工号：");
                    int teaNum1 = sc.nextInt();
                    String sq11 = "select * from TEACHER where TEA_NUM = ?";
                    Object vlaue1 = getVlaue(conn, sq11, teaNum1);
                    if (vlaue1 != null) {
                        System.out.println("经费有限，只有修改打卡地点，请输入新地点");
                        String place = sc.next();
                        String sql1 = "update CLOCK set CLO_PLACE = ? where CLO_NUM = ? and CLO_IDENTITY='T'";
                        update(conn, sql1, place, teaNum1);
                        System.out.println("修改成功");
                    } else {
                        System.out.println("查无此人");
                    }
                    break;
                case 3:
                    back = false;
                    break;
                default:
                    System.out.println("输入错误，请重新选择");
                    break;
            }
        }
    }

    /*
     * 打卡方法
     * */
    @Override
    public void clock(Connection conn, String account) {
        String sql = "SELECT CLOCK.CLO_TEMP FROM TEACHER,CLOCK WHERE  TEACHER.TEA_NUM=CLOCK.CLO_NUM AND  TEACHER.TEA_ACCOUNT = ? ";
        Object value = getVlaue(conn, sql, account);
        if (value == null) {
            System.out.println("请输入工号：");
            int num = sc.nextInt();
            System.out.println("请输入姓名");
            String name = sc.next();
            System.out.println("请输入性别");
            String sex = sc.next();
            System.out.println("请输入温度（经费有限，只接收整数）");
            int temp = sc.nextInt();
            System.out.println("请输入打卡时间");
            String time = sc.next();
            System.out.println("请输入打卡地点");
            String place = sc.next();
            String sql1 = "UPDATE TEACHER set TEA_NUM = ?,TEA_NAME =?,TEA_SEX=? where TEA_ACCOUNT = ?";
            update(conn, sql1, num, name, sex, account);
            String sql2 = "INSERT into CLOCK values(cloId.nextval,?,?,?,?,?)";
            update(conn, sql2, num, temp, time, place, "T");
            System.out.println("打卡成功！");
        } else {
            System.out.println("您已经打过卡了");
        }

    }
}
