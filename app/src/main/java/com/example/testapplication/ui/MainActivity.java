package com.example.testapplication.ui;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.api.model.GithubRepo;
import com.example.testapplication.api.model.service.GithubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list_view);


        Retrofit.Builder builder= new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=builder.build();

        GithubClient client=retrofit.create(GithubClient.class);
        Call<List<GithubRepo>> call= client.reposForUser("JayExtra");

        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {

                List<GithubRepo> repos=response.body();
                listView.setAdapter(new GitHubRepoAdapter(MainActivity.this,repos));

            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {

                Toast.makeText(MainActivity.this,"error:(",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
