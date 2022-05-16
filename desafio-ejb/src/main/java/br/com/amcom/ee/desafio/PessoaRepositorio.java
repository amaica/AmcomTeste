package br.com.amcom.ee.desafio;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;





/*import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;*/

@Dependent
@Stateless
public class PessoaRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void salvar(@NotNull PessoaEntidade pessoaEntidade) {
		System.out.println("Salvando pessoa: " + pessoaEntidade);
		em.persist(pessoaEntidade);
	}

	public List<PessoaEntidade> listarTodos() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PessoaEntidade> cq = cb.createQuery(PessoaEntidade.class);
		Root<PessoaEntidade> root = cq.from(PessoaEntidade.class);

		cq.select(root);

		TypedQuery<PessoaEntidade> query = em.createQuery(cq);

		return query.getResultList();
	}



	public void buscaPessoa() throws Exception 
	{
	try {
		HttpGet request = new HttpGet("https://reqres.in/api/users?page=2");
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity);
		/// System.out.println(result);
		JSONObject obj = JSONObject.parseObject(result);
	    

	
		
	     
		for (int i = 0; i < obj.getJSONArray("data").size(); i++) {
			
		}
	    
	     
	     
	} catch (Exception e) {
		e.getMessage();
		// TODO: handle exception
	}
		
	}

	public static void main(String[] args) {
		PessoaRepositorio obj = new PessoaRepositorio();
		try {
			obj.buscaPessoa();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO code application logic here
	}

}
