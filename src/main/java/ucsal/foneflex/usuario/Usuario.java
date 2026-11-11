package ucsal.foneflex.usuario;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
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

@Entity (name = "funcionario")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_funcionario")
    private Integer id;

    @Column (name = "nome_funcionario",nullable = false, updatable = true)
    private String nome;

    @Column (nullable = false, updatable = true)
    private String email;

    @Column (nullable = false, updatable = true)
    private String senha;

    @Column (name = "status", nullable = false, updatable = true)
    private Boolean ativo;

    @CreatedDate
    private LocalDateTime dataInclusao;
}
