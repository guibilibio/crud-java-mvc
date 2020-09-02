package Br.edu.opet.model;

import java.util.Date;

public class Enf extends Pessoa{

	private int cargahoraria;
	
	public Enf(String nome, String CPF, String sexo, String telefone, double altura, Date dtNasc, int cargahoraria) {
		
		super(nome, CPF, sexo, telefone, altura, dtNasc);
		
		this.cargahoraria = cargahoraria;
	}

	public int getCargahoraria(){
		return cargahoraria;
	}

	
	public void setCargahoraria(int cargahoraria){
		this.cargahoraria = cargahoraria;
	}
	
	public double getSalario() {
		
		return 0.0;
	}
	
	public String getAtributo(){
		
		return "Carga horária: " + this.cargahoraria + " horas";
	}
}
