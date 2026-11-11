package ucsal.foneflex.cliente.dto;

import java.time.LocalDateTime;

public record ClienteResponseDTO
(
    Integer id,
    String nome,
    String cpf,
    String telefone,
    String email,
    LocalDateTime dataCadastro
)
{}
