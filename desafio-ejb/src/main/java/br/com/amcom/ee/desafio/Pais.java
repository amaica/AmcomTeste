package br.com.amcom.ee.desafio;

/**
 * POJO representando uma dada temperatura em 3 escalas diferentes:
 * Fahrenheit, Celsius e Kelvin.
 * 
 * @author desenvolvedor
 */
public class Pais {
	
    
    public String  gentilico;
    public String nome_pais;
    public String nome_pais_int;
    public String sigla;

    public String getGentilico() {
        return gentilico;
    }

    public void setGentilico(String gentilico) {
        this.gentilico = gentilico;
    }

    public String getNome_pais() {
        return nome_pais;
    }

    public void setNome_pais(String nome_pais) {
        this.nome_pais = nome_pais;
    }

    public String getNome_pais_int() {
        return nome_pais_int;
    }

    public void setNome_pais_int(String nome_pais_int) {
        this.nome_pais_int = nome_pais_int;
    }

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

   

}
