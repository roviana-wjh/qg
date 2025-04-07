package org.example;
import java.sql.SQLException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner=new Scanner(System.in);
        menu.menus();
        if(menu.currentuser.getrole()!=1){
            menu.munu3();
        }
        else {
            menu.menus2();
        }
    }
}