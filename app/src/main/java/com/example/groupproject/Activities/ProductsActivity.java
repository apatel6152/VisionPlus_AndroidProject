package com.example.groupproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.groupproject.Fragments.ProductsFragments;
import com.example.groupproject.MainActivity;
import com.example.groupproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Fragment fragment  = ProductsFragments.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.products_container, fragment, "all_fragment");
        transaction.commit();

        ImageView btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent ik = new Intent(ProductsActivity.this, LoginRegister.class);
                startActivity(ik);
            }
        });
    }
}