package online.asteris.dama.marketsneaker;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import online.asteris.dama.marketsneaker.Util.AdapterItem;
import online.asteris.dama.marketsneaker.Util.AppController;
import online.asteris.dama.marketsneaker.Util.DataItem;
import online.asteris.dama.marketsneaker.Util.RecyclerItemClickListener;
import online.asteris.dama.marketsneaker.Util.Server;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment implements RecyclerItemClickListener, SwipeRefreshLayout.OnRefreshListener {
//    FloatingActionButton btn_add;
    ProgressDialog progressDialog;
    private AdapterItem adapterItem;
    private List<DataItem> dataItemList;
    private RecyclerView item_list;
    private LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipe;
    private GridLayoutManager gridLayoutManager;
    private static String itemApi	 = Server.URL + "item_tampil.php";


    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_item, container, false);
        item_list = (RecyclerView)view.findViewById(R.id.item_list);
        progressDialog = new ProgressDialog(getActivity());
        dataItemList = new ArrayList<>();
//        loadItem();
        swipe   = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        adapterItem = new AdapterItem(getActivity(), dataItemList);
        adapterItem.setOnItemClickListener(this);
        item_list.setLayoutManager(gridLayoutManager);
        item_list.setAdapter(adapterItem);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           dataItemList.clear();
                           adapterItem.notifyDataSetChanged();
                           loadItem();
                       }
                   }
        );

        return view;
    }

    @Override
    public void onRefresh() {
        dataItemList.clear();
        adapterItem.notifyDataSetChanged();
        loadItem();
    }

    private void loadItem(){
        dataItemList.clear();
        adapterItem.notifyDataSetChanged();
        swipe.setRefreshing(true);
        progressDialog.setMessage("Mengambil data");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JsonArrayRequest reqData = new JsonArrayRequest(itemApi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.cancel();
                Log.d("volley", "response : " + response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject data = response.getJSONObject(i);
                        DataItem dataItem = new DataItem();
                        dataItem.setItem_id(data.getString("item_id"));
                        dataItem.setItem_code(data.getString("item_code"));
                        dataItem.setItem_name(data.getString("item_name"));
                        dataItem.setItem_price(data.getString("item_price"));
                        dataItem.setItem_image(data.getString("item_image"));
                        dataItem.setItem_content(data.getString("item_content"));
                        dataItem.setItem_stock(data.getString("item_stock"));
                        dataItemList.add(dataItem);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterItem.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                        swipe.setRefreshing(true);
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }

    @Override
    public void onItemClick(int position, View view) {
        OrderActivity.start(getActivity(), adapterItem.getItem(position));
    }
}

