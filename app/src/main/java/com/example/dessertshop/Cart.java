package com.example.dessertshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dessertshop.Common.Common;
import com.example.dessertshop.Database.Database;
import com.example.dessertshop.Model.Order;
import com.example.dessertshop.Model.Request;
import com.example.dessertshop.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalHarga;
    Button btnPlace;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //init
        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalHarga = (TextView)findViewById(R.id.total);
        btnPlace = (Button)findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cart.size() > 0){
                    showAlertDialog();
                }
                else{
                    Toast.makeText(Cart.this, "Empty Cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loadListFood();
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("Dessert Shop");
        alertDialog.setMessage("Nomor Meja Anda : ");

        LayoutInflater inflater = this.getLayoutInflater();
        View order_comment = inflater.inflate(R.layout.order_comment,null);

        final MaterialEditText edtAlamat = (MaterialEditText)order_comment.findViewById(R.id.edtAddress);
        final MaterialEditText edtComment = (MaterialEditText)order_comment.findViewById(R.id.edtComment);
        alertDialog.setView(order_comment);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //create new request
                Request request = new Request(
                        Common.currentUser.getPhone(),
                        Common.currentUser.getName(),
                        edtAlamat.getText().toString(),
                        txtTotalHarga.getText().toString(),
                        "0",
                        edtComment.getText().toString(),
                        cart
                );

                //submit ke firebase dengan menggunakan System.CurrentMilli to key
                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);
                //hapus cart
                new Database(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this, "Terimakasih Atas Pesanan Anda, Mohon Tunggu Sebentar", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void loadListFood() {
        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart,this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        //perhitungan total harga
        int total = 0;
        for (Order order:cart){
            total+=(Integer.parseInt(order.getHarga()))*(Integer.parseInt(order.getQuantity()));
        }
        Locale locale = new Locale("ID", "ID");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalHarga.setText(fmt.format(total));
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals(Common.DELETE)){
            deleteCart(item.getOrder());
        }
        return true;
    }

    private void deleteCart(int position) {
        cart.remove(position); //hapus item dari LIst<order>
        new Database(this).cleanCart(); //delete data dari SQLite
        for (Order item:cart){
            new Database(this).addToCart(item);
        }
        //refresh
        loadListFood();
    }
}
