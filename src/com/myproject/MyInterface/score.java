package com.myproject.MyInterface;

public interface score {


//  void initiatingNewScoreInSQL (String name);

    public String viewProducts(String nameScore);

    public String addProduct (String title, String price, String status, String category, String nameScore);

    public String changesPrice(int id, int price);

    public String changesStatus(int id, String status);
}
