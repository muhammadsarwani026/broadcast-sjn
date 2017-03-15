package lab.zayed.com.broadcast;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lab on 14/03/2017.
 */

public class Informasi implements Parcelable {
    private String id, judul, isi, jampost;

    public Informasi() {
        // none
    }

    public Informasi(String id, String judul, String isi, String jampost) {
        this.id = id;
        this.judul = judul;
        this.isi = isi;
        this.jampost = jampost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getJampost() {
        return jampost;
    }

    public void setJampost(String jampost) {
        this.jampost = jampost;
    }

    private Informasi(Parcel in) {
        this.id = in.readString();
        this.judul = in.readString();
        this.isi = in.readString();
        this.jampost = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<Informasi> CREATOR = new Parcelable.Creator<Informasi>() {
        @Override
        public Informasi createFromParcel(Parcel source) {
            return new Informasi(source);
        }

        @Override
        public Informasi[] newArray(int size) {
            return new Informasi[0];
        }
    };
}
