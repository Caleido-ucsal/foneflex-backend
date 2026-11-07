package ucsal.foneflex.dto.cliente;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO
(
    @NotBlank (message = "O campo nome é obrigatório")
    String nome,
    String cpf,
    @NotBlank (message = "O campo telefone é obrigatório")
    String telefone,
    String email
)
{}
