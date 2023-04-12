package com.t.citydetails;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface GitDao {
    @Insert
    public void insert(RepoActivity name);

    @Query("SELECT * FROM REPO")

    public LiveData<List<RepoActivity>> getAllData();


}
