package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sales_force.Controladores.ClientController;
import com.example.sales_force.Controladores.UserController;

public class CreateClientActivity extends AppCompatActivity implements View.OnClickListener{

    public int input_id, cad_edi;
    public String input_name, input_email,input_phone, input_cpf, input_cnpj, input_address, input_district, input_uf, input_city, input_cep, input_juridica_fisica;
    Boolean valida_obrigatorio,valida_cpf,valida_cnpj;

    public EditText get_name, get_email, get_phone, get_cpf, get_cnpj, get_address, get_district, get_city, get_cep;
    public Button troca_botao;
    Spinner combo_ClientUF;

    public ClientController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);
        Intent intent = getIntent();

        //Tratar campos obrigatórios
        input_juridica_fisica = "";
        valida_obrigatorio = false;
        valida_cpf = false;
        valida_cnpj = false;

        //Tratar tela para receber cadastro ou edição de cliente
        cad_edi = intent.getIntExtra("cad_edi", 0);
        troca_botao = findViewById(R.id.but_RegisterClient);
        troca_botao.setOnClickListener(this);
        botaoCadastroEditar();

        //Criar combo box para os estados no cadastro
        combo_ClientUF = (Spinner) findViewById(R.id.combo_ClientUF);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.combo_clientUF_str, android.R.layout.simple_spinner_item);
        combo_ClientUF.setAdapter(adapter);

        //Tratar campos CPF e CNPJ (Inicializar como não modificavel até a pessoa selecionar um radio)
        findViewById(R.id.txt_input_ClientCPF).setFocusable(false);
        findViewById(R.id.txt_input_ClientCNPJ).setFocusable(false);
        get_cpf =  findViewById(R.id.txt_input_ClientCPF);
        get_cnpj = findViewById(R.id.txt_input_ClientCNPJ);
    }

    @Override
    public void onClick(View v) {
        switch (cad_edi){
            //cad_edi = 0 (Significa que estou querendo cadastrar)
            case 0:
                OnClickbuttonCadastrar(v);
                break;

            //cad_edi = 1 (Significa que estou querendo editar um cadastro)
            case 1:
                Teste();
                break;
        }
    }

    public void botaoCadastroEditar(){

        if(cad_edi == 0){
            troca_botao.setText("Cadastrar");
        }else{
            troca_botao.setText("Salvar");
        }
    }

    public void Teste(){

        Toast.makeText(this, "Chamei o método TESTE ", Toast.LENGTH_SHORT).show();
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
            input_cnpj = "cliente fisico";
        }
        else if (valida_cnpj == true){
            get_cnpj = (EditText) findViewById(R.id.txt_input_ClientCNPJ);
            input_cnpj = get_cnpj.getText().toString();
            input_cpf = "cliente juridico";
        }
        else if (valida_cpf == false && valida_cnpj == false){
            input_cnpj = "";
            input_cpf = "";
        }

        valida_obrigatorio = verificaObrigatórios(input_name, input_juridica_fisica, input_cpf, input_cnpj, valida_obrigatorio);

        if(valida_obrigatorio == true){
            controller = new ClientController(this);
            controller.SaveClient(input_name, input_email, input_phone, input_cpf, input_cnpj, input_address, input_district, input_uf, input_city, input_cep, input_juridica_fisica );
            Toast.makeText(this, "Cliente "+ input_name + " cadastrado", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        { Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show(); }
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
