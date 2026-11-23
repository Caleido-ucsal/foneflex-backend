package ucsal.foneflex.pagamento;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.ordemservico.OrdemServico;
import ucsal.foneflex.ordemservico.OrdemServicoRepository;
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
    private final OrdemServicoRepository ordemServicoRepository;

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

        venda.setValorPago(venda.getValorPago().add(pagamentoRequestDTO.valor()));
        pagamento.setOrdemServico(null);
        pagamento.setMetodoPagamento(pagamentoRequestDTO.metodoPagamento());
        pagamento.setBandeiraCartao(pagamentoRequestDTO.bandeiraCartao());
        pagamento.setValor(pagamentoRequestDTO.valor());

        pagamentoRepository.save(pagamento);

        return toResponseDTO(pagamento);
    }

    @Transactional
    public PagamentoResponseDTO registrarPagamentoOrdemServico(Integer ordemServicoId, @Valid PagamentoRequestDTO pagamentoRequestDTO)
    {

        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
        .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada"));

        Pagamento pagamento = new Pagamento();

        pagamento.setVenda(null);

        if(pagamentoRequestDTO.valor().compareTo(ordemServico.getValorEstimado().subtract(ordemServico.getValorPago())) > 0)
        {
            throw new IllegalArgumentException("Não é permitido pagar mais do que o valor total da venda.");
        }

        ordemServico.setValorPago(ordemServico.getValorPago().add(pagamentoRequestDTO.valor()));
        pagamento.setOrdemServico(ordemServico);
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
    public List<PagamentoResponseDTO> visualizarPagamentosOrdemServico(Integer ordemServicoId)
    {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        return pagamentoRepository.findByOrdemServico(ordemServico)
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

    @Transactional
    public void deletarPagamentoOrdemServico(Long id)
    {
        Pagamento pagamento = pagamentoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        OrdemServico ordemServico = pagamento.getOrdemServico();

        ordemServico.setValorPago(ordemServico.getValorPago().subtract(pagamento.getValor()));

        pagamentoRepository.delete(pagamento);
    }

    private PagamentoResponseDTO toResponseDTO(Pagamento pagamento)
    {
        return new PagamentoResponseDTO
        (
            pagamento.getId(),
            pagamento.getFuncionario().getNome(),
            pagamento.getDataPagamento(),
            pagamento.getVenda() != null ? pagamento.getVenda().getId() : null,
            pagamento.getOrdemServico() != null ? pagamento.getOrdemServico().getId() : null,
            pagamento.getMetodoPagamento(),
            pagamento.getBandeiraCartao(),
            pagamento.getValor()
        );
    }
}
