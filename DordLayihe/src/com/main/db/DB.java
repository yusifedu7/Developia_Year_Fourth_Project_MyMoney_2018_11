package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {

    private Connection cn;

    private static DB db = new DB();

    public static DB getInstange() {
        return db;
    }

    private DB() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/layihe4", "yusif1", "1234");

            System.out.println("Success connection...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public boolean iud(String query) {
        boolean result = false;
        try {
            Statement s = cn.createStatement();
            s.execute(query);
            s.close();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> getColumnRowsFromTable(String table, String column, String condition) {
        ArrayList<String> list = new ArrayList<>();

        try {
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery("select " + column + " from " + table + " " + condition);

            while (r.next()) {
                list.add(r.getString(1));
            }

            r.close();
            s.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;

    }
    
    
     public  String  getColumnSingleRowFromTable(String table, String column, String condition) {
       String  st   =null;

        try {
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery("select " + column + " from " + table + " " + condition);

           r.next();
           
                st=   r.getString(1);
        

            r.close();
            s.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return st;

    }
     

    public boolean hasRowInTableForThisCondition(String table, String condition) {
        boolean has = false;
        try {
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery("select count(*) from " + table + " " + condition);
            r.next();
            int count = r.getInt(1);
            if (count == 0) {
                has = false;
            } else {
                has = true;
            }
            r.close();
            s.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return has;
    }


    public double getSumColumnValuesFromTable(String table, String column,String condition) {
       double totalAmount=0;
       

        try {
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery("select sum("+column+") from "+table+"  "+condition);
            r.next();
            totalAmount = r.getDouble(1);

            r.close();
            s.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return totalAmount;

    }


    public int getMaxColumnValueFromTable(String table, String column) {
        int max = 0;

        try {
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery("select max(" + column + ") from " + table);
            r.next();
            max = r.getInt(1);

            r.close();
            s.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return max;

    }
}
