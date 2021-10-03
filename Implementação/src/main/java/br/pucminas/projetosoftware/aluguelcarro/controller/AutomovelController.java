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
public class AutomovelController {

    @GetMapping("/automovel")
    public String listAutomoveis(Model model) {
        try {
            File folder = new File("db/automoveis");
            File[] listOfFiles = folder.listFiles();
            ArrayList<Automovel> automoveis = new ArrayList<Automovel>();

            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    Automovel automovel = Automovel.loadFromTextFile(file.getAbsolutePath());
                    automoveis.add(automovel);
                }
            }
            model.addAttribute("automoveis", automoveis);
            model.addAttribute("automovel", new Automovel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "automovel-list";

    }

    @PostMapping("/automovel")
    public String createAutomovel(@ModelAttribute Automovel automovel) {
        try {
            File folder = new File("db/automoveis");
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
            automovel.setId(idString);
            automovel.saveToTextFile("db/automoveis/" + automovel.getId() + ".txt");
            return "redirect:/automovel/" + automovel.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/automovel";
        }
    }

    @DeleteMapping("/automovel/{id}")
    public String deleteAutomovelById(@PathVariable(value = "id") String id) {
        try {
            File file = new File("db/automoveis/" + id + ".txt");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/automovel";
    }

    @GetMapping("/automovel/{id}")
    public String getAutomovelById(@PathVariable(value = "id") String id, Model model) {
        try {
            Automovel automovel = Automovel.loadFromTextFile("db/automoveis/" + id + ".txt");

            model.addAttribute("automovel", automovel);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/automovel-details";
    }

    @PutMapping("/automovel/{id}")
    public String updateAutomovelById(@PathVariable(value = "id") String id, @ModelAttribute Automovel automovel) {
        try {
            File file = new File("db/automoveis/" + id + ".txt");
            file.delete();
            automovel.saveToTextFile("db/automoveis/" + automovel.getId() + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/automovel/" + automovel.getId();
    }
}

