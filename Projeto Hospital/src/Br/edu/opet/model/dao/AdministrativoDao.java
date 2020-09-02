package Br.edu.opet.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import Br.edu.opet.model.Adm;
import Br.edu.opet.model.Pessoa;

public class AdministrativoDao extends Pessoa{

	protected AdministrativoDao(String nome, String CPF, String sexo, String telefone, double altura, Date dtNasc){

		super(nome, CPF, sexo, telefone, altura, dtNasc);
	}

	protected boolean salvar(Adm a) {

		Connection conn = null;

		try{
			conn = Conexao.getConexao();
			conn.setAutoCommit(false);
			PreparedStatement stmt = null;

			if(super.salvar(conn)){// SALVAR PESSOA

				stmt = conn.prepareStatement("INSERT INTO ADMINISTRATIVO (ID_PESSOA, SALARIO) values (?,?)");

				stmt.setInt(1, a.getId());
				stmt.setDouble(2, a.getSalfixo());

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
