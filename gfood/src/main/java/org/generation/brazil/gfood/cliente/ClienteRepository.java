package org.generation.brazil.gfood.cliente;

import jdk.vm.ci.meta.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // SELECT * FROM cliente WHERE nome = :nome;
    List<Cliente> findByNome(String s); // esses nomes de função são padrão do spring (jpa? wtf) e são mágicos pois são meio que pré-implementados.

    // SELECT * FROM cliente WHERE data_nascimento = :dataNascimento;
    List<Cliente> findByDataDeNascimento(LocalDate d);  // o parâmetro passado pra dentro vem do front
                // por algum motivo (intellij não é pago?) não tá dando certo de puxar os nomes dos métodos ):

    // SELECT * FROM cliente WHERE nome = :nome AND data_nascimento = :dataNascimento;
    List<Cliente> findByNomeAndDataDeNascimento(String s, LocalDate d);

    @Modifying
    @Transactional
    @Query("UPDATE Cliente c SET c.nome = :nome WHERE c.id = :id")
    void updateNomeById(@Param("nome") String nome, @Param("id") Long id);

    @Modifying
    @Transactional
    void deleteByDataNascimentoAndNome(LocalDate dataNascimento, String nome);


}