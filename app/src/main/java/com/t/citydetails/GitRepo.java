package com.t.citydetails;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class GitRepo {

    private GitDao gitDao;
    private LiveData<List<RepoActivity>> repolist;

    public GitRepo(Application application)
    {
        RepoDatabase repoDatabase  = RepoDatabase.getInstance(application);
        gitDao = repoDatabase.gitDao();
        repolist=gitDao.getAllData();

    }
    public void insertData(RepoActivity name){new InsertTask(gitDao).execute(name);}

    public LiveData<List<RepoActivity>> getAllData()
    {
        return repolist;
    }
    private static class InsertTask extends AsyncTask<RepoActivity,Void,Void>{

        private GitDao gitDao;

        public InsertTask(GitDao gitDao) {
            this.gitDao = gitDao;
        }

        @Override
        protected Void doInBackground(RepoActivity... repoActivities) {
            gitDao.insert(repoActivities[0]);
            return null;
        }
    }
}
