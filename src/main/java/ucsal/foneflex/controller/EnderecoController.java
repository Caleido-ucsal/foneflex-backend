package ucsal.foneflex.controller;

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
import ucsal.foneflex.dto.endereco.EnderecoRequestDTO;
import ucsal.foneflex.dto.endereco.EnderecoResponseDTO;
import ucsal.foneflex.service.EnderecoService;

@RestController
@RequestMapping("/clientes/{clienteId}/enderecos")
@RequiredArgsConstructor
public class EnderecoController
{
    private final EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Map<Object, String>> createEndereco(@PathVariable Integer clienteId, @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO)
    {
        enderecoService.createEndereco(clienteId, enderecoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Endereço criado com sucesso"));
    }

    @GetMapping
    public List<EnderecoResponseDTO> readEnderecos(@PathVariable Integer clienteId)
    {
        return enderecoService.readEnderecos(clienteId);
    }

    @GetMapping("/{id}")
    public EnderecoResponseDTO readEnderecoEspecifico(@PathVariable Integer clienteId, @PathVariable Integer id)
    {
        return enderecoService.readEnderecoEspecifico(clienteId, id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Map<Object, String>> updateEndereco(@PathVariable Integer clienteId, @PathVariable Integer id, @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO)
    {
        enderecoService.updateEndereco(id, enderecoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Endereço atualizado com sucesso"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<Object, String>> deleteEndereco(@PathVariable Integer clienteId, @PathVariable Integer id)
    {
        enderecoService.deleteEndereco(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Endereço deletado com sucesso"));
    }
}
