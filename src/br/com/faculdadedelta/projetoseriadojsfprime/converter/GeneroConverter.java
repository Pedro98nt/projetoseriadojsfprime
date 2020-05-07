package br.com.faculdadedelta.projetoseriadojsfprime.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.projetoseriadojsfprime.dao.GeneroDAO;
import br.com.faculdadedelta.projetoseriadojsfprime.modelo.Genero;

@FacesConverter(value = "generoConverter")
public class GeneroConverter implements Converter {

	private GeneroDAO dao = new GeneroDAO();
	
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
			return String.valueOf(((Genero)valor).getId());
		}
		return null;
	}

}
