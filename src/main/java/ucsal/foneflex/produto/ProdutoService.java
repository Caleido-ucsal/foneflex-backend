package ucsal.foneflex.produto;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.produto.dto.ProdutoRequestDTO;
import ucsal.foneflex.produto.dto.ProdutoResponseDTO;

@Service
@Validated
@RequiredArgsConstructor
public class ProdutoService
{
    private final ProdutoRepository produtoRepository;

    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO produtoRequestDTO)
    {
        Produto produto = new Produto();

        produto.setNomeProduto(produtoRequestDTO.nomeProduto());
        produto.setMarcaProduto(produtoRequestDTO.marcaProduto());
        produto.setPrecoUnitario(produtoRequestDTO.precoUnitario());
        produto.setDescricaoProduto(produtoRequestDTO.descricaoProduto());
        produto.setCondicaoProduto(produtoRequestDTO.condicaoProduto());
        produto.setQuantidadeEstoque(produtoRequestDTO.quantidadeEstoque());

        Produto cadastrarProduto = produtoRepository.save(produto);
        return toResponseDTO(cadastrarProduto);
    }

    public ProdutoResponseDTO visualizarProduto(Integer id)
    {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        return toResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> listarProdutos()
    {
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<ProdutoResponseDTO> buscarPorNomeProdutoOuMarcaProduto(String nomeProduto, String marcaProduto)
    {
        return produtoRepository.findByNomeProdutoContainingIgnoreCaseOrMarcaProdutoContainingIgnoreCase(nomeProduto, marcaProduto)
            .stream()
            .map(produto -> new ProdutoResponseDTO(produto.getId(), produto.getNomeProduto(), produto.getMarcaProduto(),
                    produto.getPrecoUnitario(), produto.getDescricaoProduto(), produto.getCondicaoProduto(), produto.getQuantidadeEstoque(), produto.getDataRecebimento()))
            .toList();
    }

    public ProdutoResponseDTO atualizarProduto(Integer id, @Valid ProdutoRequestDTO produtoRequestDTO)
    {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        produto.setNomeProduto(produtoRequestDTO.nomeProduto());
        produto.setMarcaProduto(produtoRequestDTO.marcaProduto());
        produto.setPrecoUnitario(produtoRequestDTO.precoUnitario());
        produto.setDescricaoProduto(produtoRequestDTO.descricaoProduto());
        produto.setCondicaoProduto(produtoRequestDTO.condicaoProduto());
        produto.setQuantidadeEstoque(produtoRequestDTO.quantidadeEstoque());

        return toResponseDTO(produtoRepository.save(produto));
    }

    public void deletarProduto(Integer id)
    {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        produtoRepository.deleteById(produto.getId());
    }

    private ProdutoResponseDTO toResponseDTO(Produto produto)
    {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNomeProduto(),
                produto.getMarcaProduto(),
                produto.getPrecoUnitario(),
                produto.getDescricaoProduto(),
                produto.getCondicaoProduto(),
                produto.getQuantidadeEstoque(),
                produto.getDataRecebimento());
    }
}
