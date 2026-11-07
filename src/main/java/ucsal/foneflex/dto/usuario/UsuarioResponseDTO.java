package ucsal.foneflex.dto.usuario;

import java.time.LocalDateTime;

public record UsuarioResponseDTO
(
        Integer id,
        String nome,
        String email,
        Boolean ativo,
        LocalDateTime dataInclusao
)
{}