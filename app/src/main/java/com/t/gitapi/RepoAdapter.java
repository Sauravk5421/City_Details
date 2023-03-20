package com.t.gitapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.t.gitapi.databinding.ActivityDataBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RepoAdapter extends ListAdapter<RepoActivity,RepoAdapter.ViewHolder> {

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

    public RepoAdapter(){
    super(CALLBACK);

    }
private static final DiffUtil.ItemCallback<RepoActivity> CALLBACK=new DiffUtil.ItemCallback<RepoActivity>() {
    @Override
    public boolean areItemsTheSame(@NonNull RepoActivity oldItem, @NonNull RepoActivity newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull RepoActivity oldItem, @NonNull RepoActivity newItem) {
        return false;
    }
};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_data,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RepoActivity name= getItem(position);
        holder.binding.repoData.setText(name.getRepo());
        holder.binding.despData.setText(name.getDesp());


        int pos = position;
        holder.binding.repoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLinks = new Intent(Intent.ACTION_VIEW, Uri.parse(URL[pos]));
                v.getContext().startActivity(openLinks);
            }
        });

        holder.binding.shareimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "check out");
                intent.putExtra(Intent.EXTRA_TEXT, "Link");
                v.getContext().startActivity(Intent.createChooser(intent, "Share Via"));
            }
        });


    }

    public RepoActivity getRepoActivity(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ActivityDataBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ActivityDataBinding.bind(itemView);
        }
    }


}
