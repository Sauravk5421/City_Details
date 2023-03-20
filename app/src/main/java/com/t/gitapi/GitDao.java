package com.t.gitapi;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GitDao {
    @Insert
    public void insert(RepoActivity name);

    @Update
    public void update(RepoActivity name);

    @Delete
    public void delete(RepoActivity name);

    @Query("SELECT * FROM REPO")


    public LiveData<List<RepoActivity>> getAllData();


}
