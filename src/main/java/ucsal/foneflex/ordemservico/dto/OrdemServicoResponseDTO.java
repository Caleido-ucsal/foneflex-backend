package ucsal.foneflex.ordemservico.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ucsal.foneflex.ordemservico.StatusOs;

public record OrdemServicoResponseDTO
(
    Integer id,
    String aparelhoModelo,
    String aparelhoMarca,
    String numeroSerieImei,
    String descricaoProblema,
    String valorEstimado,
    BigDecimal valorPago,
    String nomeCliente,
    String nomeFuncionarioResponsavel,
    StatusOs statusOS,
    LocalDateTime dataAbertura,
    LocalDateTime dataConclusao
)
{}
