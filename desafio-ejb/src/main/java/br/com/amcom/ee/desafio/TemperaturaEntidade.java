package br.com.amcom.ee.desafio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TemperaturaEntidade implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
    private Long id;
    @NotNull
    @Column(name = "valor_fahrenheit", nullable = false)
    private double ValorFahrenheit;
    private double ValorCelsius;
    private double ValorKelvin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorFahrenheit() {
        return ValorFahrenheit;
    }

    public void setValorFahrenheit(double ValorFahrenheit) {
        this.ValorFahrenheit = ValorFahrenheit;
    }

    public double getValorCelsius() {
        return ValorCelsius;
    }

    public void setValorCelsius(double ValorCelsius) {
        this.ValorCelsius = ValorCelsius;
    }

    public double getValorKelvin() {
        return ValorKelvin;
    }

    public void setValorKelvin(double ValorKelvin) {
        this.ValorKelvin = ValorKelvin;
    }

}
