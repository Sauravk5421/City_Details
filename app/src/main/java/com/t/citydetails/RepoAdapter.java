package com.t.citydetails;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t.citydetails.databinding.ActivityDataBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RepoAdapter extends ListAdapter<RepoActivity,RepoAdapter.ViewHolder> {

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
        holder.binding.cityData.setText(name.getCity());
        holder.binding.stateData.setText(name.getState());
        holder.binding.cityData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latt  = name.getLat();
                String lonn = name.getLon();
                Uri uri = Uri.parse("geo:0, 0?q="+latt+","+lonn);
                Intent openLinks = new Intent(Intent.ACTION_VIEW,uri);
                openLinks.setPackage("com.google.android.apps.maps");
                v.getContext().startActivity(openLinks);
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
