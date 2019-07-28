package org.generation.brazil.gfood.controller;

import org.generation.brazil.gfood.service.GatoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GatoController {

  private static final Logger logger = LoggerFactory.getLogger(GatoController.class);

  @Autowired
  private GatoService gatoService;

  @GetMapping("/gatos/{statusCode}")
  public String obtemGatoDoCodigoDeStatus(@PathVariable Integer statusCode) {
    return gatoService.obtemGatoDoCodigoDeStatus(statusCode);
  }

}