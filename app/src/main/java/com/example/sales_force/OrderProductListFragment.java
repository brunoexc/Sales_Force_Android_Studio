package com.example.sales_force;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.telephony.VisualVoicemailService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sales_force.Adaptadores.ProductAdapter;
import com.example.sales_force.Classes.Products;
import com.example.sales_force.Controladores.ProductController;


/**
 * A simple {@link Fragment} subclass.
 */

//public class OrderProductListFragment extends Fragment implements View.OnClickListener
public class OrderProductListFragment extends Fragment{

    public Button but_add;
    public ListView listView;
    public ProductAdapter adapter;
    public ProductController controller;


    // Required empty public constructor
    public OrderProductListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_product_list, container, false);


        controller = new ProductController(view.getContext());

        listView = view.findViewById(R.id.list_ProductsOrder);
        adapter = new ProductAdapter(view.getContext(), controller.lista_produto);
        listView.setAdapter(adapter);


//        but_add = view.findViewById(R.id.but_List_Order_Add);
//        but_add.setOnClickListener(this);

        return view;
    }

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
