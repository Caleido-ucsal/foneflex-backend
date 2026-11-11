package ucsal.foneflex.ordemservico;

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
import ucsal.foneflex.ordemservico.dto.OrdemServicoRequestDTO;
import ucsal.foneflex.ordemservico.dto.OrdemServicoResponseDTO;

@RestController
@RequestMapping("/ordens-servico")
@RequiredArgsConstructor
public class OrdemServicoController
{
    private final OrdemServicoService ordemServicoService;

    @PostMapping
    public ResponseEntity<Map<Object, String>> registrarOrdemServico(@Valid @RequestBody OrdemServicoRequestDTO ordemServicoRequestDTO)
    {
        ordemServicoService.registrarOrdemServico(ordemServicoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Ordem de serviço registrada com sucesso"));
    }

    @GetMapping
    public List<OrdemServicoResponseDTO> listarOrdensServico()
    {
        return ordemServicoService.listarOrdensServico();
    }

    @GetMapping ("/{id}")
    public OrdemServicoResponseDTO listarOrdemServico(@PathVariable Integer id)
    {
        return ordemServicoService.listarOrdemServico(id);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Map<String, String>> atualizarOrdemServico(@PathVariable Integer id, @Valid @RequestBody OrdemServicoRequestDTO ordemServicoRequestDTO)
    {
        ordemServicoService.atualizarOrdemServico(id, ordemServicoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Ordem de serviço atualizada com sucesso"));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Map<Object, String>> excluirOrdemServico(@PathVariable Integer id)
    {
        ordemServicoService.deletarOrdemServico(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Ordem de serviço excluída com sucesso"));
    }
}
