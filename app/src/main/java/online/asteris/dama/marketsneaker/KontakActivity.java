package online.asteris.dama.marketsneaker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import online.asteris.dama.marketsneaker.Util.DataItem;
import online.asteris.dama.marketsneaker.Util.DataKontak;

public class KontakActivity extends AppCompatActivity implements View.OnClickListener{

    public static void start(Context context){
        Intent intent = new Intent(context, KontakActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, DataKontak dataKontak) {
        Intent intent = new Intent(context, KontakActivity.class);
        intent.putExtra(KontakActivity.class.getSimpleName(), dataKontak);
        context.startActivity(intent);
    }

    Toolbar toolbar;
    TextView user_name, user_phone;
    Button btn_sms, btn_call;
    DataKontak dataKontak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        dataKontak = getIntent().getParcelableExtra(KontakActivity.class.getSimpleName());
        toolbar.setTitle(dataKontak.getUser_name());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user_name = (TextView)findViewById(R.id.user);
        user_phone = (TextView)findViewById(R.id.phone);
//        btn_call = (Button)findViewById(R.id.btn_call);
        btn_sms = (Button)findViewById(R.id.btn_sms);
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + dataKontak.getUser_phone()));
//                intent.putExtra("sms_body", "PESAN#" + dataItem.getItem_code() + dataItem.getItem_name() + "#<JUMLAH>#<UKURAN>");
                startActivity(intent);
            }
        });
        user_name.setText(dataKontak.getUser_name());
        user_phone.setText(dataKontak.getUser_phone());
    }

    @Override
    public void onClick(View view) {

    }
}
