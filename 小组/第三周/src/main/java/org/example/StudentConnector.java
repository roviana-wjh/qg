package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentConnector {
    private org.example.connectionpool connectionpool;
    public StudentConnector() {
        this.connectionpool = connectionpool;
    }
    public List<Student>getStudentImformation() throws SQLException {
        List<Student> students=new ArrayList<>();
        String sql="select * from students";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql);
        ResultSet rs= stmt.executeQuery();
        while(rs.next()){
            Student student=new Student();
            student.setId(rs.getInt("id"));
            student.setUserId(rs.getInt("user_Id"));
            student.setName(rs.getString("name"));
            student.setPhone(rs.getString("Phone"));
            students.add(student);
        }
        connectionpool.releaseConnection(connection);
        return  students;
    }
    public void updatePhonenumber(int studentid, int i) throws SQLException {
        String sql="update students set phone=? where id=?";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,i);
        stmt.setInt(2,studentid);
        stmt.executeUpdate();
        connectionpool.releaseConnection(connection);
    }
}
