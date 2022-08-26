package com.example.groupproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupproject.MainActivity;
import com.example.groupproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;

    private Button checkoutBtn;
    private String editStrFirstName, editStrLastName, editStrMobile, editStrAddress, editStrCity, editCardDetails;
    private EditText editFirstName, editLastNAme, editMobileNumber, editAddress, editCity, editCreditCard;

    int totalPrice;
    private String title, productDescription;
    private int quantityCounter;
//    private static final String ARG_TITLE = "hello";
    private String image, price;
//    private int counter;
//    private Button btnLogout ;

    private Button btnCheckout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        spinner = findViewById(R.id.spinnerPayment);

        checkoutBtn = findViewById(R.id.btnCheckout);

        ImageView imageViewOrderDetails = findViewById(R.id.imageOrderView);
        TextView tv = findViewById(R.id.ordersummaryTextview);
        TextView orderPrice =  findViewById(R.id.ordersummaryPrice);

        editFirstName = findViewById(R.id.editName);
        editLastNAme = findViewById(R.id.editLastName);
        editMobileNumber = findViewById(R.id.editMobileNumber);
        editAddress = findViewById(R.id.editMobileAddress);
        editCity = findViewById(R.id.editCity);
        editCreditCard = findViewById(R.id.creditedittext);
        editCreditCard.setVisibility(View.GONE);
        btnCheckout = findViewById(R.id.btnCheckout);

        TextView desc = findViewById(R.id.ordersummaryDesc);
        TextView quantity = findViewById(R.id.ordersummaryQuantity);

        spinner.setOnItemSelectedListener(CheckoutActivity.this);

        Intent ip = getIntent();

        title = ip.getStringExtra("title");
        productDescription = ip.getStringExtra("description");
        quantityCounter = ip.getIntExtra("qtyCount",0);
        image = ip.getExtras().getString("image");
        price = ip.getExtras().getString("price");;

        int p = Integer.parseInt(price);
        totalPrice = quantityCounter * p;

        tv.setText(title);
        desc.setText(productDescription);
        quantity.setText(String.valueOf(quantityCounter));
        orderPrice.setText(String.valueOf(totalPrice));
        Picasso.with(CheckoutActivity.this).load(image).into(imageViewOrderDetails);
//        imageViewOrderDetails.setImageDrawable(getDrawable(image));

        btnCheckOutClick();
        List<String> categories = new ArrayList<String>();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        categories.add("Cash");
        categories.add("Debit/Credit");


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        ImageView btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent ik = new Intent(CheckoutActivity.this, LoginRegister.class);
                startActivity(ik);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase("Debit/Credit"))
        {
            editCreditCard.setVisibility(View.VISIBLE);
        }
        else {
            editCreditCard.setVisibility(View.GONE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void btnCheckOutClick()
    {
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editStrFirstName = editFirstName.getText().toString();
                editStrLastName = editLastNAme.getText().toString();
                editStrMobile = editMobileNumber.getText().toString();
                editStrAddress = editAddress.getText().toString();
                editStrCity = editCity.getText().toString();

                if (editFirstName.length()==0 || editLastNAme.length() == 0 || editMobileNumber.length() == 0
                        || editAddress.length() == 0 || editCity.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Enter Missing Details", Toast.LENGTH_SHORT).show();
                }
                else {

                    final Dialog dialog = new Dialog(CheckoutActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.order_dialog);

                    Button dialogButton = (Button) dialog.findViewById(R.id.confirmdialog);
                    TextView tvtxt = dialog.findViewById(R.id.dialogTexts);
                    tvtxt.setText("Hello: " + editStrFirstName + " , " + " Your order is confirmed");
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent ih = new Intent(CheckoutActivity.this, MainActivity.class);
                            startActivity(ih);
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });
    }
}