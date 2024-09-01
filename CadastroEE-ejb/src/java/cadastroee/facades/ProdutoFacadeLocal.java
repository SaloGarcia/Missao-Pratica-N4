package cadastroee.facades;

import jakarta.ejb.Local;
import cadastroee.model.Produto;
import java.util.List;

@Local
public interface ProdutoFacadeLocal {
    void create(Produto produto);
    void edit(Produto produto);
    void remove(Produto produto);
    Produto find(Object id);
    List<Produto> findAll();
    List<Produto> findRange(int[] range);
    int count();
}
