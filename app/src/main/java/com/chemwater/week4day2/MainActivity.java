package com.chemwater.week4day2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import com.chemwater.week4day2.model.users.Result;
import com.chemwater.week4day2.model.users.UserResponse;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Bind RecyclerView
        recyclerView = findViewById(R.id.rvRecyclerView);

        //Recycler View needs 2 items
        //  1. Layout Manager (Can be customized, but we generally us a default
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //  2.RecyclerView adapter (We write this)
        //myRecyclerViewAdapter = new MyRecyclerViewAdapter(generateListOfUsers());
        //recyclerView.setAdapter(myRecyclerViewAdapter);


        Thread restCallThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String jsonOne = HttpUrlConnectionHelper.getJsonUsingHttpURLConn() ;

                    Gson gsonOne = new Gson() ;

                    UserResponse userResponseOne = gsonOne.fromJson(jsonOne, UserResponse.class) ;

                    Log.d("TAG", jsonOne) ;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        restCallThread.start();





        RetrofitHelper retrofitHelper = new RetrofitHelper();
        //Start Retrofit in a async way to get our pojo response
        retrofitHelper.getRandomUsers("3").enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String email = response.body().getResults().get(0).getEmail();
                Log.d("TAG_Call", "onResponse: " + call.request().url().toString()) ;
                Log.d("TAG_RETROFIT", "onResponse: " + email);
                //String email = response.body().getResults().get(0).getEmail();
                //Log.d("TAG_RETROFIT", "onResponse: " + email);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }



    /*
    private ArrayList<UserResponse> generateListOfUsers() {
        ArrayList<UserResponse> userResponseArrayList = new ArrayList<>() ;
        userResponseArrayList.add(0, new UserResponse());
        userResponseArrayList.add(1, new UserResponse()) ;


        return userResponseArrayList ;
    }*/
}
