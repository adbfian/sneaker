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

import java.util.ArrayList;
import java.util.List;

import online.asteris.dama.marketsneaker.R;

/**
 * Created by dama on 09/09/2017.
 */

public class AdapterKontak extends RecyclerView.Adapter<AdapterKontak.KontakHolder> {
    private List<DataKontak> dataKontakList;
    private Context context;
    private DataKontak dataKontak;
    private RecyclerItemClickListener recyclerItemClickListener;
//    private String [] name ={"Apple","Facebook","Twitter","Google","Microsoft","Wikipedia","Yahoo","Youtube"};

    public AdapterKontak(Context context, List<DataKontak> dataKontakList){
        this.context = context;
        this.dataKontakList = dataKontakList;
//        this.name = name;
    }

    public DataKontak getItem(int postition){
        return dataKontakList.get(postition);
    }

    @Override
    public int getItemCount() {
        return dataKontakList.size();
    }

    public void setOnItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    static class KontakHolder extends RecyclerView.ViewHolder {
        ImageView list_icon_kontak;
        TextView list_nama_kontak;
//        TextView list_nomor_kontak;
        DataKontak dataKontak;

        public KontakHolder(View itemView) {
            super(itemView);

            list_icon_kontak = (ImageView) itemView.findViewById(R.id.list_icon_kontak);
            list_nama_kontak = (TextView) itemView.findViewById(R.id.list_nama_kontak);
//            list_nomor_kontak = (TextView) itemView.findViewById(R.id.list_nomor_kontak);
        }
    }

    @Override
    public KontakHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kontak, parent, false);
        final KontakHolder kontakHolder = new KontakHolder(view);
        kontakHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterpos = kontakHolder.getAdapterPosition();
                if (adapterpos != RecyclerView.NO_POSITION){
                    if (recyclerItemClickListener != null){
                        recyclerItemClickListener.onItemClick(adapterpos, kontakHolder.itemView);
                    }
                }
            }
        });
        return kontakHolder;
    }

    @Override
    public void onBindViewHolder(KontakHolder holder, int position) {
        DataKontak dataKontak = dataKontakList.get(position);
        Resources resources = context.getResources();
        int tileSize = resources.getDimensionPixelSize(R.dimen.letter_tile_size);

        LetterTile letterTile = new LetterTile(context);
        Bitmap letterBitmap = letterTile.getLetterTile(dataKontak.getUser_name(),
                String.valueOf(dataKontak.getUser_id()), tileSize, tileSize);

        holder.list_icon_kontak.setImageBitmap(letterBitmap);
        holder.list_nama_kontak.setText(String.valueOf(dataKontakList.get(position).getUser_id()));
        holder.list_nama_kontak.setText(String.valueOf(dataKontakList.get(position).getUser_name()));
//        holder.list_nomor_kontak.setText(String.valueOf(dataKontakList.get(position).getUser_phone()));

        holder.dataKontak = dataKontak;
    }
}
