package br.com.faculdadedelta.projetoseriadojsfprime.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.projetoseriadojsfprime.dao.StatusDAO;
import br.com.faculdadedelta.projetoseriadojsfprime.modelo.Status;


@ManagedBean
@SessionScoped
public class StatusController {

	private Status status = new Status();
	private StatusDAO dao = new StatusDAO();

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void limparCampos() {
		status = new Status();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			if (status.getId() == null) {
					dao.incluir(status);
					limparCampos();
					exibirMensagem("Inclusão realizada com sucesso!");
			} else {
				dao.alterar(status);
				limparCampos();
				exibirMensagem("Alteração realizada com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação."
					+ " Tente novamente mais tarde. " + e.getMessage());
		}
		return "cadastroStatus.xhtml";
	}
	
	public String editar() {
		return "cadastroStatus.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(status);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação."
					+ " Tente novamente mais tarde. " + e.getMessage());			
		}
		return "listaStatus.xhtml";
	}
	
	public List<Status> getListar() {
		List<Status> listaRetorno = new ArrayList<>();
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
