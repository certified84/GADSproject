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
import com.certified.gadsproject.retrofit.ApiClient;
import com.certified.gadsproject.retrofit.Hours;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragment extends Fragment {

    private static final String TAG = "LearningLeadersFragment";
    private RecyclerView learningLeardersRecyclerView;
    private LearningLeadersRecyclerViewAdapter learningLeadersAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ProgressBar progressBar;

    public LearningLeadersFragment() {
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
        return inflater.inflate(R.layout.fragment_learning_leaders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        learningLeardersRecyclerView = view.findViewById(R.id.recycler_view_learning_learders);
        learningLeadersAdapter = new LearningLeadersRecyclerViewAdapter(getContext());
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        learningLeardersRecyclerView.setLayoutManager(mLinearLayoutManager);

        getHourLeaders();
    }

    public void getHourLeaders() {
        Call<List<Hours>> hourCall = ApiClient.getHoursApi().getHours();

        progressBar.setVisibility(View.VISIBLE);
        hourCall.enqueue(new Callback<List<Hours>>() {
            @Override
            public void onResponse(Call<List<Hours>> call, Response<List<Hours>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: successful");

                    progressBar.setVisibility(View.GONE);
                    List<Hours> hours = response.body();
                    learningLeadersAdapter.setUserData(hours);
                    learningLeardersRecyclerView.setAdapter(learningLeadersAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Hours>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "An error occured: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onFailure: An error occured: " + t.getMessage());
            }
        });
    }
}