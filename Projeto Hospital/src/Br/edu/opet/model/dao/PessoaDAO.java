package Br.edu.opet.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import Br.edu.opet.model.Adm;
import Br.edu.opet.model.Enf;
import Br.edu.opet.model.Especialidade;
import Br.edu.opet.model.Med;
import Br.edu.opet.model.Pessoa;

public class PessoaDAO{ // CRUD

	public Pessoa consultar(String CPF){

		Pessoa p = null;

		try{

			Connection conn = Conexao.getConexao();

			PreparedStatement stmt = conn. prepareStatement("SELECT " + 
					"CASE WHEN M.ID_PESSOA IS NOT NULL THEN 'M' " + 
					"WHEN E.ID_PESSOA IS NOT NULL THEN 'E' " + 
					"WHEN A.ID_PESSOA IS NOT NULL THEN 'A' " + 
					"ELSE 'X' end as TIPO, " + 
					"E.CARGA_HORARIA, A.SALARIO, P.ID_PESSOA, M.ID_ESPECIALIDADE, EP.DESC_ESPECIALIDADE, " + 
					"NM_PESSOA, CPF, SEXO, TELEFONE, ALTURA, DT_NASC FROM PESSOA P " + 
					"LEFT JOIN MEDICO M ON M.ID_PESSOA = P.ID_PESSOA " + 
					"LEFT JOIN ENFERMEIRO E ON E.ID_PESSOA = P.ID_PESSOA " + 
					"LEFT JOIN ADMINISTRATIVO A ON A.ID_PESSOA = P.ID_PESSOA " + 
					"LEFT JOIN ESPECIALIDADE EP ON M.ID_ESPECIALIDADE = EP.ID_ESPECIALIDADE WHERE P.CPF = ?");

			stmt.setString(1, CPF);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				String tipo = rs.getString("TIPO");
				String nome = rs.getString("NM_PESSOA");
				int idPessoa = rs.getInt("ID_PESSOA");
				String cpf = rs.getString("CPF");
				String sexo = rs.getString("SEXO");
				String telefone = rs.getString("TELEFONE");
				double altura = rs.getDouble("ALTURA");
				Date dtnasc = rs.getDate("DT_NASC");

				switch (tipo) { 
				case "M": 
					int id = rs.getInt("ID_ESPECIALIDADE");
					String descricao = rs.getString("DESC_ESPECIALIDADE");
					p = new Med(idPessoa, nome, cpf, sexo, telefone, altura, dtnasc, new Especialidade(id, descricao));
					break;
				case "E": 
					int cargaHoraria = rs.getInt("CARGA_HORARIA");
					p = new Enf(nome, cpf, sexo, telefone, altura, dtnasc, cargaHoraria);
					break;
				case "A": 
					double salario = rs.getDouble("SALARIO");
					p = new Adm(nome, cpf, sexo,telefone, altura, dtnasc, salario);
					break;
				default:
					throw new Exception("Tipo de pessoa "+tipo+" inválido");
				}

			}
			rs.close();
			stmt.close();
			conn.close();

		}catch (Exception e) {
			System.err.println("Erro inesperado! " + e.getMessage());
			e.printStackTrace();
		}
		return p;


	}

	public ArrayList<Pessoa> listar(){

		ArrayList<Pessoa> alp = new ArrayList<>();

		try{
			Connection conn = Conexao.getConexao();

			PreparedStatement stmt = conn. prepareStatement("SELECT " + 
					"CASE WHEN M.ID_PESSOA IS NOT NULL THEN 'M' " + 
					"WHEN E.ID_PESSOA IS NOT NULL THEN 'E' " + 
					"WHEN A.ID_PESSOA IS NOT NULL THEN 'A' " + 
					"ELSE 'X' end as TIPO, " + 
					"E.CARGA_HORARIA, A.SALARIO, M.ID_ESPECIALIDADE, EP.DESC_ESPECIALIDADE, " + 
					"NM_PESSOA, CPF, SEXO, TELEFONE, ALTURA, DT_NASC FROM PESSOA P " + 
					"LEFT JOIN MEDICO M ON M.ID_PESSOA = P.ID_PESSOA " + 
					"LEFT JOIN ENFERMEIRO E ON E.ID_PESSOA = P.ID_PESSOA " + 
					"LEFT JOIN ADMINISTRATIVO A ON A.ID_PESSOA = P.ID_PESSOA " + 
					"LEFT JOIN ESPECIALIDADE EP ON M.ID_ESPECIALIDADE = EP.ID_ESPECIALIDADE");

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Pessoa p;
				String tipo = rs.getString("TIPO");
				String nome = rs.getString("NM_PESSOA");
				String cpf = rs.getString("CPF");
				String sexo = rs.getString("SEXO");
				String telefone = rs.getString("TELEFONE");
				double altura = rs.getDouble("ALTURA");
				Date dtnasc = rs.getDate("DT_NASC");

				switch (tipo) { 
				case "M": 
					int id = rs.getInt("ID_ESPECIALIDADE");
					String descricao = rs.getString("DESC_ESPECIALIDADE");
					p = new Med(0, nome, cpf, sexo, telefone, altura, dtnasc, new Especialidade(id, descricao));
					break;
				case "E": 
					int cargaHoraria = rs.getInt("CARGA_HORARIA");
					p = new Enf(nome, cpf, sexo, telefone, altura, dtnasc, cargaHoraria);
					break;
				case "A": 
					double salario = rs.getDouble("SALARIO");
					p = new Adm(nome, cpf, sexo,telefone, altura, dtnasc, salario);
					break;
				default:
					throw new Exception("Tipo de pessoa "+tipo+" inválido");
				}
				alp.add(p);
			}
			rs.close();
			stmt.close();
			conn.close();

		}catch (Exception e) {
			System.err.println("Erro inesperado! " + e.getMessage());
			e.printStackTrace();
		}
		return alp;
	}

	public boolean salvar(Pessoa p, Connection conn) throws SQLException{

		PreparedStatement stmt = conn.prepareStatement("INSERT INTO PESSOA (CPF, NM_PESSOA, DT_NASC, SEXO, TELEFONE, ALTURA) values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, p.getCPF());
		stmt.setString(2, p.getNome());
		stmt.setDate(3, new java.sql.Date(p.getDtNasc().getTime()));
		stmt.setString(4, p.getSexo());
		stmt.setString(5, p.getTelefone());
		stmt.setDouble(6, p.getAltura());

		int numLinha = stmt.executeUpdate();

		if(numLinha == 1) {// SUCESSO
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) { // EXISTE KEY
				int id = rs.getInt(1);
				p.setId(id);
			}else{
				return false;
			}
			return true;
		}else { // ERRO
			return false;
		}
	}

	public boolean atualizar(Connection conn, Pessoa p) throws SQLException {

		PreparedStatement stmt = conn.prepareStatement("update "
				+ "pessoa set "
				+ "NOME = ?, "
				+ "CPF = ?,"
				+ "DT_NASC = ?, "
				+ "SEXO = ?, "
				+ "TELEFONE = ?, "
				+ "ALTURA = ? "
				+ "WHERE ID_PESSOA = ?");

		stmt.setString(1, p.getNome());
		stmt.setString(2, p.getCPF());
		stmt.setDate(3, new java.sql.Date(p.getDtNasc().getTime()));
		stmt.setString(4, p.getSexo());
		stmt.setString(5, p.getTelefone());
		stmt.setDouble(6, p.getAltura());
		stmt.setInt(7, p.getId());

		int numLinha = stmt.executeUpdate();
		stmt.close();
		if(numLinha == 1) { // SUCESSO
			return true;
		}else { // ERRO
			return false;
		}

	}

	public boolean excluir(Connection conn, Pessoa p) throws SQLException {

		PreparedStatement stmt = conn.prepareStatement("delete "
				+ "from pessoa "
				+ "WHERE ID_PESSOA = ?");

		stmt.setInt(1, p.getId());

		int numLinha = stmt.executeUpdate();
		stmt.close();
		if(numLinha == 1) { // SUCESSO
			return true;

		}else { // ERRO
			return false;
		}
	}

}
