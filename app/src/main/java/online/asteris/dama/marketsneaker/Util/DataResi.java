package online.asteris.dama.marketsneaker.Util;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dama on 21/09/2017.
 */

public class DataResi implements Parcelable {
    String transaction_id, transaction_code, transaction_out, transaction_total, user_phone, user_name, transaction_address, item_code, item_name, item_price;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_code() {
        return transaction_code;
    }

    public void setTransaction_code(String transaction_code) {
        this.transaction_code = transaction_code;
    }

    public String getTransaction_out() {
        return transaction_out;
    }

    public void setTransaction_out(String transaction_out) {
        this.transaction_out = transaction_out;
    }

    public String getTransaction_total() {
        return transaction_total;
    }

    public void setTransaction_total(String transaction_total) {
        this.transaction_total = transaction_total;
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
        item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getTransaction_address() {
        return transaction_address;
    }

    public void setTransaction_address(String transaction_address) {
        this.transaction_address = transaction_address;
    }

    protected DataResi(Parcel in) {
        this.transaction_id = in.readString();
        this.transaction_code = in.readString();
        this.transaction_address = in.readString();
        this.transaction_out = in.readString();
        this.transaction_total = in.readString();
        this.user_name = in.readString();
        this.user_phone = in.readString();
        this.item_code = in.readString();
        this.item_name = in.readString();
        this.item_price = in.readString();
    }

    public static final Creator<DataResi> CREATOR = new Creator<DataResi>() {
        @Override
        public DataResi createFromParcel(Parcel in) {
            return new DataResi(in);
        }

        @Override
        public DataResi[] newArray(int size) {
            return new DataResi[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.transaction_id);
        parcel.writeString(this.transaction_code);
        parcel.writeString(this.transaction_address);
        parcel.writeString(this.transaction_out);
        parcel.writeString(this.transaction_total);
        parcel.writeString(this.user_name);
        parcel.writeString(this.user_phone);
        parcel.writeString(this.item_code);
        parcel.writeString(this.item_name);
        parcel.writeString(this.item_price);
    }
}
