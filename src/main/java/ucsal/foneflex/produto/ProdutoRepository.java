package ucsal.foneflex.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{
    List<Produto> findByMarcaProduto(String marcaProduto);
    List<Produto> findByNomeProduto(String nomeProduto);
    List<Produto> findByNomeProdutoContainingIgnoreCaseOrMarcaProdutoContainingIgnoreCase(String nomeProduto, String marcaProduto);
}
