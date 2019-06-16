package com.example.sales_force;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sales_force.Adaptadores.ProductAdapter;
import com.example.sales_force.Classes.Products;
import com.example.sales_force.Controladores.ClientController;
import com.example.sales_force.Controladores.OrderController;
import com.example.sales_force.Controladores.OrderItemController;
import com.example.sales_force.Controladores.ProductController;
import com.example.sales_force.Interfaces.IOrder_Product_Selected;

public class RegisterOrder extends AppCompatActivity implements View.OnClickListener, IOrder_Product_Selected {

    public ProductController p_controller;
    public ClientController c_controller;
    public OrderController o_controller;
    public OrderItemController o_item_controller;
    public Products product;
    public ListView listView;

    public ProductAdapter adapter;
    public TextView selected_user, selecao_cart_product, selecao_cart_product_hint;
    public int id_user, cad_edi, qtd_products, id_select_client;
    public String valida_input, selected_client;
    public Spinner combo_OrderClient;
    public OrderProductListFragment order_p_list;
    public OrderClientListFragment order_c_list;
    public Button troca_botao, troca_botao_add;


    Bundle b_carrinho, b_products;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_order);
        intent = getIntent();

        cad_edi = intent.getIntExtra("cad_edi", 0);

        c_controller = new ClientController(this);
        p_controller = new ProductController(this);
        //o_controller = new OrderController(this);
        //o_item_controller = new OrderItemController(this);

        order_p_list = new OrderProductListFragment();
        order_c_list = new OrderClientListFragment();

        product = new Products();
        product = null;

        b_carrinho = new Bundle();
        b_products = new Bundle();


        //Identifica a chamada da Activity, altera o framelayout para adicionar ao carrinho ou produto
        troca_botao = findViewById(R.id.but_OrderClientCart);
        troca_botao_add = findViewById(R.id.but_OrderAddProduct);
        troca_botao.setOnClickListener(this);
        selecao_cart_product = findViewById(R.id.txt_subtitle_OA_OrderProductSelect);
        selecao_cart_product_hint = findViewById(R.id.txt_input_OrderSelectedName);

        getSupportFragmentManager().beginTransaction().replace( R.id.frame_LOrder, order_p_list).commit();


        combo_OrderClient = findViewById(R.id.combo_OrderClient);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, c_controller.lista_cliente_str);
        combo_OrderClient.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (cad_edi){
            //cad_edi = 0 (Significa que estou  mexendo no carrinho de compras)
            case 0:
                cad_edi = 1;
                troca_botao.setText("Produtos");
                troca_botao_add.setText("-");
                selecao_cart_product.setText("Meu Carrinho");
                selecao_cart_product_hint.setHint("Retirar Produto");
                getSupportFragmentManager().beginTransaction().replace( R.id.frame_LOrder, order_c_list).commit();

                //b_carrinho.put("selecao_txt", selecao_cart_product_hint);

                break;

            //cad_edi = 1 (Significa que estou mexendo na lista de produto)
            case 1:
                cad_edi = 0;
                troca_botao.setText("Carrinho");
                selecao_cart_product.setText("Seleção de Produtos");
                selecao_cart_product_hint.setHint("Adicionar Produto");
                troca_botao_add.setText("+");
                getSupportFragmentManager().beginTransaction().replace( R.id.frame_LOrder, order_p_list).commit();
                break;
        }
    }


    @Override
    public void productSelected(int id) {
        product = p_controller.lista_produto.get(id - 1);
        selecao_cart_product_hint.setText(product.name);
    }


    public void adicionarAoCarrinho(View view){

        final EditText input = new EditText(this);

        if (product != null) {

            valida_input = null;

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//            alertDialogBuilder.setMessage(adapter_m_delete.getItem(1) + product.name + " ?");
            alertDialogBuilder.setMessage("Quantas unidades de " + product.name + " ?");

            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
            input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)});
            alertDialogBuilder.setView(input);

            alertDialogBuilder.setCancelable(false);


            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    valida_input = input.getText().toString();

                    if(valida_input.equals("")) {
                        dialog.cancel();
                        Toast.makeText(RegisterOrder.this, "Nenhuma quantidade selecionada", Toast.LENGTH_SHORT).show();
                    }else {

//                        selected_client = combo_OrderClient.getSelectedItem().toString();
//                        id_select_client = c_controller.getClientByName(selected_client);
//
                        qtd_products = Integer.parseInt(input.getText().toString());
//
//                        if (o_controller.lista_pedidos.size() == 0){
//                            o_item_controller.addItem(product.id, qtd_products, 1);
//                            o_controller.CriarPrimeiroPedido(id_select_client, 1, 1, null, null, null); //Continuar a partir daqui
//                        }{
//                            //o_controller.CadastrarPedido(product.id, qtd_products, o_item_controller.lista_itens.size() + 1);
//                        }




                        Toast.makeText(RegisterOrder.this, qtd_products + "x " + product.name + " adicionado(a) ao carrinho", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(RegisterOrder.this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else{
            Toast.makeText(RegisterOrder.this, "Nenhum Produto Selecionado!", Toast.LENGTH_SHORT).show();
        }


    }


//    public void botaoCadastroEditar(){
//
//        if(cad_edi == 0){
//            troca_botao.setText("Cadastrar");
//        }else{
//            get_user.setText(db_user.user);
//            get_name.setText(db_user.name);
//            get_password.setText(db_user.password);
//
//            troca_botao.setText("Salvar");
//        }
//    }









}
