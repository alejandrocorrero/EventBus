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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiServicio.start(MainActivity.this,aleatorio.nextInt(11));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(MiServicio.ACTION_FACTORIAL);
        registerReceiver(mReceptor,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceptor);
    }
}
