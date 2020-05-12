package id.ac.umn.warehaus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private Context mCtx1;
    private List<History> historyList;

    public HistoryAdapter(Context mCtx1, List<History> historyList) {
        this.mCtx1 = mCtx1;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater inflater = LayoutInflater.from(mCtx1);
        View view = inflater.inflate(R.layout.list_layout_history_masuk, null);
        HistoryAdapter.HistoryViewHolder holder = new HistoryAdapter.HistoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder historyViewHolder, int i){
        History history = historyList.get(i);

        historyViewHolder.tvName.setText(history.getNama());
        historyViewHolder.tvQty.setText(history.getJum());
    }

    @Override
    public int getItemCount(){
        return historyList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvQty;

        public HistoryViewHolder(@NonNull View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.textViewProduct1);
            tvQty = itemView.findViewById(R.id.textViewQTY1);
        }
    }
}
