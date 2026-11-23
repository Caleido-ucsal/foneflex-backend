package ucsal.foneflex.pagamento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ucsal.foneflex.usuario.Usuario;
import ucsal.foneflex.venda.Venda;
import ucsal.foneflex.ordemservico.OrdemServico;


public interface PagamentoRepository extends JpaRepository<Pagamento, Long>
{
    List<Pagamento> findByMetodoPagamento(MetodoPagamento metodoPagamento);
    List<Pagamento> findByBandeiraCartao(BandeiraCartao bandeiraCartao);
    List<Pagamento> findByFuncionario(Usuario funcionario);
    List<Pagamento> findByDataPagamento(LocalDateTime dataPagamento);
    List<Pagamento> findByVenda(Venda venda);
    List<Pagamento> findByOrdemServico(OrdemServico ordemServico);
}
