package br.com.faculdadedelta.projetoseriadojsfprime.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.projetoseriadojsfprime.dao.StatusDAO;
import br.com.faculdadedelta.projetoseriadojsfprime.modelo.Status;

@FacesConverter(value = "statusConverter")
public class StatusConverter implements Converter {

private StatusDAO dao = new StatusDAO();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if (valor != null) {
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((Status)valor).getId());
		}
		return null;
	}

}
