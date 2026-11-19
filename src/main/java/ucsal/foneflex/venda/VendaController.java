package ucsal.foneflex.venda;

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
import ucsal.foneflex.venda.dto.VendaRequestDTO;
import ucsal.foneflex.venda.dto.VendaResponseDTO;

@RestController 
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController
{
    private final VendaService vendaService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarVenda(@Valid @RequestBody VendaRequestDTO vendaRequestDTO)
    {
        vendaService.registrarVenda(vendaRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Venda registrada com sucesso!"));
    }

    @GetMapping
    public List<VendaResponseDTO> listarVendas()
    {
        return vendaService.listarVendas();
    }

    @GetMapping("/{id}")
    public VendaResponseDTO visualizarVenda(@PathVariable Integer id)
    {
        return vendaService.visualizarVenda(id);
    }

    @GetMapping("/buscar")
    public List<VendaResponseDTO> buscarVenda(@RequestParam(name = "pesquisa") String pesquisa)
    {
        return vendaService.buscarVenda(pesquisa, pesquisa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarVenda(@PathVariable Integer id, @Valid @RequestBody VendaRequestDTO vendaRequestDTO)
    {
        vendaService.atualizarVenda(id, vendaRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Venda atualizada com sucesso!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletarVenda(@PathVariable Integer id)
    {
        vendaService.deletarVenda(id);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Venda deletada com sucesso"));
    }
}
