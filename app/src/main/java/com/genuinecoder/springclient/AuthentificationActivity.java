package com.genuinecoder.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.genuinecoder.springclient.model.User;
import com.genuinecoder.springclient.reotrfit.CommandApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthentificationActivity extends AppCompatActivity {



    private EditText Username , Password;

    private Button SignInBtn;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        Username = findViewById(R.id.edtSignInUsername);
        Password = findViewById(R.id.edtSignInPassword);

        SignInBtn= findViewById(R.id.btnSignIn);

        SignInBtn.setOnClickListener(view -> {
            try {
                getApiResult();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });

    }



    private void getApiResult() throws IOException, JSONException {


        user.setUsername(Username.getText().toString());
        user.setPassword(Password.getText().toString());

        RetrofitService retrofitService = new RetrofitService();
        CommandApi userapi = retrofitService.getRetrofit().create(CommandApi.class);

        userapi.CheckCredits(user).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast.makeText(AuthentificationActivity.this, "Checking succeeded!", Toast.LENGTH_SHORT).show();

                Boolean isAuth = response.body();

                if(isAuth) {
                    Intent intent = new Intent(AuthentificationActivity.this, CommandListActivity.class); /** Class name here */
                    startActivity(intent);
                } else {
                    Toast.makeText(AuthentificationActivity.this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
                }


                Log.d("---------------Result", String.valueOf(isAuth));
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(AuthentificationActivity.this, "Checking credits failed!!", Toast.LENGTH_SHORT).show();
                Logger.getLogger(AuthentificationActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
        });
    }
}