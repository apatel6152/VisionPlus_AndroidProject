package com.example.groupproject.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.groupproject.Adapters.ProductAdapter;
import com.example.groupproject.R;
import com.example.groupproject.models.productmodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductsFragments extends Fragment {

    private RecyclerView recyclerView;
    ProductAdapter adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mbase;


    public ProductsFragments() {
    }

    public static ProductsFragments newInstance() {

        ProductsFragments allProductFragment = new ProductsFragments();
        return allProductFragment;
    }

   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_products_fragments,container,false);

        recyclerView = view.findViewById(R.id.ProductsRecyclerView);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));

        mbase = database.getReference();

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<productmodel> options
                = new FirebaseRecyclerOptions.Builder<productmodel>()
                .setQuery(mbase, productmodel.class)
                .build();

        adapter = new ProductAdapter(options, getContext().getApplicationContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext().getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        adapter.notifyDataSetChanged();

        return view;
    }



//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//
//        super.onActivityCreated(savedInstanceState);
//
//    }
//    private void initRecyclerView(View view) {
//        RecyclerView recyclerView = view.findViewById(R.id.ProductsRecyclerView);
//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setItemViewCacheSize(50);
//        ProductAdapter adapter = new ProductAdapter(myArrayList, this);
//        recyclerView.setAdapter(adapter);
//    }

//    private void buildListData() {
//
//        // reference for below description is taken from :   " Lenscart App"
//        myArrayList.add(new productmodel(R.drawable.john1, "John Jacobs",55, "Black Rimless Rectangle", 4));
//        myArrayList.add(new productmodel(R.drawable.vincent1, "Vincent Chase",30, "Dual Color Full Rim Ractangle",5));
//        myArrayList.add(new productmodel(R.drawable.titan1, "Titans+",25, "Black Full Rim Cat Eye", 5));
//        myArrayList.add(new productmodel(R.drawable.nike, "Nike",28, "Transparent Full Rim Wayfarer", 5));
//        myArrayList.add(new productmodel(R.drawable.tommy, "Tommy Hilfier",25, "Matte Dark Blue Full Rim Rectangle", 4));
//        myArrayList.add(new productmodel(R.drawable.vincent2, "Vincent Chase",50, "Black Full Rim Square", 5));
//        myArrayList.add(new productmodel(R.drawable.vincent3, "Vincent Chase",35, "Blue Full Rim Geometric", 4));
//        myArrayList.add(new productmodel(R.drawable.john2, "John Jacobs",28, "Gunmetal Black Full Rim Hexagonal", 4));
//        myArrayList.add(new productmodel(R.drawable.vincent4, "Vincent Chase",32, "Black Full Rim Rectangle", 5));
//        myArrayList.add(new productmodel(R.drawable.john3, "John Jacobs",39, "Silver Full Rim Aviator", 5));
//
//    }
//
//    @Override
//    public void onItemClick(productmodel dataModel) {
//
//        Fragment fragment = ProductDetailsFragment.newInstance(dataModel.getImage(),dataModel.getTitle(), dataModel.getPrice(),
//                dataModel.getDescription());
//
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        // transaction.replace(R.id.frame_container, fragment, "detail_fragment");
//
//        transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("all_fragment"));
//        transaction.add(R.id.products_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

}