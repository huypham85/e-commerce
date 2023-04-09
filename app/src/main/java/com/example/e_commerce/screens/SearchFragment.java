package com.example.e_commerce.screens;

import static com.example.e_commerce.screens.home.HomeProductAdapter.CompanionObject.ITEM_KEY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentSearchBinding;
import com.example.e_commerce.model.ProductModel;
import com.example.e_commerce.network.model.request.SearchProductRequest;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.product.GetProductResponse;
import com.example.e_commerce.network.model.response.product.ProductResponse;
import com.example.e_commerce.network.service.HomeService;
import com.example.e_commerce.screens.home.HomeProductAdapter;
import com.example.e_commerce.screens.home.OnClickProductItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class SearchFragment extends Fragment implements OnClickProductItem {
    @Inject
    HomeService homeService;
    private FragmentSearchBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        setupRecyclerView();
        searchProduct();
        return binding.getRoot();
    }

    private void searchProduct() {
        binding.searchBtn.setOnClickListener(v -> {
            if (!binding.searchEdt.getText().toString().trim().isEmpty()) {
                Call<ResponseAPI<GetProductResponse>> call = homeService.searchProducts(0, 100, new SearchProductRequest(binding.searchEdt.getText().toString().trim(), "asc", null, null));
                call.enqueue(new Callback<ResponseAPI<GetProductResponse>>() {
                    @Override
                    public void onResponse(Call<ResponseAPI<GetProductResponse>> call, Response<ResponseAPI<GetProductResponse>> response) {
                        if (response.body() != null) {
                            List<ProductResponse> products = response.body().getData().getContent();
                            List<ProductModel> productModels = new ArrayList();
                            if (products != null) {
                                for (ProductResponse product : products) {
                                    ProductModel productModel = new ProductModel(product);
                                    productModels.add(productModel);
                                    HomeProductAdapter productAdapter = new HomeProductAdapter(productModels, requireContext());
                                    binding.productRcv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                                    productAdapter.onClickProductItem = SearchFragment.this;
                                    binding.productRcv.setAdapter(productAdapter);

                                }
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAPI<GetProductResponse>> call, Throwable t) {
                    }
                });
            }
        });
    }

    private void setupRecyclerView() {
        Call<ResponseAPI<GetProductResponse>> call = homeService.searchProducts(0, 100, new SearchProductRequest(null, "asc", null, null));
        call.enqueue(new Callback<ResponseAPI<GetProductResponse>>() {
            @Override
            public void onResponse(Call<ResponseAPI<GetProductResponse>> call, Response<ResponseAPI<GetProductResponse>> response) {
                if (response.body() != null) {
                    List<ProductResponse> products = response.body().getData().getContent();
                    List<ProductModel> productModels = new ArrayList();
                    if (products != null) {
                        for (ProductResponse product : products) {
                            ProductModel productModel = new ProductModel(product);
                            productModels.add(productModel);
                            HomeProductAdapter productAdapter = new HomeProductAdapter(productModels, requireContext());
                            binding.productRcv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                            productAdapter.onClickProductItem = SearchFragment.this;
                            binding.productRcv.setAdapter(productAdapter);

                        }
                    } else {
                        // TODO: set empty list for recycler view
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
        Navigation.findNavController(getView()).navigate(R.id.action_searchFragment_to_productDetailFragment, bundle);
    }
}