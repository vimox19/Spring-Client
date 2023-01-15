package com.genuinecoder.springclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.model.Command;
import java.util.List;

public class CommandAdapter extends RecyclerView.Adapter<CommandHolder> {

  private List<Command> commandList;

  public CommandAdapter(List<Command> commandList) {
    this.commandList = commandList;
  }

  @NonNull
  @Override
  public CommandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_command_item, parent, false);
    return new CommandHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CommandHolder holder, int position) {
    Command employee = commandList.get(position);
    holder.name.setText(employee.getName());
    holder.description.setText(employee.getDescription());
    holder.prix.setText(employee.getPrix());
  }

  @Override
  public int getItemCount() {
    return commandList.size();
  }
}
