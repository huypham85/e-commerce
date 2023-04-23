package com.example.e_commerce.screens.order;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_commerce.R;
import com.example.e_commerce.databinding.OrderItemBinding;
import com.example.e_commerce.network.model.response.cart.CartItem;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ItemViewHolder> {

    private List<CartItem> products;
    private Context context;

    public OrderItemAdapter(List<CartItem> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public List<CartItem> getProducts() {
        return products;
    }

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CartItem item = products.get(position);
        holder.bind(item);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        OrderItemBinding binding = OrderItemBinding.bind(view);
        return new ItemViewHolder(view, binding);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        OrderItemBinding binding;

        public ItemViewHolder(View itemView, OrderItemBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(CartItem product) {

            Glide.with(context)
                    .load(product.getProductFilePath())
                    .apply(new RequestOptions().override(360, 480))
                    .into(binding.image);
            binding.productName.setText(product.getProductName());
            binding.productPrice.setText(String.valueOf(product.getProductCost()));
            binding.quantityTxt.setText(String.valueOf(product.getQuantity()));
        }
    }
}

