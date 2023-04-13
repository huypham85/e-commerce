package com.example.e_commerce.screens.home;

import static android.content.ContentValues.TAG;
import static androidx.navigation.Navigation.findNavController;
import static com.example.e_commerce.screens.home.HomeProductAdapter.CompanionObject.ITEM_KEY;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.FragmentHomeBinding;
import com.example.e_commerce.model.ProductModel;
import com.example.e_commerce.network.model.request.SearchProductRequest;
import com.example.e_commerce.network.model.response.ResponseAPI;
import com.example.e_commerce.network.model.response.product.GetProductResponse;
import com.example.e_commerce.network.model.response.product.ProductResponse;
import com.example.e_commerce.network.model.response.product.TypeProductResponse;
import com.example.e_commerce.network.service.ProductService;
import com.example.e_commerce.screens.home.spinner.CustomSpinnerAdapter;

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
    ProductService productService;
    CustomSpinnerAdapter categoryAdapter;
    private FragmentHomeBinding binding;
    private SearchProductRequest currentSearchProductRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        currentSearchProductRequest = new SearchProductRequest(null, "asc", 1, null);
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
        setUpFilterSpinner();


        return binding.getRoot();
    }

    private void setUpFilterSpinner() {
        Call<ResponseAPI<List<TypeProductResponse>>> call = productService.getProductTypes();
        call.enqueue(new Callback<ResponseAPI<List<TypeProductResponse>>>() {

            @Override
            public void onResponse(Call<ResponseAPI<List<TypeProductResponse>>> call, Response<ResponseAPI<List<TypeProductResponse>>> response) {
                if (response.body() != null) {
                    List<TypeProductResponse> types = response.body().getData();
                    ArrayList<TypeProductResponse> typeList = new ArrayList<>(types);
                    categoryAdapter = new CustomSpinnerAdapter(requireContext(), android.R.layout.simple_spinner_item, typeList, typeProductResponse -> {
                        if (typeProductResponse.getId() == 0) {
                            currentSearchProductRequest.setProductTypeName(null);
                            searchProducts(currentSearchProductRequest);
                        } else {
                            currentSearchProductRequest.setProductTypeName(typeProductResponse.getTypeProductName());
                            searchProducts(currentSearchProductRequest);
                        }
                    });
                    typeList.add(0, new TypeProductResponse(0, "Thể loại"));
                    categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.categorySpinner.setAdapter(categoryAdapter);
                    binding.categorySpinner.setSelection(0);
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI<List<TypeProductResponse>>> call, Throwable t) {

            }
        });
        binding.categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "select item");
                categoryAdapter.getTypeSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Giảm dần")) {
                    currentSearchProductRequest.setCostDescription("dsc");
                } else {
                    currentSearchProductRequest.setCostDescription("asc");
                }
                searchProducts(currentSearchProductRequest);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        binding.statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Hết hàng")) {
                    currentSearchProductRequest.setProductStatus(0);
                } else {
                    currentSearchProductRequest.setProductStatus(1);
                }
                searchProducts(currentSearchProductRequest);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void setUpProductRecyclerView() {
        searchProducts(currentSearchProductRequest);
    }

    @Override
    public void clickProduct(ProductModel productModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ITEM_KEY, productModel);
        findNavController(getView()).navigate(R.id.action_homeFragment_to_productDetailFragment, bundle);
    }

    void searchProducts(SearchProductRequest searchProductRequest) {
        Call<ResponseAPI<GetProductResponse>> call = productService.searchProducts(0, 100, searchProductRequest);
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
                        }

                    }
                    HomeProductAdapter productAdapter = new HomeProductAdapter(productModels, requireContext());
                    binding.productRcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                    productAdapter.onClickProductItem = HomeFragment.this;
                    binding.productRcv.setAdapter(productAdapter);

                    binding.secondProductRcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                    binding.secondProductRcv.setAdapter(productAdapter);

                }
            }

            @Override
            public void onFailure(Call<ResponseAPI<GetProductResponse>> call, Throwable t) {
            }
        });
    }
}