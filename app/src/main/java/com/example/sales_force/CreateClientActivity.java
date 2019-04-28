package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sales_force.Controladores.ClientController;
import com.example.sales_force.Controladores.UserController;

public class CreateClientActivity extends AppCompatActivity {

    public int input_id;
    public String input_name;
    public String input_email;
    public String input_phone;
    public String input_cpf;
    public String input_cnpj;
    public String input_address;
    public String input_district;
    public String input_uf;
    public String input_city;
    public String input_cep;
    public String input_juridica_fisica;

    Boolean valida_obrigatorio;
    Boolean valida_cpf;
    Boolean valida_cnpj;

    public EditText get_name;
    public EditText get_email;
    public EditText get_phone;
    public EditText get_cpf;
    public EditText get_cnpj;
    public EditText get_address;
    public EditText get_district;
    public EditText get_city;
    public EditText get_cep;

    public ClientController controller;

    Spinner combo_ClientUF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);

        input_juridica_fisica = "";
        valida_obrigatorio = false;
        valida_cpf = false;
        valida_cnpj = false;

        combo_ClientUF = (Spinner) findViewById(R.id.combo_ClientUF);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.combo_clientUF_str, android.R.layout.simple_spinner_item);
        combo_ClientUF.setAdapter(adapter);

        findViewById(R.id.txt_input_ClientCPF).setFocusable(false);
        findViewById(R.id.txt_input_ClientCNPJ).setFocusable(false);

        get_cpf =  findViewById(R.id.txt_input_ClientCPF);
        get_cnpj = findViewById(R.id.txt_input_ClientCNPJ);
    }


    public void OnClickbuttonCadastrar (View view){

        get_name = (EditText) findViewById(R.id.txt_input_ClientName);
        input_name = get_name.getText().toString();

        get_email = (EditText) findViewById(R.id.txt_input_ClientEmail);
        input_email = get_email.getText().toString();

        get_phone = (EditText) findViewById(R.id.txt_input_ClientPhone);
        input_phone = get_phone.getText().toString();

        get_address = (EditText) findViewById(R.id.txt_input_ClientAddress);
        input_address = get_address.getText().toString();

        get_district = (EditText) findViewById(R.id.txt_input_ClientDistrict);
        input_district = get_district.getText().toString();


        input_uf = combo_ClientUF.getSelectedItem().toString();


        get_city = (EditText) findViewById(R.id.txt_input_ClientCity);
        input_city = get_city.getText().toString();


        get_cep = (EditText) findViewById(R.id.txt_input_ClientCEP);
        input_cep = get_cep.getText().toString();

        if (valida_cpf == true){

            get_cpf = (EditText) findViewById(R.id.txt_input_ClientCPF);
            input_cpf = get_cpf.getText().toString();
            input_cnpj = "cliente juridico";

        }
        else if (valida_cnpj == true){

            get_cnpj = (EditText) findViewById(R.id.txt_input_ClientCNPJ);
            input_cnpj = get_cnpj.getText().toString();
            input_cpf = "cliente fisico";
        }
        else if (valida_cpf == false && valida_cnpj == false){
            input_cnpj = "";
            input_cpf = "";
//            Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show();
        }

        valida_obrigatorio = verificaObrigatórios(input_name, input_juridica_fisica, input_cpf, input_cnpj, valida_obrigatorio);

        if(valida_obrigatorio == true){

            controller = new ClientController(this);
            controller.RegisterClient(1, input_name, input_email, input_phone, input_cpf, input_cnpj, input_address, input_district, input_uf, input_city, input_cep, input_juridica_fisica );
            Toast.makeText(this, "Cliente "+ input_name + " cadastrado", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show();
        }

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){

            case R.id.rad_ClientType_F:

                if(checked){
                    get_cpf.setFocusableInTouchMode(true);
                    get_cnpj.getText().clear();
                    get_cnpj.setFocusable(false);
                    valida_cpf = true;
                    valida_cnpj = false;
                    input_juridica_fisica = "Física";
                }
                break;


            case R.id.rad_ClientType_J:

                if(checked){
                    get_cnpj.setFocusableInTouchMode(true);
                    get_cpf.getText().clear();
                    get_cpf.setFocusable(false);
                    valida_cpf = false;
                    valida_cnpj = true;
                    input_juridica_fisica = "Júridica";
                }
                break;
        }

    }



    public Boolean verificaObrigatórios(String name, String juri_fisi, String cpf, String cnpj, Boolean valida){

        if (name.trim().equals("") || juri_fisi.trim().equals("") || cpf.trim().equals("") || cnpj.trim().equals("") )
        {
            valida = false;
        }
        else{
            valida = true;
        }

        return valida;
    }







}
