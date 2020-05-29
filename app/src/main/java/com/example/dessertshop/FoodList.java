package com.example.dessertshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dessertshop.Interface.ItemClickListener;
import com.example.dessertshop.Model.Food;
import com.example.dessertshop.ViewHolder.FoodViewHolder;
import com.example.dessertshop.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String categoryId="";
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        //firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Foods");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent here
        if(getIntent() != null){
            categoryId = getIntent().getStringExtra("CategoryId");
        }
        if(!categoryId.isEmpty() && categoryId != null){
            loadListFood(categoryId);
        }
    }

    private void loadListFood(String categoryId) {
        FirebaseRecyclerOptions<Food> options = new FirebaseRecyclerOptions.Builder<Food>().setQuery(foodList.orderByChild("menuId").equalTo(categoryId), Food.class).build(); //its like SELECT * FROM Foods where MenuId = categoryId

        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i, @NonNull Food food) {
                foodViewHolder.food_name.setText(food.getName());
                Picasso.get().load(food.getImage()).into(foodViewHolder.food_image);

                final Food local = food;
                foodViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //untuk menghubungkan ke food detail
                        Intent foodDetail = new Intent(FoodList.this,FoodDetail.class);
                        foodDetail.putExtra("FoodId",adapter.getRef(position).getKey()); //mengirim food id ke food detail
                        startActivity(foodDetail);
                    }
                });

            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
                return new FoodViewHolder(view);
                //return null;
            }
        };
        Log.d("TAG", "Check Category"+categoryId);
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

}
