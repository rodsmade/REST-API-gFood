package org.generation.brazil.gfood.usuario;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.generation.brazil.gfood.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/teste")
    @ApiOperation(value="Cria um novo usuário",
            notes = "Insere um novo usuário. Passe os valores no corpo da request.",
            response = Usuario.class)
    @ApiResponses(value={
            @ApiResponse(code = 201, message = "Inserido com sucesso!"),
            @ApiResponse(code = 401, message = "Sem autorização."),
            @ApiResponse(code = 403,message="Proibido."),
            @ApiResponse(code = 404,message="Recurso não encontrado."),
    })
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping
    @ApiOperation(value="Lista todos os usuários")
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    @ApiOperation(value="Busca usuário pelo id")
    public Optional<Usuario> findById(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    @PutMapping("/usuarios/{id}")
    @ApiOperation(value="Atualiza registro de usuário, busca pelo id")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario)
            throws ResourceNotFoundException {
        // "UPDATE produto SET ... WHERE ..."
        return usuarioRepository.findById(id).map(usuarioAtualizado -> {
            usuarioAtualizado.setNome(usuario.getNome());
            usuarioAtualizado.setEmail(usuario.getEmail());
            return usuarioRepository.save(usuarioAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não existe produto cadastrado com o id: " + id));
    }

    @DeleteMapping("/usuarios/{id}")
    @ApiOperation(value="Deleta um novo usuário")
    public void delete(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

}
