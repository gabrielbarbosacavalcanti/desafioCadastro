package main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import desafioCadastro.LeitorDeArquivo;
import entity.Endereco;
import entity.Pet;
import entity.Pet.*;
import entity.NomeCompleto;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int opcao = 0;
		System.out.println("Sistema cadastro de Pets:");

		while (opcao != 6) {
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

				try {
					System.out.print("1. ");
					String nomePet = scan.nextLine();
					String sobrenomePet = scan.nextLine();
					System.out.print("2. ");
					String tipoPet = scan.nextLine().toUpperCase();
					System.out.print("3. ");
					String sexoPet = scan.nextLine();
					System.out.println("4. ");
					System.out.println("Número da casa: ");
					String numCasaPet = scan.nextLine();
					System.out.println("Cidade: ");
					String cidadePet = scan.nextLine();
					System.out.println("Rua: ");
					String ruaPet = scan.nextLine();
					System.out.print("5.");
					Double entradaAnos = scan.nextDouble();

					System.out.print("6. ");
					
					Integer peso = scan.nextInt();

					System.out.print("7. ");
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

					Endereco endereco = new Endereco(numCasaPet, cidadePet, ruaPet);
					NomeCompleto nomeCompleto = new NomeCompleto(nomePet, sobrenomePet);

					TipoPet tipoEscolhido = TipoPet.valueOf(tipoPet);
					SexoPet sexoEscolhido = SexoPet.valueOf(sexoPet);

					Pet novoPet = new Pet(nomeCompleto, tipoEscolhido, sexoEscolhido, endereco, entradaAnos, peso,
							raca);
					novoPet.getPesoFormatado();
					novoPet.converterIdadeAnos(entradaAnos);

				} catch (IllegalArgumentException e) {
					System.out.println("Digite um tipo de pet válido(Cachorro/Gato). ");
				}

			case 2:

			}

		}
	}

}
