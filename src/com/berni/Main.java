package com.berni;

import db.DataBase;
import entity.User;

public class Main {

    private static  final String urlDB = "jdbc:mysql://LocalHost:3306/mydb";
    private static  final String userDB = "root";
    private static  final String passwordDB = "6dH@7bZ3";

    public static User user = new User();

    public static DataBase db;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.init();
        new Run(db);
    }

    private void init() throws Exception {
        db = new DataBase(urlDB, userDB, passwordDB);
    }
}
