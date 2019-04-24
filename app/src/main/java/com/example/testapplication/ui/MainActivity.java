package com.example.testapplication.ui;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.api.model.service.AccessToken;
import com.example.testapplication.api.model.service.model2.GithubRepo;
import com.example.testapplication.api.model.service.GithubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button goToUpdate,logIn;
    private Toolbar toolbarMain;


    private String clientId="b08401b6df653188c6fe";
    private String clientSecret="cbbb0430e7b4c13cacb567f1f83392f2c7ee3ccd";
    private String redirectUri="http://localhost8080/callback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list_view);
        goToUpdate=findViewById(R.id.button_change_profile);
        toolbarMain=findViewById(R.id.toolbar_home);
        logIn=findViewById(R.id.button_login);


        setSupportActionBar(toolbarMain);


        goToUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,ChangeProfile.class);
                startActivity(intent);

            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when this button is pressed it redirects the user to the GitHub website the the device browser to lo ig
                //if user is already logged , it will redirect back to the app
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize"
                        +"?client_id"+clientId+"&scope=repo&redirect_uri="+redirectUri));
                startActivity(intent);


            }
        });

        showGitHubRepository();

    }
    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();

//at this point the redirect uri is captured and checked whether its the real captured callback uri, if it is it proceeds with
        //getting the access token
        if(uri!=null&& uri.toString().startsWith(redirectUri)){
            String code=uri.getQueryParameter("code");

            Retrofit.Builder builder= new Retrofit.Builder()
                    .baseUrl("https://github.com/")
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit=builder.build();

            GithubClient client=retrofit.create(GithubClient.class);
            Call <AccessToken>accessTokenCall=client.getAccessToken(//access token fetched at this point
                    clientId,
                    clientSecret,
                    code
            );

            //fetches the access token provided by the github server
            accessTokenCall.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }




    //this method fetches  the repositories created by the currently logged in user from GitHub
    private void showGitHubRepository(){

        Retrofit.Builder builder= new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
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
