package ucsal.foneflex.endereco;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>
{
    Optional<Endereco> findByCep(String cep);
    List<Endereco> findByClienteId(Integer clienteId);
    Optional<Endereco> findByIdAndClienteId(Integer id, Integer clienteId);
}
