package ucsal.foneflex.produto.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDTO
(
    @NotBlank (message = "O nome da marca do produto é obrigatório")
    String nomeMarca,

    @NotBlank (message = "O nome do produto é obrigatório")
    String nomeProduto,

    @NotNull (message = "O preço unitário é obrigatório")
    BigDecimal precoUnitario,

    String descricaoProduto,

    String condicaoProduto,

    Integer quantidadeEstoque
)
{}
