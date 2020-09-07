package com.certified.gadsproject.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.certified.gadsproject.LearningLeadersRecyclerViewAdapter;
import com.certified.gadsproject.R;
import com.certified.gadsproject.SkillLeadersRecyclerViewAdapter;
import com.certified.gadsproject.retrofit.ApiClient;
import com.certified.gadsproject.retrofit.IqScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillLeadersFragment extends Fragment {

    private static final String TAG = "SkillLeadersFragment";
    private RecyclerView mSkillRecyclerView;
    private SkillLeadersRecyclerViewAdapter skillLeadersAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ProgressBar progressBar;

    public SkillLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_leaders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        mSkillRecyclerView = view.findViewById(R.id.recycler_view_skill_leaders);
        skillLeadersAdapter = new SkillLeadersRecyclerViewAdapter(getContext());
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mSkillRecyclerView.setLayoutManager(mLinearLayoutManager);

        getIqLeaders();
    }

    public void getIqLeaders() {
        Call<List<IqScore>> scoreCall = ApiClient.getIqApi().getIqScores();

        progressBar.setVisibility(View.VISIBLE);
        scoreCall.enqueue(new Callback<List<IqScore>>() {
            @Override
            public void onResponse(Call<List<IqScore>> call, Response<List<IqScore>> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: successful");

                    progressBar.setVisibility(View.GONE);
                    List<IqScore> iqScores = response.body();
                    skillLeadersAdapter.setUserData(iqScores);
                    mSkillRecyclerView.setAdapter(skillLeadersAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<IqScore>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "An error occured: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onFailure: An error occured: " + t.getMessage());
            }
        });
    }
}