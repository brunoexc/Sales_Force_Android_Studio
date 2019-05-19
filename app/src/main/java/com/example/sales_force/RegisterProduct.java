package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.sales_force.Controladores.ProductController;

import java.util.Random;

public class RegisterProduct extends AppCompatActivity implements View.OnClickListener {

    public int input_id, cad_edi, input_codigo_barras, random_codigo_barra;
    public String input_name, input_um, input_qtd_estoque, input_status, input_custo, input_preco_venda;
    public Boolean valida_obrigatorio;

    public EditText get_name, get_qtd_estoque, get_custo, get_preco_venda;
    public Button troca_botao;
    Spinner combo_ProductUM;

    public ProductController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);
        Intent intent = getIntent();

        //Tratar tela para receber cadastro ou edição de produtos
        cad_edi = intent.getIntExtra("cad_edi", 0);
        troca_botao = findViewById(R.id.but_ProductRegister);
        troca_botao.setOnClickListener(this);
        botaoCadastroEditar();

        //Criar combo box para os tipos de unidade medida dos protudos no cadastro
        combo_ProductUM = (Spinner) findViewById(R.id.combo_ProductUM);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.combo_ProductUM_str, android.R.layout.simple_spinner_item);
        combo_ProductUM.setAdapter(adapter);

        valida_obrigatorio = false;
        input_status = "";
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

    public void Teste(){

        Toast.makeText(this, "Chamei o método TESTE ", Toast.LENGTH_SHORT).show();
    }

    public void botaoCadastroEditar(){

        if(cad_edi == 0){
            troca_botao.setText("Cadastrar");
        }else{
            troca_botao.setText("Salvar");
        }
    }


    public void OnClickbuttonCadastrar (View view){

        random_codigo_barra = new Random().nextInt(9000000) + 1000000; // [0, 9000000] + 1000000 => [1000000, 10000000]

        get_name = (EditText) findViewById(R.id.txt_input_ProductName);
        input_name = get_name.getText().toString();

        input_um = combo_ProductUM.getSelectedItem().toString();

        get_qtd_estoque = (EditText) findViewById(R.id.txt_input_ProductStock);
        input_qtd_estoque = get_qtd_estoque.getText().toString();


        get_custo = (EditText) findViewById(R.id.txt_input_ProductCost);
        input_custo = get_custo.getText().toString();

        get_preco_venda = (EditText) findViewById(R.id.txt_input_ProductSalesPrice);
        input_preco_venda = get_preco_venda.getText().toString();

        input_codigo_barras = random_codigo_barra;

        valida_obrigatorio = verificaObrigatórios(input_name, input_um, input_qtd_estoque, input_preco_venda, input_status, valida_obrigatorio);


        if(valida_obrigatorio == true){
            controller = new ProductController(this);
            controller.RegisterProduct(1, input_name, input_um, input_qtd_estoque, input_status, input_custo, input_preco_venda, input_codigo_barras);
            Toast.makeText(this, "Produto: "+ input_name + " cadastrado", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){

            case R.id.rad_ProductStatus_A:

                if(checked)
                    input_status = "Ativo";
                break;

            case R.id.rad_ProductStatus_I:

                if(checked)
                    input_status = "Inativo";
                break;
        }
    }


    public Boolean verificaObrigatórios(String name, String um, String estoque, String preco_venda, String status, Boolean valida){

        if (name.trim().equals("") || um.trim().equals("") || estoque.trim().equals("") || preco_venda.trim().equals("") || status.trim().equals("") )
        {
            valida = false;
        }
        else{
            if (status.trim().equals("Ativo") || status.trim().equals("Inativo")){
                valida = true;
            }
        }
        return valida;
    }









}
