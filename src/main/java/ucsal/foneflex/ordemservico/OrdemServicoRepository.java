package ucsal.foneflex.ordemservico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer>
{
    List<OrdemServico> findByFuncionarioResponsavelId(Integer funcionarioResponsavelId);
    List<OrdemServico> findByFuncionarioResponsavelIdAndId(Integer funcionarioResponsavelId, Integer id);
    List<OrdemServico> findByClienteId(Integer clienteId);
    List<OrdemServico> findByDataAbertura(String dataAbertura);
}
