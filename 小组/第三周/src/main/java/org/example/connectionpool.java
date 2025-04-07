package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class connectionpool {
    private static String url;
    private static String root;
    private static String password;
    private final int Maxpoolsize=10;
    private final int InitialSize=5;
    private int usedSize=0;
    private final BlockingQueue<Connection> availableconnection = new LinkedBlockingQueue<>(Maxpoolsize);
    private final Queue<Connection> usedConnection=new LinkedList<>();
    public connectionpool(String name,String root,String password){
        this.url=name;
        this.root=root;
        this.password=password;
    }
    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url,root,password);
    }
    private void initializePool(){
        for (int i=0;i<InitialSize;i++){
            try{
                Connection connection=createConnection();
                availableconnection.add(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
     Connection getConnection() throws SQLException {
        Connection connection=availableconnection.poll();
        if(connection==null){
            if(usedConnection.size()<Maxpoolsize){
                 connection=createConnection();
            }
            if (connection != null) {
                usedConnection.add(connection);
                usedSize++;
            }
        }
        return connection;
    }
    void releaseConnection(Connection connection){
        if(connection!=null){
            usedConnection.remove(connection);
            availableconnection.add(connection);
            usedSize--;
        }

    }
    public void close() throws SQLException {
        for(Connection connection :availableconnection){
            connection.close();
        }
        for(Connection connection :usedConnection){
            connection.close();
        }
        availableconnection.clear();
        usedConnection.clear();
    }


}
