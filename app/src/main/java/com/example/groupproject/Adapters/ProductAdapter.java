package com.example.groupproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupproject.Activities.CheckoutActivity;
import com.example.groupproject.Activities.ProductDetailsActivity;
import com.example.groupproject.R;
import com.example.groupproject.models.productmodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ProductAdapter extends FirebaseRecyclerAdapter<productmodel, ProductAdapter.ProductViewHolder> {

    private Context mContext;

    public ProductAdapter(@NonNull FirebaseRecyclerOptions<productmodel> options, Context mContext) {
        super(options);
        this.mContext = mContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position, @NonNull productmodel model) {

        holder.title.setText(model.getTitle());

        holder.price.setText(model.getPrice());

        holder.rating.setText(model.getRating());

        Picasso.with(mContext)
                .load(model.getImage())
                .placeholder(R.drawable.john3)
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, ProductDetailsActivity.class);
                i.putExtra("title", model.getTitle());
                i.putExtra("image", model.getImage());
                i.putExtra("description", model.getDescription());
                i.putExtra("price", model.getPrice());
                i.putExtra("rating", model.getRating());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(i);
            }
        });
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        return new ProductAdapter.ProductViewHolder(view);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView title, price, rating;
        ImageView image;
        private CardView cardclickHolder;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.recyclerDataTitleTextView);
            price = itemView.findViewById(R.id.recyclerDataPriceView);
            rating = itemView.findViewById(R.id.rating);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}


