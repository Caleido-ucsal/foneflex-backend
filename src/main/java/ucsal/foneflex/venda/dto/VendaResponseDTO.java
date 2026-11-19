package ucsal.foneflex.venda.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import ucsal.foneflex.venda.Status;

public record VendaResponseDTO
(
    Integer id,
    String nomeFuncionario,
    String nomeCliente,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDateTime dataVenda,
    Status status,
    BigDecimal valorVenda,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDateTime ultimaAlteracao
)
{}
