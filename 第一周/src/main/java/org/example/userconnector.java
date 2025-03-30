package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userconnector {
    private static org.example.connectionpool connectionpool;
    public userconnector(connectionpool connectionpool){
        this.connectionpool=connectionpool;
    }
    public  static user login(String userid, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ? AND password = ?";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,userid);
        stmt.setString(2,password);
        ResultSet rs=stmt.executeQuery();
        user user=null;
        while(rs.next()) {
            user = new user();
            user.setId((rs.getInt("id")));
            user.setUsername((rs.getString("username")));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getInt("role"));
        }
            connectionpool.releaseConnection(connection);
        return user;
    }
    public  void register(int user_id,String username ,String password,int role) throws SQLException {
        String sql="insert into users (id,username,password,role) values(?,?,?,?)";
        Connection connection=connectionpool.getConnection();
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,user_id);
        stmt.setString(2,username);
        stmt.setString(3,password);
        stmt.setInt(4,role);
        stmt.executeUpdate();
        connectionpool.releaseConnection(connection);
    }


}
