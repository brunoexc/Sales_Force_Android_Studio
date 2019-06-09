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

import com.example.sales_force.Adaptadores.ClientAdapter;
import com.example.sales_force.Classes.Clients;
import com.example.sales_force.Controladores.ClientController;
import com.example.sales_force.Controladores.UserController;

public class CreateClientActivity extends AppCompatActivity implements View.OnClickListener{

    public int input_id, cad_edi, id_client;
    public String input_name, input_email,input_phone, input_cpf, input_cnpj, input_address, input_address_num, input_district, input_uf, input_city, input_cep, input_juridica_fisica, db_juridica_fisica;
    Boolean valida_obrigatorio,valida_cpf,valida_cnpj;

    ArrayAdapter adapter;

    public EditText get_name, get_email, get_phone, get_cpf, get_cnpj, get_address, get_district, get_city, get_cep, get_address_num;
    public Button troca_botao;
    Spinner combo_ClientUF;
    public RadioButton rad_status;

    public ArrayAdapter get_spinner;
    public int spinner_position;

    Clients db_client;

    public ClientController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);
        Intent intent = getIntent();

        controller = new ClientController(this);

        //Tratar campos obrigatórios
        input_juridica_fisica = "";
        valida_obrigatorio = false;
        valida_cpf = false;
        valida_cnpj = false;

        //Tratar tela para receber cadastro ou edição de cliente
        cad_edi = intent.getIntExtra("cad_edi", 0);
        id_client = intent.getIntExtra("client_id", 0);

        db_client = controller.lista_cliente.get(id_client - 1);

        troca_botao = findViewById(R.id.but_RegisterClient);
        troca_botao.setOnClickListener(this);

        //Criar combo box para os estados no cadastro
        combo_ClientUF = (Spinner) findViewById(R.id.combo_ClientUF);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.combo_clientUF_str, android.R.layout.simple_spinner_item);
        combo_ClientUF.setAdapter(adapter);

        //Pega os dados do Spinner para posicionar qual a unidade de medida lida na edição de produto
        get_spinner = (ArrayAdapter) combo_ClientUF.getAdapter();
        spinner_position = get_spinner.getPosition(db_client.uf);

        //Tratar campos CPF e CNPJ (Inicializar como não modificavel até a pessoa selecionar um radio)
        findViewById(R.id.txt_input_ClientCPF).setFocusable(false);
        findViewById(R.id.txt_input_ClientCNPJ).setFocusable(false);
        get_cpf =  findViewById(R.id.txt_input_ClientCPF);
        get_cnpj = findViewById(R.id.txt_input_ClientCNPJ);

        get_name = findViewById(R.id.txt_input_ClientName);
        get_email = findViewById(R.id.txt_input_ClientEmail);
        get_phone = findViewById(R.id.txt_input_ClientPhone);
        get_address = findViewById(R.id.txt_input_ClientAddress);
        get_address_num = findViewById(R.id.txt_input_ClientAddressNum);
        get_district = findViewById(R.id.txt_input_ClientDistrict);
        get_city = findViewById(R.id.txt_input_ClientCity);
        get_cep = findViewById(R.id.txt_input_ClientCEP);

        botaoCadastroEditar();
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
                AtualizaCliente();
                break;
        }
    }

    public void botaoCadastroEditar(){

        if(cad_edi == 0){
            troca_botao.setText("Cadastrar");
        }else{

            get_name.setText(db_client.name);
            get_email.setText(db_client.email);
            get_phone.setText(db_client.phone);
            get_address.setText(db_client.address);
            get_address_num.setText(db_client.address_num);
            get_district.setText(db_client.district);
            get_city.setText(db_client.city);
            get_cep.setText(db_client.cep);

            //Quando carrego o editar produto, a variavel input_status do formulário recebe valor automático, ou seja,
            //é necessário preencher ela manualmente porque no edição o valor input_status está como null e trava no método verificaObrigatórios()
            db_juridica_fisica = db_client.juridica_fisica;
            AtualizaCPFouCNPJ(db_juridica_fisica);
            input_juridica_fisica = db_juridica_fisica;

            //Seta o spinner do cliente
            combo_ClientUF.setSelection(spinner_position);

            troca_botao.setText("Salvar");
        }
    }

    public void OnClickbuttonCadastrar (View view){

        input_name = get_name.getText().toString();
        input_email = get_email.getText().toString();
        input_phone = get_phone.getText().toString();
        input_address = get_address.getText().toString();
        input_address_num = get_address_num.getText().toString();
        input_district = get_district.getText().toString();
        input_uf = combo_ClientUF.getSelectedItem().toString();
        input_city = get_city.getText().toString();
        input_cep = get_cep.getText().toString();

        if (valida_cpf == true){
            get_cpf = findViewById(R.id.txt_input_ClientCPF);
            input_cpf = get_cpf.getText().toString();
            input_cnpj = "cliente fisico";
        }
        else if (valida_cnpj == true){
            get_cnpj = findViewById(R.id.txt_input_ClientCNPJ);
            input_cnpj = get_cnpj.getText().toString();
            input_cpf = "cliente juridico";
        }
        else if (valida_cpf == false && valida_cnpj == false){
            input_cnpj = "";
            input_cpf = "";
        }

        valida_obrigatorio = verificaObrigatórios(input_name, input_juridica_fisica, input_cpf, input_cnpj, valida_obrigatorio);

        if(valida_obrigatorio == true){
            controller.SaveClient(input_name, input_email, input_phone, input_cpf, input_cnpj, input_address, input_address_num, input_district, input_uf, input_city, input_cep, input_juridica_fisica);
            Toast.makeText(this, "Cliente "+ input_name + " cadastrado", Toast.LENGTH_SHORT).show();

            finish();
        }
        else
        { Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show(); }
    }

    public void AtualizaCliente(){

        input_id = db_client.id;
        input_name = get_name.getText().toString();
        input_email = get_email.getText().toString();
        input_phone = get_phone.getText().toString();
        input_address = get_address.getText().toString();
        input_address_num = get_address_num.getText().toString();
        input_district = get_district.getText().toString();
        input_uf = combo_ClientUF.getSelectedItem().toString();
        input_city = get_city.getText().toString();
        input_cep = get_cep.getText().toString();

        if (valida_cpf == true){
            get_cpf = findViewById(R.id.txt_input_ClientCPF);
            input_cpf = get_cpf.getText().toString();
            input_cnpj = "cliente fisico";
        }
        else if (valida_cnpj == true){
            get_cnpj = findViewById(R.id.txt_input_ClientCNPJ);
            input_cnpj = get_cnpj.getText().toString();
            input_cpf = "cliente juridico";
        }
        else if (valida_cpf == false && valida_cnpj == false){
            input_cnpj = "";
            input_cpf = "";
        }

        valida_obrigatorio = verificaObrigatórios(input_name, input_juridica_fisica, input_cpf, input_cnpj, valida_obrigatorio);

        if (valida_obrigatorio){
            controller.UpdateClient(input_id, input_name, input_email, input_phone, input_cpf, input_cnpj, input_address, input_address_num, input_district, input_uf, input_city, input_cep, input_juridica_fisica);
            Toast.makeText(this, "Cliente Atualizado!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCPFouCNPJ(View view) {

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


    public void AtualizaCPFouCNPJ(String juridica_fisica) {

        switch (juridica_fisica){

            case "Física":

                rad_status = findViewById(R.id.rad_ClientType_F );
                rad_status.setChecked(true);

                get_cpf.setFocusableInTouchMode(true);
                get_cpf.setText(db_client.cpf);
                get_cnpj.getText().clear();
                get_cnpj.setFocusable(false);
                valida_cpf = true;
                valida_cnpj = false;
                input_juridica_fisica = "Física";
                break;

            case "Júridica":

                rad_status = findViewById(R.id.rad_ClientType_J);
                rad_status.setChecked(true);

                get_cnpj.setFocusableInTouchMode(true);
                get_cnpj.setText(db_client.cnpj);
                get_cpf.getText().clear();
                get_cpf.setFocusable(false);
                valida_cpf = false;
                valida_cnpj = true;
                input_juridica_fisica = "Júridica";

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
