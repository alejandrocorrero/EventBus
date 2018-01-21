package com.example.usuario.broadcastejemplo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class MiServicio extends IntentService {

    public static final String EXTRA_MENSAJE = "EXTRA_MENSAJE";
    private static final int DEFAULT_NUMBER = 1;
    public static final String ACTION_FACTORIAL = "com.example.usuario.broadcastejemplo.action.actionfactorial";
    public static final String EXTRA_RESULTADO = "EXTRA_RESULTADO";

    //Añadir nombre del servicio , y no olvidar añadir al manifiesto
    public MiServicio() {
        super("Mi servicio");
    }
        //Asignamos el estado del servicio en su creacion , en este caso no es pegajoso
    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }
        //Metodo que se utiliza para mandar un intent en broadcast a quien lo reciba
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Obtenemos el numero de la creacion del intent
        int numero = intent.getIntExtra(EXTRA_MENSAJE, DEFAULT_NUMBER);
        int resultado = factorial(numero);

        //Creamos un intent de retorno
        Intent retorno = new Intent(ACTION_FACTORIAL);
        retorno.putExtra(EXTRA_MENSAJE, numero);
        retorno.putExtra(EXTRA_RESULTADO, resultado);

        sendBroadcast(retorno);
    }
    //Metodo estatico para crear el servicio desde otra clase.
    public static void start(Context context, int numero) {
        Intent intent = new Intent(context, MiServicio.class);
        intent.putExtra(EXTRA_MENSAJE, numero);
        context.startService(intent);
    }
    //Calculo del factorial
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
