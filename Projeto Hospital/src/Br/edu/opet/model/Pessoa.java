package Br.edu.opet.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import Br.edu.opet.model.dao.PessoaDAO;

public class Pessoa extends PessoaDAO{

	private int id;
	private String nome;
	private String CPF;
	private Date dtNasc;
	private String sexo;	
	private String telefone;
	private double altura;

	public Pessoa(String nome, String CPF, String sexo, String telefone, double altura, Date dtNasc){

		this.nome = nome;
		this.CPF = CPF;
		this.sexo = sexo;
		this.telefone = telefone;
		this.altura = altura;
		this.dtNasc = dtNasc;
	}

	public Pessoa(String CPF) {
		
		this.CPF = CPF;
	}
	
	public Pessoa(){

	}

	public int getIdade(){

		Date now = new Date();

		int idade = now.getYear() - this.dtNasc.getYear();

		if(now.getMonth() < this.getDtNasc().getMonth()){

			idade = idade - 1;			
		}

		if(now.getMonth() == this.getDtNasc().getMonth()){

			if(now.getDay() < this.getDtNasc().getMonth()){

				idade = idade + 1;
			}
		}
		return idade;
	}

	public double getSalario() {

		return 0.0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public Date getDtNasc(){

		return dtNasc;
	}

	public void setDtNasc(Date dtNasc){
		this.dtNasc = dtNasc;
	}

	public String getSexo(){
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getAtributo(){
		return "sem atributo";
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getCPF() {
		return CPF;
	}

	public Pessoa consultar(){
		return super.consultar(this.CPF);
	}

	protected boolean salvar(Connection conn) throws SQLException{
		return super.salvar(this, conn);
	}

	protected boolean atualizar(Connection conn) throws SQLException{
		return super.atualizar(conn, this);
	}
	
	protected boolean excluir(Connection conn) throws SQLException{
		return super.excluir(conn, this);
	}
	
	public boolean salvar(){
		System.out.println("Chamada inválda!");
		return false;
	}
	
	public boolean atualizar(){
		System.out.println("Chamada inválda!");
		return false;
	}

	public boolean excluir(){
		System.out.println("Chamada inválda!");
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
