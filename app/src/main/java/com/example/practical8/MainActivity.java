package com.example.practical8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String USER_FILE_NAME = "user_file1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnGallery = findViewById(R.id.BtnViewGallery);
        Button btnSettings = findViewById(R.id.BtnSettings);

        btnGallery.setOnClickListener(v -> {
            Log.d("MainActivity", "Gallery Button Clicked");
            startActivity(new Intent(MainActivity.this, GalleryActivity.class));
        });

        btnSettings.setOnClickListener(v -> {
            Log.d("MainActivity", "Settings Button Clicked");
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });

        Context context = getApplicationContext();
        File UserFile = new File(context.getFilesDir(), USER_FILE_NAME);

        if(UserFile.exists()){
            FragmentContainerView FCVCreateUser = findViewById(R.id.FCVCreateUser);
            FCVCreateUser.setVisibility(View.GONE);
            String username = readUserName();
            TextView TVWelcome = findViewById(R.id.TVWelcome);
            TVWelcome.setText("Welcome " + username);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    protected String readUserName() {
        String FileContent = "";
        FileInputStream FIS = null;
        try {
            FIS = getApplicationContext().openFileInput(USER_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStreamReader inputStreamReader = new InputStreamReader(FIS, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            FileContent = stringBuilder.toString();
        }

        return FileContent;
    }
}
