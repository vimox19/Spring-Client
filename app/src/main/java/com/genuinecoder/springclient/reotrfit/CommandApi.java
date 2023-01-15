package com.genuinecoder.springclient.reotrfit;

import com.genuinecoder.springclient.model.Command;
import com.genuinecoder.springclient.model.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CommandApi {

  @GET("/command/get-all")
  Call<List<Command>> getAllCommands();

  @POST("/command/save")
  Call<Command> save(@Body Command command);

  @POST("/checkCredits")
  Call<Boolean> CheckCredits(@Body User user);
}
