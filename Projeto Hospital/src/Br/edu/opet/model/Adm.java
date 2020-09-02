package Br.edu.opet.model;

import java.util.Date;

import Br.edu.opet.model.dao.AdministrativoDao;

public class Adm extends AdministrativoDao{

	private double salfixo;

	public Adm(String nome, String CPF, String sexo, String telefone, double altura, Date dtNasc, double salfixo){

		super(nome, CPF, sexo, telefone, altura, dtNasc);

		this.salfixo = salfixo;
	}

	public double getSalfixo(){
		return salfixo;
	}

	public void setSalfixo(double salfixo){
		this.salfixo = salfixo;
	}

	public String getAtributo(){

		return "Salário fixo: R$" + this.salfixo;
	}
	
	public boolean salvar() {
		return super.salvar(this);
	}
}
