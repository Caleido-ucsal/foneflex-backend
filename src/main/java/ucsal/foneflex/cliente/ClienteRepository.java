package ucsal.foneflex.cliente;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>
{
    Optional<Cliente> findByNome(String nome);
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByTelefone(String telefone);
    Optional<Cliente> findByDataCadastro(LocalDateTime dataCadastro);
}
