package lab.zayed.com.broadcast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lab on 14/03/2017.
 */

public class recyclerInformasi extends RecyclerView.Adapter<recyclerInformasi.myViewHolder> {

    private List<Informasi> informasiList;

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_informasi, parent, false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Informasi m = informasiList.get(position);
        holder.textViewID.setText(m.getId());
        holder.textViewJudul.setText(m.getJudul());
        holder.textViewIsi.setText(m.getIsi());
        holder.textViewJamPost.setText(m.getJampost());
    }

    @Override
    public int getItemCount() {
        return informasiList.size();
    }

    public recyclerInformasi(List<Informasi> informasiList) {
        this.informasiList = informasiList;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewID, textViewJudul, textViewIsi, textViewJamPost;

        public myViewHolder(View itemView) {
            super(itemView);

            textViewID = (TextView) itemView.findViewById(R.id.textID);
            textViewJudul = (TextView) itemView.findViewById(R.id.textJUDUL);
            textViewIsi = (TextView) itemView.findViewById(R.id.textISI);
            textViewJamPost = (TextView) itemView.findViewById(R.id.textTANGGAL);
        }
    }
}
