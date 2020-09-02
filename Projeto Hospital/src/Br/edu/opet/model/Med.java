package Br.edu.opet.model;

import java.util.Date;

import Br.edu.opet.model.dao.MedicoDAO;


public class Med extends MedicoDAO{

	public Especialidade especialidade;

	public Med(int id, String nome, String CPF, String sexo, String telefone, double altura, Date dtNasc, Especialidade especialidade) {

		super(nome, CPF, sexo, telefone, altura, dtNasc);
		this.setId(id);
		this.especialidade = especialidade;
	}

	public Especialidade getEspecialidade(){
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public double getSalario() {

		return 0.0;
	}

	public String getAtributo(){

		return "Especialidade: " + this.especialidade.getDescrição();
	}
	
	public boolean salvar() {
		return super.salvar(this);
	}
	
	public boolean atualizar() {
		return super.atualizar(this);
	}
	
	public boolean excluir() {
		return super.excluir(this);
	}
	
	
}
