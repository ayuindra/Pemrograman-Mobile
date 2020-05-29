package com.example.dessertshop.Common;

import com.example.dessertshop.Model.User;

import org.jetbrains.annotations.NotNull;

public class Common {
    public static User currentUser;

    public static final String DELETE = "Delete";

    public static String convertCodeToStatus(@NotNull String status) {
        if(status == null){
            return "kosong";
        }
        else if(status.equals("0")){
            return "Placed";
        }
        else if(status.equals("1")){
            return "On My Way";
        }
        else{
            return "Shipped";
        }
    }
}
