package online.asteris.dama.marketsneaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import online.asteris.dama.marketsneaker.Util.FragmentAdapter;
import online.asteris.dama.marketsneaker.Util.SlidingTabLayout;

import static online.asteris.dama.marketsneaker.MainActivity.TAG_LOGIN_USERNAME;

public class HomeActivity extends AppCompatActivity {

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    Toolbar toolbar;
    String user_id, user_phone;
    SharedPreferences sharedpreferences;
    public static final String TAG_LOGIN_ID = "user_id";
    public static final String TAG_LOGIN_USERNAME = "user_phone";

    String tag_json_obj = "json_obj_req";
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        user_id = getIntent().getStringExtra(TAG_LOGIN_ID);
        user_phone = getIntent().getStringExtra(TAG_LOGIN_USERNAME);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager)findViewById(R.id.konten_tab);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), this));
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.style_tab);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        slidingTabLayout.setCustomTabView(R.layout.pager_tab, R.id.tab_view);
        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.logout:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(TAG_LOGIN_ID, null);
                editor.putString(TAG_LOGIN_USERNAME, null);
                editor.commit();

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.tentang:
                Intent intent1 = new Intent(HomeActivity.this, TentangActivity.class);
                finish();
                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan lagi untuk KELUAR", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
