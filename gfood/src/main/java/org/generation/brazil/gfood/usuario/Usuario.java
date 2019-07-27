package org.generation.brazil.gfood.usuario;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//  Adicionando Unique Constraint combinada que não deixa adicionar dois registros
//  com o mesmo e-mail e login
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        }),
        @UniqueConstraint(columnNames = {
                "login"
        })
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)    // Unique Constraint
    private String email;

    @NotBlank
    @Size(max = 100)
    @Column(unique = true)    // Unique Constraint
    private String login;

    @NotBlank
    @Size(max = 100)
    private String senha;

}