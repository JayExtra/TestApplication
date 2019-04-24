package com.example.testapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.api.model.service.UserClient;
import com.example.testapplication.api.model.service.model2.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangeProfile extends AppCompatActivity {

    private EditText editName, editTopic, editEmail, editAge;
    private Button update;
    private Toolbar toolbarUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        editAge=findViewById(R.id.age);
        editName=findViewById(R.id.name);
        editTopic=findViewById(R.id.topics);
        editEmail=findViewById(R.id.email);
        update=findViewById(R.id.button_update);
        toolbarUpdate=findViewById(R.id.toolbar_update);

        setSupportActionBar(toolbarUpdate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    //get details from the text fields and convert them into object

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a= editAge.getText().toString();
                int age2= Integer.parseInt(a);
                User user=new User(
                        editName.getText().toString(),
                        editEmail.getText().toString(),
                        age2,
                        editTopic.getText().toString().split(",")
                );

                //send user object with details to server

                sendNetworkRequest(user);

            }
        });





    }

    //method for communicating with server
    private void sendNetworkRequest(User user){
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https:api.github.com")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=builder.build();

        //get client and call object for the request

        UserClient client =retrofit.create(UserClient.class);

        Call<User> call=client.createAccount(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(ChangeProfile.this,"Success USER-ID:"+response.body().getId(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ChangeProfile.this,"error:(",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
