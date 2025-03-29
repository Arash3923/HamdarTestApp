package com.hamdartestapp.Views.ListApp.adapter;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hamdartestapp.Api.Models.AppModel;
import com.hamdartestapp.R;

import java.util.ArrayList;


public class ListAppAdapter extends RecyclerView.Adapter<ListAppAdapter.ViewHolder> {

    Context context;
    ArrayList<AppModel> array;


    public void remove(AppModel item) {
        array.remove(item);
    }

    public ListAppAdapter(Context context, ArrayList<AppModel> array) {
        this.context = context;
        this.array = array;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_app, parent, false);
        return new ViewHolder(view,
                (Activity) context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {


        AppModel model = array.get(position);
        viewHolder.packageName.setText(model.getPkgName());
        viewHolder.name.setText(model.getAppName());
        Glide.with(viewHolder.logo).load(getUrlLogo(model.getIconUrl())).
                placeholder(R.drawable.ic_launcher_background).
                error(R.drawable.ic_launcher_background).
                dontAnimate().into(viewHolder.logo);
    }

    private String getUrlLogo(String iconUrl) {
        return "https://v2.hamdar.ir/panel/"+iconUrl;
    }

    @Override
    public int getItemCount() {
        if (array == null) {
            return 0;
        }
        return array.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView packageName;
        public ImageView logo;
        public ConstraintLayout layout;



        public ViewHolder(@NonNull View itemView, final Activity Activity) {
            super(itemView);
            initViews();

        }


        private View findViewById(final int id) {
            return itemView.findViewById(id);
        }

        private void initViews() {


            this.name = (TextView) findViewById(R.id.name);
            this.logo = (ImageView) findViewById(R.id.logo);
            this.packageName = (TextView) findViewById(R.id.packageName);
            this.layout = (ConstraintLayout) findViewById(R.id.layout);


        }


    }

}
