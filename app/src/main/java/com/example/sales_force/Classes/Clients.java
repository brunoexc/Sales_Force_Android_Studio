package com.example.sales_force.Classes;

public class Clients {


    public int id;
    public String name;
    public String email;
    public String phone;
    public String cpf;
    public String cnpj;
    public String address;
    public String address_num;
    public String district;
    public String uf;
    public String city;
    public String juridica_fisica;
    public String cep;

    public Clients() {}

    @Override
    public String toString() {

        String print = "id - " + String.valueOf(id) + " - " + String.valueOf(name);
        return print;
    }

}
