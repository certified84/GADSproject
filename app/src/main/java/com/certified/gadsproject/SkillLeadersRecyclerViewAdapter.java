package com.certified.gadsproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.certified.gadsproject.retrofit.IqScore;

import java.util.ArrayList;
import java.util.List;

public class SkillLeadersRecyclerViewAdapter extends RecyclerView.Adapter<SkillLeadersRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "SkillLeadersRecyclerAdapter";
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<IqScore> iqScoreList;

    public SkillLeadersRecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setUserData(List<IqScore> iqScoreList) {
        this.iqScoreList = iqScoreList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.skill_leaders_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        IqScore iqScore = iqScoreList.get(position);

        String name = iqScore.getName();
        String country = iqScore.getCountry();
        Integer score = iqScore.getScore();
        String badgeUrl = iqScore.getBadgeUrl();

        Glide.with(mContext)
                .load(badgeUrl)
                .into(holder.skillBadge);

        holder.mName.setText(name);
        holder.mSkillScore.setText(score.toString() + " skill IQ Score, ");
        holder.mCountry.setText(country);

    }

    @Override
    public int getItemCount() {
        return iqScoreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView mName, mSkillScore, mCountry;
        private final ImageView skillBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skillBadge = itemView.findViewById(R.id.iv_skill_badge);
            mName = itemView.findViewById(R.id.tv_name);
            mSkillScore = itemView.findViewById(R.id.tv_iq_score);
            mCountry = itemView.findViewById(R.id.tv_country);
        }
    }
}
