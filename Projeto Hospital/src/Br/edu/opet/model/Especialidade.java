package Br.edu.opet.model;

import java.util.ArrayList;

import Br.edu.opet.model.dao.EspecialidadeDAO;

public class Especialidade extends EspecialidadeDAO{

	private int id;
	private String descri��o;

	public Especialidade(int id, String descri��o){

		this.id = id;
		this.descri��o = descri��o;
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
	
	public String getDescri��o(){
		return descri��o;
	}
	
	public void setDescri��o(String descri��o){
		this.descri��o = descri��o;
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
