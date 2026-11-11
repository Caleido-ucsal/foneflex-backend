package ucsal.foneflex.endereco.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDTO
(
    String rua,
    String numero,
    String bairro,
    @NotBlank (message = "O campo cep é obrigatório")
    String cep,
    String complemento,
    String pontoReferencia
)
{}
