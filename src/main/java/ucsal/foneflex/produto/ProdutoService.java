package ucsal.foneflex.produto;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
        
        produto.setNomeMarca(produtoRequestDTO.nomeMarca());
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

    public ProdutoResponseDTO atualizarProduto(ProdutoRequestDTO produtoRequestDTO)
    {
        Produto produto = new Produto();
        
        produto.setNomeMarca(produtoRequestDTO.nomeMarca());
        produto.setMarcaProduto(produtoRequestDTO.marcaProduto());
        produto.setPrecoUnitario(produtoRequestDTO.precoUnitario());
        produto.setDescricaoProduto(produtoRequestDTO.descricaoProduto());
        produto.setCondicaoProduto(produtoRequestDTO.condicaoProduto());
        produto.setQuantidadeEstoque(produtoRequestDTO.quantidadeEstoque());

        Produto atualizarProduto = produtoRepository.save(produto);
        return toResponseDTO(atualizarProduto);
    }
    

    private ProdutoResponseDTO toResponseDTO(Produto produto)
    {
        return new ProdutoResponseDTO
        (
            produto.getId(),
            produto.getNomeMarca(),
            produto.getMarcaProduto(),
            produto.getPrecoUnitario(),
            produto.getDescricaoProduto(),
            produto.getCondicaoProduto(),
            produto.getQuantidadeEstoque(),
            produto.getDataRecebimento()
        );
    }
}
