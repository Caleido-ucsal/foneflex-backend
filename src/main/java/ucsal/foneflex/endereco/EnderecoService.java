package ucsal.foneflex.endereco;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import ucsal.foneflex.cliente.Cliente;
import ucsal.foneflex.cliente.ClienteRepository;
import ucsal.foneflex.endereco.dto.EnderecoRequestDTO;
import ucsal.foneflex.endereco.dto.EnderecoResponseDTO;

@Service 
@Validated
@RequiredArgsConstructor 
public class EnderecoService
{
    private final EnderecoRepository enderecoRepository;

    private final ClienteRepository clienteRepository;

    public EnderecoResponseDTO createEndereco(Integer clienteId, EnderecoRequestDTO enderecoRequestDTO)
    {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Endereco endereco = new Endereco();
        endereco.setRua(enderecoRequestDTO.rua());
        endereco.setNumero(enderecoRequestDTO.numero());
        endereco.setBairro(enderecoRequestDTO.bairro());
        endereco.setCep(enderecoRequestDTO.cep());
        endereco.setComplemento(enderecoRequestDTO.complemento());
        endereco.setPontoReferencia(enderecoRequestDTO.pontoReferencia());
        endereco.setCliente(cliente);

        Endereco savedEndereco = enderecoRepository.save(endereco);
        return toResponseDTO(savedEndereco);
    }

    public EnderecoResponseDTO readEndereco(Integer id)
    {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado."));
        return toResponseDTO(endereco);
    }

    public EnderecoResponseDTO readEnderecoEspecifico(Integer clienteId, Integer id)
    {
        Endereco endereco = enderecoRepository.findByIdAndClienteId(id, clienteId)
            .orElseThrow(() -> new RuntimeException("Endereço não encontrado para o cliente especificado."));
        return toResponseDTO(endereco);
    }

    public List<EnderecoResponseDTO> readEnderecos(Integer clienteId)
    {
        return enderecoRepository.findByClienteId(clienteId)
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    public EnderecoResponseDTO updateEndereco(Integer id, EnderecoRequestDTO enderecoRequestDTO)
    {
        Endereco updateEndereco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado."));
        updateEndereco.setRua(enderecoRequestDTO.rua());
        updateEndereco.setNumero(enderecoRequestDTO.numero());
        updateEndereco.setBairro(enderecoRequestDTO.bairro());
        updateEndereco.setCep(enderecoRequestDTO.cep());
        updateEndereco.setComplemento(enderecoRequestDTO.complemento());
        updateEndereco.setPontoReferencia(enderecoRequestDTO.pontoReferencia());

        return toResponseDTO(enderecoRepository.save(updateEndereco));
    }

    public void deleteEndereco(Integer id)
    {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado."));

        enderecoRepository.deleteById(endereco.getId());
    }

    private EnderecoResponseDTO toResponseDTO(Endereco endereco)
    {
        return new EnderecoResponseDTO(
            endereco.getId(),
            endereco.getRua(),
            endereco.getNumero(),
            endereco.getBairro(),
            endereco.getCep(),
            endereco.getComplemento(),
            endereco.getPontoReferencia()
        );
    }
}
