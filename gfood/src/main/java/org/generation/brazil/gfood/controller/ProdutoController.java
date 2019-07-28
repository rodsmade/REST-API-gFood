package org.generation.brazil.gfood.controller;

import org.generation.brazil.gfood.exception.ResourceNotFoundException;
import org.generation.brazil.gfood.model.Produto;
import org.generation.brazil.gfood.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoRepository repository;

  //                                                                              //
  //                                                                              //
  //                                                                              //
  //      ~*-_*+           MÉTODOS DO CONTROLLER DE CLIENTE         +*_-*~        //
  //                                                                              //
  //                                                                              //
  //                                                                              //

  //      (   C   )                                                MÉTODOS DE CREATE

  // INSERIR NOVO REGISTRO
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public Produto criarNovoProduto(@RequestBody Produto produto) {
    return repository.save(produto);
  }



  //      (   R   )                                                  MÉTODOS DE READ

  // LISTAR TODOS OS REGISTROS      ("SELECT * FROM cliente")
  @GetMapping
  public List<Produto> buscarTodos() {
    return repository.findAll();
  }

  // BUSCAR UM REGISTRO PELO ID
  @GetMapping("/{id}")
  public Produto buscarPorId(@PathVariable("id") Long id) {
    return repository.findById(id).get();
  }

  // FAZER BUSCA POR NOME (retorna lista com correspondências)
  @GetMapping("/{nome}")
  public List<Produto> buscarPeloNome(@PathVariable("nome") String nome){
    return repository.findByNome(nome);
  }

  @GetMapping("/filter/preco/less-than") // ?preco=10.00
  public List<Produto> buscarPorPrecoMenorQue(@RequestParam BigDecimal preco) {
    return repository.findByPrecoLessThan(preco);
  }

  @GetMapping("/filter/preco/greater-than") // ?preco=10.00
  public List<Produto> buscarPorPrecoMaiorQue(@RequestParam BigDecimal preco) {
    return repository.findByPrecoGreaterThan(preco);
  }

  @GetMapping("/filter/between") // ?minimo=10.00&maximo=20.00
  public List<Produto> buscarPorPrecoEntre(@RequestParam BigDecimal minimo, BigDecimal maximo) {
    return repository.findByPrecoBetween(minimo, maximo);
  }

  //      (   U   )                                                MÉTODOS DE UPDATE

  // ATUALIZAR REGISTRO PELO ID
  @PatchMapping("/atualizar/{id}")
  public Optional<Produto> atualizarPrecoPorId(@PathVariable Long id, @RequestParam BigDecimal preco) {
    repository.updatePrecoById(preco, id);
    return repository.findById(id);
  }



  //      (   D   )                                         MÉTODOS DE DELETE

  // DELETAR REGISTRO PELO ID
  @DeleteMapping("/{id}")
  public void deletarPorId(@PathVariable("id") Long id) {
    repository.deleteById(id);
  }

  @DeleteMapping("/delete-by")
  public void deletarPorPrecoMenorQue(@RequestParam BigDecimal preco) {
    // "DELETE FROM produto WHERE preco < ..."
    repository.deleteByPrecoLessThan(preco);
  }
}
