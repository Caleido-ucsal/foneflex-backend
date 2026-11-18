package ucsal.foneflex.venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ucsal.foneflex.cliente.Cliente;
import ucsal.foneflex.produtovenda.ProdutoVenda;
import ucsal.foneflex.usuario.Usuario;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Venda
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venda")
    private Integer id;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "codigo_funcionario")
    private Usuario funcionario;

    @JoinColumn(name = "codigo_cliente", nullable = true, updatable = true)
    @ManyToOne
    private Cliente cliente;

    @CreatedDate
    private LocalDateTime dataVenda;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = true)
    private Status status = Status.EM_ABERTO;

    private BigDecimal valorVenda;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoVenda> produtosVenda = new ArrayList<>();
}
