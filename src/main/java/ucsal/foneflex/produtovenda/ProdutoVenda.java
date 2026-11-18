package ucsal.foneflex.produtovenda;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ucsal.foneflex.produto.Produto;
import ucsal.foneflex.venda.Venda;

@Entity 
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor
public class ProdutoVenda
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_produto_venda")
    private Integer id;

    @JoinColumn(name = "codigo_produto")
    @ManyToOne 
    private Produto produto;

    @JoinColumn(name = "codigo_venda")
    @ManyToOne
    private Venda venda;

    @Column(nullable = false, updatable = true)
    private Integer quantidade = 1;

    private BigDecimal precoUnitario;

    private BigDecimal subTotal;
}
