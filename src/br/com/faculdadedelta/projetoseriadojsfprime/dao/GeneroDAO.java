package br.com.faculdadedelta.projetoseriadojsfprime.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.projetoseriadojsfprime.modelo.Genero;
import br.com.faculdadedelta.projetoseriadojsfprime.util.Conexao;

public class GeneroDAO {

	public void incluir(Genero genero) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO genero (descricao) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, genero.getDescricao().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar(Genero genero) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE genero SET descricao = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, genero.getDescricao().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void excluir(Genero genero) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM genero WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, genero.getDescricao());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public Genero pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, descricao FROM genero WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Genero retorno = new Genero();
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
	
	public List<Genero> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, descricao FROM genero";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		List<Genero> listaRetorno = new ArrayList<>();
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Genero status = new Genero();
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
