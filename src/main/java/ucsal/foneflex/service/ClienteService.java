package ucsal.foneflex.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import ucsal.foneflex.dto.cliente.ClienteRequestDTO;
import ucsal.foneflex.dto.cliente.ClienteResponseDTO;
import ucsal.foneflex.model.Cliente;
import ucsal.foneflex.model.Usuario;
import ucsal.foneflex.repository.ClienteRepository;

@Service
@Validated
@RequiredArgsConstructor
public class ClienteService
{
    private final ClienteRepository clienteRepository;

    public ClienteResponseDTO createCliente(ClienteRequestDTO clienteRequestDTO)
    {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDTO.nome());
        cliente.setCpf(clienteRequestDTO.cpf());
        cliente.setTelefone(clienteRequestDTO.telefone());
        cliente.setEmail(clienteRequestDTO.email());

        Cliente savedCliente = clienteRepository.save(cliente);
        return toResponseDTO(savedCliente);
    }

    public List<ClienteResponseDTO> readClientes()
    {
        return clienteRepository.findAll()
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    public ClienteResponseDTO updateCliente(Long id, ClienteRequestDTO clienteRequestDTO)
    {
        Cliente updateCliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        updateCliente.setNome(clienteRequestDTO.nome());
        updateCliente.setCpf(clienteRequestDTO.cpf());
        updateCliente.setTelefone(clienteRequestDTO.telefone());
        updateCliente.setEmail(clienteRequestDTO.email());

        return toResponseDTO(clienteRepository.save(updateCliente));
    }

    public void deleteCliente(Long id)
    {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        clienteRepository.deleteById(cliente.getId());
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente)
    {
        return new ClienteResponseDTO
         (
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getTelefone(),
            cliente.getEmail()
            // cliente.getDataCadastro()
        );
    }
}
