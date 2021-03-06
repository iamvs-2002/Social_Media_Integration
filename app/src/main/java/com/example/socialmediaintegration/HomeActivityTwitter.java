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

public class HomeActivityTwitter extends AppCompatActivity {

    TextView name,email;
    ImageView image;
    Button logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_twitter);

        name = findViewById(R.id.usernamet);
        email = findViewById(R.id.useremailt);
        image = findViewById(R.id.userimaget);
        logout = findViewById(R.id.logoutbuttont);

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

                Intent x = new Intent(HomeActivityTwitter.this,LoginActivity.class);
                startActivity(x);
                finish();
            }
        });


    }
}