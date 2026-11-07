package ucsal.foneflex.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_cliente")
    private Integer id;

    @Column (nullable = false, updatable = true)
    private String nome;

    @Column (nullable = false, updatable = true)
    private String cpf;

    @Column (nullable = false, updatable = true)
    private String telefone;

    @Column (nullable = true, updatable = true)
    private String email;

    @CreatedDate
    @Column (nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @OneToMany 
    private List<Endereco> enderecos;
}
