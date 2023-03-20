package com.t.gitapi;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "REPO")
public class RepoActivity {
    private String repo;
    private String desp;


    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public RepoActivity(String repo,String desp) {
        this.repo = repo;
        this.desp = desp;

    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getOname() {
        return desp;
    }

    public void getOname(String desp) {
        this.desp = desp;
    }
}
