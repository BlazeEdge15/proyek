package id.ac.umn.warehaus;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private Context mCtx;
    private List<Product> productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout1, null);
        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i){
        Product product = productList.get(i);

        productViewHolder.tvTitle.setText(product.getItem());
        productViewHolder.tvDesc.setText(product.getDesc());

        productViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, ShipmentActivity.class);
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount(){
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvDesc;
        CardView cardView;

        public ProductViewHolder(@NonNull View itemView){
            super(itemView);

            tvTitle = itemView.findViewById(R.id.textViewTitle);
            tvDesc = itemView.findViewById(R.id.textViewDesc);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}