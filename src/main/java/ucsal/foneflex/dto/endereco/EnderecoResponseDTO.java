package ucsal.foneflex.dto.endereco;

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