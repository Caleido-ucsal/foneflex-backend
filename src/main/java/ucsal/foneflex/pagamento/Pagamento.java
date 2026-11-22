package ucsal.foneflex.pagamento;

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
import ucsal.foneflex.usuario.Usuario;
import ucsal.foneflex.venda.Venda;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_pagamento")
    private Long id;

    @JoinColumn(name = "codigo_funcionario", nullable = false, updatable = false)
    @ManyToOne
    @CreatedBy
    private Usuario funcionario;

    @CreatedDate
    private LocalDateTime dataPagamento;

    @JoinColumn(name = "codigo_venda")
    @ManyToOne
    private Venda venda;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeiraCartao;

    @Column(nullable = false, updatable = false)
    private BigDecimal valor = BigDecimal.ZERO;
}
