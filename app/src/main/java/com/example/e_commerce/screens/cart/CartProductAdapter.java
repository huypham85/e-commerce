package com.example.e_commerce.screens.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_commerce.R;
import com.example.e_commerce.network.model.response.cart.CartItem;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ItemViewHolder> {

    CartItemListener cartItemListener;
    private List<CartItem> products;
    private Context context;

    public CartProductAdapter(List<CartItem> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public CartItemListener getCartItemListener() {
        return cartItemListener;
    }

    public void setCartItemListener(CartItemListener cartItemListener) {
        this.cartItemListener = cartItemListener;
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

    void setData(List<CartItem> list, int position) {
        setProducts(list);
        notifyItemChanged(position);
    }

    @SuppressLint("NotifyDataSetChanged")
    void setDataAfterRemove(List<CartItem> list, int position) {
        setProducts(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CartItem item = products.get(position);
        holder.bind(item);
        holder.onClick(item, position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView priceTextView, quantityTextView;
        CheckBox checkBox;
        Button addButton, subtractButton, deleteButton;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            titleTextView = itemView.findViewById(R.id.product_name);
            priceTextView = itemView.findViewById(R.id.product_price);
            quantityTextView = itemView.findViewById(R.id.quantityTxt);
            checkBox = itemView.findViewById(R.id.checkbox);
            addButton = itemView.findViewById(R.id.add_button);
            subtractButton = itemView.findViewById(R.id.subtract_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }

        @SuppressLint("SetTextI18n")
        public void bind(CartItem product) {

            Glide.with(context)
                    .load(product.getProductFilePath())
                    .apply(new RequestOptions().override(360, 480))
                    .into(imageView);
            titleTextView.setText(product.getProductName());
            priceTextView.setText(String.valueOf(product.getProductCost()));
            quantityTextView.setText(String.valueOf(product.getQuantity()));
        }

        private void onClick(CartItem product, int position) {
            addButton.setOnClickListener(v -> cartItemListener.increaseQuantity(position));

            subtractButton.setOnClickListener(v -> cartItemListener.decreaseQuantity(position));

            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    cartItemListener.onSelectCartItem(product.getQuantity() * product.getProductCost(),
                            position,
                            isChecked);
                } else {
                    cartItemListener.onSelectCartItem(-1 * (product.getQuantity() * product.getProductCost()),
                            position,
                            isChecked);

                }
            });

            deleteButton.setOnClickListener(v -> cartItemListener.deleteItem(position));
        }
    }
}
