package br.pucminas.projetosoftware.aluguelcarro.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Automovel {
    private String id;
    private int ano;
    private String marca;
    private String modelo;
    private String placa;
    private double preco;
 

    public Automovel() {
        this.id = "";
        this.ano = 0;
        this.marca = "";
        this.modelo = "";
        this.placa = "";
        this.preco = 0;
    }

    public Automovel(String id, int ano, String marca, String modelo, String placa, double preco) {
        this.id = id;
        this.ano = ano;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.preco = preco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }


    public static Automovel loadFromTextFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

        String[] lines = content.split("\n");

        String id = lines[0];
        String ano = lines[1];
        String marca = lines[2];
        String modelo = lines[3];
        String placa = lines[4];
        String preco = lines[5];

        Automovel automovel = new Automovel();
        automovel.setId(id);
        automovel.setAno(Integer.parseInt(ano));
        automovel.setMarca(marca);
        automovel.setModelo(modelo);
        automovel.setPlaca(placa);
        automovel.setPreco(Double.parseDouble(preco));

        return automovel;   
}
    

    public void saveToTextFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append("\n");
        sb.append(this.ano);
        sb.append("\n");
        sb.append(this.marca);
        sb.append("\n");
        sb.append(this.modelo);
        sb.append("\n");
        sb.append(this.placa);
        sb.append("\n");
        sb.append(this.preco);

        Files.write(Paths.get(fileName), sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}
     
