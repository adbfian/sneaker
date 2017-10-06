package online.asteris.dama.marketsneaker.Util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import online.asteris.dama.marketsneaker.R;

/**
 * Created by dama on 13/09/2017.
 */

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ItemHolder> {
    private List<DataItem> dataItemList;
    private Context context;
    private DataItem dataItem;
    private RecyclerItemClickListener recyclerItemClickListener;
//    private String [] name ={"Apple","Facebook","Twitter","Google","Microsoft","Wikipedia","Yahoo","Youtube"};

    public AdapterItem(Context context, List<DataItem> dataItemList){
        this.context = context;
        this.dataItemList = dataItemList;
//        this.dataItemList = new ArrayList<>();
//        this.name = name;
    }

    public DataItem getItem(int postition){
        return dataItemList.get(postition);
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public void setOnItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        ImageView list_icon_item;
        TextView list_nama_item;
        TextView list_harga_item;
        /*TextView list_konten_item;
        TextView list_stock_item;*/
        DataItem dataItem;

        public ItemHolder(View itemView) {
            super(itemView);

            list_icon_item = (ImageView) itemView.findViewById(R.id.img_item);
            list_nama_item = (TextView) itemView.findViewById(R.id.nama_item);
            list_harga_item = (TextView) itemView.findViewById(R.id.harga_item);
            /*list_konten_item = (TextView) itemView.findViewById(R.id.konten_item);
            list_stock_item = (TextView) itemView.findViewById(R.id.stock_item);*/
        }
    }

    @Override
    public AdapterItem.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        final AdapterItem.ItemHolder itemHolder = new AdapterItem.ItemHolder(view);
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterpos = itemHolder.getAdapterPosition();
                if (adapterpos != RecyclerView.NO_POSITION){
                    if (recyclerItemClickListener != null){
                        recyclerItemClickListener.onItemClick(adapterpos, itemHolder.itemView);
                    }
                }
            }
        });
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(AdapterItem.ItemHolder holder, int position) {
        DataItem dataItem = dataItemList.get(position);
        Resources resources = context.getResources();
        int tileSize = resources.getDimensionPixelSize(R.dimen.letter_tile_size);

        LetterTile letterTile = new LetterTile(context);
        Bitmap letterBitmap = letterTile.getLetterTile(dataItem.getItem_name(),
                String.valueOf(dataItem.getItem_id()), tileSize, tileSize);

        Glide.with(context)
                .load("http://asteris.online/market/api-data/items/" + dataItemList.get(position).getItem_image())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.list_icon_item);

//        holder.list_icon_item.setImageBitmap(letterBitmap);
        holder.list_nama_item.setText(String.valueOf(dataItemList.get(position).getItem_name()));
        holder.list_harga_item.setText("IDR " + String.valueOf(dataItemList.get(position).getItem_price()) + ",00");
        /*holder.list_konten_item.setText(String.valueOf(dataItemList.get(position).getItem_content()));
        holder.list_stock_item.setText(String.valueOf(dataItemList.get(position).getItem_stock()));*/
        holder.dataItem = dataItem;
    }
}
