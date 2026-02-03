package entity;

import java.util.InputMismatchException;

import exception.RacaInvalidaException;

public class Pet {
	public enum TipoPet {
		CACHORRO, GATO;
	}

	public enum SexoPet {
		MASCULINO, FEMININO;
	}

	private String nome;
	private String sobrenome;
	private TipoPet tipo;
	private SexoPet sexo;
	private Endereco endereco;
	private double idade;
	private Integer peso;
	private String raca;

	
	public Pet() {
		super();

	}
	
	public static boolean contemApenasNumeros(String valor) {
		if(valor == null || valor.isEmpty()) {
		return false;
	}
		for(char c : valor.toCharArray()) {
			if(!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
}
	
	public Pet(String nome, String sobrenome, TipoPet tipo, SexoPet sexo, Endereco endereco, Double idade, Integer peso,
			String raca)  {
		super();

		if (peso == null ||peso < 0.5 || peso > 6) {
			throw new IllegalArgumentException("Peso de animal inválido.");
		}

		if (nome == null || nome.isBlank() ) {
			nome = "NÃO INFORMADO";
		}
		
		if(sobrenome == null || sobrenome.isBlank()) {
			sobrenome = "NÃO INFORMADO";
		}
		if(tipo == null) {
			throw new IllegalArgumentException("Tipo do pet é obrigatorio.");
		}
		if (idade <= 0 || idade > 240) {
			throw new IllegalArgumentException("Idade inválida.");
		}
		
		if (sexo == null) {
			throw new IllegalArgumentException("Sexo inválido.");
		}
		
		if(contemApenasNumeros(endereco.getNumCasa()) == false) {
			throw new InputMismatchException("Digite apenas valores esperados.");
		}
		
		if(contemApenasNumeros(endereco.getCidade()) == true) {
			
		}
		
		if(contemApenasNumeros(peso.toString()) == false) {
			throw new InputMismatchException("Digte um valor válido.");
		}
		
		
		
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.endereco = endereco;
		this.peso = peso;
		this.tipo = tipo;
		this.sexo = sexo;
		this.idade = idade;
		this.raca = (raca == null || raca.isBlank()) ? "NÃO INFORMADO" : raca;

	}
	
	
	public Double getIdade() {
		return idade;
	}

	public void setIdade(Double idade) {
		this.idade = idade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public TipoPet getTipo() {
		return tipo;
	}

	public void setTipo(TipoPet tipo) {
		this.tipo = tipo;
	}

	public SexoPet getSexo() {
		return sexo;
	}

	public void setSexo(SexoPet sexo) {
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeFormatado() {
		return this.nome + " " + this.sobrenome;
	}


	public static void validarRaca(String raca) throws RacaInvalidaException {

		   for (char c : raca.toCharArray()) {
		       if (!Character.isLetter(c)) {
		           throw new RacaInvalidaException(
		               "Raça inválida. Utilize apenas letras."
		           );
		       }
		   }
		
	}

	@Override
	public String toString() {
		return "1 - " + getNomeFormatado() + "\n2 - " + tipo + "\n3 - " + sexo + "\n4 - " + endereco + "\n5 - " + idade
				+ "\n6 - " + peso + "\n7 - " + raca + "]";
	}

	public String formatarDados() {
		return getNomeFormatado() + " - " + this.tipo + " - " + this.endereco.enderecoFormatado() + " - "
				+ this.endereco.getCidade() + " -  " + this.idade + " anos" + " - " + this.peso + " kg" + " - " + this.raca;
	}
}
