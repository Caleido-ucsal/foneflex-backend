package ucsal.foneflex.produtovenda;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.produto.Produto;
import ucsal.foneflex.produto.ProdutoRepository;
import ucsal.foneflex.produtovenda.dto.ProdutoVendaRequestDTO;
import ucsal.foneflex.produtovenda.dto.ProdutoVendaResponseDTO;
import ucsal.foneflex.venda.Venda;
import ucsal.foneflex.venda.VendaRepository;

@Service
@Validated
@RequiredArgsConstructor
public class ProdutoVendaService
{
    private final ProdutoVendaRepository produtoVendaRepository;
    private final ProdutoRepository produtoRepository;
    private final VendaRepository vendaRepository;

    @Transactional
    public ProdutoVendaResponseDTO lancarProduto(Integer vendaId, @Valid ProdutoVendaRequestDTO produtoVendaRequestDTO)
    {
        Venda venda = vendaRepository.findById(vendaId)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        Produto produto = produtoRepository.findById(produtoVendaRequestDTO.produtoId())
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        ProdutoVenda produtoVenda = new ProdutoVenda();
        
        produtoVenda.setVenda(venda);
        produtoVenda.setProduto(produto);
        produtoVenda.setPrecoUnitario(produto.getPrecoUnitario());
        produtoVenda.setQuantidade(produtoVendaRequestDTO.quantidade());
        produtoVenda.setSubTotal(produto.getPrecoUnitario().multiply(produtoVendaRequestDTO.quantidade()));
        venda.setValorVenda(venda.getValorVenda().add(produtoVenda.getSubTotal()));

        produtoVendaRepository.save(produtoVenda);

        return toResponseDTO(produtoVenda);
    }

    @Transactional(readOnly = true)
    public List<ProdutoVendaResponseDTO> visualizarProdutos(Integer vendaId)
    {
        return produtoVendaRepository.findByVendaId(vendaId)
        .stream()
        .map(this::toResponseDTO)
        .toList();
    }

    @Transactional
    public ProdutoVendaResponseDTO atualizarProduto(Integer vendaId, Integer id, @Valid ProdutoVendaRequestDTO produtoVendaRequestDTO)
    {
        Venda venda = vendaRepository.findById(vendaId)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        ProdutoVenda produtoVenda = produtoVendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        
        Produto produto = produtoRepository.findById(produtoVendaRequestDTO.produtoId())
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        venda.setValorVenda(venda.getValorVenda().subtract(produtoVenda.getSubTotal()));

        produtoVenda.setVenda(venda);
        produtoVenda.setProduto(produto);
        produtoVenda.setPrecoUnitario(produtoVenda.getPrecoUnitario());
        produtoVenda.setQuantidade(produtoVendaRequestDTO.quantidade());
        produtoVenda.setSubTotal(produto.getPrecoUnitario().multiply(produtoVendaRequestDTO.quantidade()));
        venda.setValorVenda(venda.getValorVenda().add(produtoVenda.getSubTotal()));

        produtoVendaRepository.save(produtoVenda);

        return toResponseDTO(produtoVenda);
    }

    @Transactional
    public void deletarProduto(Integer vendaId, Integer id)
    {
        Venda venda = vendaRepository.findById(vendaId)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        ProdutoVenda produtoVenda = produtoVendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        venda.setValorVenda(venda.getValorVenda().subtract(produtoVenda.getSubTotal()));

        produtoVendaRepository.delete(produtoVenda);
    }

    private ProdutoVendaResponseDTO toResponseDTO(ProdutoVenda produtoVenda)
    {
        return new ProdutoVendaResponseDTO
        (
            produtoVenda.getId(),
            produtoVenda.getProduto().getNomeProduto(),
            produtoVenda.getQuantidade(),
            produtoVenda.getProduto().getPrecoUnitario(),
            produtoVenda.getSubTotal()
        );
    }
}
