package com.example.groupproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.groupproject.Activities.CheckoutActivity;
import com.example.groupproject.Adapters.ProductAdapter;
import com.example.groupproject.models.productmodel;
//import com.example.groupproject.Activities.OrderSummary;

import com.example.groupproject.R;


import java.util.ArrayList;

public class ProductDetailsFragment extends Fragment{

    private Button buyNowbtn;

    private static int qty;
    private int stringVal;
    TextView qtyText;

    private CardView btnAdd, btnSub;



//    private static final String TITLE = "title";
//    private static final int PRICE = 0;
    private static final int IMAGE = 0;

    private TextView textviewDesc;

    private String title, descrPara;
    private int price;
    private int image;

    private String mmm = "";

    private ArrayList<productmodel> arrayListFrag = new ArrayList<productmodel>();

    public ProductDetailsFragment() {

    }

    public static ProductDetailsFragment newInstance(int image, String title, int price, String descPara) {

        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("price", price);
        args.putInt(String.valueOf(IMAGE), image);
        args.putString("desc", descPara);
        productDetailsFragment.setArguments(args);
        return productDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            price = getArguments().getInt("price");
            image = getArguments().getInt(String.valueOf(IMAGE));
            descrPara = getArguments().getString("desc");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_products_details, container, false);

        TextView titleTxt = view.findViewById(R.id.text_view_productDetails_title);
        TextView priceTxt = view.findViewById(R.id.text_view_productDetails_price);
        ImageView imageView = view.findViewById(R.id.productDetails_img);
        textviewDesc = view.findViewById(R.id.text_view_productDetails_desc);
        qtyText = view.findViewById(R.id.text_view_productDetails_qty);

        btnAdd = view.findViewById(R.id.btnIncQty);
        btnSub = view.findViewById(R.id.btnDecQty);

        buyNowbtn = view.findViewById(R.id.btnBuyNow);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for(int i=0;i<1;i++){

                qty = Integer.parseInt(qtyText.getText().toString());
                qty++;
                stringVal = qty;
                qtyText.setText(String.valueOf(stringVal));
//                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for(int j=0;j<1;j++){

                qty = Integer.parseInt(qtyText.getText().toString());
                if(qty < 1) {
                    qty = 1;
                }
                qty--;
                stringVal = qty;
                qtyText.setText(String.valueOf(stringVal));
//                }

            }
        });

        titleTxt.setText(title);
        priceTxt.setText(String.valueOf(price));
        imageView.setImageDrawable(getContext().getDrawable(image));
        textviewDesc.setText(descrPara);

        buyNowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // mmm = mParam1;
                Intent i = new Intent(getContext(), CheckoutActivity.class);

                i.putExtra("title", title);
                i.putExtra("qty", stringVal);
                i.putExtra("imageView",image);
                i.putExtra("price", price);
                i.putExtra("description", descrPara);
                getContext().startActivity(i);
//                qtyText.setText("0");
            }
        });
        return view;
    }

    @Override
    public void onPause() {

        super.onPause();
    }
}