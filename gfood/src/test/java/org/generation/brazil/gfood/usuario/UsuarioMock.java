package org.generation.brazil.gfood.usuario;

import com.github.javafaker.Faker;
import org.generation.brazil.gfood.model.Usuario;

import java.util.Locale;

public class UsuarioMock {

  public static Usuario getUsuarioMock() {
    Usuario usuario = new Usuario();
    Faker faker = new Faker(new Locale("pt-BR"));
    usuario.setNome(faker.name().fullName());
    usuario.setEmail(faker.internet().emailAddress());
    usuario.setLogin(faker.name().username());
    usuario.setSenha(faker.internet().password());
    return usuario;
  }

}
