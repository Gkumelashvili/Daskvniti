package com.example.bookapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private String Tag = "MainActivity";
    private FirebaseAuth mAuth;
    private Button btnSignOut;
    private int RC_SIGN_IN = 1 ;
    private  Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        button =(Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openActivity2();
//            }
//        });




        setContentView(R.layout.activity_main);
        signInButton = findViewById(R.id.sign_in_button);
        mAuth = FirebaseAuth.getInstance();
        btnSignOut = findViewById(R.id.sign_out_button);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }

        });
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut();
                Toast.makeText(MainActivity.this,"თქვენ გახვედით",Toast.LENGTH_LONG).show();
                btnSignOut.setVisibility(View.INVISIBLE);
            }
        });
    }


    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RC_SIGN_IN){
        Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
        handleSignInResult(task);
    }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(MainActivity.this,"ზდ ბრატ",Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(acc);

        }
        catch (ApiException e){
            Toast.makeText(MainActivity.this,"თავიდან სცადე",Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(null);

        }
    }
    private  void FirebaseGoogleAuth(GoogleSignInAccount acct){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"ზდ",Toast.LENGTH_LONG).show();
                    FirebaseUser user =mAuth.getCurrentUser();
                    updateUI(user);
                }
                else{
                    Toast.makeText(MainActivity.this,"თავიდან სცადე",Toast.LENGTH_LONG).show();
                    updateUI(null);
                }
            }
        });
    }
    private  void updateUI(FirebaseUser fUser){
        btnSignOut.setVisibility(View.VISIBLE);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null){
            String personName = account.getDisplayName();
            String personGivenName=account.getGivenName();
            String personFamilyName=account.getFamilyName();
            String PersonEmail=account.getEmail();
            String personId=account.getId();
//            String personPhoto=account.getPhotoUrl();

            Toast.makeText(MainActivity.this,personName+PersonEmail,Toast.LENGTH_SHORT).show();
        }

    }
//    public void openActivity2() {
//        Intent intent = new Intent(this, Activity2.class);
//        startActivity(intent);
//    }
}