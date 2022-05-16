package br.com.amcom.ee.desafio;

/**
 * POJO representando uma dada temperatura em 3 escalas diferentes:
 * Fahrenheit, Celsius e Kelvin.
 * 
 * @author desenvolvedor
 */
public class Temperatura {

    /**
     * Valor da temperatura em Fahrenheit
     */
    public double ValorFahrenheit;
    public double ValorCelsius;
    public double ValorKelvin;

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
