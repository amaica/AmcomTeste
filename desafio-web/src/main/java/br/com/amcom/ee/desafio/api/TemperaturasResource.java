package br.com.amcom.ee.desafio.api;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.amcom.ee.desafio.Temperatura;
import br.com.amcom.ee.desafio.TemperaturaEntidade;
import br.com.amcom.ee.desafio.TemperaturaRepositorio;

@Path("/temperatura")
//@Api(value = "TemperaturaEntidade")
public class TemperaturasResource {

//    @Inject
//    Logger logger;
	private final static Logger LOGGER = Logger.getLogger(TemperaturasResource.class.getName());

	@Inject
	private TemperaturaRepositorio temperaturaRepositorio;

	@GET
	@Path("/Fahrenheit/{temperatura}")
	@Produces(MediaType.APPLICATION_JSON)
//	@ApiOperation(value = "Recebe temperatura para conversão: Fahrenhei,Celsius ou Kelvin")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PaisesEntidade.class),
//			@ApiResponse(code = 204, message = "Nenhum conteúdo") })
	public Object getConversaoFahrenheit(@PathParam("temperatura") int temperatura) {
		Temperatura dados = new Temperatura();

		try {
			LOGGER.info(String.format("Recebida temperatura para conversão: %s", temperatura));

			dados.valorFahrenheit(temperatura);

		} catch (Exception err) {
			LOGGER.info("Ocorreu um problema ao converter: " + err.getMessage());
		}

		LOGGER.info(String.format("Resultado Celsius: %s", dados.getValorCelsius()));
		LOGGER.info(String.format("Resultado Fahrenheit: %s", dados.getValorFahrenheit()));
		LOGGER.info(String.format("Resultado Kelvin: %s", dados.getValorKelvin()));
		return dados;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Temperatura> listaTemperatura() {
		List<TemperaturaEntidade> temperaturaEntidades = temperaturaRepositorio.listarTodas();

		List<Temperatura> temperaturas = temperaturaEntidades.stream().map(t -> 
			new Temperatura(t.getValorFahrenheit(), t.getValorCelsius(), t.getValorKelvin())
		).collect(Collectors.toList());


		return temperaturas;
	}

	@GET
	@Path("/{temperatura}/{tipoTemperatura}")
	@Produces(MediaType.APPLICATION_JSON)
//	@ApiOperation(value = "Recebe temperatura para conversão com opção de enviar o tipo de temperatura por parametro")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PaisesEntidade.class),
//			@ApiResponse(code = 204, message = "Nenhum conteúdo") })

	public Object getRangeTemperature(@PathParam("temperatura") int temperatura,
			@PathParam("tipoTemperatura") String tipoTemperatura) {
		Temperatura dados = new Temperatura();

		try {
			if (temperatura >= 18 || temperatura <= 25) {
				switch (tipoTemperatura) {
				case "FAHRENHEIT":
					dados.valorFahrenheit(temperatura);
					LOGGER.info(String.format("Resultado FAHRENHEIT", dados.getValorFahrenheit()));
					break;

				case "KELVIN":
					dados.valorKelvin(temperatura);
					LOGGER.info(String.format("Resultado KELVIN:", dados.getValorKelvin()));
					break;

				default:
					dados.valorCelsius(temperatura);
					LOGGER.info(String.format("Resultado CELSIUS:", dados.getValorCelsius()));
					break;
				}

			} else {
				LOGGER.info("Temperatura inválida, digite entre 18 e 25");
			}

		} catch (Exception err) {
			LOGGER.info("Ocorreu um problema : " + err.getMessage());
		}

		LOGGER.info(String.format("Resultado concluído: %s", dados.getValorCelsius()));
		LOGGER.info(String.format("Resultado concluído: %s", dados.getValorFahrenheit()));
		LOGGER.info(String.format("Resultado concluído: %s", dados.getValorKelvin()));
		return dados;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
//	@ApiOperation(value = "Salva Temperatura em TXT")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PaisesEntidade.class),
//			@ApiResponse(code = 204, message = "Nenhum conteúdo") })
	public Response salvaTemperaturatxt(Temperatura temperatura) throws FileNotFoundException {

		TemperaturaEntidade temperaturaEntidade = new TemperaturaEntidade();

		temperaturaEntidade.setId(Instant.now().toEpochMilli());
		temperaturaEntidade.setValorCelsius(temperatura.getValorCelsius());
		temperaturaEntidade.setValorFahrenheit(temperatura.getValorFahrenheit());
		temperaturaEntidade.setValorKelvin(temperatura.getValorKelvin());

		temperaturaRepositorio.salvar(temperaturaEntidade);

		String diretorioUsuarioSistemaOperacioanal = System.getProperty("user.home");
		PrintWriter file = new PrintWriter(diretorioUsuarioSistemaOperacioanal + "//temperatura.txt");

		file.println(temperatura.getValorKelvin());
		file.println(temperatura.getValorCelsius());
		file.println(temperatura.getValorFahrenheit());

		file.close();

		return Response.ok().build();
	}

}
