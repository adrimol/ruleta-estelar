package co.adrimol.ruleta.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RuletaController {

  @GetMapping("/iframes/game")
  @ResponseBody
  public ResponseEntity getRuleta(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(
              "<html>\n"
                  + "  <head>\n"
                  + "    <style>\n"
                  + "    .contenedor{\n"
                  + "    position: relative;\n"
                  + "    display: inline-block;\n"
                  + "    text-align: center;\n"
                  + "}\n"
                  + ".texto-encima{\n"
                  + "    position: absolute;\n"
                  + "    top: 10px;\n"
                  + "    left: 10px;\n"
                  + "}\n"
                  + ".centrado{\n"
                  + "    position: absolute;\n"
                  + "    top: 50%;\n"
                  + "    left: 50%;\n"
                  + "    transform: translate(-50%, -50%);\n"
                  + "}\n"
                  + "</style>\n"
                  + "  </head>\n"
                  + "  <body>\n"
                  + "    <div class=\"contenedor\">\n"
                  + "  <img"
                  + " src=\"https://stellarjson.s3.us-west-2.amazonaws.com/images/roulette.png\""
                  + " />\n"
                  + "  <div class=\"texto-encima\">\n"
                  + "    <h3><span style=\"background-color: #914c53; color: #ffffff; padding: 0"
                  + " 3px;\">Ruleta Estelar 2022</span></h3>\n"
                  + "  </div>\n"
                  + "  <div class=\"centrado\">\n"
                  + "  \t<p><strong>Digita tu número ganador y se uno de los afortunados en el"
                  + " próximo giro</strong></p>   \n"
                  + "    <input type=\"number\" id=\"numero\" name=\"numero\" required min=\"1\""
                  + " max=\"54\" >\n"
                  + "    <button type=\"button\">Jugar</button>\n"
                  + "  </div>\n"
                  + "</div>\n"
                  + "  </body>\n"
                  + "</html>\n"
                  + "\n");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @GetMapping("/iframes/administrative")
  @ResponseBody
  public ResponseEntity getRuletaAdmin(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body("<html><body>Ruleta Admin Get</body></html>");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @PatchMapping("/api/payorder/generate")
  @ResponseBody
  public ResponseEntity generatePayOrder(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body("<html><body>Pay Order Patch</body></html>");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @PostMapping("/api/payorder/confirm")
  @ResponseBody
  public ResponseEntity confirmPayOrder(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body("<html><body>Confirm Pay Order post</body></html>");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @PostMapping("/ruleta")
  @ResponseBody
  public ResponseEntity getRuletaPost(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body("<html><body>Ruleta Post</body></html>");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }
}
