package id.ac.umn.warehaus;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email, pass;
    private Button signup, login;
    FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("DEBUG", "Test1");
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Log.d("DEBUG", "User Belom Login");
        }
        else{
            Log.d("DEBUG", "User Sudah Login");
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(i);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.user_name);
        pass = findViewById(R.id.user_password);

        mAuth = FirebaseAuth.getInstance();

        signup = findViewById(R.id.btnSignIn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        login = findViewById(R.id.btnLogIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = email.getText().toString();
                String txtPass = pass.getText().toString();

                loginUser(txtEmail, txtPass);
            }
        });

    }

    public void loginUser(String userEmail, String userPass){
        Log.d("DEBUG1", "signIn:" +email);
        mAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("DEBUG", "SignInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    //updateUI(user);
                    Intent i = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(i);
                } else {
                    Log.d("DEBUG", "SignInWithEmail:Failure", task.getException());
                    Toast.makeText(MainActivity.this, "Email / Password salah!!!", Toast.LENGTH_SHORT).show();
                    //UpdateUI(null);
                }
            }
        });
    }
}
