package dao;

import bean.Student;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class StudentDAOImpl extends BaseDAO<Student> implements StudentDAO {
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
        String sql = "SELECT STU_ACCOUNT \"account\" ,STU_PASSWORD \"pwd\" from STUDENT";
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
        List<Student> list = getAll(conn);
        for (Student s : list) {
            if (account.equals(s.getAccount())) {
                System.out.println("该用户已存在");
                return false;
            }
        }
        String sql = "insert into STUDENT (STU_ID,STU_ACCOUNT,STU_PASSWORD) values(stuId.nextval,?,?)";
        update(conn, sql, account, pwd);
        System.out.println("注册成功");
        return true;

    }

    /*
     * 查询所有学生打卡
     * */
    @Override
    public List<Student> getAll(Connection conn) {
        // 获取的结果集列名为大写，所以此处需加引号包裹以保持小写状态，避免为对象赋值错误
        String sql = "select stu.STU_ID \"id\", stu.STU_NUM \"num\",stu.STU_NAME \"name\",stu.STU_SEX \"sex\",stu.STU_ACCOUNT \"account\",stu.STU_PASSWORD \"password\", clo.CLO_TEMP \"temp\",clo.CLO_TIME \"time\",clo.CLO_PLACE \"place\" from STUDENT stu,CLOCk clo where stu.STU_NUM = clo.CLO_NUM  and ClO_IDENTITY = 'S'";
        List<Student> list = getForList(conn, sql);
        return list;
    }

    /*
     * 查询一个学生打卡
     * */
    @Override
    public Student getStudentByStuNum(Connection conn) {
        System.out.println("请输入要查询学生的学号：");
        int stuNum = sc.nextInt();
        String sql = "select * from STUDENT where STU_NUM = ?";
        Object vlaue = getVlaue(conn, sql, stuNum);
        if (vlaue != null) {
            String sql1 = "select stu.STU_ID \"id\", stu.STU_NUM \"num\",stu.STU_NAME \"name\",stu.STU_SEX \"sex\",stu.STU_ACCOUNT \"account\",stu.STU_PASSWORD \"password\", clo.CLO_TEMP \"temp\",clo.CLO_TIME \"time\",clo.CLO_PLACE \"place\" from STUDENT stu,CLOCk clo where stu.STU_NUM = clo.CLO_NUM AND STU_NUM = ? and ClO_IDENTITY = 'S'";
            Student stu = getInstance(conn, sql1, stuNum);
            if (stu != null) {
                System.out.println(stu);
            } else {
                System.out.println("该学生尚未打卡");
            }
        } else {
            System.out.println("查无此人");
        }
        return null;
    }

    /*
     * 删除学生
     * */
    @Override
    public void deleteByStuNum(Connection conn) {
        System.out.println("请输入您要删除学生的学号：");
        int stuNum = sc.nextInt();
        String sq1 = "select * from STUDENT where STU_NUM = ?";
        Object vlaue = getVlaue(conn, sq1, stuNum);
        if (vlaue != null) {
            String sql = "DELETE FROM CLOCK WHERE CLO_NUM = ? and CLO_IDENTITY = 'S'";
            String sql1 = "DELETE FROM STUDENT WHERE STU_NUM = ?";
            update(conn, sql, stuNum);
            update(conn, sql1, stuNum);
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
            System.out.println("请选择您的操作：1. 修改学生信息 2. 修改打卡记录 3. 返回上级");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("请输入学生学号");
                    int stuNum = sc.nextInt();
                    String sq1 = "select * from STUDENT where STU_NUM = ?";
                    Object vlaue = getVlaue(conn, sq1, stuNum);
                    if (vlaue != null) {
                        System.out.println("经费有限，只有修改名字的功能，请输入新名字");
                        String name = sc.next();
                        String sql = "update STUDENT set STU_NAME = ? where STU_NUM = ?";
                        update(conn, sql, name, stuNum);
                        System.out.println("修改成功");
                    } else {
                        System.out.println("查无此人");
                    }
                    break;
                case 2:
                    System.out.println("请输入学生学号");
                    int stuNum1 = sc.nextInt();
                    String sql = "select * from STUDENT where STU_NUM = ?";
                    Object vlaue1 = getVlaue(conn, sql, stuNum1);
                    if (vlaue1 != null) {
                        System.out.println("经费有限，只有修改打卡地点，请输入新地点");
                        String place = sc.next();
                        String sql1 = "update CLOCK set CLO_PLACE = ? where CLO_NUM = ? and CLO_IDENTITY = 'S'";
                        update(conn, sql1, place, stuNum1);
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
        String sql = "SELECT CLOCK.CLO_TEMP FROM STUDENT,CLOCK WHERE  STUDENT.STU_NUM=CLOCK.CLO_NUM AND  STUDENT.STU_ACCOUNT = ? ";
        Object value = getVlaue(conn, sql, account);
        if (value == null) {
            System.out.println("请输入学号：");
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
            String sql1 = "UPDATE STUDENT set STU_NUM = ?,STU_NAME =?,STU_SEX=? where STU_ACCOUNT = ?";
            update(conn, sql1, num, name, sex, account);
            String sql2 = "INSERT into CLOCK values(cloId.nextval,?,?,?,?,?)";
            update(conn, sql2, num, temp, time, place, "S");
            System.out.println("打卡成功！");
        } else {
            System.out.println("您已经打过卡了");
        }

    }
}
