package ucsal.foneflex.produtovenda.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProdutoVendaRequestDTO
(
    Integer vendaId,
    Integer produtoId,
    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "Você deve escolher ao menos um de cada produto")
    @Digits(integer = 10, fraction = 0, message = "Só é permitido números inteiros")
    BigDecimal quantidade
)
{}
