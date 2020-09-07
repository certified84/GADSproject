package com.certified.gadsproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.certified.gadsproject.pages.LearningLeadersFragment;
import com.certified.gadsproject.pages.SkillLeadersFragment;
import com.google.android.material.tabs.TabLayout;

public class LeaderBoardActivity extends AppCompatActivity {

    private static final String TAG = "LeaderBoardActivity";
    TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private SkillLeadersFragment mSkillLeadersFragment;
    private LearningLeadersFragment mLearningLeadersFragment;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }

        btnSubmit = findViewById(R.id.btn_submit);
        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tabs);

        mSkillLeadersFragment = new SkillLeadersFragment();
        mLearningLeadersFragment = new LearningLeadersFragment();

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewPagerAdapter.addFragment(mLearningLeadersFragment, getString(R.string.learning_leaders));
        mViewPagerAdapter.addFragment(mSkillLeadersFragment, getString(R.string.skill_iq_leaders));

        btnSubmit.setOnClickListener(view -> startActivity(new Intent(LeaderBoardActivity.this, SubmissionActivity.class)));
    }
}