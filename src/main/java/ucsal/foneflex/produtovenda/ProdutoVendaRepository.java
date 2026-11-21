package ucsal.foneflex.produtovenda;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Integer>
{
    List<ProdutoVenda> findByVendaId(Integer id);
}
