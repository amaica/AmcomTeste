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
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;



@Dependent
@Stateless
public class TemperaturaRepositorio {

    @PersistenceContext
    private EntityManager em;

    public void salvar(@NotNull TemperaturaEntidade temperaturaEntidade) {
        System.out.println("Salvando temperatura: " + temperaturaEntidade);
        em.persist(temperaturaEntidade);
    }

    public List<TemperaturaEntidade> listarTodas() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TemperaturaEntidade> cq = cb.createQuery(TemperaturaEntidade.class);
        Root<TemperaturaEntidade> root = cq.from(TemperaturaEntidade.class);

        cq.select(root);

        TypedQuery<TemperaturaEntidade> query = em.createQuery(cq);

        return query.getResultList();
    }
    
    
    
    public TemperaturaEntidade porId(Long id) {
		return this.em.find(TemperaturaEntidade.class, id);
	}
    
    @Transactional
	public void remover(TemperaturaEntidade temperarura)  {
		try {
			temperarura = porId(temperarura.getId());
			em.remove(temperarura);
			em.flush();
		} catch (javax.persistence.PersistenceException e) {
			e.getMessage();
		}
	}
    
    

}
