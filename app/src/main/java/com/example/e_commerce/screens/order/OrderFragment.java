package com.example.e_commerce.screens.order;

import static com.example.e_commerce.screens.cart.CartFragment.CompanionObject.CART_ITEMS;
import static com.example.e_commerce.screens.cart.CartFragment.CompanionObject.TOTAL_PRICE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.databinding.FragmentOrderBinding;
import com.example.e_commerce.network.model.response.cart.CartItem;

import java.util.List;


public class OrderFragment extends Fragment {
    FragmentOrderBinding binding;
    OrderItemAdapter orderItemAdapter;
    List<CartItem> cartItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        if (getArguments() != null) {
            cartItems = getArguments().getParcelableArrayList(CART_ITEMS);
            binding.costValue.setText(String.valueOf(getArguments().getFloat(TOTAL_PRICE)));
            setUpOrderItems();
        }
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = binding.getRoot().findViewById(checkedId);
                String checkedText = checkedRadioButton.getText().toString();
                Toast.makeText(requireContext(), "You selected: " + checkedText, Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    private void setUpOrderItems() {
        orderItemAdapter = new OrderItemAdapter(cartItems, requireContext());
        binding.orderItemsRcv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.orderItemsRcv.setAdapter(orderItemAdapter);
    }
}