package com.example.e_commerce.screens.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.OrderItemBinding;
import com.example.e_commerce.network.model.response.order.OderItem;

import java.util.List;

public class OrderItemListAdapter extends RecyclerView.Adapter<OrderItemListAdapter.ItemViewHolder> {
    private List<OderItem> itemList;
    private Context context;

    public OrderItemListAdapter(List<OderItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public List<OderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OderItem> itemList) {
        this.itemList = itemList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        OderItem item = itemList.get(position);
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
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        OrderItemBinding binding;

        public ItemViewHolder(@NonNull View itemView, OrderItemBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(OderItem item) {
            binding.productName.setText(item.getProductName());
            binding.productPrice.setText(String.valueOf(item.getProductCost()));
            binding.quantityTxt.setText(String.valueOf(item.getQuantity()));
        }
    }
}
