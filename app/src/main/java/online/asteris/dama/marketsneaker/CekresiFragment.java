package online.asteris.dama.marketsneaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import online.asteris.dama.marketsneaker.Util.AppController;
import online.asteris.dama.marketsneaker.Util.Server;


/**
 * A simple {@link Fragment} subclass.
 */
public class CekresiFragment extends Fragment {

    Button btn_cek;
    EditText kode;
    String transaction_code;
    String tag_json_obj = "json_obj_req";
    int success;

    private String url = Server.URL + "trans.php";


    public CekresiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cekresi, container, false);
        kode = (EditText)view.findViewById(R.id.kode);
        btn_cek = (Button)view.findViewById(R.id.btn_cek);
        btn_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "cek transaksi", Toast.LENGTH_SHORT).show();
                String transaction_codex = kode.getText().toString();
                cek_kode(transaction_codex);
            }
        });
        return view;
    }

    private void cek_kode(final String transaction_codex){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    success = jsonObject.getInt("success");
                    if (success == 1) {
                        String transaction_code = jsonObject.getString("transaction_code");

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("transaction_code", transaction_codex);
//                    finish();
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

}
