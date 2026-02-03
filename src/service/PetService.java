package service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import entity.Endereco;
import entity.Pet;
import main.Main.Criterio;

public class PetService {
	
	private final List<Pet> pets;
	
	public PetService(List<Pet> pets) {
		this.pets = pets;
	}
	
	public void cadastrar(Pet pet) {
		validarPet(pet);
		pets.add(pet);
	}
	
	public List<Pet> buscar(Map<Criterio, Object> filtros) {
		return pets.stream()
				.filter(pet -> atendeFiltros(pet, filtros))
				.toList();
	}
	
	private boolean atendeFiltros(Pet pet, Map<Criterio, Object> filtros) {
		for(Map.Entry<Criterio, Object> entry : filtros.entrySet()) {
			switch(entry.getKey()) {
			case NOME -> {
				if(!pet.getNomeFormatado().equalsIgnoreCase((String) entry.getValue())) {
					return false;
				}
			}
			case TIPO -> {
				if(pet.getTipo() != entry.getValue()) {
					return false;
				}
			}
			case RACA -> {
				if(!pet.getRaca().equalsIgnoreCase((String)entry.getValue())) {
					return false;
					
				}
			}
			case SEXO -> {
				if(pet.getSexo() != entry.getValue()) {
					return false;
				}
			}
			case PESO -> {
				if(!pet.getRaca().equalsIgnoreCase((String) entry.getValue())) {
					return false;
				}
			}
			case IDADE -> {
				if(pet.getPeso() != entry.getValue()) {
					return false;
				}
			}
			}
		}
		return true;
	}
	
	public Pet buscarPorIndice(int indice) {
		if(indice < 0 || indice >= pets.size()) {
			throw new IndexOutOfBoundsException("Pet inexistente.");
		}
		return pets.get(indice);
	}
	public void remover(int indice) {
		Pet pet = buscarPorIndice(indice);
		pets.remove(pet);
	}
	
	public List<Pet> listarTodos(){
		return new ArrayList<>(pets);
	}
	
	public List<Pet> filtrar(Map<Criterio,Object> filtros){
		List<Pet> resultado = new ArrayList<>(pets);
		
		for(Map.Entry<Criterio, Object> entry : filtros.entrySet()) {
			aplicarCriterio(entry.getKey(), entry.getValue(), resultado);
		}
		return resultado;
		
	}
	
	public void alterarNome(Pet pet, String nome,String sobrenome) {
		if (nome == null || nome.isBlank()) {
	        throw new IllegalArgumentException("Nome inválido.");
	    }
	    pet.setNome(nome);
	    pet.setSobrenome(
	        sobrenome == null || sobrenome.isBlank()
	        ? "NÃO INFORMADO"
	        : sobrenome
	    );
	}
	
	public void alterarEndereco(Pet pet, Endereco endereco) {
		  if (endereco == null) {
		        throw new IllegalArgumentException("Endereço inválido.");
		    }
		    pet.setEndereco(endereco);
	}
	
	public void alterarIdade(Pet pet, double idade) {
		 if (idade <= 0 || idade > 20) {
		        throw new IllegalArgumentException("Idade inválida.");
		    }
		    pet.setIdade(idade);
	}
	
	public void alterarPeso(Pet pet, int peso) {
		if (peso <= 0) {
	        throw new IllegalArgumentException("Peso inválido.");
	    }
	    pet.setPeso(peso);
	}
	
	public void alterarRaca(Pet pet, String raca) {
		if (raca == null || !raca.matches("^[A-Za-zÀ-ÿ ]+$")) {
	        throw new IllegalArgumentException("Raça inválida.");
	    }
	    pet.setRaca(raca);
	}
	
	private void aplicarCriterio(Criterio criterio, Object valor, List<Pet> lista) {
		switch(criterio) {
		case NOME ->
		lista.removeIf(p -> !p.getNomeFormatado().toLowerCase()
				.contains(valor.toString().toLowerCase()));
		
		
		case IDADE -> 
			lista.removeIf(p -> p.getIdade() != (double) valor);
			
		case PESO -> 
		lista.removeIf(p -> p.getPeso() != (int) valor);
		
		case RACA ->
			lista.removeIf(p -> !p.getRaca().toLowerCase().contains(valor.toString().toLowerCase()));
	
		case SEXO -> 
		lista.removeIf(p -> p.getSexo() != valor);
		
		case TIPO ->
		lista.removeIf(p -> p.getTipo() != valor);
		
		} 
	}
	
	public void validarPet(Pet pet) {
		if(pet.getNome() == null || pet.getNome().isBlank()) {
			throw new IllegalArgumentException("Nome inválido.");
		}
		if(pet.getPeso() <= 0) {
			throw new IllegalArgumentException("Peso inválido.");
		}
		if(pet.getIdade() <= 0) {
			throw new IllegalArgumentException("Idade inválida.");
		}
	}
	
	public String lerArquivo(String caminhoArquivo) throws Exception {
		String conteudo = "";
		String linha = null;
		
		try(BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
			linha = leitor.readLine();
			while(linha != null) {
				conteudo += linha + "\n";
				linha = leitor.readLine();
			}
		}catch (Exception e) {
			throw e;
		}
		return conteudo;
	}
}
