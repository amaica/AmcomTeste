package br.com.amcom.ee.desafio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaisesEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
    private Long id;
    private String gentilico;
    private String nome_pais;
    private String nome_pais_int;
    private String sigla;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
