package com.myproject.db;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class MyConect {

    //connect properties ...
    final String URL = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";
    final String JDBS = "com.mysql.cj.jdbc.Driver";
    final String USER = "root";
    final String PASS = "root";

    //SCRIPT TO CREATE DB AND TABLE AND INSERT PRODUCT ... ... ...
    //================================================================================================

    final String CREATE_SCHEMAS = "CREATE SCHEMA my_products";
    final String CREATE_TABLE = "CREATE TABLE my_products.products (id int NOT NULL AUTO_INCREMENT, title varchar(30) NOT NULL, price varchar(30) NOT NULL, status varchar(30) NOT NULL, category varchar(30) NOT NULL, score varchar(30) NOT NULL, " +
            "PRIMARY KEY(id))";

    public final String INSERT(String title, String price, String status, String category, String score) {
        return "INSERT INTO my_products.products (title, price, status, category, score) VALUES ('" + title + "', '" + price + "', '" + status + "','" + category + "', '" + score + "'); ";
    }

    //=================================================================================================

    //use to create the schema, table, insert products in table
    public void conectByCreate(String Sql) throws ClassNotFoundException, SQLException {
        Connection con = null;
        System.out.println("Registering JDBC driver...");
        Class.forName(JDBS);
        System.out.println("Creating database connection...");
        con = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Executing statement...");
        int i = con.createStatement().executeUpdate(Sql);
        System.out.println("Closing connection and releasing resources..." + i);
        con.close();
    }

    //use to see the filling table...
    public void conectByView(String Sql) throws ClassNotFoundException, SQLException{
        Connection con = null;
        Statement stat = null;
        System.out.println("Registering JDBC driver...");
        Class.forName(JDBS);
        System.out.println("Creating database connection...");
        con = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Executing statement...");
        stat = con.createStatement();
        ResultSet resultSet = stat.executeQuery(Sql);
        System.out.println("Retrieving data from database...");
        System.out.println("\nProducts:");
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            int id = resultSet.getInt("id");
            String price = resultSet.getString("price");
            String status = resultSet.getString("status");
            String category = resultSet.getString("category");
            System.out.println("\n================\n");
            System.out.println("Title: " +title);
            System.out.println("id: " + id);
            System.out.println("Price: " + price);
            System.out.println("Status: " + status);
            System.out.println("Category: " + category);
        }
        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        stat.close();
        con.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        MyConect conObj = new MyConect();
            //create schemas
        //conObj.conectByCreate(conObj.CREATE_SCHEMAS());
            //create table in schemas
        //conObj.conectByCreate(conObj.CREATE_TABLE);
            //test to insert product in table
        //conObj.conectByCreate(conObj.INSERT("basturma","10","absent", "snack", "varus"));
        ParseIncomProducts performanceObg = new ParseIncomProducts();
        performanceObg.initial();
        performanceObg.setNumberOfColumsProducts(performanceObg.incomProducts.size());
        System.out.println(performanceObg.incomProducts);
        performanceObg.productsArray = performanceObg.pA(performanceObg.incomProducts);
        for (int row = 0; row<performanceObg.productsArray.length; row++){
            System.out.println();
            for(int colum = 0; colum<performanceObg.productsArray[row].length; colum++){
                System.out.print(performanceObg.productsArray[row][colum]);
            }
        }
        // insert products in mySQL products table
        for (int i = 0; i<performanceObg.productsArray.length; i++){
            conObj.conectByCreate(conObj.INSERT(performanceObg.productsArray[i][1], performanceObg.productsArray[i][2], performanceObg.productsArray[i][3], performanceObg.productsArray[i][4], performanceObg.productsArray[i][5]));
        }
    }
}

/*
 * class parse produsts.txt before recording in mySQL*/
class ParseIncomProducts {
    final int number_Parameter_Products = 6;
    int numberOfColumsProducts;

    ArrayList<String> incomProducts = new ArrayList<String>();
    PrimaryFilling treatmentProducts = new PrimaryFilling();
    String[][] productsArray = new String[numberOfColumsProducts][number_Parameter_Products];

    String[][] pA(ArrayList<String> s) {
        String[][] temp = new String[numberOfColumsProducts][number_Parameter_Products];
        StringBuilder bilder = new StringBuilder();
        for(int row = 0; row<s.size(); row++){
            int countColumArray = 0;
            for (int lengthRow = 0; lengthRow<s.get(row).length(); lengthRow++){
                if (s.get(row).charAt(lengthRow) != ' '){
                    bilder.append(s.get(row).charAt(lengthRow));
                } else if (s.get(row).charAt(lengthRow) == ' '){
                    temp[row][countColumArray] = bilder.toString();
                    bilder.delete(0,bilder.length());
                    countColumArray++;
                }
            }
            temp[row][countColumArray] = bilder.toString();
            bilder.delete(0,bilder.length());
        }
        return temp;
    }

    void setIncomProducts(ArrayList<String> S) {
        this.incomProducts = S;
    }


//this is void to set incomProducts...from products.txt across PrimaryFiling class.
//======================================================================================
    void initial() throws IOException {
        setIncomProducts(treatmentProducts.scanerWay(treatmentProducts.getFILE_NAME()));
    }
//======================================================================================
    void setNumberOfColumsProducts (int i){
        this.numberOfColumsProducts = i;
    }
}