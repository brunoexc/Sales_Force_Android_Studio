package com.example.sales_force;


import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.telephony.VisualVoicemailService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sales_force.Adaptadores.ProductAdapter;
import com.example.sales_force.Classes.Products;
import com.example.sales_force.Controladores.ProductController;
import com.example.sales_force.Interfaces.IOrder_Product_Selected;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */

//public class OrderProductListFragment extends Fragment implements View.OnClickListener
public class OrderProductListFragment extends Fragment{

    public Button but_add;
    public ListView listView;
    public ProductAdapter adapter;
    public ProductController controller;
    public Products produto;
    public TextView txt_view;
    public RegisterOrder regs;

    public Activity order_actv;


    // Required empty public constructor
    public OrderProductListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_product_list, container, false);


        controller = new ProductController(view.getContext());

        regs = new RegisterOrder();
        order_actv = getActivity();

        //txt_view = regs.findViewById(R.id.regs.selecao_cart_product_hint);



        listView = view.findViewById(R.id.list_ProductsOrder);
        adapter = new ProductAdapter(view.getContext(), controller.lista_produto);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                produto = controller.lista_produto.get(position);

                if (order_actv instanceof IOrder_Product_Selected) {
                    IOrder_Product_Selected listener = (IOrder_Product_Selected)order_actv;
                    listener.productSelected(produto.id);
                }
                //SelecionaLista(produto);
                //id_user = produto.id;
            }
        });

        return view;
    }



//    public void SelecionaLista (Products produto){
//        regs.selecao_cart_product_hint.setText(produto.name);
//    }



    public void onClickAddProduct(){



    }
//
//    @Override
//    public void onClick(View v) {
//
//        if (v.getId() == R.id.but_List_Order_Add){
//            Toast.makeText(getActivity(),"teste", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
}
