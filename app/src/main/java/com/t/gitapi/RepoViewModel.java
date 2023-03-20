package com.t.gitapi;


import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RepoViewModel extends AndroidViewModel {

    private GitRepo gitRepo;
    private LiveData<List<RepoActivity>> gitlist;


    public RepoViewModel(@NonNull Application application) {
        super(application);
        gitRepo=new GitRepo(application);
        gitlist=gitRepo.getAllData();

    }
    public void insert(RepoActivity name){
        gitRepo.insertData(name);
    }
    public void delete(RepoActivity name){
        gitRepo.deleteData(name);
    }
    public LiveData<List<RepoActivity>> getAllGit(){
        return gitlist;
    }


}
