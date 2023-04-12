

package com.example.myapplication.Adapter;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String email;

    public Usuario(String nombre,String email){
        this.nombre = nombre;
        this.email = email;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

