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
    private double valorFahrenheit;
    private double valorCelsius;
    private double valorKelvin;
    
    public Temperatura() {
    	
    }
    
    public Temperatura(double valorFahrenheit, double valorCelsius, double valorKelvin) {
    	this.valorFahrenheit = valorFahrenheit;
    	this.valorCelsius = valorCelsius;
    	this.valorKelvin = valorKelvin;
    }

    public double getValorFahrenheit() {
        return valorFahrenheit;
    }

    public double getValorCelsius() {
        return valorCelsius;
    }


    public double getValorKelvin() {
        return valorKelvin;
    }
    
    public void valorFahrenheit(double valorFahrenheit) {
    	this.valorFahrenheit = valorFahrenheit;
    	this.valorCelsius = (valorFahrenheit - 32.0) / 1.8;
    	this.valorKelvin = ((valorFahrenheit - 32.0) / 1.8) + 273.15;
    }

    public void valorCelsius(double valorCelsius) {
//    	this.valorFahrenheit = ?;
        this.valorCelsius = valorCelsius;
        this.valorKelvin = ((valorCelsius - 32.0) / 1.8) + 273.15;
    }
    

    public void valorKelvin(double valorKelvin) {
        this.valorKelvin = valorKelvin;
//        this.valorFahrenheit = ?;
        this.valorCelsius = (273 - valorKelvin);
        this.valorKelvin = valorKelvin;
    }



}
