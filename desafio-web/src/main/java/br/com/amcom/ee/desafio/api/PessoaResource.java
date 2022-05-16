package br.com.amcom.ee.desafio.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
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

import br.com.amcom.ee.desafio.Pessoa;
import br.com.amcom.ee.desafio.PessoaEntidade;
import br.com.amcom.ee.desafio.PessoaRepositorio;


@Path("/pessoas")
public class PessoaResource {

//    @Inject
//    Logger logger;
    private final static Logger LOGGER = Logger.getLogger(PessoaResource.class.getName());

    @Inject
    private PessoaRepositorio pessoaRepositorio;

    //  5)	Retornar dados da API https://reqres.in/api/users?page=2 e aplicar filtros para buscar pessoas por email e/ou nome.
   	public void buscarPessoa(String cnpj) throws IOException {
   	///Gson gson = new Gson();

   		URL url = new URL("https://reqres.in/api/users?" + cnpj);
   		
   	}
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> listaPessoas() {
        List<PessoaEntidade> paisEntidades = pessoaRepositorio.listarTodos();
        
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        
        for (PessoaEntidade paisEntidade : paisEntidades) {
            Pessoa pessoa = new Pessoa();
            
            pessoa.avatar = paisEntidade.getAvatar();
            pessoa.first_name = paisEntidade.getFirst_name();
            pessoa.last_name = paisEntidade.getLast_name();
            pessoa.email = paisEntidade.getEmail();
            
            pessoas.add(pessoa);
        }
        
        return pessoas;
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response SalvaPaistxt(Pessoa pessoa) throws FileNotFoundException {

        PessoaEntidade paisEntidade = new PessoaEntidade();

        paisEntidade.setId(Instant.now().toEpochMilli());
        paisEntidade.setEmail(pessoa.getEmail());
        paisEntidade.setFirst_name(pessoa.getFirst_name());
        paisEntidade.setLast_name(pessoa.getLast_name());
        paisEntidade.setAvatar(pessoa.getAvatar());

        pessoaRepositorio.salvar(paisEntidade);

        String diretorioUsuarioSistemaOperacioanal = System.getProperty("user.home");
        PrintWriter file = new PrintWriter(diretorioUsuarioSistemaOperacioanal + "/pessoa.txt");

        file.println(pessoa.email);
        file.println(pessoa.avatar);
        file.println(pessoa.first_name);
        file.println(pessoa.last_name);

        file.close();

        return Response.ok().build();
    }

    @GET
    @Path("/pessoa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response RetornaPaises() {
        return Response.ok().build();
    }

  
}