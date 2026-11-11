package ucsal.foneflex.dto.ordemServico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ucsal.foneflex.model.StatusOs;

public record OrdemServicoRequestDTO
(
    @NotBlank (message = "O campo modelo do aparelho é obrigatório")
    String aparelhoModelo,

    @NotBlank (message = "O campo marca do aparelho é obrigatório")
    String aparelhoMarca,

    @NotBlank (message = "O campo número de série/IMEI é obrigatório")
    String numeroSerieImei,

    @NotBlank (message = "O campo descrição do problema é obrigatório")
    String descricaoProblema,

    @NotNull (message = "O campo valor estimado é obrigatório")
    BigDecimal valorEstimado,

    @NotNull (message = "O campo cliente é obrigatório")
    Integer clienteId,

    // @NotNull (message = "O campo funcionário responsável é obrigatório")
    Integer funcionarioResponsavelId,

    StatusOs statusOs,
    
    LocalDateTime dataConclusao
)
{}
    