package ucsal.foneflex.ordemservico;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;
import ucsal.foneflex.cliente.Cliente;
import ucsal.foneflex.cliente.ClienteRepository;
import ucsal.foneflex.ordemservico.dto.OrdemServicoRequestDTO;
import ucsal.foneflex.ordemservico.dto.OrdemServicoResponseDTO;
import ucsal.foneflex.usuario.Usuario;
import ucsal.foneflex.usuario.UsuarioRepository;

@Service 
@Validated 
@RequiredArgsConstructor 
public class OrdemServicoService
{
    private final OrdemServicoRepository ordemServicoRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public OrdemServicoResponseDTO registrarOrdemServico(OrdemServicoRequestDTO ordemServicoRequestDTO)
    {
        Cliente cliente = clienteRepository.findById(ordemServicoRequestDTO.clienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Optional<Usuario> funcionarioResponsavel = ordemServicoRequestDTO.funcionarioResponsavelId() != null
            ? usuarioRepository.findById(ordemServicoRequestDTO.funcionarioResponsavelId())
            : Optional.empty();
            
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setAparelhoModelo(ordemServicoRequestDTO.aparelhoModelo());
        ordemServico.setAparelhoMarca(ordemServicoRequestDTO.aparelhoMarca());
        ordemServico.setNumeroSerieImei(ordemServicoRequestDTO.numeroSerieImei());
        ordemServico.setDescricaoProblema(ordemServicoRequestDTO.descricaoProblema());
        ordemServico.setValorEstimado(ordemServicoRequestDTO.valorEstimado());
        ordemServico.setCliente(cliente);
        ordemServico.setFuncionarioResponsavel(funcionarioResponsavel.orElse(null));
        ordemServico.setStatusOS(ordemServicoRequestDTO.statusOs());
        ordemServico.setDataConclusao(ordemServicoRequestDTO.dataConclusao());

        OrdemServico registrarOrdemServico = ordemServicoRepository.save(ordemServico);
        return toResponseDTO(registrarOrdemServico);
    }

    public OrdemServicoResponseDTO listarOrdemServico(Integer id)
    {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada."));

        return toResponseDTO(ordemServico);
    }

    public List<OrdemServicoResponseDTO> listarOrdensServico()
    {
        return ordemServicoRepository.findAll()
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    public OrdemServicoResponseDTO atualizarOrdemServico(Integer id, OrdemServicoRequestDTO ordemServicoRequestDTO)
    {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada."));

        Cliente cliente = clienteRepository.findById(ordemServicoRequestDTO.clienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        ordemServico.setAparelhoModelo(ordemServicoRequestDTO.aparelhoModelo());
        ordemServico.setAparelhoMarca(ordemServicoRequestDTO.aparelhoMarca());
        ordemServico.setNumeroSerieImei(ordemServicoRequestDTO.numeroSerieImei());
        ordemServico.setDescricaoProblema(ordemServicoRequestDTO.descricaoProblema());
        ordemServico.setValorEstimado(ordemServicoRequestDTO.valorEstimado());
        ordemServico.setCliente(cliente);
        ordemServico.setStatusOS(ordemServicoRequestDTO.statusOs());
        ordemServico.setDataConclusao(ordemServicoRequestDTO.dataConclusao());

        OrdemServico atualizarOrdemServico = ordemServicoRepository.save(ordemServico);
        return toResponseDTO(atualizarOrdemServico);
    }

    public void deletarOrdemServico(Integer id)
    {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada."));

        ordemServicoRepository.delete(ordemServico);
    }

    private OrdemServicoResponseDTO toResponseDTO(OrdemServico ordemServico)
    {
        return new OrdemServicoResponseDTO
        (
            ordemServico.getId(),
            ordemServico.getAparelhoModelo(),
            ordemServico.getAparelhoMarca(),
            ordemServico.getNumeroSerieImei(),
            ordemServico.getDescricaoProblema(),
            ordemServico.getValorEstimado().toString(),
            ordemServico.getCliente().getNome(),
            ordemServico.getFuncionarioResponsavel().getNome(),
            ordemServico.getStatusOS(),
            ordemServico.getDataAbertura(),
            ordemServico.getDataConclusao()
        );
    }
}
