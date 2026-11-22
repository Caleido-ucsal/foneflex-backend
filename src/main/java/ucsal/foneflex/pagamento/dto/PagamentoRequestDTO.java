package ucsal.foneflex.pagamento.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import ucsal.foneflex.pagamento.BandeiraCartao;
import ucsal.foneflex.pagamento.MetodoPagamento;

public record PagamentoRequestDTO
(
    Long vendaId,
    @NotNull(message = "O método de pagamento é obrigatório")
    MetodoPagamento metodoPagamento,
    BandeiraCartao bandeiraCartao,
    @NotNull(message = "O valor do pagamento é obrigatório")
    @DecimalMin(value = "0.01", message = "Você deve informar um valor positivo")
    BigDecimal valor
)
{}
