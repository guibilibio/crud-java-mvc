package Br.edu.opet.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Br.edu.opet.model.Especialidade;

public class EspecialidadeDAO {

	protected boolean salvar(Especialidade esp){

		Connection conn = null;

		try{
			conn = Conexao.getConexao();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement("INSERT INTO ESPECIALIDADE (DESC_ESPECIALIDADE) values (?)");

			stmt.setString(1, esp.getDescrição());

			int numLinha = stmt.executeUpdate();

			if(numLinha == 1) { // SUCESSO
				conn.commit();

			}else { // ERRO
				conn.rollback();
			}

			stmt.close();
			conn.close();

			return true;

		}catch(Exception e){ // ERRO

			try{
				conn.rollback();
				conn.close();
				return false;
			}catch (SQLException e1) {
				return false;
			}
		}

	}

	protected ArrayList<Especialidade> listar(){

		ArrayList<Especialidade> alE = new ArrayList<>();

		try{
			Connection conn = Conexao.getConexao();

			PreparedStatement stmt = conn. prepareStatement("SELECT ID_ESPECIALIDADE, DESC_ESPECIALIDADE FROM ESPECIALIDADE");

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Especialidade e  = new Especialidade();
				e.setId(rs.getInt("ID_ESPECIALIDADE"));
				e.setDescrição(rs.getString("DESC_ESPECIALIDADE"));

				alE.add(e);
			}

			rs.close();
			stmt.close();
			conn.close();

		}catch (Exception e) {
			System.err.println("Erro inesperado! " + e.getMessage());
			e.printStackTrace();
		}
		return alE;

	}

	protected boolean consultar(Especialidade especialidade) {

		boolean temDados = false;

		try{
			Connection conn = Conexao.getConexao();

			PreparedStatement stmt = conn. prepareStatement("SELECT ID_ESPECIALIDADE, DESC_ESPECIALIDADE FROM ESPECIALIDADE WHERE ID_ESPECIALIDADE = ?");

			stmt.setInt(1, especialidade.getId());

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				especialidade.setId(rs.getInt("ID_ESPECIALIDADE"));
				especialidade.setDescrição(rs.getString("DESC_ESPECIALIDADE"));
				temDados = true;
			}

			rs.close();
			stmt.close();
			conn.close();

		}catch (Exception e) {
			System.err.println("Erro inesperado! " + e.getMessage());
			e.printStackTrace();
		}
		return temDados;
	}

	protected boolean atualizar(Especialidade especialidade) {

		Connection conn = null;

		try{
			conn = Conexao.getConexao();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement("UPDATE ESPECIALIDADE SET DESC_ESPECIALIDADE = ? WHERE ID_ESPECIALIDADE = ?");

			stmt.setString(1, especialidade.getDescrição());
			stmt.setInt(2, especialidade.getId());

			int numLinha = stmt.executeUpdate();

			if(numLinha == 1) { // SUCESSO
				conn.commit();

			}else { // ERRO
				conn.rollback();
			}

			stmt.close();
			conn.close();

			return true;

		}catch(Exception e){ // ERRO

			try{
				conn.rollback();
				conn.close();
				return false;
			}catch (SQLException e1) {
				return false;
			}
		}
	}

	public boolean excluir(Especialidade especialidade) {
		
		Connection conn = null;

		try{
			conn = Conexao.getConexao();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement("DELETE FROM ESPECIALIDADE WHERE ID_ESPECIALIDADE = ?");

			stmt.setInt(1, especialidade.getId());

			int numLinha = stmt.executeUpdate();

			if(numLinha == 1) { // SUCESSO
				conn.commit();

			}else { // ERRO
				conn.rollback();
			}

			stmt.close();
			conn.close();

			return true;

		}catch(Exception e){ // ERRO

			try{
				conn.rollback();
				conn.close();
				return false;
			}catch (SQLException e1) {
				return false;
			}
		}
	}

}
