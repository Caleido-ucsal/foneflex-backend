package ucsal.foneflex.cliente;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import ucsal.foneflex.cliente.dto.ClienteRequestDTO;
import ucsal.foneflex.cliente.dto.ClienteResponseDTO;

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

    public ClienteResponseDTO updateCliente(Integer id, ClienteRequestDTO clienteRequestDTO)
    {
        Cliente updateCliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        updateCliente.setNome(clienteRequestDTO.nome());
        updateCliente.setCpf(clienteRequestDTO.cpf());
        updateCliente.setTelefone(clienteRequestDTO.telefone());
        updateCliente.setEmail(clienteRequestDTO.email());

        return toResponseDTO(clienteRepository.save(updateCliente));
    }

    public void deleteCliente(Integer id)
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
            cliente.getEmail(),
            cliente.getDataCadastro()
        );
    }
}
