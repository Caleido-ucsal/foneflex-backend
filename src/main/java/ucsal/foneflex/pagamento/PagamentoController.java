package ucsal.foneflex.pagamento;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.pagamento.dto.PagamentoRequestDTO;
import ucsal.foneflex.pagamento.dto.PagamentoResponseDTO;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PagamentoController
{
    private final PagamentoService pagamentoService;

    @PostMapping("vendas/{vendaId}/pagamentos")
    public ResponseEntity<Map<String, Object>> registrarPagamento(@PathVariable Integer vendaId, @Valid @RequestBody PagamentoRequestDTO pagamentoRequestDTO)
    {
        pagamentoService.registrarPagamento(vendaId, pagamentoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Pagamento lançado com sucesso"));
    }

    @PostMapping("ordens-servico/{ordemServicoId}/pagamentos")
    public ResponseEntity<Map<String, Object>> registrarPagamentoOrdemServico(@PathVariable Integer ordemServicoId, @Valid @RequestBody PagamentoRequestDTO pagamentoRequestDTO)
    {
        pagamentoService.registrarPagamentoOrdemServico(ordemServicoId, pagamentoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Pagamento lançado com sucesso"));
    }
    
    @GetMapping("vendas/{vendaId}/pagamentos")
    public List<PagamentoResponseDTO> visualizarPagamentosVenda(@PathVariable Integer vendaId)
    {
        return pagamentoService.visualizarPagamentosVenda(vendaId);
    }

    @GetMapping("ordens-servico/{ordemServicoId}/pagamentos")
    public List<PagamentoResponseDTO> visualizarPagamentosOrdemServico(@PathVariable Integer ordemServicoId)
    {
        return pagamentoService.visualizarPagamentosOrdemServico(ordemServicoId);
    }

    @GetMapping("pagamentos")
    public List<PagamentoResponseDTO> listarPagamentos()
    {
        return pagamentoService.listarPagamentos();
    }

    @DeleteMapping("vendas/{vendaId}/pagamentos/{id}")
    public ResponseEntity<Map<String, Object>> deletarPagamento(@PathVariable Long id)
    {
        pagamentoService.deletarPagamento(id);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "O Pagamento foi deletado da venda com sucesso"));
    }
    
    @DeleteMapping("ordens-servico/{ordemServicoId}/pagamentos/{id}")
    public ResponseEntity<Map<String, Object>> deletarPagamentoOrdemServico(@PathVariable Long id)
    {
        pagamentoService.deletarPagamentoOrdemServico(id);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "O Pagamento foi deletado da ordem de serviço com sucesso"));
    }
}
