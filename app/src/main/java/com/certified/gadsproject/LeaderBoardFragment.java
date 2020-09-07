package com.certified.gadsproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.certified.gadsproject.pages.LearningLeadersFragment;
import com.certified.gadsproject.pages.SkillLeadersFragment;
import com.google.android.material.tabs.TabLayout;

public class LeaderBoardFragment extends Fragment {

    private static final String TAG = "LeaderBoardFragment";
    private NavController mNavController;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private SkillLeadersFragment mSkillLeadersFragment;
    private LearningLeadersFragment mLearningLeadersFragment;
    private Button btnSubmit;

    public LeaderBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leader_board, container, false);

        btnSubmit = view.findViewById(R.id.btn_submit);
        mViewPager = view.findViewById(R.id.view_pager);
        mTabLayout = view.findViewById(R.id.tabs);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNavController = Navigation.findNavController(view);

        mSkillLeadersFragment = new SkillLeadersFragment();
        mLearningLeadersFragment = new LearningLeadersFragment();

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPagerAdapter = new ViewPagerAdapter(getParentFragmentManager(), 0);
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewPagerAdapter.addFragment(mLearningLeadersFragment, getString(R.string.learning_leaders));
        mViewPagerAdapter.addFragment(mSkillLeadersFragment, getString(R.string.skill_iq_leaders));
        mViewPagerAdapter.notifyDataSetChanged();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavController.navigate(R.id.action_leaderBoardFragment_to_submissionFragment);
            }
        });
    }
}