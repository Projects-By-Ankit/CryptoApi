package com.example.cryptoapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {
    String IDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView ids = findViewById(R.id.ids);
        Intent intent = getIntent();
        IDS = intent.getStringExtra(PostAdapter.MSG);
        ids.setText(IDS);
        IDS = IDS.toLowerCase();
        Function();


    }

    public void Function() {
        TextView coinrank, high24, low24, ATH, MarketCap, MarketVol, Current_Price, dname;
        ImageView cimg;

        coinrank = findViewById(R.id.coinrank);
        high24 = findViewById(R.id.High_24);
        low24 = findViewById(R.id.Low_24);
        ATH = findViewById(R.id.ATH);
        MarketCap = findViewById(R.id.Market_Cap);
        MarketVol = findViewById(R.id.Total_Vol);
        Current_Price = findViewById(R.id.Current_Price);
        dname = findViewById(R.id.dname);
        cimg = findViewById(R.id.cimg);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.coingecko.com/api/v3/coins/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<List<Post>> call = jsonPlaceholder.getSinglePost("inr", IDS, "market_cap_desc", "false");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DetailsActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Post> postList = response.body();
                Post post = postList.get(0);
                coinrank.setText(String.valueOf(post.getMarket_cap_rank()));
                high24.setText(String.valueOf(post.getHigh_24h()));
                low24.setText(String.valueOf(post.getLow_24h()));
                ATH.setText(String.valueOf(post.getAth()));
                MarketCap.setText(String.valueOf(post.getMarket_cap()));
                MarketVol.setText(String.valueOf(post.getTotal_volume()));
                Current_Price.setText(String.valueOf(post.getCurrent_price()));
                Glide.with(DetailsActivity.this).load(post.getImage()).into(cimg);
                dname.setText(String.valueOf(post.getSymbol()).toUpperCase());

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}