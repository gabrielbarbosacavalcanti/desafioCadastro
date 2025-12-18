package entity;

public class NomeCompleto {
	protected String nome;
	protected String sobrenome;
	
	public NomeCompleto() {
		
	}
	public NomeCompleto(String nome, String sobrenome) {
		super();
		this.nome = "NÃO INFORMADO";
		this.sobrenome = "NÃO INFORMADO";
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
	
	
}
