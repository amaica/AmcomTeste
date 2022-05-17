package br.com.amcom.ee.desafio.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.amcom.ee.desafio.Pais;
import br.com.amcom.ee.desafio.PaisesEntidade;
import br.com.amcom.ee.desafio.PaisesRepositorio;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/paises")
@Api(value = "PaisesEntidade")
public class PaisesResource {

//    @Inject
//    Logger logger;
	private final static Logger LOGGER = Logger.getLogger(PaisesResource.class.getName());

	@Inject
	private PaisesRepositorio paisRepositorio;

	@GET
	/// @Path("/pais")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Lista Paises do arquivo Jason")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PaisesEntidade.class),
			@ApiResponse(code = 204, message = "Nenhum conteúdo") })
	public List<PaisesEntidade> listaPaises() throws IOException {

		return paisRepositorio.listaJson();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Salva Pais em arquivo txt")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PaisesEntidade.class),
			@ApiResponse(code = 204, message = "Nenhum conteúdo") })
	public Response SalvaPaistxt(Pais pais) throws FileNotFoundException {

		PaisesEntidade paisEntidade = new PaisesEntidade();

		paisEntidade.setId(Instant.now().toEpochMilli());
		paisEntidade.setGentilico(pais.gentilico);
		paisEntidade.setNome_pais(pais.nome_pais);
		paisEntidade.setNome_pais_int(pais.nome_pais_int);

		paisRepositorio.salvar(paisEntidade);

		String diretorioUsuarioSistemaOperacioanal = System.getProperty("user.home");
		PrintWriter file = new PrintWriter(diretorioUsuarioSistemaOperacioanal + "/pais.txt");

		file.println(pais.gentilico);
		file.println(pais.nome_pais);
		file.println(pais.nome_pais_int);

		file.close();

		return Response.ok().build();
	}

	@GET
	@Path("/pais")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RetornaPaises() {
		return Response.ok().build();
	}

	@GET
	@Path("/pais-por-sigla")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RetornaPaisPorSigla() {
		return Response.ok().build();
	}

}
