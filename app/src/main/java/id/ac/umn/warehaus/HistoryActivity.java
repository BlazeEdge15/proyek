package id.ac.umn.warehaus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    /*private SharedPreferenceConfig preferenceConfig;

    HistoryAdapter historyAdapter1, historyAdapter2;
    RecyclerView recyclerView1, recyclerView2;
    List<History> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        historyList = new ArrayList<>();

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setHasFixedSize(true);
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        historyList.add(new History(
                1,
                "Nissan 370Z",
                2
        ));

        historyList.add(new History(
                2,
                "Nissan March",
                1
        ));

        historyList.add(new History(
                3,
                "Toyota Agya",
                3
        ));

        historyAdapter1 = new HistoryAdapter(this, historyList);
        historyAdapter2 = new HistoryAdapter(this, historyList);
        recyclerView1.setAdapter(historyAdapter1);
        recyclerView2.setAdapter(historyAdapter2);
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
            preferenceConfig.writeLoginStatus(false);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        return true;
    }*/
}
