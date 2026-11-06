package ucsal.foneflex.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, updatable = true)
    private String nome;

    @Column (nullable = false, updatable = true)
    private String cpf;

    @Column (nullable = false, updatable = true)
    private String telefone;

    @Column (nullable = true, updatable = true)
    private String email;

    // @CreatedDate
    // @Column (nullable = false, updatable = false)
    // private LocalDateTime dataCadastro;
}
