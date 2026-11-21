package ucsal.foneflex.produtovenda;

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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.produtovenda.dto.ProdutoVendaRequestDTO;
import ucsal.foneflex.produtovenda.dto.ProdutoVendaResponseDTO;

@RestController
@RequestMapping("/vendas/{vendaId}/produtos")
@RequiredArgsConstructor
public class ProdutoVendaController
{
    private final ProdutoVendaService produtoVendaService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> lancarProduto(@PathVariable Integer vendaId, @Valid @RequestBody ProdutoVendaRequestDTO produtoVendaRequestDTO)
    {
        produtoVendaService.lancarProduto(vendaId, produtoVendaRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Produto lançado com sucesso"));
    }

    @GetMapping
    public List<ProdutoVendaResponseDTO> visualizarProdutos(@PathVariable Integer vendaId)
    {
        return produtoVendaService.visualizarProdutos(vendaId);
    }

    @PutMapping("{id}")
    public ResponseEntity<Map<String, Object>> atualizarProduto(@PathVariable Integer vendaId, @PathVariable Integer id, @Valid @RequestBody ProdutoVendaRequestDTO produtoVendaRequestDTO)
    {
        produtoVendaService.atualizarProduto(vendaId, id, produtoVendaRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Produto atualizado com sucesso"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Object>> deletarProduto(@PathVariable Integer vendaId, @PathVariable Integer id)
    {
        produtoVendaService.deletarProduto(vendaId, id);
        
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "O produto foi retirado da venda com sucesso"));
    }
}
