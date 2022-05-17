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

@Path("/paises")
public class PaisesResource {

//    @Inject
//    Logger logger;
	private final static Logger LOGGER = Logger.getLogger(PaisesResource.class.getName());

	@Inject
	private PaisesRepositorio paisRepositorio;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PaisesEntidade> listaPaises() throws IOException {

		return paisRepositorio.listaJson();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
		public Response salvaPaistxt(Pais pais) throws FileNotFoundException {

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


}
