package ucsal.foneflex.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{
    Optional<Usuario> findByNome(String nome);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByAtivo(Boolean ativo);
}
