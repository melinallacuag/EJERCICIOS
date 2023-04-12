package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.Adapter.Usuario;

public class DescriptionActivity extends AppCompatActivity {

    TextView txtnombre, txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        txtnombre = findViewById(R.id.titleDescriptionTextView);
        txtemail  = findViewById(R.id.emailDescriptionTextView);
        String nombre = getIntent().getExtras().getString("nombre");
        String email = getIntent().getExtras().getString("email");
        txtnombre.setText(nombre);
        txtemail.setText(email);
    }
}