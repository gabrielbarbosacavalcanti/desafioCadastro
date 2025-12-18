package entity;

public class Endereco {
	protected String numCasa;
	protected String cidade;
	protected String rua;
	
	public Endereco() {
		
	}
	
	public Endereco(String numCasa, String cidade, String rua) {
		this.numCasa = "NÃO INFORMADO";
		this.cidade = cidade;
		this.rua = rua;
	}
	public String getNumCasa() {
		return numCasa;
	}
	public void setNumCasa(String numCasa) {
		this.numCasa = numCasa;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	
}
