package com.example.usuario.broadcastejemplo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private Random aleatorio;
    private BroadcastReceiver mReceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.buttonTest);
        //Metodo encargado de qjecutar algo cuando se recive el broadcast
        mReceptor= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int numero=intent.getIntExtra(MiServicio.EXTRA_MENSAJE,0);
                int resultado = intent.getIntExtra(MiServicio.EXTRA_RESULTADO,0);
                if(numero>0&&resultado>0){
                    Toast.makeText(MainActivity.this, numero+" "+resultado, Toast.LENGTH_SHORT).show();
                }
            }
        };
        aleatorio=new Random();

        //Creacion del servicio
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiServicio.start(MainActivity.this,aleatorio.nextInt(11));
            }
        });
    }
    //Se crea un filtro de intents al crear la aplicacion, asi podemos filtrar por el servicio que creamos2
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(MiServicio.ACTION_FACTORIAL);
        registerReceiver(mReceptor,intentFilter);
    }
    //Se elimina la captacion del filtro al cerrar la app
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceptor);
    }
}
