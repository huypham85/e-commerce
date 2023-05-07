package com.example.e_commerce.screens.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.OrderStatusBinding;
import com.example.e_commerce.network.model.response.order.UserOrderResponse;

import java.util.List;

public class UserOrderAdapter extends RecyclerView.Adapter<UserOrderAdapter.ItemViewHolder> {
    public OnClickUserOrder onClickUserOrder;
    private List<UserOrderResponse> userOrderList;
    private Context context;

    public UserOrderAdapter(List<UserOrderResponse> userOrderList, Context context) {
        this.userOrderList = userOrderList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_status, parent, false);
        OrderStatusBinding binding = OrderStatusBinding.bind(view);
        return new ItemViewHolder(view, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        UserOrderResponse order = userOrderList.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return userOrderList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        OrderStatusBinding binding;

        public ItemViewHolder(View itemView, OrderStatusBinding binding) {
            super(itemView);
            this.binding = binding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickUserOrder.clickOrder(userOrderList.get(getAdapterPosition()));
                }
            });
        }

        @SuppressLint("SetTextI18n")
        public void bind(UserOrderResponse order) {
            binding.userorderId.setText("Mã: " + order.getOderCode());
            if (order.getStatus() == 0)
                binding.userorderStatus.setText("Đang xử lý");
            else
                binding.userorderStatus.setText("Đã xử lý");
        }
    }
}
