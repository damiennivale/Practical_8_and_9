package com.example.practical8;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private EditText ETBgColorCode;
    private EditText ETImageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ETBgColorCode = findViewById(R.id.ETBgColorCode);
        ETImageNumber = findViewById(R.id.ETImageNumber);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String bgColorCode = sharedPref.getString("BgColorCode", "");
        int imageNumber = sharedPref.getInt("ImageNumber", 3);

        ETBgColorCode.setText(bgColorCode);
        ETImageNumber.setText(String.valueOf(imageNumber));

        // Initialize Button inside onCreate
        Button BtnSubmit = findViewById(R.id.BtnSubmitSettings);
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }

    private void saveSettings() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharedPreferences.edit();

        String bgColorCode = ETBgColorCode.getText().toString();
        int imageNumber;

        try {
            imageNumber = Integer.parseInt(ETImageNumber.getText().toString());
        } catch (NumberFormatException e) {
            imageNumber = 3; // Default value
        }

        spEditor.putString("BgColorCode", bgColorCode);
        spEditor.putInt("ImageNumber", imageNumber);
        spEditor.apply();
    }
}
