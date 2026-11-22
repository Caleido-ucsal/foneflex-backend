package ucsal.foneflex.pagamento.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import ucsal.foneflex.pagamento.BandeiraCartao;
import ucsal.foneflex.pagamento.MetodoPagamento;

public record PagamentoResponseDTO
(
    Long id,
    String nomeFuncionario,
    @JsonFormat(pattern = "dd-MM-yyyy 'T' HH:mm:ss")
    LocalDateTime dataPagamento,
    Integer vendaId,
    MetodoPagamento metodoPagamento,
    BandeiraCartao bandeiraCartao,
    BigDecimal valor
)
{}
