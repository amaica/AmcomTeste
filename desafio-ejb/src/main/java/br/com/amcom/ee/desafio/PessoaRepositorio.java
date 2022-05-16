package br.com.amcom.ee.desafio;

import java.io.IOException;
import java.net.URL;
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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	public List<PessoaEntidade> listaJson(String email) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		PessoaEntidade pessoa = objectMapper.readValue(new URL("https://reqres.in/api/users?page=2&email=" + email),
				PessoaEntidade.class);
		System.out.println(pessoa);
		return new ArrayList<>();

	}


}
