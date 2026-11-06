package ucsal.foneflex.dto.cliente;

import java.time.LocalDateTime;

public record ClienteResponseDTO
(
    Long id,
    String nome,
    String cpf,
    String telefone,
    String email
    // LocalDateTime dataCadastro
)
{}
