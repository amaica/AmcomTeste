package br.com.amcom.ee.desafio.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.amcom.ee.desafio.PaisesEntidade;
import br.com.amcom.ee.desafio.PaisesRepositorio;
import br.com.amcom.ee.desafio.util.FacesUtil;
import java.io.Serializable;

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
        this.objeto = new PaisesEntidade();
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
    		paisesRepositorio.salvar(this.objeto);
    		this.objeto = new PaisesEntidade();
    		 paisList = listaPaises();
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
