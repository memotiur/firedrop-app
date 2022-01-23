package com.eudocode.firedrop.adapter;

import static com.eudocode.firedrop.network.RetrofitIntence.IMG_BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eudocode.firedrop.R;
import com.eudocode.firedrop.activity.ProductDetailsActivity;
import com.eudocode.firedrop.model.ProductModel;

import java.util.List;

public class ProductInlineAdapter extends RecyclerView.Adapter<ProductInlineAdapter.MyViewHolder> {

    List<ProductModel> productList;
    Context mContext;

    public ProductInlineAdapter(List<ProductModel> productList, Context context) {
        this.productList = productList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ProductInlineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_inline_item, parent, false);
        return new ProductInlineAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductInlineAdapter.MyViewHolder holder, int position) {
        holder.productName.setText(productList.get(position).getProductName());
        holder.productPrice.setText(productList.get(position).getSellingPrice());
        String image_link = IMG_BASE_URL + productList.get(position).getFeaturedImage();
        Glide.with(mContext)
                .load(image_link)
                .centerCrop()
                .placeholder(R.drawable.product)
                .into(holder.featuredImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProductDetailsActivity.class);
                intent.putExtra("product_data", productList.get(position));
                mContext.startActivity(intent);


               /* AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new ProductDetailsFragment();

                Bundle args = new Bundle();
                args.putSerializable("product_data", productList.get(position));
                //args.putString("YourKey", "YourValue");
                myFragment.setArguments(args);


                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fframe_container, myFragment)
                        .addToBackStack(null)
                        .commit();*/

                Log.d("TAG", "Click: " + position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView featuredImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            featuredImage = itemView.findViewById(R.id.product_image);
        }
    }
}
