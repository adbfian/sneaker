package online.asteris.dama.marketsneaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class TentangActivity extends AppCompatActivity {
    Toolbar toolbar;

    SharedPreferences sharedpreferences;
    public static final String TAG_LOGIN_ID = "user_id";
    public static final String TAG_LOGIN_USERNAME = "user_phone";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tentang);
        toolbar.setSubtitle("Market Sneaker");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
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

        if (id == android.R.id.home ) {
            Intent intent = new Intent(TentangActivity.this, HomeActivity.class);
            startActivity(intent);
            return true;
        }

        switch (id) {
            case R.id.logout:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(TAG_LOGIN_ID, null);
                editor.putString(TAG_LOGIN_USERNAME, null);
                editor.commit();

                Intent intent = new Intent(TentangActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.tentang:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
