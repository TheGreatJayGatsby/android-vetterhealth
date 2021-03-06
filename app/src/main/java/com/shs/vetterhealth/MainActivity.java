package com.shs.vetterhealth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shs.vetterhealth.blogzone.BlogPost;
import com.shs.vetterhealth.blogzone.PostActivity;
import com.shs.vetterhealth.R;
import com.shs.vetterhealth.blogzone.SinglePostActivity;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.blog_activity_main);





        //Sets to night theme
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("BlogPost");
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser() == null) {
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                    finish();
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);



    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            //noinspection RestrictedApi
            m.setOptionalIconsVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_sleeptrack) {
            startActivity(new Intent(MainActivity.this, com.shs.vetterhealth.sleeptracker.MainActivity.class));
        } else if (id == R.id.action_blog) {
            startActivity(new Intent(MainActivity.this, com.shs.vetterhealth.blogzone.MainActivity.class));
        } else if (id == R.id.action_chatbot) {
            Intent intent = new Intent(MainActivity.this, com.shs.vetterhealth.chatbot.MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_pillnotifier) {
            Intent intent = new Intent(MainActivity.this, com.shs.vetterhealth.pillnotifier.medicine.MedicineActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_add) {
            startActivity(new Intent(MainActivity.this, PostActivity.class));
        } else if (id == R.id.logout) {
            mAuth.signOut();
            Intent logoutIntent = new Intent(MainActivity.this, RegisterActivity.class);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
