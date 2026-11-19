package ucsal.foneflex.venda;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Integer>
{
    List<Venda> findByFuncionarioNomeContainingIgnoreCaseOrClienteNomeContainingIgnoreCase(String nomeFuncionario, String nomeCliente);
}
