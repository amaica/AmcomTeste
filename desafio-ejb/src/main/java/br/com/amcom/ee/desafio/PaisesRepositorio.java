package br.com.amcom.ee.desafio;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Dependent
@Stateless
public class PaisesRepositorio {

	@PersistenceContext
	private EntityManager em;

	public void update(PaisesEntidade paisesEntidade) {
	
		em.merge(paisesEntidade);
		
    }
	public void salvar(@NotNull PaisesEntidade paisesEntidade) {
		System.out.println("Salvando pais: +++++" + paisesEntidade);
		em.persist(paisesEntidade);
	}
	///1)	Retornar a lista de países como origem o arquivo ‘países.json’ e disponibilizar as informações em um endpoint.
	///  http://127.0.0.1:8080/desafio-web-1.0.0-SNAPSHOT/api/paises
	public List<PaisesEntidade> listaJson() throws IOException {

	
	

		try {
			
			 URL url = this.getClass()
				        .getClassLoader()
				        .getResource("paises.json");
			 File file = new File(url.getFile());			
		    Path path = Paths.get(file.getAbsolutePath());
			String content = Files.readString(path);
			System.out.println(content);
			final var objectMapper = new ObjectMapper();
			final var paises = objectMapper.readValue(content, new TypeReference<List<PaisesEntidade>>() {

			});
			return paises;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}

	public PaisesEntidade porId(Long id) {
		return this.em.find(PaisesEntidade.class, id);
	}

	@Transactional
	public void remover(PaisesEntidade obj) {
		try {
			obj = porId(obj.getId());
			em.remove(obj);
			em.flush();
		} catch (javax.persistence.PersistenceException e) {
			e.getMessage();
		}
	}

	public List<PaisesEntidade> listarTodos() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PaisesEntidade> cq = cb.createQuery(PaisesEntidade.class);
		Root<PaisesEntidade> root = cq.from(PaisesEntidade.class);

		cq.select(root);

		TypedQuery<PaisesEntidade> query = em.createQuery(cq);

		return query.getResultList();
	}

}
