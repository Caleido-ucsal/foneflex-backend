package ucsal.foneflex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucsal.foneflex.model.Endereco;

@Repository 
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>
{
    Optional<Endereco> findByCep(String cep);
    List<Endereco> findByClienteId(Integer clienteId);
    Optional<Endereco> findByIdAndClienteId(Integer id, Integer clienteId);
}
