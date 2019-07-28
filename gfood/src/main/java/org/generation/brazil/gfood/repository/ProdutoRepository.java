package org.generation.brazil.gfood.repository;

import org.generation.brazil.gfood.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


@Repository     // essa annotarion "Repository" substitui o DAO (entende-se que DAO é obsoleto)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // List<Produto> findByNome(String nome);
    List<Produto> findByNome(String nome);

    List<Produto> findByPrecoLessThan(BigDecimal preco);

    List<Produto> findByPrecoGreaterThan(BigDecimal preco);

    List<Produto> findByPrecoBetween(BigDecimal minimo, BigDecimal maximo);

    @Modifying
    @Transactional
    @Query("UPDATE Produto p SET p.preco = :preco WHERE p.id = :id")
    void updatePrecoById(@Param("preco") BigDecimal preco, @Param("id") Long id);

    @Modifying
    @Transactional
    void deleteByPrecoLessThan(BigDecimal preco);

}
