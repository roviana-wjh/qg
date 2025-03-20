package org.example;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class menu {
    static user currentuser = null;

    static void menus() throws SQLException {
        connectionpool connectionPool = new connectionpool(
                "jdbc:mysql://localhost:3306/db02",
                "root",
                "lwnznxf5555"

        );
        boolean a=true;
        while (a) {
            System.out.println("    欢迎进入系统   ");
            System.out.println("---------------------------");
            System.out.println("选项");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("3. 退出");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println(" 欢迎进入登录页面 ");
                    System.out.println("---------------");
                    System.out.println("请输入用户账号");
                    String username = scanner.nextLine();
                    System.out.println("请输入密码");
                    String password = scanner.nextLine();
                    userconnector userconn = new userconnector(connectionPool);
                    currentuser = userconn.login(username, password);
                    if(currentuser!=null){
                        a=false;
                    }
                    else{
                        System.out.println("登录失败");
                    }
                    break;
                case 2:
                    int flag = 0;
                    do {
                        System.out.println(" 欢迎进入注册页面 ");
                        System.out.println("---------------");
                        System.out.println("请输入账号");
                        int userid= scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("请输入用户名");
                        String username1 = scanner.nextLine();
                        System.out.println("请输入密码");
                        String password1 = scanner.nextLine();
                        System.out.println("请再次输入密码");
                        String password2 = scanner.nextLine();
                        System.out.println("请输入你的身份（学生选1，管理员选0");
                        int role= scanner.nextInt();
                        if (!Objects.equals(password1, password2)) {
                            flag = 0;
                            System.out.println("两次输入不一致");
                        } else {
                            userconnector conn = new userconnector(connectionPool);
                            conn.register(userid,username1, password1, role);
                            flag = 1;
                        }
                    } while (flag == 0);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效选项");
                    break;
            }
        }

    }

    static void menus2() throws SQLException {
        connectionpool connectionPool = new connectionpool(
                "jdbc:mysql://localhost:3306/db02",
                "root",
                "lwnznxf5555"

        );
        boolean a=true;
        while (a) {
            System.out.println("    登录成功  ");
            System.out.println("----------------");
            System.out.println("选项");
            System.out.println("1. 查询可选课程");
            System.out.println("2. 选择课程（最多五门）");
            System.out.println("3. 推选课程（只能推选未开课的课程）");
            System.out.println("4. 查询自己已选课程");
            System.out.println("5. 退出");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("可选课程如下");
                    courseConnector courseConnector = new courseConnector(connectionPool);
                    System.out.println(courseConnector.seeCourse(1));
                    break;
                case 2:
                    System.out.println("请你输入课程的编号");
                    int courseid = scanner.nextInt();
                    courseConnector courseConnector2 = new courseConnector(connectionPool);
                    courseConnector2.selectcourse(currentuser.getId(), courseid);
                    break;
                case 3:
                    System.out.println("请你输入推选课程的编号");
                    int tuixuancourse = scanner.nextInt();
                    courseConnector courseConnector3 = new courseConnector(connectionPool);
                    courseConnector3.selecttuixuancourse(currentuser.getId(), tuixuancourse);
                    break;
                case 4:
                    courseConnector courseConnector1=new courseConnector(connectionPool);
                    System.out.println(courseConnector1.seeSelectedCourse(currentuser.getId()));
                    break;
                case 5:
                    a=false;
                    System.out.println("退出成功");
                    break;
                default:
                    System.out.println("无效选项");
                    break;
            }
        }
    }

    static void munu3() throws SQLException {
        connectionpool connectionPool = new connectionpool(
                "jdbc:mysql://localhost:3306/db02",
                "root",
                "lwnznxf5555");
        boolean a=true;
        while (a) {
            System.out.println("===== 管理员菜单 =====");
            System.out.println("1. 查询所有学生");
            System.out.println("2. 修改学生手机号");
            System.out.println("3. 查询所有课程");
            System.out.println("4. 修改课程信息");
            System.out.println("5. 查询某课程的学生名单");
            System.out.println("6. 查询某学生的选课情况");
            System.out.println("7. 退出");
            System.out.print("请选择操作（输入 1-7）：");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    StudentConnector studentConnector = new StudentConnector(connectionPool);
                    System.out.println(studentConnector.getStudentImformation());
                    break;
                case 2:
                    System.out.println("请你输入学生的学号");
                    int id = scanner.nextInt();
                    System.out.println("请你输入学生的新的电话号码");
                    scanner.nextLine();
                    String phonenumber = scanner.nextLine();
                    StudentConnector studentConnector1 = new StudentConnector(connectionPool);
                    studentConnector1.updatePhonenumber(id, phonenumber);
                    System.out.println("修改成功");
                    break;
                case 3:
                    courseConnector courseConnector = new courseConnector(connectionPool);
                    System.out.println(courseConnector.getCourseImformation());
                    break;
                case 4:
                    System.out.println("请你学则修改内容");
                    System.out.println("1. 删除课程");
                    System.out.println("2. 修改课程学分");
                    System.out.println("3. 增加课程");
                    int choice1= scanner.nextInt();
                    switch(choice1){
                        case 1:
                            System.out.println("请输入要删除的课程号");
                            int cid=scanner.nextInt();
                            courseConnector courseConnector4 = new courseConnector(connectionPool);
                            courseConnector4.deletecourse(cid);
                            break;
                        case 2:
                            int sid=scanner.nextInt();
                            System.out.println("请输入新的学分");
                            int nid=scanner.nextInt();
                            courseConnector courseConnector5 = new courseConnector(connectionPool);
                            courseConnector5.updatecourse1(sid,nid);
                            break;
                        case 3:
                            System.out.println("请你输入新的课程编号");
                            int newid= scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("请你输入新的课程名");
                            String newname= scanner.nextLine();
                            System.out.println("请你输入新的课程学分");
                            int newcredit= scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("请你输入新的课程状态（可选为0，推选为1）");
                            int status= scanner.nextInt();
                            courseConnector courseConnector6 = new courseConnector(connectionPool);
                            courseConnector6.addcoursee(newid,newname,newcredit,status);
                        default:
                            System.out.println("无效选项");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("请你输入课程号");
                    int CourseId = scanner.nextInt();
                    courseConnector courseConnector1 = new courseConnector(connectionPool);
                    System.out.println(courseConnector1.getCourseByid(CourseId));
                    break;
                case 6:
                    System.out.println("请你输入学生的学生号");
                    int studentId = scanner.nextInt();
                    courseConnector courseConnector2 = new courseConnector(connectionPool);
                    System.out.println(courseConnector2.seecoursesByid(studentId));
                    break;
                case 7:
                    a=false;
                    break;
                default:
                    System.out.println("无效选项");
                    break;
            }
        }
    }
}



