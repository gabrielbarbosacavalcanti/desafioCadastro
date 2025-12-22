package main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import desafioCadastro.GeradorArquivoPet;
import desafioCadastro.LeitorDeArquivo;
import entity.Endereco;
import entity.Pet;
import entity.Pet.*;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int opcao = 1;
		System.out.println("Sistema cadastro de Pets:");

		while (opcao > 0 && opcao <= 6) {
			System.out.println("1.Cadastrar um novo pet");
			System.out.println("2.Alterar os dados do pet cadastrado");
			System.out.println("3.Deletar um pet cadastrado");
			System.out.println("4.Listar todos os pets cadastrados");
			System.out.println("5.Listar pets por algum critério(idade, nome, raça)");
			System.out.println("6.Sair");
			System.out.println();

			try {
				opcao = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Digite um valor válido para leitura.");
				continue;
			}

			switch (opcao) {

			case 1:
				LeitorDeArquivo c = new LeitorDeArquivo();
				List<String> linhas = c.lerLinhas("formulario.txt");

				System.out.println("\n-- Conteúdo recebido na Main ---");
				for (String linha : linhas) {
					System.out.println(linha);
				}

				Pet p = new Pet();
				System.out.println();

				boolean validoNome = true;
				do {
					System.out.println("1. ");
					try {
						
						String nomePet = scan.next();
						String sobrenomePet = scan.next();

						p.setNome(nomePet);
						p.setSobrenome(sobrenomePet);

					} catch (IllegalArgumentException e ) {
						System.out.println("Preencha todos os espaços.");
						validoNome = false;

					}
					catch (InputMismatchException e) {
						System.out.println("Digite apenas valores válidos");
						validoNome = false;
					}
					
				} while (validoNome != true);

				System.out.println("Nome completo: " + p.getNome() + " " + p.getSobrenome());

				scan.nextLine();
				boolean tipoPetValido = true;
				do {
				System.out.print("2. ");
				try {
					String tipoPet = scan.nextLine().toUpperCase();
					TipoPet tipoEscolhido = TipoPet.valueOf(tipoPet);
					p.setTipo(tipoEscolhido);

				} catch (IllegalArgumentException e) {
					System.out.println("Digite um tipo de animal válido");
					tipoPetValido = false;
				}
				catch (InputMismatchException e) {
					System.out.println("Digite valores dentro padrão");
					tipoPetValido = false;
				}
				}while(tipoPetValido != true);
				
				System.out.println("3. ");
				try {
					String sexoPet = scan.nextLine();
					SexoPet sexoEscolhido = SexoPet.valueOf(sexoPet);
					p.setSexo(sexoEscolhido);
				} catch (IllegalArgumentException e) {
					System.out.println("Digite um sexo válido para o Pet.");
				}

				System.out.println("4. ");
				System.out.println("Número da casa: ");
				try {
					String numCasaPet = scan.nextLine();
					System.out.println("Cidade: ");
					String cidadePet = scan.nextLine();
					System.out.println("Rua: ");
					String ruaPet = scan.nextLine();

					Endereco endereco = new Endereco(numCasaPet, cidadePet, ruaPet);
					p.setEndereco(endereco);

				} catch (InputMismatchException e) {
					System.out.println("Digite somento valores válidos.");
				}
				System.out.print("5.");

				try {
					Double entradaAnos = scan.nextDouble();
					p.setIdade(entradaAnos);
				} catch (InputMismatchException e) {
					System.out.println("Digite um valor de acordo com esperado.");
				}

				System.out.print("6. ");
				try {
					Integer peso = scan.nextInt();
					p.setPeso(peso);
				} catch (InputMismatchException e) {
					System.out.println("Digite apenas digitos para o peso.");
				}
				System.out.println("");

				System.out.print("7. ");
				try {
					String raca = scan.nextLine();
					boolean valido = false;
					while (valido != true) {
						for (char letra : raca.toCharArray()) {
							if (!Character.isLetter(letra) && !Character.isSpaceChar(letra)) {
								System.out.println("Digite uma raça válida para seu Pet: ");
								raca = scan.nextLine();

							} else {
								valido = true;
							}

						}
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Digite um nome de raça sem uso de caracteres especiais ou números.");
				}

				List<Pet> pets = List.of(new Pet());
				pets.add(p);

				GeradorArquivoPet ge = new GeradorArquivoPet();
				Path caminho = GeradorArquivoPet.gerarCaminho(pets.get(0).nomeformatado());

				try (PrintWriter pw = new PrintWriter(new FileWriter(caminho.toFile()))) {
					for (Pet p1 : pets) {
						pw.println(p.toString());
					}
				}

			case 2:

			}

		}
	}

}
