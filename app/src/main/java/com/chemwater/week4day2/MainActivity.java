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



                    String jsonTwo = HttpUrlConnectionHelper.getJsonUsingHttpURLConn();

                    Gson gsonTwo = new Gson();

                    UserResponse userResponseTwo = gsonTwo.fromJson(jsonTwo,UserResponse.class);

                    Log.d("TAG", jsonTwo);


                    Thread.sleep(10000) ;


                    String jsonThree = HttpUrlConnectionHelper.getJsonUsingHttpURLConn();

                    Gson gsonThree = new Gson();

                    UserResponse userResponseThree = gsonThree.fromJson(jsonThree,UserResponse.class);

                    Log.d("TAG", jsonThree);





                    String jsonFour = HttpUrlConnectionHelper.getJsonUsingHttpURLConn();

                    Gson gsonFour = new Gson();

                    UserResponse userResponseFour = gsonFour.fromJson(jsonFour,UserResponse.class);

                    Log.d("TAG", jsonFour);

                    Thread.sleep(1000) ;


                    String jsonFive = HttpUrlConnectionHelper.getJsonUsingHttpURLConn();

                    Gson gsonFive = new Gson();

                    UserResponse userResponseFive = gsonFive.fromJson(jsonFive,UserResponse.class);

                    Log.d("TAG", jsonFive) ;

                    Thread.sleep(1000) ;


                    String jsonSix = HttpUrlConnectionHelper.getJsonUsingHttpURLConn();

                    Gson gsonSix = new Gson();

                    UserResponse userResponseSix = gsonSix.fromJson(jsonSix,UserResponse.class);

                    Log.d("TAG", jsonSix);


                    Thread.sleep(1000) ;

                    String jsonSeven = HttpUrlConnectionHelper.getJsonUsingHttpURLConn();

                    Gson gsonSeven = new Gson();

                    UserResponse userResponseSeven = gsonSeven.fromJson(jsonSeven,UserResponse.class);

                    Log.d("TAG", jsonSeven);

                    Thread.sleep(1000) ;


                    String jsonEight = HttpUrlConnectionHelper.getJsonUsingHttpURLConn();

                    Gson gsonEight = new Gson();

                    UserResponse userResponseEight = gsonEight.fromJson(jsonEight,UserResponse.class);

                    Log.d("TAG", jsonEight);


                    Thread.sleep(1000) ;

                    String jsonNine = HttpUrlConnectionHelper.getJsonUsingHttpURLConn();

                    Gson gsonNine = new Gson();

                    UserResponse userResponseNine = gsonNine.fromJson(jsonNine,UserResponse.class);

                    Log.d("TAG", jsonNine);


                    Thread.sleep(1000) ;

                    String jsonTen = HttpUrlConnectionHelper.getJsonUsingHttpURLConn();

                    Gson gsonTen = new Gson();

                    UserResponse userResponseTen = gsonTen.fromJson(jsonTen,UserResponse.class);

                    Log.d("TAG", jsonTen);
                    Log.d("TAG", userResponseTen.getResults().get(0).getEmail());

                    if(jsonOne != null && jsonTwo != null && jsonThree != null && jsonFour != null && jsonFive != null
                    && jsonSix != null && jsonSeven != null && jsonEight != null && jsonNine != null && jsonTen != null) {
                        ArrayList<UserResponse> listOne = new ArrayList<>() ;
                        listOne.add(0, userResponseOne) ;
                        listOne.add(1, userResponseTwo) ;
                        listOne.add(2, userResponseThree) ;
                        listOne.add(3, userResponseFour) ;
                        listOne.add(4, userResponseFive) ;
                        listOne.add(5, userResponseSix) ;
                        listOne.add(6, userResponseSeven) ;
                        listOne.add(7, userResponseEight) ;
                        listOne.add(8, userResponseNine) ;
                        listOne.add(9, userResponseTen) ;

                        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(listOne) ;
                        recyclerView.setAdapter(myRecyclerViewAdapter) ;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        restCallThread.start();





        RetrofitHelper retrofitHelper = new RetrofitHelper();
        //Start Retrofit in a async way to get our pojo response
        retrofitHelper.getRandomUsers("3.").enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String email = response.body().getResults().get(0).getEmail();
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
