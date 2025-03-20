package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class courseConnector {
    private org.example.connectionpool connectionpool;
    public courseConnector(connectionpool connectionpool){
        this.connectionpool=connectionpool;
    }
    public List<course>getCourseImformation() throws SQLException {
        String sql="select * from courses";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql);
        ResultSet rs=stmt.executeQuery();
        List<course>courses=new ArrayList<>();
        while(rs.next()){
            course course=new course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setCredit(rs.getInt("credit"));
            courses.add(course);
        }
        return courses;
    }
    public List<course> seeCourse(int status) throws SQLException {
        String sql="select * from courses where status=?";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,status);
        ResultSet rs=stmt.executeQuery();
        List<course>courses=new ArrayList<>();
        while(rs.next()){
            course course=new course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setCredit(rs.getInt("credit"));
            courses.add(course);
        }
        connectionpool.releaseConnection(connection);
        return courses;

    }
    public List<Student> getCourseByid(int CourseId) throws SQLException {
        String sql="select * from students where id in(select student_id from student_courses where status=? and course_id=?)";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt= connection.prepareStatement(sql);
        stmt.setString(1,"yes");
        stmt.setInt(2,CourseId);
        ResultSet rs=stmt.executeQuery();
        List<Student>students=new ArrayList<>();
        while(rs.next()){
            Student student =new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setPhone(rs.getString("phone"));
            students.add(student);
        }
        connectionpool.releaseConnection(connection);
        return students;
    }
public List<course> seecoursesByid(int studentId) throws SQLException {
        String sql="select * from courses where id in (select course_id from student_courses where student_id=?)";
    Connection connection=connectionpool.getConnection();
    PreparedStatement stmt=connection.prepareStatement(sql);
    stmt.setInt(1,studentId);
    ResultSet rs= stmt.executeQuery();
    List<course>courses=new ArrayList<>();
    while (rs.next()){
        course course=new course();
        course.setId(rs.getInt("id"));
        course.setName(rs.getString("name"));
        course.setCredit(rs.getInt("credit"));
        courses.add(course);
    }
    connectionpool.releaseConnection(connection);
    return courses;
}
    public void selectcourse(int studentid, int courseid ) throws SQLException {
        String sql="insert into student_courses values(?,?,?)";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,studentid);
        stmt.setInt(2,courseid);
        stmt.setString(3,"yes");
        stmt.executeUpdate();
        connectionpool.releaseConnection(connection);
    }
    public List<course> seeSelectedCourse(int  studentid) throws SQLException {
        //String sql="select  courses.name from courses inner join student_courses on courses.id=student_course.course_id";
        String sql="select * from courses where id in (select course_id from student_courses where status=? and student_id=?) ";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,"yes");
        stmt.setInt(2,studentid);
        ResultSet rs=stmt.executeQuery();
        List<course>courses=new ArrayList<>();
        while(rs.next()){
            course course=new course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setCredit(rs.getInt("credit"));
            courses.add(course);
        }
        connectionpool.releaseConnection(connection);
        return courses;
    }
    public void selecttuixuancourse(int studentid, int courseid ) throws SQLException {
        String sql="insert into student_courses values(?,?,?)";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,studentid);
        stmt.setInt(2,courseid);
        stmt.setString(3,"no");
        stmt.executeUpdate();
        connectionpool.releaseConnection(connection);
    }
    public void updatecourse1(int credit,int courseid) throws SQLException {
        String sql="update courses set credit=? where id=?";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,credit);
        stmt.setInt(2,courseid);
        stmt.executeUpdate();
        connectionpool.releaseConnection(connection);
    }
    public void deletecourse(int courseid) throws SQLException {
        String sql = "delete from courses where id=?";
        Connection connection = connectionpool.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, courseid);
        stmt.executeUpdate();
        connectionpool.releaseConnection(connection);

    }
    public void addcoursee(int id,String name,int credit,int status) throws SQLException {
        String sql="insert into courses values(?,?,?,?)";
        Connection connection = connectionpool.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.setString(2,name);
        stmt.setInt(3,credit);
        stmt.setInt(4,status);
        stmt.executeUpdate();
        connectionpool.releaseConnection(connection);
    }
}
