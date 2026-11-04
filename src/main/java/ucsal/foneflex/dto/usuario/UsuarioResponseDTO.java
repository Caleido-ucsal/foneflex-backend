package ucsal.foneflex.dto.usuario;

public record UsuarioResponseDTO
(
        Long id,
        String nome,
        String email,
        Boolean ativo
)
{}