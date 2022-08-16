package co.adrimol.ruleta.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RuletaController {

  @GetMapping(
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE},
      path = "/ruleta")
  public ResponseEntity getRuleta(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body("<html><body>Hola</body></html>");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }
}
