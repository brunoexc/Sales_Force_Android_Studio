package com.example.sales_force;

import android.content.DialogInterface;
import android.os.AsyncTask;

import com.example.sales_force.Interfaces.ITarefaCallback;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TarefaGet extends AsyncTask<String, Void, Integer> {

//    private TextView id_texto;
//    public TarefaPost(TextView id_texto){
//        this.id_texto = id_texto;
//    }

    String ipServidor;
    String portaServidor;
    public String dados;
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
        reader = null;
        try {

            ipServidor = "192.168.0.17";
            portaServidor = "8080";

            rotaPost(chamada, params[0]);

            url = new URL("http://"+ ipServidor +":"+ portaServidor +"/usuarios/" + params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");//
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);

            urlConnection.connect();

            retorno = urlConnection.getResponseCode();

            if (retorno == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                while ((params[0] = br.readLine()) != null) {
                    sb.append(params[0] + "\n");
                }
                dados = sb.toString();
                br.close();
            }


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



    public void rotaPost(int chamada, String... params) throws IOException {

        switch (chamada){

            case 0:


                break;
            case 1:


                break;

            case 2:



                break;


            case 3:


                break;

        }

    }


}

