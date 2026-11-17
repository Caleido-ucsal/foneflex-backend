package ucsal.foneflex.produto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_produto")
    private Integer id;

    @Column(nullable = false, updatable = true)
    private String marcaProduto;

    @Column(nullable = false, updatable = true)
    private String nomeProduto;

    @Column(nullable = false, updatable = true)
    private BigDecimal precoUnitario;

    @Column(nullable = true, updatable = true)
    private String descricaoProduto;

    @Column(nullable = false, updatable = true)
    private String condicaoProduto;

    @Column(nullable = false, updatable = true)
    private Integer quantidadeEstoque;

    @LastModifiedDate
    @Column(nullable = false, updatable = true)
    private LocalDate dataRecebimento;
}
