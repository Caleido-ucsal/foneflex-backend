package ucsal.foneflex.produto;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.produto.dto.ProdutoRequestDTO;
import ucsal.foneflex.produto.dto.ProdutoResponseDTO;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController
{
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Map<Object, String>> cadastrarProduto (@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO)
    {
        produtoService.cadastrarProduto(produtoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Produto cadastrado com sucesso!"));
    }

    @GetMapping
    public List<ProdutoResponseDTO> listarProdutos()
    {
        return produtoService.listarProdutos();
    }

    @GetMapping ("/{id}")
    public ProdutoResponseDTO visualizarProduto(@PathVariable Integer id)
    {
        return produtoService.visualizarProduto(id);     
    }

    @GetMapping("/buscar")
    public List<ProdutoResponseDTO> buscarPorNomeProdutoOuMarcaProduto(@RequestParam(name = "pesquisa") String pesquisa)
    {
        return produtoService.buscarPorNomeProdutoOuMarcaProduto(pesquisa, pesquisa);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarProduto(@PathVariable Integer id, @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO)
    {
        produtoService.atualizarProduto(id, produtoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Produto atualizado com sucesso!"));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Map<String, Object>> deletarProduto(@PathVariable Integer id)
    {
        produtoService.deletarProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Produto deletado com sucesso"));
    }
}
