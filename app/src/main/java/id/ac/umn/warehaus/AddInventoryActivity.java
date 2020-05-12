package id.ac.umn.warehaus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.UUID;

public class AddInventoryActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    /*FirebaseStorage storage;
    StorageReference storageReference;*/

    static  final int REQUEST_IMAGE_CAPTURE = 100;
    private String mCurrentPhotoPath = null;
    private Uri filePath;

    Button btnSimpan, btnPicture;
    EditText edtNamaItem, edtDescription, edtTanggal, edtCost, edtQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);

        edtNamaItem = findViewById(R.id.edtItem);
        edtDescription = findViewById(R.id.edtDesc);
        edtTanggal = findViewById(R.id.edtDate);
        edtCost = findViewById(R.id.edtCostperItem);
        edtQty = findViewById(R.id.edtQuantity);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference dbRef = database.getReference();

        String folder = currentUser.getUid();

        btnPicture = findViewById(R.id.btnPic);

        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btnSimpan = findViewById(R.id.btnSave);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = edtNamaItem.getText().toString();
                String desc = edtDescription.getText().toString();
                String date = edtTanggal.getText().toString();
                String cost = edtCost.getText().toString();
                String QTY = edtQty.getText().toString();

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference dbRef = database.getReference();

                String folder = currentUser.getUid();

                dbRef = database.getReference(folder + "/Barang/" + item + "/Item");
                dbRef.setValue(item);

                dbRef = database.getReference(folder + "/Barang/" + item + "/Desc");
                dbRef.setValue(desc);

                dbRef = database.getReference(folder + "/Barang/" + item + "/Tanggal");
                dbRef.setValue(date);

                dbRef = database.getReference(folder + "/Barang/" + item + "/Cost");
                dbRef.setValue(cost);

                dbRef = database.getReference(folder + "/Barang/" + item + "/QTY");
                dbRef.setValue(QTY);

                startActivity(new Intent(AddInventoryActivity.this, MenuActivity.class));
            }
        });
    }

    public void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, 567);
            Log.d("CAMERA", "TEST1");


//            File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
//            imagesFolder.mkdirs();
//            File image = new File(imagesFolder, "image.jpg");
//            Uri uriSavedImage = Uri.fromFile(image);
//            TakePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 567 && resultCode == RESULT_OK && data != null && data.getData() != null ){
            filePath = data.getData();
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imgThumbnail = (ImageView) findViewById(R.id.imgThumbnail);
            imgThumbnail.setImageBitmap(imageBitmap);
        }
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
            Intent i = new Intent(AddInventoryActivity.this, MainActivity.class);
            startActivity(i);
        }

        return true;
    }
}
