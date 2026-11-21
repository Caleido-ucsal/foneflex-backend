package ucsal.foneflex.produtovenda.dto;

import java.math.BigDecimal;

public record ProdutoVendaRequestDTO
(
    Integer vendaId,
    Integer produtoId,
    BigDecimal quantidade
)
{}
