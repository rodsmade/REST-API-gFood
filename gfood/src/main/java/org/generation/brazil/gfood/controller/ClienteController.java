package org.generation.brazil.gfood.controller;

import org.generation.brazil.gfood.model.Cliente;
import org.generation.brazil.gfood.repository.ClienteRepository;
import org.generation.brazil.gfood.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/clientes")
public class ClienteController {

  // Não usamos esse Logger (ainda) mas é pra deixar mensagens no console, tipo o sout, mas diferente??
  private final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

  // pra poder instanciar ProdutoRepository, q n rola de instanciar pq é interface, usa a anotação @Autowired
  @Autowired
  private ClienteRepository repository;

  //                                                                       //
  //                                                                       //
  //                                                                       //
  //     ~*-_*+          MÉTODOS DO CONTROLLER DE CLIENTE        +*_-*~    //
  //                                                                       //
  //                                                                       //
  //                                                                       //

  //      (   C   )                                         MÉTODOS DE CREATE

  // INSERIR NOVO REGISTRO ("INSERT INTO cliente ...")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Cliente save(@RequestBody Cliente cliente) {
    return repository.save(cliente);
  }



  //      (   R   )                                           MÉTODOS DE READ

  // LISTAR TODOS OS REGISTROS      ("SELECT * FROM cliente")
  @GetMapping
  public List<Cliente> listarTodosRegistros() {
    return repository.findAll();
  }

  // BUSCAR UM REGISTRO PELO ID     ("SELECT * FROM cliente WHERE id=...")
  @GetMapping("/clientes/{id}")
  public Optional<Cliente> buscarPeloID(@PathVariable("id") Long id) {
    return repository.findById(id);
  }

  // BUSCAR PELO NOME               ("SELECT * FROM cliente WHERE nome=...")
  @GetMapping("/clientes/filter/nome") // ?nome=Hélio Pierazzo
  public List<Cliente> buscarPorNome(@RequestParam String nome) {
    return repository.findByNome(nome);
  }

  // BUSCAR PELA DATA DE NASCIMENTO ("SELECT * FROM cliente WHERE nasc=...")
  @GetMapping("/clientes/filter/data-nascimento") // ?data-nascimento=1990-07-18
  public List<Cliente> buscarPorNascimento(
          @RequestParam(value = "data-nascimento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAa) {
    return repository.findByDataDeNascimento(dataAa);
  }

  // BUSCAR POR NOME E DATA DE NASCIMENTO
   @PostMapping("/clientes/nomeEnasc")
   public List<Cliente> buscarPorNomeENascimento(@RequestParam String nome, @RequestParam LocalDate nasc){
     return repository.findByNomeAndDataDeNascimento(nome,nasc);
   }



   //      (   U   )                                        MÉTODOS DE UPDATE

   // ATUALIZAR REGISTRO (ENDERECO, NOME, NASCIMENTO)
   //                               ("UPDATE cliente SET ... WHERE ...")
  @PutMapping("/clientes/{id}")
  public Cliente atualizarRegistroPorId(@PathVariable("id") Long id, @RequestBody Cliente cliente)
      throws ResourceNotFoundException {
    return repository.findById(id).map(c -> {
      c.setEndereco(cliente.getEndereco());
      c.setNome(cliente.getNome());
      c.setDataDeNascimento(cliente.getDataDeNascimento());
      return repository.save(c);

    }).orElseThrow(
        () -> new ResourceNotFoundException("Não existe cliente cadastrado com o id: " + id));
  }

  // ATUALIZAR NOME DO REGISTRO     ("UPDATE cliente SET ... WHERE ...")
  @PatchMapping("/clientes/update/{id}")
  public Optional<Cliente> atualizarNomePeloId(@PathVariable("id") Long id, @RequestParam String nome) {
    repository.updateNomeById(nome, id);
    return repository.findById(id);
  }



  //      (   D   )                                         MÉTODOS DE DELETE

  // APAGAR PELO ID                 ("DELETE FROM cliente WHERE id = ...")
  @DeleteMapping("/clientes/{id}")
  public void apagarPeloId(@PathVariable("id") Long id) {
    repository.deleteById(id);
  }

  // APAGAR PELO NASCIMENTO         ("DELETE FROM cliente WHERE data_nascimento = ... AND nome = ...")
  @DeleteMapping("/clientes/delete-by")
  public void apagarPorNomeENascimento(
          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataNascimento,
          @RequestParam String nome) {
    repository.deleteByDataNascimentoAndNome(dataNascimento, nome);
  }

}