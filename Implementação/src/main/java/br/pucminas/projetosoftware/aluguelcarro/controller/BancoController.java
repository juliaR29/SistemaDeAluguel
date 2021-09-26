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
public class BancoController {

    @GetMapping("/banco")
    public String listBancos(Model model) {
        try {
            File folder = new File("db/bancos");
            File[] listOfFiles = folder.listFiles();
            ArrayList<Banco> bancos = new ArrayList<Banco>();

            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    Banco banco = Banco.loadFromTextFile(file.getAbsolutePath());
                    bancos.add(banco);
                }
            }
            model.addAttribute("bancos", bancos);
            model.addAttribute("banco", new Banco());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "banco-list";

    }

    @PostMapping("/banco")
    public String createBanco(@ModelAttribute Banco banco) {
        try {
            File folder = new File("db/bancos");
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
            banco.setId(idString);
            banco.saveToTextFile("db/bancos/" + banco.getId() + ".txt");
            return "redirect:/banco/" + banco.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/banco";
        }
    }

    @DeleteMapping("/banco/{id}")
    public String deleteBancoById(@PathVariable(value = "id") String id) {
        try {
            File file = new File("db/bancos/" + id + ".txt");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/banco";
    }

    @GetMapping("/banco/{id}")
    public String getBancoById(@PathVariable(value = "id") String id, Model model) {
        try {
            Banco banco = Banco.loadFromTextFile("db/bancos/" + id + ".txt");

            model.addAttribute("banco", banco);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/banco-details";
    }

    @PutMapping("/banco/{id}")
    public String updateBancoById(@PathVariable(value = "id") String id, @ModelAttribute Banco banco) {
        try {
            Banco oldBanco = Banco.loadFromTextFile("db/bancos/" + id + ".txt");
            banco.setSenha(oldBanco.getSenha());
            banco.saveToTextFile("db/bancos/" + id + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/banco/" + id;
    }

}
