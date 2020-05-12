package id.ac.umn.warehaus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText edtNama, edtEmail, edtPass, edtCompany, edtPhone;
    Button btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        edtNama = findViewById(R.id.edtText_Name);
        edtEmail = findViewById(R.id.edtText_Email);
        edtPass = findViewById(R.id.edtText_Password);
        edtCompany = findViewById(R.id.edtText_Company);
        edtPhone = findViewById(R.id.edtText_Phone);

        btnRegister = findViewById(R.id.btnSignUp);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edtEmail.getText().toString().trim();
                final String password = edtPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            String nama = edtNama.getText().toString().trim();
                            String company = edtNama.getText().toString().trim();
                            String phone = edtNama.getText().toString().trim();

                            FirebaseUser  currentUser = FirebaseAuth.getInstance().getCurrentUser();

                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            DatabaseReference dbRef = database.getReference();
                            String folder = currentUser.getUid();

                            dbRef = database.getReference(folder + "/User/Name");
                            dbRef.setValue(nama);

                            dbRef = database.getReference(folder + "/User/Email");
                            dbRef.setValue(email);

                            dbRef = database.getReference(folder + "/User/Password");
                            dbRef.setValue(password);

                            dbRef = database.getReference(folder + "/User/Company");
                            dbRef.setValue(company);

                            dbRef = database.getReference(folder + "/User/Phone");
                            dbRef.setValue(phone);

                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            }
        });

    }
}
