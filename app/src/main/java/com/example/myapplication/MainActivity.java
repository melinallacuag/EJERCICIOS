package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.Card;
import com.example.myapplication.Adapter.CardAdapter;
import com.example.myapplication.Adapter.Usuario;
import com.example.myapplication.Adapter.UsuariosAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  SearchView.OnQueryTextListener{

    SearchView textBuscar;

    RecyclerView recyclerUsuarios;
    TextView txtname;
    UsuariosAdapter usuariosAdapter;
    Spinner dropStatus;

    Chip filtro1,filtro2,filtro3;

    EditText textagre;
    Button agregar;
    ChipGroup chipglobal;
    List<Usuario> usuarioList;

    List<Usuario> listatemporal = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vista = inflater.inflate(R.layout.activity_main, null);
        setContentView(vista);

        inicializarElementos();
        textBuscar   = findViewById(R.id.searchView);
        dropStatus   = findViewById(R.id.dropStatus);

        filtro1 = findViewById(R.id.chip_categoria1);
        filtro2 = findViewById(R.id.chip_categoria2);


        textagre = findViewById(R.id.clipa);
        agregar = findViewById(R.id.agregar);
        chipglobal = findViewById(R.id.chipglobal);

        textBuscar.setOnQueryTextListener(this);

        listatemporal = usuarioList;

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chip chip = new Chip(MainActivity.this);
                chip.setText(textagre.getText().toString());
                chipglobal.addView(chip);

                chip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                     //   filtrarLista(chip.getText().toString());

                        String chips = textagre.getText().toString();

                        chip.setRippleColor(ColorStateList.valueOf(Color.BLUE));
                        Toast.makeText(MainActivity.this, "precionaste el clip" +chips , Toast.LENGTH_SHORT).show();
                    }
                });

                setContentView(vista);
            }
        });



        select();

    }



    private void select(){
        List<Card> cardlist = new ArrayList<>();

        for (int i = 0; i < 1; i++){
            cardlist.add(new Card("Opcion 1"));
            cardlist.add(new Card("Opcion 2"));

        }

        Resources res = getResources();
        CardAdapter card = new CardAdapter(this, R.layout.item, (ArrayList<Card>) cardlist, res);
        dropStatus.setAdapter(card);
    }


    private void inicializarElementos(){
        recyclerUsuarios = findViewById(R.id.recycler);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this));

        usuarioList = new ArrayList<>();

        for (int i = 0; i < 1; i++){
            usuarioList.add(new Usuario("gato","Kalie.com"));
            usuarioList.add(new Usuario("Gato","Humberto.com"));
            usuarioList.add(new Usuario("Perro","Manuel.com"));
            usuarioList.add(new Usuario("Diedo","Humberto.com"));
            usuarioList.add(new Usuario("Dielmes","Manuel.com"));
            usuarioList.add(new Usuario("1","Manuel.com"));
            usuarioList.add(new Usuario("1784 - Diedo","Humberto.com"));
            usuarioList.add(new Usuario("8517 - Dielmes","Manuel.com"));
            usuarioList.add(new Usuario("7854 - Edilsier","Manuel.com"));
        }


      /*  for (int i = 0; i < 1; i++) {
            for (Usuario usuario : usuarioList) {
                usuario.getNombre();
                Toast.makeText(MainActivity.this,"hola " + usuarioList.get(1).getNombre(),Toast.LENGTH_SHORT).show();
            }
        }*/


        usuariosAdapter = new UsuariosAdapter(usuarioList, this, new UsuariosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Usuario item) {
                txtname =  findViewById(R.id.name);
                String textsol = item.getNombre();
                txtname.setText(textsol);
                moveToDescription(item);
            }
        });

        recyclerUsuarios.setAdapter(usuariosAdapter);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this));

    }

    public void moveToDescription(Usuario item){
        Toast.makeText(getApplicationContext(),"Nombre: "+item.getNombre(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,DescriptionActivity.class);
        intent.putExtra("nombre",item.getNombre());
        intent.putExtra("email",item.getEmail());
        startActivity(intent);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        usuariosAdapter.filtrado(s);
        return false;
    }
}