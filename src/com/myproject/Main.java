package com.myproject;

import com.myproject.db.MyConect;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
	// write your code here
        varus varusObj = new varus();
        MyConect conectObj = new MyConect();
        //conectObj.conectByCreate(varusObj.changesStatus(10, varusObj.AVAILABLE));
        conectObj.conectByCreate(varusObj.changesPrice(10, 20));
        conectObj.conectByCreate(varusObj.addProduct("fresh_bread","4",varusObj.EXPECTED,"bakery_products", varusObj.nameScore));
        conectObj.conectByView(varusObj.viewProducts(varusObj.nameScore));

    }
}
