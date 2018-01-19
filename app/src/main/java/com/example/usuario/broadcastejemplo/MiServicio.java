package com.example.usuario.broadcastejemplo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class MiServicio extends IntentService {

    public static final String EXTRA_MENSAJE = "EXTRA_MENSAJE";
    private static final int DEFAULT_NUMBER = 1;
    public static final String ACTION_FACTORIAL ="com.example.usuario.broadcastejemplo.action.actionfactorial" ;
    public static final String EXTRA_RESULTADO ="EXTRA_RESULTADO" ;

    public MiServicio() {
        super("Mi servicio");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int numero = intent.getIntExtra(EXTRA_MENSAJE, DEFAULT_NUMBER);

        int resultado=factorial(numero);
        Intent retorno=new Intent(ACTION_FACTORIAL);
        retorno.putExtra(EXTRA_MENSAJE,numero);
        retorno.putExtra(EXTRA_RESULTADO,resultado);

        sendBroadcast(retorno);
    }

    public static void start(Context context, int numero) {
        Intent intent = new Intent(context, MiServicio.class);
        intent.putExtra(EXTRA_MENSAJE, numero);
        context.startService(intent);
    }

    int factorial(int numero) {
        int resultado = 1;
        for (int i = 2; i <= numero; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resultado *= i;
        }
        return resultado;
    }
}
