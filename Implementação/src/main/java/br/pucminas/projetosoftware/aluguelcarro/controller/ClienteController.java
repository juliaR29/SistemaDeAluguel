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
public class ClienteController {

	@GetMapping("/cliente")
	public String listClientes(Model model) {
		try {
			File folder = new File("db/clientes");
			File[] listOfFiles = folder.listFiles();
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();

			for (File file : listOfFiles) {
				if (file.isFile() && file.getName().endsWith(".txt")) {
					Cliente cliente = Cliente.loadFromTextFile(file.getAbsolutePath());
					clientes.add(cliente);

				}
			}
			model.addAttribute("clientes", clientes);
			model.addAttribute("cliente", new Cliente());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cliente-list";

	}

	@PostMapping("/cliente")
	public String createCliente(@ModelAttribute Cliente cliente) {
		try {
			// figure out a new sequencial id
			File folder = new File("db/clientes");
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
			cliente.setId(idString);
			cliente.saveToTextFile("db/clientes/" + cliente.getId() + ".txt");
			return "redirect:/cliente/" + cliente.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/cliente";
		}
	}

	@DeleteMapping("/cliente/{i}")
	public String deleteClienteById(@PathVariable(value = "id") String id) {
		return "";
	}

	@GetMapping("/cliente/{id}")
	public String getClienteById(@PathVariable(value = "id") String id, Model model) {
		try {
			Cliente cliente = Cliente.loadFromTextFile("db/clientes/" + id + ".txt");

			model.addAttribute("cliente", cliente);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/cliente-details";
	}

	@PutMapping("/cliente/{i}")
	public String updateClienteById(@PathVariable(value = "id") String id) {
		return "";
	}

}
