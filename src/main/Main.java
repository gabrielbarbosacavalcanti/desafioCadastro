package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import entity.Endereco;
import entity.Pet;
import entity.Pet.*;
import exception.RacaInvalidaException;
import service.PetService;

public class Main {
	public enum Criterio {
		NOME, IDADE, PESO, RACA, SEXO, TIPO
	}
	
	private static String lerNome(Scanner scan) {
		while(true) {
			System.out.println("Nome: ");
			String nome = scan.nextLine().trim();
			if(nome.isEmpty()) {
				System.out.println("Nome não pode ser vazio.");
				continue;
			}
			return nome;
		}
		
	}
	
	public static String lerSobrenome(Scanner scan) {
		while(true) {
			System.out.println("Sobrenome: ");
			String sobrenome = scan.nextLine().trim();
			if(sobrenome.isEmpty()) {
				System.out.println("Sobrenome não pode ser vazio.");
				continue;
			}
			return sobrenome;
		}
	}
	
	private static TipoPet lerTipoPet(Scanner scan) {
		while(true) {
			System.out.println("Tipo do pet "+ List.of(TipoPet.values()));
			String entrada = scan.nextLine().toUpperCase();
			try {
				return TipoPet.valueOf(entrada);
			}catch (IllegalArgumentException e) {
				System.out.println("Tipo inválido.");
		}
		
		}
	}
	
	private static SexoPet lerSexoPet(Scanner scan) {
		while(true) {
			System.out.println("Sexo do pet " + List.of(SexoPet.values()));
			String entrada = scan.nextLine().toUpperCase();	
		
		try {
			return SexoPet.valueOf(entrada);
		}catch (IllegalArgumentException e) {
			System.out.println("Sexo inválido.");
		}
		}
	}
	
	private static Endereco lerEndereco(Scanner scan) {
		while(true) {
			try {
				System.out.println("Número da casa:");
				String numero = scan.nextLine();
				
				System.out.println("Cidade: ");
				String cidade = scan.nextLine();
				
				System.out.println("Rua: ");
				String rua = scan.nextLine();
				
				return new Endereco(numero,cidade,rua);
			}catch (IllegalArgumentException e) {
				System.out.println("Endereço inválido. Tente Novamente.");
			}
		}
	}
	
	private static double lerIdade(Scanner scan) {
		while(true) {
			try {
				System.out.println("Idade em (M)eses ou (A)nos:");
				char tipo = scan.nextLine().toUpperCase().charAt(0);
				
				System.out.println("Valor da idade: ");
				int valor = Integer.parseInt(scan.nextLine());
				
				if(tipo == 'M') {
					if(valor < 1 || valor > 12) {
						throw new IllegalArgumentException();
					}
					return valor/12.0;
					}	
					
					if(tipo == 'A') {
						if(valor < 1 || valor > 20) {
							throw new IllegalArgumentException();
						}
						return valor;
						}
						
						throw new IllegalArgumentException();
					
				
			}catch (Exception e) {
				System.out.println("Idade inválida.");
			}
		}
	}
	
	private static int lerPeso(Scanner scan) {
		while(true) {
			try {
				System.out.println("Peso: ");
				int peso = Integer.parseInt(scan.nextLine());
				
				if(peso <= 0) 
					throw new IllegalArgumentException();
				
				return peso;
			}catch (Exception e) {
				System.out.println("Peso inválido.");
			}
		}
	}
	
	private static String lerRaca(Scanner scan) {
		while(true) {
			try {
				System.out.println("Raça: ");
				String raca = scan.nextLine().trim();
				
				if(!raca.matches("^[A-Za-zÀ-ÿ ]+$")) {
					throw new RacaInvalidaException("Raça deve conter apenas letras.");
				}
				return raca;
			}catch (RacaInvalidaException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static Pet cadastrarPet(Scanner scan, PetService petService) {
		String caminhoArquivo = "C:\\Users\\Administrator\\Projetos\\desafioCadastro\\formulario.txt";
		
		try{
			String conteudoArquivo = petService.lerArquivo(caminhoArquivo);
			System.out.println(conteudoArquivo);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Pet pet = new Pet();
		
		pet.setNome(lerNome(scan));
		pet.setSobrenome(lerSobrenome(scan));
		pet.setTipo(lerTipoPet(scan));
		pet.setSexo(lerSexoPet(scan));
		pet.setEndereco(lerEndereco(scan));
		pet.setIdade(lerIdade(scan));
		pet.setPeso(lerPeso(scan));
		pet.setRaca(lerRaca(scan));
		
		return pet;
	}
	
	private static Pet selecionarPet(List<Pet> pets, Scanner scan) {
		while(true) {
			System.out.println("Selecione o pet:");
			
			for(int i = 0; i < pets.size(); i++) {
				System.out.println("(" + i + ")" + pets.get(i).formatarDados());
			}
			try {
				System.out.println("Número: ");
				int indice = Integer.parseInt(scan.nextLine());
				
				if(indice < 0 || indice >= pets.size()) {
					throw new IndexOutOfBoundsException();
				}
				
				return pets.get(indice);
			}catch (Exception e) {
				System.out.println("Pet inválido. Tente novamente.");
			}
		}
	}
	
	private static void alterarPet(Scanner scan, PetService petService) {

	    List<Pet> pets = petService.listarTodos();

	    if (pets.isEmpty()) {
	        System.out.println("Não há pets cadastrados.");
	        return;
	    }

	    System.out.println("Selecione o pet:");
	    for (int i = 0; i < pets.size(); i++) {
	        System.out.println("(" + i + ") " + pets.get(i).formatarDados());
	    }

	    int indice;
	    try {
	        System.out.print("Número: ");
	        indice = Integer.parseInt(scan.nextLine());
	    } catch (NumberFormatException e) {
	        System.out.println("Entrada inválida.");
	        return;
	    }

	    if (indice < 0 || indice >= pets.size()) {
	        System.out.println("Pet inexistente.");
	        return;
	    }

	    Pet petSelecionado = pets.get(indice);

	    System.out.println("""
	        O que deseja alterar?
	        1 - Nome
	        2 - Endereço
	        3 - Idade
	        4 - Peso
	        5 - Raça
	        """);

	    int opcao;
	    try {
	        opcao = Integer.parseInt(scan.nextLine());
	    } catch (NumberFormatException e) {
	        System.out.println("Opção inválida.");
	        return;
	    }

	    try {
	        switch (opcao) {

	            case 1 -> {
	                System.out.print("Novo nome: ");
	                String nome = scan.nextLine();

	                System.out.print("Sobrenome: ");
	                String sobrenome = scan.nextLine();

	                petService.alterarNome(petSelecionado, nome, sobrenome);
	            }

	            case 2 -> {
	                System.out.print("Número da casa: ");
	                String num = scan.nextLine();

	                System.out.print("Cidade: ");
	                String cidade = scan.nextLine();

	                System.out.print("Rua: ");
	                String rua = scan.nextLine();

	                petService.alterarEndereco(
	                    petSelecionado,
	                    new Endereco(num, cidade, rua)
	                );
	            }

	            case 3 -> {
	                System.out.print("Nova idade: ");
	                double idade = Double.parseDouble(scan.nextLine());

	                petService.alterarIdade(petSelecionado, idade);
	            }

	            case 4 -> {
	                System.out.print("Novo peso: ");
	                int peso = Integer.parseInt(scan.nextLine());

	                petService.alterarPeso(petSelecionado, peso);
	            }

	            case 5 -> {
	                System.out.print("Nova raça: ");
	                String raca = scan.nextLine();

	                petService.alterarRaca(petSelecionado, raca);
	            }

	            default -> System.out.println("Opção inválida.");
	        }

	        System.out.println("Alteração realizada com sucesso.");

	    } catch (RuntimeException e) {
	        System.out.println("Erro: " + e.getMessage());
	    }
	}

	
	private static void alterarNome(Pet pet, Scanner scan) {
		System.out.println("Novo nome: ");
		String nome = scan.nextLine().strip();
		
		if(!nome.isEmpty()) {
			pet.setNome(nome);
			System.out.println("Nome alterado com sucesso.");
		}else{
			System.out.println("Nome não pode ser vazio.");
		}
		
	}
	
	private static void alterarEndereco(Pet pet, Scanner scan) {
		Endereco novoEndereco = lerEndereco(scan);
		pet.setEndereco(novoEndereco);
		System.out.println("Endereço alterado com sucesso.");
	}
	
	public static void main(String[] args) throws Exception, RacaInvalidaException {
		Scanner scan = new Scanner(System.in);
		List<Pet> pets = new ArrayList<>();
		PetService  petService = new PetService(pets);
		int opcao = 0;

		System.out.println("Sistema cadastro de Pets:");

		do {
			System.out.println("1 - Cadastrar um novo pet");
			System.out.println("2 - Buscar pet com filtro");
			System.out.println("3 - Alterar pet cadastrado");
			System.out.println("4 - Remover pet");
			System.out.println("5 - Listar todos Pets");
			System.out.println("0 - Sair");
			
			
				try {
				opcao = Integer.parseInt(scan.nextLine());
				
			
				}catch (Exception e) {
					System.out.println("Digite um valor válido para leitura.");
				continue;
				}
				
				switch (opcao) {

				case 1 -> {
					Pet pet = cadastrarPet(scan, petService);
					petService.cadastrar(pet);
				}
				case 2 -> buscarPet(scan, petService);
				case 3 -> alterarPet(scan,petService); 
				case 4 -> removerPet(scan, petService);
				case 5 -> listarTodos(petService);			
													
								}
		
		}while(opcao != 0);
		scan.close();
	}
	
	private static void buscarPet(Scanner scan, PetService service) {
		Map<Criterio, Object> filtros = new HashMap<>();
		
		boolean adicionando = true;
		while(adicionando) {
			System.out.println("Adicionar critério de busca:");
			System.out.println("1 - Nome");
			System.out.println("2 - Idade");
			System.out.println("3 - Peso");
			System.out.println("4 - Raça");
			System.out.println("0 - Buscar");
			
			int opcao = Integer.parseInt(scan.nextLine());
			switch(opcao) {
			case 1 -> {
				System.out.println("Digite o nome: ");
				filtros.put(Criterio.NOME, scan.nextLine());
				
			}
			case 2 -> {
				System.out.println("Digite a idade: ");
				filtros.put(Criterio.IDADE, Double.parseDouble(scan.nextLine()));
				
			}
			case 3 -> {
				System.out.println("Digite o peso: ");
				filtros.put(Criterio.RACA, scan.nextLine());
			}
			
			case 4 -> {
				System.out.println("Digite a raça: ");
				filtros.put(Criterio.SEXO, lerSexoPet(scan));
			}
			case 5 -> {
				System.out.println("Tipo: ");
				filtros.put(Criterio.TIPO, lerTipoPet(scan));
			}
			case 0 -> adicionando = false;
				default -> System.out.println("Opção inválida.");
			}
			}
		
		List<Pet> resultado = service.buscar(filtros);
		if(resultado.isEmpty()) {
			System.out.println("Nenhum pet encontrado.");
		}else {
			resultado.forEach(p -> System.out.println(p.formatarDados()));
		}
		}
	

	private static void removerPet(Scanner scan, PetService service) {
		System.out.println("Índice do pet: ");
		int indice = Integer.parseInt(scan.nextLine());
		service.remover(indice);
		System.out.println("Pet removido");
	}
	
	private static void listarTodos(PetService service) {
	    service.listarTodos()
	           .forEach(p -> System.out.println(p.formatarDados()));
	}

}
		
