package com.example.e_commerce.screens.home;

import static androidx.navigation.Navigation.findNavController;
import static com.example.e_commerce.screens.home.HomeProductAdapter.CompanionObject.ITEM_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentHomeBinding;
import com.example.e_commerce.model.ProductModel;
import com.example.e_commerce.network.model.request.SearchProductRequest;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.product.GetProductResponse;
import com.example.e_commerce.network.model.response.product.ProductResponse;
import com.example.e_commerce.network.service.HomeService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class HomeFragment extends Fragment implements OnClickProductItem {
    @Inject
    HomeService homeService;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        List<ProductModel> productModelList = new ArrayList<>();
        ProductModel productModel = new ProductModel("Ao thun",
                10000L,
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

        setUpProductRecyclerView();


        return binding.getRoot();
    }

    private void setUpProductRecyclerView() {
        Call<ResponseAPI<GetProductResponse>> call = homeService.searchProducts(0, 100, new SearchProductRequest(null, "asc", null, null));
        call.enqueue(new Callback<ResponseAPI<GetProductResponse>>() {
            @Override
            public void onResponse(Call<ResponseAPI<GetProductResponse>> call, Response<ResponseAPI<GetProductResponse>> response) {
                if (response.body() != null) {
                    List<ProductResponse> products = response.body().getData().getContent();
                    System.out.println(response.body().getData().toString());
                    List<ProductModel> productModels = new ArrayList();
                    if (products != null) {
                        for (ProductResponse product : products) {
                            ProductModel productModel = new ProductModel(product);
                            productModels.add(productModel);
                            HomeProductAdapter productAdapter = new HomeProductAdapter(productModels, requireContext());
                            binding.productRcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                            productAdapter.onClickProductItem = HomeFragment.this;
                            binding.productRcv.setAdapter(productAdapter);

                            binding.secondProductRcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                            binding.secondProductRcv.setAdapter(productAdapter);
                        }
                    }


                }
            }

            @Override
            public void onFailure(Call<ResponseAPI<GetProductResponse>> call, Throwable t) {
            }
        });
    }

    @Override
    public void clickProduct(ProductModel productModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ITEM_KEY, productModel);
        findNavController(getView()).navigate(R.id.action_homeFragment_to_productDetailFragment, bundle);
    }
}