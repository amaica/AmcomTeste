package br.com.amcom.ee.desafio.api.documentacao;



import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import br.com.amcom.ee.desafio.api.PaisesResource;
import br.com.amcom.ee.desafio.api.TemperaturasResource;
import io.swagger.jaxrs.Reader;
import io.swagger.models.Info;
import io.swagger.models.Swagger;
import io.swagger.util.Json;

@Path("/documentacao")
public class ServiceDocumentacao {

	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		resources.add(PaisesResource.class);
		resources.add(PaisesResource.class);
		resources.add(TemperaturasResource.class);
		return resources;
	}

	private String getJson(Swagger swagger) throws JsonProcessingException {
		String json = new Gson().toJson(getJsonSwagger(swagger));
		json = json.replaceAll("\\\\", "");
		json = json.substring(1, json.length() - 1);

		return json;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8" })
	public Response getJsonSwagger()
			throws JsonProcessingException, UnknownHostException, NoSuchAlgorithmException, UnsupportedEncodingException {
		Swagger swagger = new Reader(new Swagger()).read(getClasses());
		swagger.setBasePath("/documentacao-swagger/rest");
		setInfo(swagger);

		return Response.status(200).entity(getJson(swagger)).build();

	}

	private String getJsonSwagger(Swagger swagger) throws JsonProcessingException {
		return Json.mapper().writeValueAsString(swagger);
	}

	private void setInfo(Swagger swagger) {
		Info info = new Info();
		info.setTitle("Documentação  API REST c/ Swagger");

		StringBuilder descricao = new StringBuilder();
		descricao.append("Utilize os serviços listados abaixo para consultar, inserir ou apagar informações ");

		info.setDescription(descricao.toString());

		swagger.setInfo(info);
	}
}