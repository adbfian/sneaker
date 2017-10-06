package online.asteris.dama.marketsneaker;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import online.asteris.dama.marketsneaker.Util.AdapterKontak;
import online.asteris.dama.marketsneaker.Util.AppController;
import online.asteris.dama.marketsneaker.Util.DataKontak;
import online.asteris.dama.marketsneaker.Util.RecyclerItemClickListener;
import online.asteris.dama.marketsneaker.Util.Server;


/**
 * A simple {@link Fragment} subclass.
 */
public class KontakFragment extends Fragment implements RecyclerItemClickListener{
//    FloatingActionButton btn_add;
    ProgressDialog progressDialog;
    private AdapterKontak adapterKontak;
    private List<DataKontak> dataKontakList;
    private RecyclerView kontak_list;
    private LinearLayoutManager linearLayoutManager;
    private static String kontakApi	 = Server.URL + "user_tampil.php";



    public KontakFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_kontak, container, false);
        kontak_list = (RecyclerView)view.findViewById(R.id.kontak_list);
//        btn_add = (FloatingActionButton) view.findViewById(R.id.fab_add);
        progressDialog = new ProgressDialog(getActivity());
        dataKontakList = new ArrayList<>();
        loadKontak();
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapterKontak = new AdapterKontak(getActivity(), dataKontakList);
        adapterKontak.setOnItemClickListener(this);
        kontak_list.setLayoutManager(linearLayoutManager);
        kontak_list.setAdapter(adapterKontak);

        return view;
    }

    private void loadKontak(){
        progressDialog.setMessage("Mengambil data");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JsonArrayRequest reqData = new JsonArrayRequest(kontakApi, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.cancel();
                        Log.d("volley", "response : " + response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                DataKontak dataKontak = new DataKontak();
                                dataKontak.setUser_id(data.getString("user_id"));
                                dataKontak.setUser_phone(data.getString("user_phone"));
                                dataKontak.setUser_name(data.getString("user_name"));
                                dataKontakList.add(dataKontak);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterKontak.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }

    @Override
    public void onItemClick(int position, View view) {
        KontakActivity.start(getActivity(), adapterKontak.getItem(position));
    }

}
