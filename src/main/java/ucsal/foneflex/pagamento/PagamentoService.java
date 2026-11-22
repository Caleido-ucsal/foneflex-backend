package ucsal.foneflex.pagamento;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.pagamento.dto.PagamentoRequestDTO;
import ucsal.foneflex.pagamento.dto.PagamentoResponseDTO;
import ucsal.foneflex.venda.Venda;
import ucsal.foneflex.venda.VendaRepository;

@Service
@Validated
@RequiredArgsConstructor
public class PagamentoService
{
    private final PagamentoRepository pagamentoRepository;
    private final VendaRepository vendaRepository;

    @Transactional
    public PagamentoResponseDTO registrarPagamento(Integer vendaId, @Valid PagamentoRequestDTO pagamentoRequestDTO)
    {
        Venda venda = vendaRepository.findById(vendaId)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        Pagamento pagamento = new Pagamento();

        pagamento.setVenda(venda);

        if(pagamentoRequestDTO.valor().compareTo(venda.getValorVenda().subtract(venda.getValorPago())) > 0)
        {
            throw new IllegalArgumentException("Não é permitido pagar mais do que o valor total da venda.");
        }

        // if(pagamentoRequestDTO.valor().compareTo(venda.getValorVenda()) > 0)
        // {
        //     throw new IllegalArgumentException("O pagamento não pode ser maior que o valor total");
        // }
        venda.setValorPago(venda.getValorPago().add(pagamentoRequestDTO.valor()));
        pagamento.setMetodoPagamento(pagamentoRequestDTO.metodoPagamento());
        pagamento.setBandeiraCartao(pagamentoRequestDTO.bandeiraCartao());
        pagamento.setValor(pagamentoRequestDTO.valor());

        pagamentoRepository.save(pagamento);

        return toResponseDTO(pagamento);
    }

    @Transactional(readOnly = true)
    public List<PagamentoResponseDTO> visualizarPagamentosVenda(Integer vendaId)
    {
        Venda venda = vendaRepository.findById(vendaId)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        return pagamentoRepository.findByVenda(venda)
        .stream()
        .map(this::toResponseDTO)
        .toList();
    }

    @Transactional(readOnly = true)
    public List<PagamentoResponseDTO> listarPagamentos()
    {
        return pagamentoRepository.findAll()
        .stream()
        .map(this::toResponseDTO)
        .toList();
    }

    @Transactional
    public void deletarPagamento(Long id)
    {
        Pagamento pagamento = pagamentoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        Venda venda = pagamento.getVenda();

        venda.setValorPago(venda.getValorPago().subtract(pagamento.getValor()));

        pagamentoRepository.delete(pagamento);
    }

    private PagamentoResponseDTO toResponseDTO(Pagamento pagamento)
    {
        return new PagamentoResponseDTO
        (
            pagamento.getId(),
            pagamento.getFuncionario().getNome(),
            pagamento.getDataPagamento(),
            pagamento.getVenda().getId(),
            pagamento.getMetodoPagamento(),
            pagamento.getBandeiraCartao(),
            pagamento.getValor()
        );
    }
}
