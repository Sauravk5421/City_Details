package com.t.citydetails;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = RepoActivity.class,version = 1)
public abstract class RepoDatabase extends RoomDatabase {

    private static RepoDatabase instance;

    public abstract GitDao gitDao();

    public static synchronized RepoDatabase getInstance(Context context)
    {
        if (instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),RepoDatabase.class,"Repo_database").fallbackToDestructiveMigration().build();
        }
        return instance;

    }
}