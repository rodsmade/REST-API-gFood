package org.generation.brazil.gfood.service;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GatoService {

  private static final Logger logger = LoggerFactory.getLogger(GatoService.class);

  public String obtemGatoDoCodigoDeStatus(Integer statusCode) {
    String retorno = null;
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        // .url("https://http.cat/" + statusCode)
        .url("https://viacep.com.br/ws/04548000/json/")
        .get()
        .build();
    try {
      Response response = client.newCall(request).execute();
      retorno = response.body().string();
    } catch (IOException e) {
      logger.info("Ocorreu um erro na hora de consumir a API de gatinhos");
    }
    return retorno;
  }

}