package main;

import bean.Student;
import bean.Teacher;
import dao.StudentDAOImpl;
import dao.TeacherDAOImpl;
import utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        StudentDAOImpl stuDAO = new StudentDAOImpl();
        TeacherDAOImpl teaDao = new TeacherDAOImpl();
        Connection conn = JDBCUtils.getConnectionByDruid();
        boolean back = true;
        while (back) {
            Scanner sc = new Scanner(System.in);
            System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
            System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥欢迎使用疫情打卡系统♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
            System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
            System.out.println("请选择您的身份：1. 学生 2. 教师 3. 退出");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    boolean back1 = true;
                    while (back1) {
                        System.out.println("请选择操作：1. 登陆 2. 注册 3. 返回");
                        int choice1 = sc.nextInt();
                        switch (choice1) {
                            case 1:
                                String nowStu = stuDAO.login(conn);
                                if (nowStu != null) {
                                    boolean back2 = true;
                                    while (back2) {
                                        System.out.println("请选择您的操作：1. 打卡 2. 查询学生打卡 3. 查询所有学生 4. 删除学生 5. 修改信息 6. 返回");
                                        int choice2 = sc.nextInt();
                                        switch (choice2) {
                                            case 1:
                                                stuDAO.clock(conn, nowStu);
                                                break;
                                            case 2:
                                                stuDAO.getStudentByStuNum(conn);
                                                break;
                                            case 3:
                                                List<Student> list = stuDAO.getAll(conn);
                                                if (list.size() != 0) {
                                                    list.forEach(System.out::println);
                                                } else {
                                                    System.out.println("当前没有学生打卡");
                                                }
                                                break;
                                            case 4:
                                                stuDAO.deleteByStuNum(conn);
                                                break;
                                            case 5:
                                                stuDAO.update(conn);
                                                break;
                                            case 6:
                                                back2 = false;
                                                break;
                                            default:
                                                System.out.println("您的输入有误，请重新输入");
                                                break;
                                        }
                                    }
                                }
                                break;
                            case 2:
                                stuDAO.register(conn);
                                break;
                            case 3:
                                back1 = false;
                                break;
                            default:
                                System.out.println("输入有误，请重新输入");
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean teaBack1 = true;
                    while (teaBack1) {
                        System.out.println("请选择操作：1. 登陆 2. 注册 3. 返回");
                        int teaChoice1 = sc.nextInt();
                        switch (teaChoice1) {
                            case 1:
                                String nowUser = teaDao.login(conn);
                                if (nowUser != null) {
                                    boolean teaBack2 = true;
                                    while (teaBack2) {
                                        System.out.println("请选择您的操作：1. 打卡 2. 查询教师打卡 3. 查询所有教师 4. 删除教师 5. 修改信息 6. 返回");
                                        int teaChoice2 = sc.nextInt();
                                        switch (teaChoice2) {
                                            case 1:
                                                teaDao.clock(conn, nowUser);
                                                break;
                                            case 2:
                                                teaDao.getTeacherByTeaNum(conn);
                                                break;
                                            case 3:
                                                List<Teacher> list = teaDao.getAll(conn);
                                                if (list.size() != 0) {
                                                    list.forEach(System.out::println);
                                                } else {
                                                    System.out.println("当前没有教师打卡");
                                                }
                                                break;
                                            case 4:
                                                teaDao.deleteByTeaNum(conn);
                                                break;
                                            case 5:
                                                teaDao.update(conn);
                                                break;
                                            case 6:
                                                teaBack2 = false;
                                                break;
                                            default:
                                                System.out.println("您的输入有误，请重新输入");
                                                break;
                                        }
                                    }
                                }
                                break;
                            case 2:
                                teaDao.register(conn);
                                break;
                            case 3:
                                teaBack1 = false;
                                break;
                            default:
                                System.out.println("输入有误，请重新输入");
                                break;
                        }
                    }
                    break;
                case 3:
                    back = false;
                    break;
                default:
                    System.out.println("输入有误，重新输入");
                    break;
            }
        }
    }

}
