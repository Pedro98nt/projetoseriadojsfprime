package br.com.faculdadedelta.projetoseriadojsfprime.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.projetoseriadojsfprime.dao.SerieDAO;
import br.com.faculdadedelta.projetoseriadojsfprime.modelo.Genero;
import br.com.faculdadedelta.projetoseriadojsfprime.modelo.Serie;
import br.com.faculdadedelta.projetoseriadojsfprime.modelo.Status;

@ManagedBean
@SessionScoped
public class SerieController {

	private Serie serie = new Serie();
	private SerieDAO dao = new SerieDAO();
	private Genero generoSelecionado = new Genero();
	private Status statusSelecionado = new Status();

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Genero getGeneroSelecionado() {
		return generoSelecionado;
	}

	public void setGeneroSelecionado(Genero generoSelecionado) {
		this.generoSelecionado = generoSelecionado;
	}

	public Status getStatusSelecionado() {
		return statusSelecionado;
	}

	public void setStatusSelecionado(Status statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	public void limparCampos() {
		serie = new Serie();
		generoSelecionado = new Genero();
		statusSelecionado = new Status();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			if (serie.getId() == null) {
					if (dao.pesquisarSeriePorNome(serie.getNome()) == null) {
						serie.setGenero(generoSelecionado);
						serie.setStatus(statusSelecionado);
						dao.incluir(serie);
						limparCampos();
						exibirMensagem("Inclusão realizada com sucesso!");
					} else {
						exibirMensagem("Já exixte essa serie cadastrada!");
					}
			} else {
				serie.setGenero(generoSelecionado);
				serie.setStatus(statusSelecionado);
				dao.alterar(serie);
				limparCampos();
				exibirMensagem("Alteração realizada com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação."
					+ " Tente novamente mais tarde. " + e.getMessage());
		}
		return "cadastroSerie.xhtml";
	}
	
	public String editar() {
		generoSelecionado = serie.getGenero();
		statusSelecionado = serie.getStatus();
		return "cadastroSerie.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(serie);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação."
					+ " Tente novamente mais tarde. " + e.getMessage());			
		}
		return "listaSerie.xthml";
	}
	
	public List<Serie> getLista() {
		List<Serie> listaRetorno = new ArrayList<>();
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
