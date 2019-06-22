package com.example.sales_force;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.sales_force.Interfaces.ITarefaCallback;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TarefaPost extends AsyncTask<String, Void, Integer> {

//    private TextView id_texto;
//    public TarefaPost(TextView id_texto){
//        this.id_texto = id_texto;
//    }

    public String ipServidor;
    public String portaServidor;
    public int chamada, retorno;
    public SincronizaDados callback;
    URL url;
    HttpURLConnection urlConnection;
    BufferedInputStream reader;
    PrintStream printStream;




    @Override
    protected Integer doInBackground(String... params) {
        retorno = 0;
        urlConnection = null;
        BufferedInputStream reader = null;
        try {

            ipServidor = "192.168.0.17";
            portaServidor = "8080";

//            Intent intent = new Intent();
//            chamada = intent.getIntExtra("chamada", 0);

            rotaPost(chamada, params[0]);

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

    public void rotaPost(int chamada, String... params) throws IOException {

        switch (chamada){

            case 0:
                url = new URL("http://"+ ipServidor +":"+ portaServidor +"/usuarios");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");//
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);

                PrintStream printStream = new PrintStream(urlConnection.getOutputStream());
                printStream.println(params[0]);

                urlConnection.connect();
                retorno = urlConnection.getResponseCode();

                break;
            case 1:

                url = new URL("http://"+ ipServidor +":"+ portaServidor +"/clientes");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");//
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);

                printStream = new PrintStream(urlConnection.getOutputStream());
                printStream.println(params[0]);

                urlConnection.connect();

                retorno = urlConnection.getResponseCode();

                break;

            case 2:

                url = new URL("http://"+ ipServidor +":"+ portaServidor +"/produtos");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");//
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);

                printStream = new PrintStream(urlConnection.getOutputStream());
                printStream.println(params[0]);

                urlConnection.connect();

                retorno = urlConnection.getResponseCode();

                break;


            case 3:

                url = new URL("http://"+ ipServidor +":"+ portaServidor +"/pedidos");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");//
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);

                printStream = new PrintStream(urlConnection.getOutputStream());
                printStream.println(params[0]);

                urlConnection.connect();

                retorno = urlConnection.getResponseCode();

                break;

        }

    }

    @Override
    protected void onPostExecute(Integer code) {
        //id_texto.setText(String.valueOf(s));

        if(callback != null)
            callback.retornoCallback(code);

    }
}

