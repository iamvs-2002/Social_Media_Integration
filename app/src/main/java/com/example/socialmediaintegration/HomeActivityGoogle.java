package com.example.socialmediaintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivityGoogle extends AppCompatActivity {

    TextView name,email;
    ImageView image;
    Button logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_google);

        name = findViewById(R.id.username);
        email = findViewById(R.id.useremail);
        image = findViewById(R.id.userimage);
        logout = findViewById(R.id.logoutbutton);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        String username = user.getDisplayName();
        String useremail = user.getEmail();
        Uri userimage = user.getPhotoUrl();

        name.setText(username);
        email.setText(useremail);
        Glide.with(getApplicationContext()).load(userimage).into(image);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                GoogleSignInOptions gso = new GoogleSignInOptions.
                        Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                        build();

                GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(getApplicationContext(),gso);
                googleSignInClient.signOut();

                Intent x = new Intent(HomeActivityGoogle.this,LoginActivity.class);
                startActivity(x);
                finish();
            }
        });


    }
}