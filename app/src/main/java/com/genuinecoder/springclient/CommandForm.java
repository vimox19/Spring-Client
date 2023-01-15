package com.genuinecoder.springclient;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.genuinecoder.springclient.model.Command;
import com.genuinecoder.springclient.reotrfit.CommandApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommandForm extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initializeComponents();
  }

  private void initializeComponents() {
    TextInputEditText inputEditTextName = findViewById(R.id.form_textFieldName);
    TextInputEditText inputEditBranch = findViewById(R.id.form_textFieldPrix);
    TextInputEditText inputEditLocation = findViewById(R.id.form_textFieldDescription);
    MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

    RetrofitService retrofitService = new RetrofitService();
    CommandApi employeeApi = retrofitService.getRetrofit().create(CommandApi.class);

    buttonSave.setOnClickListener(view -> {
      String name = String.valueOf(inputEditTextName.getText());
      String branch = String.valueOf(inputEditBranch.getText());
      String location = String.valueOf(inputEditLocation.getText());

      Command employee = new Command();
      employee.setName(name);
      employee.setPrix(branch);
      employee.setDescription(location);

      employeeApi.save(employee)
          .enqueue(new Callback<Command>() {
            @Override
            public void onResponse(Call<Command> call, Response<Command> response) {
              Toast.makeText(CommandForm.this, "Save successful!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Command> call, Throwable t) {
              Toast.makeText(CommandForm.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
              Logger.getLogger(CommandForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
            }
          });
    });
  }
}