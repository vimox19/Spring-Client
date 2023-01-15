package com.genuinecoder.springclient.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.genuinecoder.springclient.R;

public class CommandHolder extends RecyclerView.ViewHolder {

  TextView name, description, prix;

  public CommandHolder(@NonNull View itemView) {
    super(itemView);
    name = itemView.findViewById(R.id.commandListItem_name);
    description = itemView.findViewById(R.id.commandListItem_description);
    prix = itemView.findViewById(R.id.commandListItem_prix);
  }
}
