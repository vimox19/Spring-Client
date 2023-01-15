package com.genuinecoder.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.genuinecoder.springclient.adapter.CommandAdapter;
import com.genuinecoder.springclient.model.Command;
import com.genuinecoder.springclient.reotrfit.CommandApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommandListActivity extends AppCompatActivity {

  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_command_list);

    recyclerView = findViewById(R.id.commandList_recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    FloatingActionButton floatingActionButton = findViewById(R.id.commandList_fab);
    floatingActionButton.setOnClickListener(view -> {
      Intent intent = new Intent(this, CommandForm.class);
      startActivity(intent);
    });
  }

  private void loadEmployees() {
    RetrofitService retrofitService = new RetrofitService();
    CommandApi employeeApi = retrofitService.getRetrofit().create(CommandApi.class);
    employeeApi.getAllCommands()
        .enqueue(new Callback<List<Command>>() {
          @Override
          public void onResponse(Call<List<Command>> call, Response<List<Command>> response) {
            populateListView(response.body());
          }

          @Override
          public void onFailure(Call<List<Command>> call, Throwable t) {
            Toast.makeText(CommandListActivity.this, "Failed to load commands", Toast.LENGTH_SHORT).show();
          }
        });
  }

  private void populateListView(List<Command> employeeList) {
    CommandAdapter employeeAdapter = new CommandAdapter(employeeList);
    recyclerView.setAdapter(employeeAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();
    loadEmployees();
  }
}