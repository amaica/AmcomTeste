package br.com.amcom.ee.desafio.api;

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

import br.com.amcom.ee.desafio.PaisesEntidade;
import br.com.amcom.ee.desafio.Temperatura;
import br.com.amcom.ee.desafio.TemperaturaEntidade;
import br.com.amcom.ee.desafio.TemperaturaRepositorio;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/temperatura")
@Api(value = "TemperaturaEntidade")
public class TemperaturasResource {

//    @Inject
//    Logger logger;
	private final static Logger LOGGER = Logger.getLogger(TemperaturasResource.class.getName());

	@Inject
	private TemperaturaRepositorio temperaturaRepositorio;

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

	public Object GetRangeTemperature(@PathParam("temperatura") int temperatura,
			@PathParam("tipoTemperatura") String tipoTemperatura) {
		Temperatura dados = new Temperatura();

		try {
			if (temperatura >= 18 || temperatura <= 25) {
				switch (tipoTemperatura) {
				case "FAHRENHEIT":
					dados.ValorFahrenheit = temperatura;
					LOGGER.info(String.format("Resultado FAHRENHEIT", dados.ValorFahrenheit));
					break;

				case "KELVIN":
					dados.ValorKelvin = dados.ValorCelsius + 273.15;
					LOGGER.info(String.format("Resultado concluído:", dados.ValorCelsius));
					break;

				default:
					dados.ValorCelsius = (temperatura - 32.0) / 1.8;
					LOGGER.info(String.format("Resultado concluído:", dados.ValorCelsius));
					break;
				}

			} else {
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
