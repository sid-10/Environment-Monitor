package com.example.siddharth.airpollution;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by siddharth on 17/1/16.
 */
public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDataBase;

    public UserLocalStore(Context context)
    {
        userLocalDataBase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user)
    {
        SharedPreferences.Editor spEditor = userLocalDataBase.edit();
        spEditor.putString("name",user.name);
        spEditor.putInt("age", user.age);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    public User getLoggedInUser()
    {
        String name = userLocalDataBase.getString("name", "");
        int age = userLocalDataBase.getInt("age", -1);
        String username = userLocalDataBase.getString("username", "");
        String password = userLocalDataBase.getString("password","");

        User storedUser = new User(name, age, username, password);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn)
    {
        SharedPreferences.Editor spEditor = userLocalDataBase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn()
    {
        if(userLocalDataBase.getBoolean("loggedIn", false)==true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public void clearUserData()
    {
        SharedPreferences.Editor spEditor = userLocalDataBase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
