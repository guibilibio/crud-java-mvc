package Br.edu.opet.model;

import java.util.ArrayList;

import Br.edu.opet.model.dao.EspecialidadeDAO;

public class Especialidade extends EspecialidadeDAO{

	private int id;
	private String descrição;

	public Especialidade(int id, String descrição){

		this.id = id;
		this.descrição = descrição;
	}

	public Especialidade(int id){

		this.id = id;
	}
	
	public Especialidade(){
		
	}
	
	public boolean salvar(){
		return super.salvar(this);
	}

	public ArrayList<Especialidade> listar(){
		return super.listar();
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public String getDescrição(){
		return descrição;
	}
	
	public void setDescrição(String descrição){
		this.descrição = descrição;
	}

	public boolean consultar() {
		return super.consultar(this);
	}

	public boolean atualizar() {
		return super.atualizar(this);
	}

	public boolean excluir() {
		return super.excluir(this);
	}
}
