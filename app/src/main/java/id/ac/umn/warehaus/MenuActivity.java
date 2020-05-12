package id.ac.umn.warehaus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;

    ArrayList<Product> productList;

    Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        productList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference();
        String folder = currentUser.getUid();

        ValueEventListener changeListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    long jumlahAnak = dataSnapshot.getChildrenCount();

                    for(DataSnapshot child : dataSnapshot.getChildren()){
                        Product product = child.getValue(Product.class);
                        productList.add(product);
                    }
                    Product product1 = productList.get(1);
                    Log.d("DEBUG", "Item : " + product1.getItem());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        dbRef = database.getReference(folder + "/Barang");
        dbRef.addValueEventListener(changeListener);

        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);

        btnRefresh = findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, MenuActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.History) {
            startActivity(new Intent(this, HistoryActivity.class));
        }

        else if (item.getItemId()==R.id.Home) {
            startActivity(new Intent(this, MenuActivity.class));
        }

        else if (item.getItemId()==R.id.newItem) {
            startActivity(new Intent(this, AddInventoryActivity.class));
        }

        else if (item.getItemId()==R.id.LogOut){
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(i);
        }
        return true;
    }
}
