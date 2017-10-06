package online.asteris.dama.marketsneaker.Util;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dama on 09/09/2017.
 */

public class DataKontak implements Parcelable {

    public String user_id, user_phone, user_name;

    public DataKontak(){}

    protected DataKontak(Parcel in) {
        user_id = in.readString();
        user_phone = in.readString();
        user_name = in.readString();
    }

    public static final Creator<DataKontak> CREATOR = new Creator<DataKontak>() {
        @Override
        public DataKontak createFromParcel(Parcel in) {
            return new DataKontak(in);
        }

        @Override
        public DataKontak[] newArray(int size) {
            return new DataKontak[size];
        }
    };

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(user_id);
        parcel.writeString(user_phone);
        parcel.writeString(user_name);
    }
}
