package com.certified.gadsproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.certified.gadsproject.retrofit.Hours;

import java.util.List;

public class LearningLeadersRecyclerViewAdapter extends RecyclerView.Adapter<LearningLeadersRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "LearningLeadersRecyclerAdapter";
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<Hours> hoursList;

    public LearningLeadersRecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setUserData(List<Hours> hoursList) {
        this.hoursList = hoursList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.learning_leaders_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");

        Hours hours = hoursList.get(position);

        String name = hours.getName();
        String country = hours.getCountry();
        Integer hour = hours.getHours();
        String badgeUrl = hours.getBadgeUrl();

        Glide.with(mContext)
                .load(badgeUrl)
                .into(holder.learningBadge);
        holder.mName.setText(name);
        holder.mCountry.setText(country);
        holder.mLearningHours.setText(hour.toString() + " learning hours, ");
    }

    @Override
    public int getItemCount() {
        return hoursList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView mName, mLearningHours, mCountry;
        private final ImageView learningBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            learningBadge = itemView.findViewById(R.id.iv_learning_badge);
            mName = itemView.findViewById(R.id.tv_name);
            mLearningHours = itemView.findViewById(R.id.tv_learning_hours);
            mCountry = itemView.findViewById(R.id.tv_country);
        }
    }
}
