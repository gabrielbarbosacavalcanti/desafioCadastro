package entity;

public class Pet {
	public enum TipoPet {
		Cachorro, Gato;
	}

	public enum SexoPet {
		Masculino, Feminino;
	}

	private String nome;
	private String sobrenome;
	private TipoPet tipo;
	private SexoPet sexo;
	private Endereco endereco;
	private Double idade;
	private Integer peso;
	private String raca;
	

	public Pet() {
		super();

	}

	public Pet(String nome,String sobrenome, TipoPet tipo, SexoPet sexo, Endereco endereco, Double idade, Integer peso,
			String raca) throws IllegalArgumentException {
		super();

		this.nome = "NÃO INFORMADO";
		this.sobrenome = "NÃO INFORMADO";
		this.endereco = endereco;
		this.peso = peso;
		this.tipo = tipo;
		this.sexo = sexo;
		this.idade = idade;
		this.raca = "NÃO INFORMADO";

		if (peso < 0.5 || peso > 6) {
			throw new IllegalArgumentException("Peso de animal inválido.");
		}

		if (this.nome == null || this.sobrenome == null) {
			throw new IllegalArgumentException("Os espaços nome e sobrenome devem ser preeenchidos.");
		}

		if (idade > 240) {
			throw new IllegalArgumentException("Idade de animal inválida.");
		}
		
			
		if(this.tipo != TipoPet.Cachorro || this.tipo != TipoPet.Gato) {
			throw new IllegalArgumentException("Valor para tipo de Pet inválido.");
		
		}
		if(this.sexo != SexoPet.Masculino || this.sexo != SexoPet.Masculino ) {
			throw new IllegalArgumentException();
		}

	}

	public String getPesoFormatado() {
		if (this.peso == null) {
			return "NÃO INFORMADO";
		}

		return this.peso.toString();
	}

	public String getIdadeFormatada() {
		if (this.idade == null) {
			return "NÃO INFORMADO";
		}
		return this.idade.toString();
	}

	public double getIdade() {
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
	
	public String nomeformatado() {
		return this.nome + this.sobrenome;
	}

	public void converterIdadeAnos(Double idadeMeses) {
		if (idadeMeses > 1 && idadeMeses <= 240) {
			Double idadeAnos = idadeMeses / 12;
			this.setIdade(idadeAnos);
		}
		

	}

	@Override
	public String toString() {
		return "1 - " + nomeformatado() + "\n2 - " + tipo + "\n3 - " + sexo + "\n4 - "
				+ endereco + "\n5 - " + idade + "\n6 - " + peso + "\n7 - " + raca + "]";
	}


}
