package br.com.faculdadedelta.projetoseriadojsfprime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.projetoseriadojsfprime.modelo.Status;
import br.com.faculdadedelta.projetoseriadojsfprime.util.Conexao;

public class StatusDAO {

	public void incluir(Status status) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO status (descricao) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, status.getDescricao().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar(Status status) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE status SET descricao = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, status.getDescricao().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void excluir(Status status) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM status WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, status.getDescricao());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public Status pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, descricao FROM status WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Status retorno = new Status();
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setDescricao(rs.getString("descricao"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return retorno;
	}
	
	public List<Status> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, descricao FROM status";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		List<Status> listaRetorno = new ArrayList<>();
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Status status = new Status();
				status.setId(rs.getLong("id"));
				status.setDescricao(rs.getString("descricao"));
				listaRetorno.add(status);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno; 
	}
}
