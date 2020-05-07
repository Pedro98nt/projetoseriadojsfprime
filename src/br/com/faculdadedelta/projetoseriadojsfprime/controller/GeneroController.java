package br.com.faculdadedelta.projetoseriadojsfprime.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.projetoseriadojsfprime.dao.GeneroDAO;
import br.com.faculdadedelta.projetoseriadojsfprime.modelo.Genero;

@ManagedBean
@SessionScoped
public class GeneroController {

	private Genero genero = new Genero();
	private GeneroDAO dao = new GeneroDAO();

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void limparCampos() {
		genero = new Genero();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			if (genero.getId() == null) {
					dao.incluir(genero);
					limparCampos();
					exibirMensagem("Inclusão realizada com sucesso!");
			} else {
				dao.alterar(genero);
				limparCampos();
				exibirMensagem("Alteração realizada com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação."
					+ " Tente novamente mais tarde. " + e.getMessage());
		}
		return "cadastroGenero.xhtml";
	}
	
	public String editar() {
		return "cadastroGenero.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(genero);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação."
					+ " Tente novamente mais tarde. " + e.getMessage());			
		}
		return "listaGenero.xhtml";
	}
	
	public List<Genero> getListar() {
		List<Genero> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação."
					+ " Tente novamente mais tarde. " + e.getMessage());			
		}
		return listaRetorno;
	}
}