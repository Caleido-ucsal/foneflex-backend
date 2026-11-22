package ucsal.foneflex.venda;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ucsal.foneflex.cliente.Cliente;
import ucsal.foneflex.cliente.ClienteRepository;
import ucsal.foneflex.venda.dto.VendaRequestDTO;
import ucsal.foneflex.venda.dto.VendaResponseDTO;

@Service 
@Validated 
@RequiredArgsConstructor 
public class VendaService
{
    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public VendaResponseDTO registrarVenda(VendaRequestDTO vendaRequestDTO)
    {
        Cliente cliente = null;

        if (vendaRequestDTO.clienteId() != null)
        {
            cliente = clienteRepository.findById(vendaRequestDTO.clienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        }

        Venda venda = new Venda();
        venda.setCliente(cliente);

        vendaRepository.save(venda);
        return toResponseDTO(venda);
    }

    @Transactional(readOnly = true)
    public VendaResponseDTO visualizarVenda(Integer id)
    {
        Venda venda = vendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada."));

        return toResponseDTO(venda);
    }

    @Transactional(readOnly = true)
    public List<VendaResponseDTO> buscarVenda(String nomeFuncionario, String nomeCliente)
    {
        return vendaRepository.findByFuncionarioNomeContainingIgnoreCaseOrClienteNomeContainingIgnoreCase(nomeFuncionario, nomeCliente)
        .stream().map(this::toResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<VendaResponseDTO> listarVendas()
    {
        return vendaRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    @Transactional
    public VendaResponseDTO atualizarVenda(Integer id, @Valid VendaRequestDTO vendaRequestDTO)
    {
        Venda venda = vendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada."));

        Cliente cliente = null;

        if (vendaRequestDTO.clienteId() != null)
        {
            cliente = clienteRepository.findById(vendaRequestDTO.clienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        }

        venda.setCliente(cliente);

        vendaRepository.save(venda);
        return toResponseDTO(venda);
    }

    @Transactional
    public void deletarVenda(Integer id)
    {
        Venda venda = vendaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada."));

        vendaRepository.delete(venda);
    }

    private VendaResponseDTO toResponseDTO(Venda venda)
    {
        return new VendaResponseDTO
        (
            venda.getId(),
            venda.getFuncionario().getNome(),
            venda.getCliente() != null ? venda.getCliente().getNome() : null,
            venda.getDataVenda(),
            venda.getStatus(),
            venda.getValorVenda(),
            venda.getValorPago(),
            venda.getUltimaAlteracao()
        );
    }
}
