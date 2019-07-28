package org.generation.brazil.gfood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"}),
        @UniqueConstraint(columnNames = {
                "login"})
})

public class Cliente {

  @Id         // identifica esse atributo como primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // igual à constraint AUTO_INCREMENT
  private Long id;


  @NotBlank // significa não pode ser vazio E NEM @NotNull (este garante q ñ é nulo, mas ñ q ñ é vazio)
  @Size(max = 100)  // tamanho máximo em caracteres
  private String nome;

  @Column(name = "endereco")  // nesse caso a anotação @Column é redundante
  private String endereco;

  @Column(name = "nascimento")       // Camel-case é padrão do java.
  @NotNull                         // usei o @Column pra linkar o nome aqui no java com o do BD
  private LocalDate dataDeNascimento;      // LocalDate pega o horário do servidor (parece, algo assim)
}