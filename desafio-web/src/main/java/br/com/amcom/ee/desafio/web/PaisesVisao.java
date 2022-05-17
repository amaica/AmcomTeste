package br.com.amcom.ee.desafio.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.amcom.ee.desafio.PaisesEntidade;
import br.com.amcom.ee.desafio.PaisesRepositorio;
import br.com.amcom.ee.desafio.util.FacesUtil;

@Named
@ViewScoped
public class PaisesVisao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private PaisesRepositorio paisesRepositorio;
    @Inject
    private PaisesEntidade objeto;
    private List<PaisesEntidade> paisList;
  
    @PostConstruct
    private void carregaPaises() {
    	if (FacesUtil.isNotPostback()) {
        paisList = listaPaises();
       
    	}
    }

    
  

	
    
    private List<PaisesEntidade> listaPaises() {
        List<PaisesEntidade> temperaturaEntidades = paisesRepositorio.listarTodos();

      

        return temperaturaEntidades;
    }
    
    
  /*  private List<Pais> listaPaises() {
        List<PaisesEntidade> temperaturaEntidades = paisesRepositorio.listarTodos();

        List<Pais> paises = new ArrayList<Pais>();

        for (PaisesEntidade temperaturaEntidade : temperaturaEntidades) {
            Pais pais = new Pais();

            pais.gentilico = temperaturaEntidade.getGentilico();
            pais.nome_pais = temperaturaEntidade.getNome_pais();
            pais.nome_pais_int = temperaturaEntidade.getNome_pais_int();

            paisList.add(pais);
        }

        return paises;
    }*/
    public void novo() {
    	try {
    		this.objeto = new PaisesEntidade();
		} catch (Exception e) {
			e.getMessage();
			// TODO: handle exception
		}
    	 
    }
    
    public void salvar() {
    	try {
    		if(this.objeto.getId()!=null) {
    			paisesRepositorio.update(this.objeto);
    		}else {
    			paisesRepositorio.salvar(this.objeto);
    			
       		 
    		}
    		FacesUtil.addInfoMessage("Salvo com sucesso!");
    		this.objeto = new PaisesEntidade();
    		paisList = listaPaises();
    		FacesContext.getCurrentInstance().getExternalContext().redirect("/desafio-web-1.0.0-SNAPSHOT/paises.xhtml");
			PrimeFaces.current().ajax().update(":form1");
		} catch (Exception e) {
			e.getMessage();
			// TODO: handle exception
		}
    	 
    }
    
    
    
    
    public void excluir() {
		try {
			paisesRepositorio.remover(objeto);
			paisList.remove(getObjeto());

			FacesUtil.addInfoMessage("Registro " + objeto.getId() + " excluído com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao exluir " + objeto.getId() + e.getMessage());
			// TODO: handle exception
		}

	}





	public PaisesEntidade getObjeto() {
		return objeto;
	}





	public void setObjeto(PaisesEntidade objeto) {
		this.objeto = objeto;
	}





	public List<PaisesEntidade> getPaisList() {
		return paisList;
	}





	public void setPaisList(List<PaisesEntidade> paisList) {
		this.paisList = paisList;
	}

	
    
    
   

	
   
}
