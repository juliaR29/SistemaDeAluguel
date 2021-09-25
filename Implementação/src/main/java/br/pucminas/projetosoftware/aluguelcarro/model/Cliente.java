package br.pucminas.projetosoftware.aluguelcarro.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Cliente {
    private String id;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String endereco;
    private String profissao;
    private String entidadeEmpregadora;
    private ArrayList<Double> rendimentosAuferidos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEntidadeEmpregadora() {
        return entidadeEmpregadora;
    }

    public void setEntidadeEmpregadora(String entidadeEmpregadora) {
        this.entidadeEmpregadora = entidadeEmpregadora;
    }

    public ArrayList<Double> getRendimentosAuferidos() {
        return rendimentosAuferidos;
    }

    public void setRendimentosAuferidos(ArrayList<Double> rendimentosAuferidos) {
        this.rendimentosAuferidos = rendimentosAuferidos;
    }

    public Cliente(String id, String nome, String cpf, String rg, String email, String endereco, String profissao,
            String entidadeEmpregadora, ArrayList<Double> rendimentosAuferidos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.endereco = endereco;
        this.profissao = profissao;
        this.entidadeEmpregadora = entidadeEmpregadora;
        this.rendimentosAuferidos = rendimentosAuferidos;
    }

    public Cliente() {
        this.id = "";
        this.nome = "";
        this.cpf = "";
        this.rg = "";
        this.email = "";
        this.endereco = "";
        this.profissao = "";
        this.entidadeEmpregadora = "";
        this.rendimentosAuferidos = new ArrayList<>();
    }

    public static Cliente loadFromTextFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

        String[] lines = content.split("\n");

        ArrayList<Double> rendimentosAuferidos = new ArrayList<Double>();
        String[] rendimentos = lines[8].split(";");
        for (String rendimento : rendimentos) {
            rendimentosAuferidos.add(Double.parseDouble(rendimento));
        }

        String id = lines[0];
        String nome = lines[1];
        String cpf = lines[2];
        String rg = lines[3];
        String email = lines[4];
        String endereco = lines[5];
        String profissao = lines[6];
        String entidadeEmpregadora = lines[7];

        return new Cliente(id, nome, cpf, rg, email, endereco, profissao, entidadeEmpregadora, rendimentosAuferidos);

    }

    public void saveToTextFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append("\n");
        sb.append(this.nome);
        sb.append("\n");
        sb.append(this.cpf);
        sb.append("\n");
        sb.append(this.rg);
        sb.append("\n");
        sb.append(this.email);
        sb.append("\n");
        sb.append(this.endereco);
        sb.append("\n");
        sb.append(this.profissao);
        sb.append("\n");
        sb.append(this.entidadeEmpregadora);
        sb.append("\n");

        for (Double rendimento : this.rendimentosAuferidos) {
            sb.append(rendimento);
            sb.append(";");
        }

        Files.write(Paths.get(fileName), sb.toString().getBytes());
    }

}
