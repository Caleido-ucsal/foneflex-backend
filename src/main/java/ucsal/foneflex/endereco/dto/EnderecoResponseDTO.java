package ucsal.foneflex.endereco.dto;

public record EnderecoResponseDTO
(
    Integer id,
    String rua,
    String numero,
    String bairro,
    String cep,
    String complemento,
    String pontoReferencia
)
{}