package com.example.vetemovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class activity_pagina6 extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment fragmentInicio, fragmentanimalandia, fragmentanimalitos, fragmentmrfirulays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina6);

        fragmentmrfirulays=new mrfirulays();
        fragmentanimalandia=new animalandia();
        fragmentanimalitos=new animalitos();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, fragmentInicio).commit();
    }

    public void onClick (View view) {
        transaction=getSupportFragmentManager().beginTransaction();
        switch (view.getId())
        {
            case R.id.mr_firulays: transaction.replace(R.id.contenedorFragments,fragmentmrfirulays);
            transaction.addToBackStack(null);
                break;

            case R.id.animalandia: transaction.replace(R.id.contenedorFragments,fragmentanimalandia);
                transaction.addToBackStack(null);
                break;

            case R.id.animalitos: transaction.replace(R.id.contenedorFragments,fragmentanimalitos);
                transaction.addToBackStack(null);
                break;
        }
        transaction.commit();
    }
}