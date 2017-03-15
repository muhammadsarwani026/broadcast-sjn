package lab.zayed.com.broadcast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zayed elfasa on 15/03/2017.
 */

public class recyclerViewInformasiAlternatif extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private boolean loading = true;

    private List<Informasi> memberList;
    private OnItemClickListener onItemClickListener;

    public recyclerViewInformasiAlternatif() {
        memberList = new ArrayList<>();
    }

    private void add(Informasi item) {
        memberList.add(item);
        notifyItemInserted(memberList.size());
    }

    public void addAll(List<Informasi> memberList) {
        for (Informasi member : memberList) {
            add(member);
        }
    }

    public void remove(Informasi item) {
        int position = memberList.indexOf(item);
        if (position > -1) {
            memberList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Informasi getItem(int position) {
        return memberList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return VIEW_TYPE_LOADING;
        }
        return VIEW_TYPE_ITEM;
    }

    private boolean isPositionFooter(int position) {
        return position == memberList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_informasi, parent, false);
            return new MemberViewHolder(view, onItemClickListener);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MemberViewHolder) {
            MemberViewHolder memberViewHolder = (MemberViewHolder) holder;

            final Informasi member = memberList.get(position);

            memberViewHolder.textViewID.setText(member.getId());
            memberViewHolder.textViewJudul.setText(member.getJudul());
            memberViewHolder.textViewISI.setText(member.getIsi());
            memberViewHolder.textViewJampost.setText(member.getJampost());

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
            loadingViewHolder.progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        }
    }

    public void setLoading(boolean loading){
        this.loading = loading;
    }

    @Override
    public int getItemCount() {
        return memberList == null ? 0 : memberList.size() + 1;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class MemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewJudul;
        TextView textViewISI;
        TextView textViewJampost;
        TextView textViewID;

        OnItemClickListener onItemClickListener;

        public MemberViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            textViewID = (TextView) itemView.findViewById(R.id.textID);
            textViewJudul = (TextView) itemView.findViewById(R.id.textJUDUL);
            textViewISI = (TextView) itemView.findViewById(R.id.textISI);
            textViewJampost = (TextView) itemView.findViewById(R.id.textTANGGAL);

            itemView.setOnClickListener(this);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.loading);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}