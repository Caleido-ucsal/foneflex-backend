package ucsal.foneflex.produtovenda.dto;

import java.math.BigDecimal;

public record ProdutoVendaResponseDTO
(
    Integer id,
    String nomeProduto,
    BigDecimal quantidade,
    BigDecimal precoUnitario,
    BigDecimal subTotal
)
{}
