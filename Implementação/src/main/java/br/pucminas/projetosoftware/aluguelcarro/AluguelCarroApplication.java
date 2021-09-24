package br.pucminas.projetosoftware.aluguelcarro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.pucminas.projetosoftware.aluguelcarro.model.*;

@SpringBootApplication
@Controller
public class AluguelCarroApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluguelCarroApplication.class, args);
	}

	@GetMapping("/cliente")
	public String listClientes() {
		return "cliente-list";
	}

	@PostMapping("/cliente")
	public String createCliente() {
		return "";
	}

	@DeleteMapping("/cliente/{i}")
	public String deleteClienteById(@PathVariable(value = "id") String id) {
		return "";
	}

	@GetMapping("/cliente/{id}")
	public String getClienteById(@PathVariable(value = "id") String id, Model model) {
		try {
			Cliente cliente = Cliente.loadFromTextFile("db/clientes/" + id + ".txt");

			model.addAttribute("id", cliente.getId());
			model.addAttribute("nome", cliente.getNome());
			model.addAttribute("cpf", cliente.getCpf());
			model.addAttribute("rg", cliente.getRg());
			model.addAttribute("email", cliente.getEmail());
			model.addAttribute("endereco", cliente.getEndereco());
			model.addAttribute("profissao", cliente.getProfissao());
			model.addAttribute("entidadeEmpregadora", cliente.getEntidadeEmpregadora());
			model.addAttribute("rendimentosAuferidos", cliente.getRendimentosAuferidos());

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
