package ucsal.foneflex.ordemservico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ucsal.foneflex.cliente.Cliente;
import ucsal.foneflex.usuario.Usuario;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter 
@Setter
@AllArgsConstructor 
@NoArgsConstructor 
public class OrdemServico
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_ordem")
    private Integer id;

    @Column (nullable = false, updatable = true)
    private String aparelhoModelo;

    @Column (nullable = false, updatable = true)
    private String aparelhoMarca;

    @Column (nullable = false, updatable = true)
    private String numeroSerieImei;

    @Column (nullable = false, updatable = true)
    private String descricaoProblema;

    @Column (nullable = false, updatable = true)
    private BigDecimal valorEstimado;

    @JoinColumn (name = "codigo_cliente", nullable = false, updatable = true)
    @ManyToOne
    private Cliente cliente;

    @JoinColumn (name = "codigo_funcionario", nullable = false, updatable = true)
    @ManyToOne
    @CreatedBy
    private Usuario funcionarioResponsavel;

    @Enumerated(EnumType.STRING)
    @Column (nullable = true, updatable = true)
    private StatusOs statusOS = StatusOs.EM_ANALISE;

    @Column (nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime dataAbertura;

    private LocalDateTime dataConclusao;
}
