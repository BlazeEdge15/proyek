package id.ac.umn.warehaus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShipmentActivity extends AppCompatActivity {

    Button btnMasuk, btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment);

        btnMasuk = findViewById(R.id.buttonMasuk);
        btnKeluar = findViewById(R.id.buttonKeluar);

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
