package com.example.sales_force;

import android.content.DialogInterface;
import android.os.AsyncTask;

import com.example.sales_force.Interfaces.ITarefaCallback;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TarefaDelete extends AsyncTask<String, Void, Integer> {

//    private TextView id_texto;
//    public TarefaPost(TextView id_texto){
//        this.id_texto = id_texto;
//    }

    String ipServidor;
    String portaServidor;
    public DialogInterface.OnClickListener callback;


    @Override
    protected Integer doInBackground(String... params) {
        int retorno = 0;
        HttpURLConnection urlConnection = null;
        BufferedInputStream reader = null;
        try {

            ipServidor = "192.168.0.17";
            portaServidor = "8080";

            URL url = new URL("http://"+ ipServidor +":"+ portaServidor +"/usuarios/" + params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("DELETE");//
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);

            urlConnection.connect();

            retorno = urlConnection.getResponseCode();

        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return retorno;
    }

    @Override
    protected void onPostExecute(Integer code) {
        //id_texto.setText(String.valueOf(s));

//        if(callback != null)
//            callback.retornoCallback(code);

    }
}

