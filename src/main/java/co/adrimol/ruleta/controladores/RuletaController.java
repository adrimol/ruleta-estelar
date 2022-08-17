package co.adrimol.ruleta.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RuletaController {

  @GetMapping("/ruleta")
  @ResponseBody
  public ResponseEntity getRuleta(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body("<html><body>Hola Ruleta</body></html>");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @PostMapping("/ruleta-post")
  @ResponseBody
  public ResponseEntity getRuletaPost(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body("<html><body>Hola Ruleta Post</body></html>");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }
}
