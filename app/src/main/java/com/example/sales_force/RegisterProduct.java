package com.example.sales_force;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.sales_force.Controladores.ProductController;

import java.util.Random;

public class RegisterProduct extends AppCompatActivity {


    public int count_id;
    public int sequencial;
    public int input_id;
    public int input_codigo_barras;
    public int random_codigo_barra;

    public String input_name;
    public String input_um;
    public String input_qtd_estoque;
    public String input_status;
    public String input_custo;
    public String input_preco_venda;


    public Boolean valida_obrigatorio;


    public EditText get_name;
    public EditText get_qtd_estoque;
    public EditText get_custo;
    public EditText get_preco_venda;


    public ProductController controller;

    Spinner combo_ProductUM;

    View view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);

        combo_ProductUM = (Spinner) findViewById(R.id.combo_ProductUM);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.combo_ProductUM_str, android.R.layout.simple_spinner_item);
        combo_ProductUM.setAdapter(adapter);

        valida_obrigatorio = false;
        input_status = "";
    }



    public void OnClickbuttonCadastrar (View view){


        incrementNumber();
        input_id = getNumber();

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
            controller.RegisterProduct(input_id, input_name, input_um, input_qtd_estoque, input_status, input_custo, input_preco_venda, input_codigo_barras);
            Toast.makeText(this, "Produto: "+ input_name + " cadastrado", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show();
        }

    }


    public void incrementNumber(){
        count_id = 1;

        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                sequencial = getPreferences(MODE_PRIVATE).getInt("id_product",count_id);
                ++sequencial;
                getPreferences(MODE_PRIVATE).edit().putInt("id_product",sequencial).commit();
    }


    public int getNumber(){
        count_id = getPreferences(MODE_PRIVATE).getInt("id_product",count_id);
        return count_id;
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
