package com.eudocode.firedrop.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eudocode.firedrop.R;
import com.eudocode.firedrop.adapter.CategoryAdapter;
import com.eudocode.firedrop.adapter.ProductAdapter;
import com.eudocode.firedrop.adapter.ProductInlineAdapter;
import com.eudocode.firedrop.adapter.SliderAdapter;
import com.eudocode.firedrop.model.CategoryModel;
import com.eudocode.firedrop.model.ProductModel;
import com.eudocode.firedrop.model.ProductResponseModel;
import com.eudocode.firedrop.model.SliderModel;
import com.eudocode.firedrop.network.RetrofitIntence;
import com.eudocode.firedrop.network.ServerApi;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    List<ProductModel> productList;
    List<SliderModel> sliderList;
    List<CategoryModel> categoryList;
    RecyclerView productRecyclerView, categoryRecyclerView, featuredProductRecyclerView;
    ProgressDialog progressdialog;
    SliderView sliderView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_store, container, false);
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        productRecyclerView = view.findViewById(R.id.productRecyclerView);
        featuredProductRecyclerView = view.findViewById(R.id.featuredProductRecyclerView);

        sliderView = view.findViewById(R.id.slider);

        progressdialog = new ProgressDialog(getActivity());
        productList = new ArrayList<>();
        sliderList = new ArrayList<>();
        categoryList = new ArrayList<>();

        getProduct();


        return view;


    }


    private void getProduct() {

        progressdialog.setMessage("Please Wait....");
        progressdialog.setCanceledOnTouchOutside(false);
        progressdialog.show();

        ServerApi serverApi = RetrofitIntence.getRetrofit().create(ServerApi.class);
        Call<ProductResponseModel> call = serverApi.getHomeData("Util.getApiKey()");
        call.enqueue(new Callback<ProductResponseModel>() {
            @Override
            public void onResponse(Call<ProductResponseModel> call, Response<ProductResponseModel> response) {


                // Log.d("TAG", "onResponse: "+call.request().url());

                progressdialog.dismiss();
                productList = response.body().getPopularProducts();
                sliderList = response.body().getSliders();
                categoryList = response.body().getTopCategories();

                //Sliders
                // SliderAdapter sliderAdapter = new SliderAdapter(sliderList, getActivity());
               /* sliderRecyclerView.setAdapter(sliderAdapter);
                sliderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
*/

                // below method is used to set auto cycle direction in left to
                // right direction you can change according to requirement.


                SliderAdapter sliderAdapter = new SliderAdapter(getActivity(), sliderList);
                sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

                // below method is used to
                // setadapter to sliderview.
                sliderView.setSliderAdapter(sliderAdapter);

                // below method is use to set
                // scroll time in seconds.
                sliderView.setScrollTimeInSec(3);

                // to set it scrollable automatically
                // we use below method.
                sliderView.setAutoCycle(true);

                // to start autocycle below method is used.
                sliderView.startAutoCycle();


                //Products
                ProductAdapter adapter = new ProductAdapter(productList, getActivity());
                productRecyclerView.setAdapter(adapter);
                productRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));


                //FeaturedProduct
                ProductInlineAdapter featuredProductInlineAdapter = new ProductInlineAdapter(productList, getActivity());
                featuredProductRecyclerView.setAdapter(featuredProductInlineAdapter);
                featuredProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));


                //Slider
                CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, getActivity());

                categoryRecyclerView.setAdapter(categoryAdapter);
                categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

            }

            @Override
            public void onFailure(Call<ProductResponseModel> call, Throwable t) {
                Log.d("TAG", "onResponse: " + t.getLocalizedMessage());
                Log.d("TAG", "onResponse: " + t.getMessage());
                Log.d("TAG", "onResponse: " + call.request().url());
                progressdialog.dismiss();
            }
        });

    }
}