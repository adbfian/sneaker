package online.asteris.dama.marketsneaker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import online.asteris.dama.marketsneaker.Util.DataItem;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    TextView nama_item, harga_item, konten_item, stock_item;
    Button btn_hubungi, btn_order;
    ImageView img_item;
    DataItem dataItem;

    public static void start(Context context){
        Intent intent = new Intent(context, OrderActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, DataItem dataItem) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(OrderActivity.class.getSimpleName(), dataItem);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img_item = (ImageView) findViewById(R.id.img_item);
        nama_item = (TextView) findViewById(R.id.nama_item);
        harga_item =  (TextView) findViewById(R.id.harga_item);
        konten_item = (TextView) findViewById(R.id.konten_item);
        stock_item = (TextView) findViewById(R.id.stock_item);

        btn_hubungi = (Button) findViewById(R.id.btn_hubungi);

        dataItem = getIntent().getParcelableExtra(OrderActivity.class.getSimpleName());
            nama_item.setText(dataItem.getItem_name());
            harga_item.setText("IDR " + dataItem.getItem_price() + ",00");
            konten_item.setText(dataItem.getItem_content());
            stock_item.setText("Stock masih " + dataItem.getItem_stock());
            Glide.with(getApplicationContext())
//                    .load("http://asteris.online/market/api-data/items/" + dataItem.getItem_image())
                    .load("http://asteris.online/market/api-data/items/" + dataItem.getItem_image())
                    .crossFade()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img_item);


        btn_hubungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "089606462774"));
                intent.putExtra("sms_body", "PESAN#" + dataItem.getItem_code()+"#" + dataItem.getItem_name() + "#<JUMLAH>#<UKURAN>");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
