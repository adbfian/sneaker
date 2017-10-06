package online.asteris.dama.marketsneaker.Util;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dama on 13/09/2017.
 */

public class DataItem implements Parcelable {
    String item_id, item_code;
    public String item_name, item_price, item_image, item_content, item_stock;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public String getItem_content() {
        return item_content;
    }

    public void setItem_content(String item_content) {
        this.item_content = item_content;
    }

    public String getItem_stock() {
        return item_stock;
    }

    public void setItem_stock(String item_stock) {
        this.item_stock = item_stock;
    }

    protected DataItem(Parcel in){
        this.item_id = in.readString();
        this.item_code = in.readString();
        this.item_name = in.readString();
        this.item_price = in.readString();
        this.item_image = in.readString();
        this.item_content = in.readString();
        this.item_stock = in.readString();
    }

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel source) {
            return new DataItem(source);
        }

        @Override
        public DataItem[] newArray(int i) {
            return new DataItem[i];
        }
    };

    public DataItem() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.item_id);
        parcel.writeString(this.item_code);
        parcel.writeString(this.item_name);
        parcel.writeString(this.item_price);
        parcel.writeString(this.item_image);
        parcel.writeString(this.item_content);
        parcel.writeString(this.item_stock);
    }
}
