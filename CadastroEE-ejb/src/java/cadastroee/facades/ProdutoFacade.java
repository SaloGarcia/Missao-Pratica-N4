package cadastroee.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import cadastroee.model.Produto;
import java.util.List;

@Stateless
public class ProdutoFacade implements ProdutoFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    public void create(Produto produto) {
        em.persist(produto);
    }

    @Override
    public void edit(Produto produto) {
        em.merge(produto);
    }

    @Override
    public void remove(Produto produto) {
        em.remove(em.merge(produto));
    }

    @Override
    public Produto find(Object id) {
        return em.find(Produto.class, id);
    }

    @Override
    public List<Produto> findAll() {
        return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }

    @Override
    public List<Produto> findRange(int[] range) {
        Query q = em.createQuery("SELECT p FROM Produto p", Produto.class);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        Query q = em.createQuery("SELECT COUNT(p) FROM Produto p");
        Long count = (Long) q.getSingleResult();
        return count != null ? count.intValue() : 0;
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
