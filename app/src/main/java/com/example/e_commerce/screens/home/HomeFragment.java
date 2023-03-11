package com.example.e_commerce.screens.home;

import static androidx.navigation.Navigation.findNavController;

import static com.example.e_commerce.screens.home.HomeProductAdapter.CompanionObject.ITEM_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentHomeBinding;
import com.example.e_commerce.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnClickProductItem {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        List<ProductModel> productModelList = new ArrayList<>();
        ProductModel productModel = new ProductModel("Ao thun",
                10000f,
                "con hang",
                "https://ih1.redbubble.net/image.4646407321.9310/ssrco,classic_tee,mens,fafafa:ca443f4786,front_alt,square_product,600x600.jpg",
                "Áo giữ nhiệt cao cấp, chất liệu thun lụa lạnh co dãn 4 chiều");
        for (int i = 0; i < 15; i++) {
            productModelList.add(productModel);
        }


        HomeProductAdapter newProductAdapter = new HomeProductAdapter(productModelList, requireContext());
        binding.newProductRcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        newProductAdapter.onClickProductItem = this;
        binding.newProductRcv.setAdapter(newProductAdapter);

        HomeProductAdapter productAdapter = new HomeProductAdapter(productModelList, requireContext());
        binding.productRcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        productAdapter.onClickProductItem = this;
        binding.productRcv.setAdapter(productAdapter);

        binding.secondProductRcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.secondProductRcv.setAdapter(productAdapter);

        return binding.getRoot();
    }

    @Override
    public void clickProduct(ProductModel productModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ITEM_KEY,productModel);
        findNavController(getView()).navigate(R.id.action_homeFragment_to_productDetailFragment,bundle);
    }
}