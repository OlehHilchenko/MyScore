package com.myproject;


import com.myproject.db.MyConect;

abstract public class  score implements com.myproject.MyInterface.score {

    protected String nameScore;
    final String AVAILABLE = "Available";
    final String ABSENT = "Absent";
    final String EXPECTED = "Expected";

    @Override
    public String viewProducts (String nameScore){
        return "SELECT title,id,price,status,category FROM my_products.products WHERE score='"+nameScore+"'ORDER BY category,status";
    }

    @Override
    public String addProduct (String title, String price, String status, String category, String nameScore) {
        MyConect insertObj = new MyConect();
        return insertObj.INSERT(title, price, status, category, nameScore);
    }

    @Override
    public String changesPrice(int id, int price){
        return "UPDATE my_products.products SET price='"+String.valueOf(price)+"' WHERE id="+id;
    }

    @Override
    public String changesStatus(int id, String status){
        return "UPDATE my_products.products SET status='"+status+"' WHERE id="+id;
    }
}
