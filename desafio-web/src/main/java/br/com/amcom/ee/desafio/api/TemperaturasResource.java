package br.com.amcom.ee.desafio.api;

import br.com.amcom.ee.desafio.PaisesEntidade;
import br.com.amcom.ee.desafio.Temperatura;
import br.com.amcom.ee.desafio.TemperaturaEntidade;
import br.com.amcom.ee.desafio.TemperaturaRepositorio;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/temperatura")
@Api(value = "TemperaturaEntidade")
public class TemperaturasResource {

//    @Inject
//    Logger logger;
    private final static Logger LOGGER = Logger.getLogger(TemperaturasResource.class.getName());

    @Inject
    private TemperaturaRepositorio temperaturaRepositorio;
    
    
    
    
    
  /*  1)	Retornar a lista de países como origem o arquivo ‘países.json’ e disponibilizar as informações em um endpoint.
    2)	Verificar o Controller de Temperaturas e verificar itens para serem melhorados e consertados.
    3)	Cálculos de temperaturas já realizados, não precisam ser calculados novamente em novas requisições, devem ser armazenados (cache) e retornados quando requisitado.
    4)	Crie um novo endpoint para retornar temperaturas que estejam dentro de uma terminada faixa (padrão entre 18 e 25 graus Celsius) informada pelo consumidor e na escala informada: Celsius, Fahrenheit e Kelvin (se não for informada, utilizar Celsius).
    5)	Retornar dados da API https://reqres.in/api/users?page=2 e aplicar filtros para buscar pessoas por email e/ou nome.
    6)	Documente os endpoints no OpenApi/Swagger.
    7)  Transforme a tela de consultar temperaturas registradas no banco em um CRUDL completo.
    8)  Crie a tela de CRUDL de países.
    9)  Aplique o tema "Vela" do Primefaces.
    10)	Publique seu código em um repositório git privado e nos dê acesso de leitura 😊.
    11)	Crie uma imagem Docker do seu aplicativo e publique lá no Docker Hub.*/
    
    
    

    @GET
    @Path("/Fahrenheit/{temperatura}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Recebe temperatura para conversão: Fahrenhei,Celsius ou Kelvin")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PaisesEntidade.class),
			@ApiResponse(code = 204, message = "Nenhum conteúdo") })
    public Object GetConversaoFahrenheit(@PathParam("temperatura") int temperatura) {
        Temperatura dados = new Temperatura();

        try {
            LOGGER.info(String.format("Recebida temperatura para conversão: %s", temperatura));

            dados.ValorFahrenheit = temperatura;
            dados.ValorCelsius = (temperatura - 32.0) / 1.8;
            dados.ValorKelvin = dados.ValorCelsius + 273.15;

        } catch (Exception err) {
            LOGGER.info("Ocorreu um problema ao converter: " + err.getMessage());
        }

        LOGGER.info(String.format("Resultado concluído: {0}", dados.ValorCelsius));
        LOGGER.info(String.format("Resultado concluído: {0}", dados.ValorFahrenheit));
        LOGGER.info(String.format("Resultado concluído: {0}", dados.ValorKelvin));
        return dados;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperatura> listaTemperatura() {
        List<TemperaturaEntidade> temperaturaEntidades = temperaturaRepositorio.listarTodas();
        
        List<Temperatura> temperaturas = new ArrayList<Temperatura>();
        
        for (TemperaturaEntidade temperaturaEntidade : temperaturaEntidades) {
            Temperatura temperatura = new Temperatura();
            
            temperatura.ValorKelvin = temperaturaEntidade.getValorKelvin();
            temperatura.ValorFahrenheit = temperaturaEntidade.getValorCelsius();
            temperatura.ValorCelsius = temperaturaEntidade.getValorFahrenheit();
            
            temperaturas.add(temperatura);
        }
        
        return temperaturas;
    }
    
    
    @GET
    @Path("/Temperatura/{temperatura}/{tipoTemperatura}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Recebe temperatura para conversão com opção de enviar o tipo de temperatura por parametro")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PaisesEntidade.class),
			@ApiResponse(code = 204, message = "Nenhum conteúdo") })
    
    public Object GetRangeTemperature(@PathParam("temperatura") int temperatura, @PathParam("tipoTemperatura") String tipoTemperatura) {
        Temperatura dados = new Temperatura();

        try {
        	if(temperatura >=18 || temperatura <=25) {
        	   
                 if(tipoTemperatura.equals("FAHRENHEIT")) {
                	 dados.ValorFahrenheit = temperatura;
                	  LOGGER.info(String.format("Resultado FAHRENHEIT", dados.ValorFahrenheit));
                 } else
                 if(tipoTemperatura.equals("CELCIUS")) {
                	 dados.ValorCelsius = (temperatura - 32.0) / 1.8;
                	 LOGGER.info(String.format("Resultado concluído:", dados.ValorCelsius));
                 } else if(tipoTemperatura.equals("KELVIN")) {
                	 dados.ValorKelvin = dados.ValorCelsius + 273.15;
                	 LOGGER.info(String.format("Resultado concluído:", dados.ValorCelsius));
                 }else {
                	 dados.ValorCelsius = (temperatura - 32.0) / 1.8;
                	 LOGGER.info(String.format("Resultado concluído:", dados.ValorCelsius));
                 }
        	
                 
    
               
        	}else {
        		  LOGGER.info("Temperatura inválida, digite entre 18 e 25");
        	}
        

        } catch (Exception err) {
            LOGGER.info("Ocorreu um problema : " + err.getMessage());
        }

        LOGGER.info(String.format("Resultado concluído: {0}", dados.ValorCelsius));
        LOGGER.info(String.format("Resultado concluído: {0}", dados.ValorFahrenheit));
        LOGGER.info(String.format("Resultado concluído: {0}", dados.ValorKelvin));
        return dados;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Salva Temperatura em TXT")
 	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PaisesEntidade.class),
 			@ApiResponse(code = 204, message = "Nenhum conteúdo") })
    public Response SalvaTemperaturatxt(Temperatura temperatura) throws FileNotFoundException {

        TemperaturaEntidade temperaturaEntidade = new TemperaturaEntidade();

        temperaturaEntidade.setId(Instant.now().toEpochMilli());
        temperaturaEntidade.setValorCelsius(temperatura.ValorCelsius);
        temperaturaEntidade.setValorFahrenheit(temperatura.ValorFahrenheit);
        temperaturaEntidade.setValorKelvin(temperatura.ValorKelvin);

        temperaturaRepositorio.salvar(temperaturaEntidade);

        String diretorioUsuarioSistemaOperacioanal = System.getProperty("user.home");
        PrintWriter file = new PrintWriter(diretorioUsuarioSistemaOperacioanal + "//temperatura.txt");

        file.println(temperatura.ValorKelvin);
        file.println(temperatura.ValorCelsius);
        file.println(temperatura.ValorFahrenheit);

        file.close();

        return Response.ok().build();
    }

  
   
}
