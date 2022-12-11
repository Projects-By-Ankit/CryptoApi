package com.example.cryptoapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    List<Post> postList;
    Context context;
    String ids;
    public static final String MSG = "msg";

    public PostAdapter(Context context, List<Post> postList) {
        this.postList = postList;
        this.context = context;
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.name.setText(post.getSymbol().toUpperCase());
        holder.price.setText("₹" + String.valueOf(post.getCurrent_price()));
        holder.ids.setText(post.getId().toUpperCase());
        Glide.with(context.getApplicationContext()).load(post.getImage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, price, ids;
        ImageView img;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.symbol);
            price = itemView.findViewById(R.id.price);
            ids = itemView.findViewById(R.id.cid1);
            img = itemView.findViewById(R.id.img);

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailsActivity.class);
            String IDS = ids.getText().toString();
            intent.putExtra(MSG, IDS);
            context.startActivity(intent);
//            Toast.makeText(context.getApplicationContext(), "Name is: " + name.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
