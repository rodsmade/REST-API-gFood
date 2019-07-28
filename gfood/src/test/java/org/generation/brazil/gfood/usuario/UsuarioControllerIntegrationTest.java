package org.generation.brazil.gfood.usuario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.generation.brazil.gfood.GfoodApplication;
import org.generation.brazil.gfood.model.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GfoodApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioControllerIntegrationTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @LocalServerPort
  private int port;

  /**
   * Monta a URL para a chamada de teste da API
   * @return String
   */
  private String getRootUrl() {
    return "http://localhost:" + port + "/api/v1/usuarios/";
  }

  /*
  @Before
  public void before() {
    // tudo que eu precisar fazer antes de comecar os testes
  }

  @After
  public void after() {
    // tudo que eu quiser que seja executado apos os testes
  }
  */

  @Test
  public void testaCriacaoDeUmNovoUsuario() {
    ResponseEntity<Usuario> postResponse =
        testRestTemplate.postForEntity(
            getRootUrl(),
            UsuarioMock.getUsuarioMock(),
            Usuario.class);
    assertNotNull(postResponse);
    assertEquals(201,
        postResponse.getStatusCodeValue());
  }

  @Test
  public void testaConsultaDeTodosOsUsuarios() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<String> response = testRestTemplate.exchange(
        getRootUrl(),
        HttpMethod.GET, entity, String.class);
    assertNotNull(response.getBody());
    assertEquals(200, response.getStatusCodeValue());
  }

  @Test
  public void testaConsultaPorId() {
    Usuario usuario = testRestTemplate.getForObject(
        getRootUrl() + "1",
        Usuario.class);
    System.out.println(usuario.getNome());
    assertNotNull(usuario);
  }

  @Test
  public void testaAtualizacaoDeUmUsuario() {
    int id = 1;
    Usuario usuario = testRestTemplate.getForObject(
        getRootUrl() + id,
        Usuario.class);
    String novoNome = UsuarioMock.getUsuarioMock().getNome();
    String novoEmail = UsuarioMock.getUsuarioMock().getEmail();
    usuario.setNome(novoNome);
    usuario.setEmail(novoEmail);
    testRestTemplate.put(getRootUrl() + id, usuario);
    Usuario usuarioAtualizado = testRestTemplate.getForObject(
        getRootUrl() + id,
        Usuario.class);
    assertNotNull(usuarioAtualizado);
    assertEquals(novoNome, usuarioAtualizado.getNome());
    assertEquals(novoEmail, usuarioAtualizado.getEmail());
  }

  @Test
  public void testaDelecaoDeUmUsuario() {
    int id = 2;
    Usuario usuario = testRestTemplate.getForObject(
        getRootUrl() + id,
        Usuario.class);
    assertNotNull(usuario);
    testRestTemplate.delete(getRootUrl() + id);
    try {
      testRestTemplate.getForObject(
          getRootUrl() + id,
          Usuario.class);
    } catch (final HttpClientErrorException e) {
      assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
    }
  }

}