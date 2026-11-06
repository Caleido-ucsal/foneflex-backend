package ucsal.foneflex.dto.cliente;

public record ClienteRequestDTO
(
    String nome,
    String cpf,
    String telefone,
    String email
)
{}
