package ucsal.foneflex.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{
    List<Produto> findByNomeMarca(String nomeMarca);
    List<Produto> findByNomeProduto(String nomeProduto);
}
