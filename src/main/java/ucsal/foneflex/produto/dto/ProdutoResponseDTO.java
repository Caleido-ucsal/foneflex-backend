package ucsal.foneflex.produto.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ProdutoResponseDTO
(
    Integer id,
    String nomeMarca,
    String marcaProduto,
    BigDecimal precoUnitario,
    String descricaoProduto,
    String condicaoProduto,
    Integer quantidadeEstoque,
    @JsonFormat (pattern = "dd/MM/yyyy 'T' HH:mm")
    LocalDate dataRecebimento 
)
{}
