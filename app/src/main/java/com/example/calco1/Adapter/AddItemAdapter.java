package com.example.calco1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calco1.Model.AddItemModel;
import com.example.calco1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class AddItemAdapter extends RecyclerView.Adapter<AddItemAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<AddItemModel> addItemList;
    private final FirebaseFirestore db;
    private final FirebaseAuth auth;

    public AddItemAdapter(Context context, ArrayList<AddItemModel> addItemList) {
        this.context = context;
        this.addItemList = addItemList;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public AddItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.food_added_item, parent, false);
        return new AddItemAdapter.MyViewHolder(v);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull AddItemAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.foodName.setText(addItemList.get(position).getFoodName());
        holder.totalCalories.setText(String.valueOf(addItemList.get(position).getTotalCalories()));

        holder.deleteItem.setOnClickListener(v -> {
            String className = context.getClass().getSimpleName();

            switch (className) {
                case "MondayActivity":
                    db.collection("MondayAdd").document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).collection("User").document(addItemList.get(position).getDocumentId()).delete().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            addItemList.remove(addItemList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Produkt usunięty", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Błąd" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case "TuesdayActivity":
                    db.collection("TuesdayAdd").document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).collection("User").document(addItemList.get(position).getDocumentId()).delete().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            addItemList.remove(addItemList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Produkt usunięty", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Błąd" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case "WednesdayActivity":
                    db.collection("WednesdayAdd").document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).collection("User").document(addItemList.get(position).getDocumentId()).delete().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            addItemList.remove(addItemList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Produkt usunięty", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Błąd" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case "ThursdayActivity":
                    db.collection("ThursdayAdd").document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).collection("User").document(addItemList.get(position).getDocumentId()).delete().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            addItemList.remove(addItemList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Produkt usunięty", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Błąd" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case "FridayActivity":
                    db.collection("FridayAdd").document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).collection("User").document(addItemList.get(position).getDocumentId()).delete().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            addItemList.remove(addItemList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Produkt usunięty", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Błąd" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case "SaturdayActivity":
                    db.collection("SaturdayAdd").document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).collection("User").document(addItemList.get(position).getDocumentId()).delete().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            addItemList.remove(addItemList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Produkt usunięty", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Błąd" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case "SundayActivity":
                    db.collection("SundayAdd").document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).collection("User").document(addItemList.get(position).getDocumentId()).delete().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            addItemList.remove(addItemList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Produkt usunięty", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Błąd" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return addItemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView foodName;
        private final TextView totalCalories;
        private final ImageButton deleteItem;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodTextView);
            totalCalories = itemView.findViewById(R.id.kcalTextView);
            deleteItem = itemView.findViewById(R.id.deleteItemButton);
        }
    }
}
