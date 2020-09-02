package Br.edu.opet.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import Br.edu.opet.model.Med;
import Br.edu.opet.model.Pessoa;

public class MedicoDAO extends Pessoa{

	protected  MedicoDAO(String nome, String CPF, String sexo, String telefone, double altura, Date dtNasc) {
		super(nome, CPF, sexo, telefone, altura, dtNasc);
	}

	protected boolean salvar(Med m) {

		Connection conn = null;

		try{
			conn = Conexao.getConexao();
			conn.setAutoCommit(false);
			PreparedStatement stmt = null;

			if(super.salvar(conn)){// SALVAR PESSOA

				stmt = conn.prepareStatement("INSERT INTO MEDICO (ID_PESSOA, ID_ESPECIALIDADE) values (?,?)");

				stmt.setInt(1, m.getId());
				stmt.setInt(2, m.getEspecialidade().getId());

				int numLinha = stmt.executeUpdate();

				if(numLinha == 1) {// SUCESSO
					conn.commit();
				}else { // ERRO
					conn.rollback();
				}
			}else {
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

	protected boolean atualizar(Med m){

		Connection conn = null;

		try{
			conn = Conexao.getConexao();
			conn.setAutoCommit(false);
			PreparedStatement stmt = null;

			if(super.atualizar(conn)){// SALVAR PESSOA

				stmt = conn.prepareStatement("UPDATE MEDICO SET ID_ESPECIALIDADE WHERE ID_PESSOA = ?");

				stmt.setInt(1, m.getEspecialidade().getId());
				stmt.setInt(2, m.getId());

				int numLinha = stmt.executeUpdate();

				if(numLinha == 1) {// SUCESSO
					conn.commit();
				}else { // ERRO
					conn.rollback();
				}
			}else {
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

	protected boolean excluir(Med m) {

		Connection conn = null;

		try{
			conn = Conexao.getConexao();
			conn.setAutoCommit(false);
			PreparedStatement stmt = null;

			if(super.excluir(conn)){// EXCLUIR PESSOA

				stmt = conn.prepareStatement("UPDATE MEDICO SET ID_ESPECIALIDADE WHERE ID_PESSOA = ?");

				stmt.setInt(1, m.getEspecialidade().getId());
				stmt.setInt(2, m.getId());

				int numLinha = stmt.executeUpdate();
 
				if(numLinha == 1) {// SUCESSO
					conn.commit();
				}else { // ERRO
					conn.rollback();
				}
			}else {
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
