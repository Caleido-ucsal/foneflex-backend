package ucsal.foneflex.endereco;

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
import ucsal.foneflex.cliente.Cliente;

@Entity 
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Endereco
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_endereco")
    private Integer id;

    private String rua;

    private String numero;

    private String bairro;

    private String cep;

    private String complemento;

    private String pontoReferencia;

    @ManyToOne 
    @JoinColumn (name = "codigo_cliente")
    private Cliente cliente;
}
