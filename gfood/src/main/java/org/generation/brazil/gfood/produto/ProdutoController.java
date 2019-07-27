package org.generation.brazil.gfood.produto;

import org.generation.brazil.gfood.exception.ResourceNotFoundException;
import org.generation.brazil.gfood.produto.Produto;
import org.generation.brazil.gfood.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
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
  public Produto save(@RequestBody Produto produto) {
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
  public Produto encontrarPorId(@PathVariable("id") Long id) {
    return repository.findById(id).get();
  }

  // FAZER BUSCA POR NOME (retorna lista com correspondências)
  @GetMapping("/{nome}")
  public List<Produto> buscarPeloNome(@PathVariable("nome") String nome){
    return repository.findByNome(nome);
  }



  //      (   U   )                                                MÉTODOS DE UPDATE

  // ATUALIZAR REGISTRO PELO ID
  @PutMapping("/{id}")
  public Produto update(@PathVariable("id") Long id, @RequestBody Produto produto)
      throws ResourceNotFoundException {
    return repository.findById(id).map(produtttt -> {
      produtttt.setNome(produto.getNome());
      produtttt.setDescricao(produto.getDescricao());
      produtttt.setPreco(produto.getPreco());
      return repository.save(produtttt);

    }).orElseThrow(() -> new ResourceNotFoundException(
        "Não existe produto cadastrado com o id_produto: " + id));
  }



  //      (   D   )                                         MÉTODOS DE DELETE

  // DELETAR REGISTRO PELO ID
  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) {
    repository.deleteById(id);
  }

  @DeleteMapping("/delete-by")
  public void deleteByPrecoLessThan(@RequestParam BigDecimal preco) {
    // "DELETE FROM produto WHERE preco < ..."
    repository.deleteByPrecoLessThan(preco);
  }
}
