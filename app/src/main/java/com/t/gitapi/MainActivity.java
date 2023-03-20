package com.t.gitapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.t.gitapi.databinding.ActivityDataInsertBinding;
import com.t.gitapi.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding;
private RepoViewModel repoViewModel;
List list1;
SearchView searchView;


String[] URL = {"https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-organization-repositories",
            "https://docs.github.com/en/rest/repos/repos#create-an-organization-repository",
            "https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#get-a-repository",
            "https://docs.github.com/en/rest/repos/repos#update-a-repository",
            "https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#delete-a-repository",
            "https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#enable-automated-security-fixes",
            "https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#disable-automated-security-fixes",
            "https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-codeowners-errors",
            "https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repository-contributors",
            "https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#create-a-repository-dispatch-event"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        searchView = findViewById(R.id.searchView);

        repoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.
                getInstance(this.getApplication())).get(RepoViewModel.class);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DataInsertActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setHasFixedSize(true);
        RepoAdapter adapter =new RepoAdapter();
        binding.Rv.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String q) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        repoViewModel.getAllGit().observe(this, new Observer<List<RepoActivity>>() {
            @Override
            public void onChanged(List<RepoActivity> repoActivities) {
                adapter.submitList(repoActivities);
            }
        });


    new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            repoViewModel.delete(adapter.getRepoActivity(viewHolder.getAdapterPosition()));

        }
    }).attachToRecyclerView(binding.Rv);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            String repo = data.getStringExtra("repo");
            String desp = data.getStringExtra("desp");
            RepoActivity name = new RepoActivity(repo,desp);
            repoViewModel.insert(name);
            Toast.makeText(this,"Repo Added",Toast.LENGTH_SHORT).show();

        }
    }

}