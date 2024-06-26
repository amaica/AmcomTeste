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
import br.com.amcom.ee.desafio.TemperaturaEntidade;
import br.com.amcom.ee.desafio.TemperaturaRepositorio;
import br.com.amcom.ee.desafio.util.FacesUtil;

@Named
@ViewScoped
public class TemperaturasVisao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TemperaturaRepositorio temperaturaRepositorio;
	private List<TemperaturaEntidade> temperaturas;
	@Inject
	private TemperaturaEntidade objeto;

	@PostConstruct
	private void carregaTemperaturas() {
		if (FacesUtil.isNotPostback()) {
			temperaturas = listaTemperatura();

		}
	}

	private List<TemperaturaEntidade> listaTemperatura() {
		List<TemperaturaEntidade> temperaturaEntidades = temperaturaRepositorio.listarTodas();

		return temperaturaEntidades;
	}

	/*
	 * private List<Temperatura> listaTemperatura() { List<TemperaturaEntidade>
	 * temperaturaEntidades = temperaturaRepositorio.listarTodas();
	 * 
	 * List<Temperatura> temperaturas = new ArrayList<Temperatura>();
	 * 
	 * for (TemperaturaEntidade temperaturaEntidade : temperaturaEntidades) {
	 * Temperatura temperatura = new Temperatura();
	 * 
	 * temperatura.ValorKelvin = temperaturaEntidade.getValorKelvin();
	 * temperatura.ValorFahrenheit = temperaturaEntidade.getValorCelsius();
	 * temperatura.ValorCelsius = temperaturaEntidade.getValorFahrenheit();
	 * 
	 * temperaturas.add(temperatura); }
	 * 
	 * return temperaturas; }
	 */

	public void novo() {
		try {
			this.objeto = new TemperaturaEntidade();
		} catch (Exception e) {
			e.getMessage();
			// TODO: handle exception
		}

	}

	public void excluir() {
		try {
			temperaturaRepositorio.remover(objeto);
			temperaturas.remove(getObjeto());

			FacesUtil.addInfoMessage("Registro " + objeto.getId() + " excluído com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao exluir " + objeto.getId() + e.getMessage());
			// TODO: handle exception
		}

	}

	public void salvar() {
		try {

			if (this.objeto.getId() != null) {
				temperaturaRepositorio.update(this.objeto);
			} else {
				temperaturaRepositorio.salvar(this.objeto);

			}
			FacesUtil.addInfoMessage("Salvo com sucesso!");
			this.objeto = new TemperaturaEntidade();
			carregaTemperaturas();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/desafio-web-1.0.0-SNAPSHOT/temperaturas.xhtml");
			PrimeFaces.current().ajax().update(":form1");

		} catch (Exception e) {
			e.getMessage();
			// TODO: handle exception
		}

	}

	public List<TemperaturaEntidade> getTemperaturas() {
		return temperaturas;
	}

	public void setTemperaturas(List<TemperaturaEntidade> temperaturas) {
		this.temperaturas = temperaturas;
	}

	public TemperaturaEntidade getObjeto() {
		return objeto;
	}

	public void setObjeto(TemperaturaEntidade objeto) {
		this.objeto = objeto;
	}

}