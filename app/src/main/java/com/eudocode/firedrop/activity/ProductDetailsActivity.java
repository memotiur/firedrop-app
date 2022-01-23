package com.eudocode.firedrop.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.eudocode.firedrop.R;
import com.eudocode.firedrop.adapter.ImageAdapter;
import com.eudocode.firedrop.database.Cart;
import com.eudocode.firedrop.database.CartDao;
import com.eudocode.firedrop.database.CartDatabase;
import com.eudocode.firedrop.model.ImageModel;
import com.eudocode.firedrop.model.ProductModel;
import com.smarteist.autoimageslider.SliderView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    ProductModel productModel;
    private ActionBar toolbar;
    TextView tvProductName, tvSellingPrice, tvRating, tvDetails;
    Button btnCart;
    LinearLayout lnShare, lnDownload, lnWhatsapp;
    SliderView sliderView;
    List<ImageModel> imageList;

    private CartDatabase cartDatabase;
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        productModel = (ProductModel) intent.getSerializableExtra("product_data");
        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle(productModel.getProductName());

        viewInitialize();
        setData();

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Cart", Toast.LENGTH_SHORT).show();

                tsetFunction();

            }
        });
        lnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Share", Toast.LENGTH_SHORT).show();




                CartDao userDao = cartDatabase.getCartDao();
                List<Cart> product = userDao.getAll();

                Log.d("TAG", "onClick: "+product.size());


            }
        });
        lnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Download", Toast.LENGTH_SHORT).show();
            }
        });
        lnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Whatsapp", Toast.LENGTH_SHORT).show();
            }
        });


        //loadFragment(new ProductDetailsFragment());
        //finish();



    }


    private void tsetFunction() {


        cart = new Cart(1, "et_content.getText().toString()",
                "et_title.getText().toString()");

        // create worker thread to insert data into database

        cartDatabase.getCartDao().insertCart(cart);

    }




    private void setData() {

        tvProductName.setText(productModel.getProductName());
        tvSellingPrice.setText(productModel.getSellingPrice());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvDetails.setText(Html.fromHtml(productModel.getProductDetails(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvDetails.setText(Html.fromHtml(productModel.getProductDetails()));
        }


        imageList = productModel.getImages();
        ImageAdapter sliderAdapter = new ImageAdapter(getApplicationContext(), imageList);
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
    }

    private void viewInitialize() {

        imageList = new ArrayList<>();

        sliderView = findViewById(R.id.slider_view);
        tvProductName = findViewById(R.id.product_name);
        tvSellingPrice = findViewById(R.id.regular_price);
        tvDetails = findViewById(R.id.details);

        btnCart = findViewById(R.id.cart);

        lnShare = findViewById(R.id.share);
        lnDownload = findViewById(R.id.download);
        lnWhatsapp = findViewById(R.id.whatsApp);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fframe_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}