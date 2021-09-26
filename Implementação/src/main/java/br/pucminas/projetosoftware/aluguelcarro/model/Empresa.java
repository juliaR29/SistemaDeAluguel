package br.pucminas.projetosoftware.aluguelcarro.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Empresa extends Usuario {
    private String razaoSocial;
    private String cnpj;

    public Empresa() {
        super();
        this.razaoSocial = "";
        this.cnpj = "";
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public static Empresa loadFromTextFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

        String[] lines = content.split("\n");

        String id = lines[0];
        String email = lines[1];
        String senha = lines[2];
        String razaoSocial = lines[3];
        String cnpj = lines[4];

        Empresa empresa = new Empresa();
        empresa.setId(id);
        empresa.setEmail(email);
        empresa.setSenha(senha);
        empresa.setRazaoSocial(razaoSocial);
        empresa.setCnpj(cnpj);

        return empresa;

    }

    public void saveToTextFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append("\n");
        sb.append(this.email);
        sb.append("\n");
        sb.append(this.senha);
        sb.append("\n");
        sb.append(this.razaoSocial);
        sb.append("\n");
        sb.append(this.cnpj);
        sb.append("\n");

        Files.writeString(Paths.get(fileName), sb.toString(), StandardCharsets.UTF_8);
    }

}
