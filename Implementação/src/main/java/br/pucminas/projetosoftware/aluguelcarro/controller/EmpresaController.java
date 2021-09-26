package br.pucminas.projetosoftware.aluguelcarro.controller;

import java.io.File;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.pucminas.projetosoftware.aluguelcarro.model.*;

@Controller
public class EmpresaController {

    @GetMapping("/empresa")
    public String listEmpresas(Model model) {
        try {
            File folder = new File("db/empresas");
            File[] listOfFiles = folder.listFiles();
            ArrayList<Empresa> empresas = new ArrayList<Empresa>();

            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    Empresa empresa = Empresa.loadFromTextFile(file.getAbsolutePath());
                    empresas.add(empresa);
                }
            }
            model.addAttribute("empresas", empresas);
            model.addAttribute("empresa", new Empresa());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "empresa-list";

    }

    @PostMapping("/empresa")
    public String createEmpresa(@ModelAttribute Empresa empresa) {
        try {
            File folder = new File("db/empresas");
            File[] listOfFiles = folder.listFiles();
            int id = 0;
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    String fileName = file.getName();
                    String idString = fileName.substring(0, fileName.indexOf("."));
                    int idFile = Integer.parseInt(idString);
                    if (idFile > id) {
                        id = idFile;
                    }
                }
            }
            id++;
            String idString = String.valueOf(id);
            empresa.setId(idString);
            empresa.saveToTextFile("db/empresas/" + empresa.getId() + ".txt");
            return "redirect:/empresa/" + empresa.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/empresa";
        }
    }

    @DeleteMapping("/empresa/{id}")
    public String deleteEmpresaById(@PathVariable(value = "id") String id) {
        try {
            File file = new File("db/empresas/" + id + ".txt");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/empresa";
    }

    @GetMapping("/empresa/{id}")
    public String getEmpresaById(@PathVariable(value = "id") String id, Model model) {
        try {
            Empresa empresa = Empresa.loadFromTextFile("db/empresas/" + id + ".txt");

            model.addAttribute("empresa", empresa);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/empresa-details";
    }

    @PutMapping("/empresa/{id}")
    public String updateEmpresaById(@PathVariable(value = "id") String id, @ModelAttribute Empresa empresa) {
        try {
            Empresa oldEmpresa = Empresa.loadFromTextFile("db/empresas/" + id + ".txt");
            empresa.setSenha(oldEmpresa.getSenha());
            empresa.saveToTextFile("db/empresas/" + id + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/empresa/" + id;
    }

}
