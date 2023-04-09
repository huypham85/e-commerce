package com.example.e_commerce.screens.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_commerce.R;
import com.example.e_commerce.model.ProductModel;

import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ItemViewHolder> {

    public OnClickProductItem onClickProductItem;
    private List<ProductModel> products;
    private Context context;
    public HomeProductAdapter(List<ProductModel> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ProductModel item = products.get(position);
        holder.bind(item);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class CompanionObject {
        public static final String ITEM_KEY = "PRODUCT_ITEM";
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;

        TextView priceTextView;
        TextView statusTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productImg);
            titleTextView = itemView.findViewById(R.id.productNameTxt);
            priceTextView = itemView.findViewById(R.id.productPriceTxt);
            statusTextView = itemView.findViewById(R.id.statusTxt);
            itemView.setOnClickListener(v -> onClickProductItem.clickProduct(products.get(getAdapterPosition())));
        }

        public void bind(ProductModel product) {
            Glide.with(context)
                    .load(product.getProductImageURL())
                    .apply(new RequestOptions().override(360, 480))
                    .into(imageView);
            titleTextView.setText(product.getProductName());
            priceTextView.setText(String.valueOf(product.getProductPrice()));
            statusTextView.setText(product.getProductStatus());
        }
    }
}
