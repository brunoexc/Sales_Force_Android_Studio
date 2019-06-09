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

import com.example.sales_force.Classes.Products;
import com.example.sales_force.Controladores.ProductController;

import java.util.Random;

public class RegisterProduct extends AppCompatActivity implements View.OnClickListener {

    public int input_id, cad_edi, input_codigo_barras, random_codigo_barra, id_product;
    public String input_name, input_um, input_qtd_estoque, input_status, input_custo, input_preco_venda, db_status;
    public Boolean valida_obrigatorio;

    public EditText get_name, get_qtd_estoque, get_custo, get_preco_venda, get_um, get_status;
    public Button troca_botao;
    public Spinner combo_ProductUM;
    public RadioButton rad_status;

    public ArrayAdapter get_spinner;
    public int spinner_position;

    public ProductController controller;

    Products db_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);
        Intent intent = getIntent();

        controller = new ProductController(this);

        get_name = findViewById(R.id.txt_input_ProductName);
        get_qtd_estoque = findViewById(R.id.txt_input_ProductStock);
        get_custo = findViewById(R.id.txt_input_ProductCost);
        get_preco_venda = findViewById(R.id.txt_input_ProductSalesPrice);

        cad_edi = intent.getIntExtra("cad_edi", 0);
        id_product = intent.getIntExtra("product_id", 0);

        db_product = controller.lista_produto.get(id_product - 1);

        //Tratar tela para receber cadastro ou edição de produtos

        troca_botao = findViewById(R.id.but_ProductRegister);
        troca_botao.setOnClickListener(this);


        //Criar combo box para os tipos de unidade medida dos protudos no cadastro
        combo_ProductUM = findViewById(R.id.combo_ProductUM);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.combo_ProductUM_str, android.R.layout.simple_spinner_item);
        combo_ProductUM.setAdapter(adapter);

        //Pega os dados do Spinner para posicionar qual a unidade de medida lida na edição de produto
        get_spinner = (ArrayAdapter) combo_ProductUM.getAdapter();
        spinner_position = get_spinner.getPosition(db_product.um);


        valida_obrigatorio = false;
        input_status = "";
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
                AtualizaProduto();
                break;
        }
    }

    public void botaoCadastroEditar(){

        if(cad_edi == 0){
            troca_botao.setText("Cadastrar");
        }else{
            get_name.setText(db_product.name);
            get_qtd_estoque.setText(db_product.qtd_estoque);
            get_custo.setText(db_product.custo);
            get_preco_venda.setText(db_product.preco_venda);

            //Quando carrego o editar produto, a variavel input_status do formulário recebe valor automático, ou seja,
            //é necessário preencher ela manualmente porque no edição o valor input_status está como null e trava no método verificaObrigatórios()
            db_status = db_product.status;
            DBStatusCheck(db_status);
            input_status = db_status;

            //Seta o spinner do produto
            combo_ProductUM.setSelection(spinner_position);

            troca_botao.setText("Salvar");
        }
    }


    public void OnClickbuttonCadastrar (View view){

        random_codigo_barra = new Random().nextInt(9000000) + 1000000; // [0, 9000000] + 1000000 => [1000000, 10000000]

        input_name = get_name.getText().toString();
        input_um = combo_ProductUM.getSelectedItem().toString();
        input_qtd_estoque = get_qtd_estoque.getText().toString();
        input_custo = get_custo.getText().toString();
        input_preco_venda = get_preco_venda.getText().toString();
        input_codigo_barras = random_codigo_barra;

        valida_obrigatorio = verificaObrigatórios(input_name, input_um, input_qtd_estoque, input_preco_venda, input_status, valida_obrigatorio);

        if(valida_obrigatorio){

            controller.SaveProduct(input_name, input_um, input_qtd_estoque, input_status, input_custo, input_preco_venda, input_codigo_barras);
            Toast.makeText(this, "Produto: "+ input_name + " cadastrado", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show();
        }
    }


    public void AtualizaProduto(){

        input_id = db_product.id;
        input_name = get_name.getText().toString();
        input_um = combo_ProductUM.getSelectedItem().toString();
        input_qtd_estoque = get_qtd_estoque.getText().toString();
        input_custo = get_custo.getText().toString();
        input_preco_venda = get_preco_venda.getText().toString();

        valida_obrigatorio = verificaObrigatórios(input_name, input_um, input_qtd_estoque, input_preco_venda, input_status, valida_obrigatorio);

        if (valida_obrigatorio){
            controller.UpdateProduct(input_id, input_name, input_um, input_qtd_estoque, input_status, input_custo, input_preco_venda, input_codigo_barras);
            Toast.makeText(this, "Produto Atualizado!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Favor Preencher Campos Obrigatórios(*)", Toast.LENGTH_SHORT).show();
        }
    }

    public void ProductStatusCheck(View view) {

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


    public void DBStatusCheck(String db_status) {

        switch (db_status){

            case "Ativo":
                rad_status = findViewById(R.id.rad_ProductStatus_A);
                rad_status.setChecked(true);
                break;

            case "Inativo":
                rad_status = findViewById(R.id.rad_ProductStatus_I);
                rad_status.setChecked(true);
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
