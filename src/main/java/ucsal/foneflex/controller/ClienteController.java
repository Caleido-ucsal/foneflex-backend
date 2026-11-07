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
import ucsal.foneflex.dto.cliente.ClienteRequestDTO;
import ucsal.foneflex.dto.cliente.ClienteResponseDTO;
import ucsal.foneflex.service.ClienteService;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController
{
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Map<Object, String>> createUsuario(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO)
    {
        clienteService.createCliente(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Cliente criado com sucesso"));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> readClientes()
    {
        return ResponseEntity.ok(clienteService.readClientes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCliente(@PathVariable Integer id, @RequestBody ClienteRequestDTO clienteRequestDTO)
    {
        clienteService.updateCliente(id, clienteRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Cliente atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluirCliente(@PathVariable Integer id)
    {
        clienteService.deleteCliente(id);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "O usuário foi deletado com sucesso"));
    }
}
