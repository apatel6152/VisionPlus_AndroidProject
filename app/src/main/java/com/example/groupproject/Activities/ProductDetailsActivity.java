package com.example.groupproject.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupproject.MainActivity;
import com.example.groupproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView productImage;
    private TextView productTitle, productPrice, productDescription, productRating;
    private int pImg, pPrice;
    Button buyNow;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        productTitle = findViewById(R.id.text_view_productDetails_title);
        productPrice = findViewById(R.id.text_view_productDetails_price);
        productDescription = findViewById(R.id.text_view_productDetails_desc);

        productImage = findViewById(R.id.productDetails_img);

        Intent ip = getIntent();
        String pImage = ip.getExtras().getString("image");
        String pName = ip.getStringExtra("title");
        String pDescription = ip.getStringExtra("description");
        String pPrice = ip.getExtras().getString("price");


        Picasso.with(ProductDetailsActivity.this).load(pImage).into(productImage);
        productTitle.setText(pName);
        productDescription.setText(pDescription);
        productPrice.setText(pPrice);


        TextView qtyCount =(TextView) findViewById(R.id.qtycount);
        Button buttonInc= (Button) findViewById(R.id.qtyInc);
        Button buttonDec= (Button) findViewById(R.id.qtyDec);
        Button buyNow = (Button) findViewById(R.id.btnBuyNow);

        ImageView btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent ik = new Intent(ProductDetailsActivity.this, LoginRegister.class);
                startActivity(ik);
            }
        });


        buttonInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                qtyCount.setText(String.valueOf(count));
                ip.putExtra("qtyCount", count);
            }
        });

        buttonDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if(count < 0) {
                    count = 0;
                }
                qtyCount.setText(String.valueOf(count));
                ip.putExtra("qtyCount", count);
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(ProductDetailsActivity.this, CheckoutActivity.class);
                intent.putExtra("title",pName);
                intent.putExtra("description",pDescription);
                intent.putExtra("price",pPrice);
                intent.putExtra("image",pImage);
                intent.putExtra("qtyCount", count);
                startActivity(intent);
            }
        });
    }
}
