package ucsal.foneflex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucsal.foneflex.model.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer>
{
    List<OrdemServico> findByFuncionarioResponsavelId(Integer funcionarioResponsavelId);
    List<OrdemServico> findByFuncionarioResponsavelIdAndId(Integer funcionarioResponsavelId, Integer id);
    List<OrdemServico> findByClienteId(Integer clienteId);
    List<OrdemServico> findByDataAbertura(String dataAbertura);
}
