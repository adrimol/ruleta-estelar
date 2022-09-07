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
              "<!DOCTYPE html>\n"
                  + "<html>\n"
                  + "  <head>\n"
                  + "    <style>\n"
                  + "      .contenedor{\n"
                  + "      position: relative;\n"
                  + "      display: inline-block;\n"
                  + "      text-align: center;\n"
                  + "      }\n"
                  + "      .texto-encima{\n"
                  + "      position: absolute;\n"
                  + "      top: 10px;\n"
                  + "      left: 10px;\n"
                  + "      }\n"
                  + "      .centrado{\n"
                  + "      position: absolute;\n"
                  + "      top: 50%;\n"
                  + "      left: 50%;\n"
                  + "      transform: translate(-50%, -50%);\n"
                  + "      }\n"
                  + "      img {\n"
                  + "        width:100%;\n"
                  + "        height:100%;\n"
                  + "      }\n"
                  + "    </style>\n"
                  + "  </head>\n"
                  + "  <body>\n"
                  + "    <div class=\"contenedor\">\n"
                  + "      <img"
                  + " src=\"https://stellarjson.s3.us-west-2.amazonaws.com/images/roulette.png\"/>\n"
                  + "      <div class=\"texto-encima\">\n"
                  + "        <h3><span style=\"background-color: #914c53; color: #ffffff; padding:"
                  + " 0 3px;\">\n"
                  + "        Ruleta Estelar </br> 2022</span></h3>\n"
                  + "      </div>\n"
                  + "      <div class=\"centrado\">\n"
                  + "        <p><strong>Digita tu número ganador y se uno de los afortunados en el"
                  + " próximo giro</strong></p>   \n"
                  + "        <input type=\"number\" id=\"numero\" name=\"numero\" required"
                  + " min=\"1\" max=\"54\" >\n"
                  + "        <button type=\"button\" id=\"btnJugar\">Jugar</button>\n"
                  + "      </div>\n"
                  + "    </div>\n"
                  + "    <script>\n"
                  + "\t\tdocument.getElementById('btnJugar').addEventListener(\"click\","
                  + " function(e) {\n"
                  + "\t\t\tvar message = {\n"
                  + "        ready: true,\n"
                  + "        keyboard_input : {\n"
                  + "            enable : true,\n"
                  + "            key : \"count\",\n"
                  + "            type: \"int\"\n"
                  + "        }\n"
                  + "      }\n"
                  + ";\n"
                  + "            var domain = 'https://dev.pagando.tech';\n"
                  + "\t\t\tparent.postMessage(message,domain); //sending the message\n"
                  + "\t\t}, false);\n"
                  + "    </script>\n"
                  + "  </body>\n"
                  + "</html>");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @GetMapping("/iframes/administrative")
  @ResponseBody
  public ResponseEntity getRuletaAdmin(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(
              "<!DOCTYPE html>\n"
                  + "<html>\n"
                  + "<body>\n"
                  + "\n"
                  + "<div>\n"
                  + "<h1>Ruleta parent window listener</h1>\n"
                  + "<p>Message received: [object Object]</p>\n"
                  + "</div>\n"
                  + "<script>\n"
                  + "window.addEventListener('message', function(e) {\t\n"
                  + "  var origin = e.origin;\n"
                  + "  document.getElementsByTagName('p')[0].innerHTML = 'Message received:  ' +"
                  + " e.data;\n"
                  + "  console.log('Message received:  ' + e.data);\n"
                  + "}, false);\n"
                  + "</script>\n"
                  + "\n"
                  + "<iframe src=\"https://ruleta-estelar.herokuapp.com/iframes/game\""
                  + " title=\"Ruleta Estelar\"\n"
                  + "height=500 width=500 onload=\"onRuletaLoaded(this)\">\n"
                  + "</iframe>\n"
                  + "<script>\n"
                  + "  function onRuletaLoaded() {\n"
                  + "    var message = 'Ruleta cargada por completo!';\n"
                  + "    var domain = 'https://dev.pagando.tech';\n"
                  + "\tparent.postMessage(message,domain); //sending the message\n"
                  + "  };\n"
                  + "  </script>\n"
                  + "\n"
                  + "</body>\n"
                  + "</html>\n");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @GetMapping("/iframes/test")
  @ResponseBody
  public ResponseEntity getRuletaTest(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(
              "<!DOCTYPE html>\n"
                  + "<html>\n"
                  + "<body>\n"
                  + "\n"
                  + "<div>\n"
                  + "<h1>Ruleta parent window listener</h1>\n"
                  + "<p>Message received: [object Object]</p>\n"
                  + "</div>\n"
                  + "<script>\n"
                  + "window.addEventListener('message', function(e) {\t\n"
                  + "  var origin = e.origin;\n"
                  + "  document.getElementsByTagName('p')[0].innerHTML = 'Message received:  ' +"
                  + " e.data;\n"
                  + "  console.log('Message received:  ' + e.data);\n"
                  + "}, false);\n"
                  + "</script>\n"
                  + "\n"
                  + "<iframe src=\"https://ruleta-estelar.herokuapp.com/iframes/game\""
                  + " title=\"Ruleta Estelar\"\n"
                  + "height=500 width=500 onload=\"onRuletaLoaded(this)\">\n"
                  + "</iframe>\n"
                  + "<script>\n"
                  + "  function onRuletaLoaded() {\n"
                  + "    var message = 'Ruleta cargada por completo!';\n"
                  + "    var domain = 'https://ruleta-estelar.herokuapp.com';\n"
                  + "\tparent.postMessage(message,domain); //sending the message\n"
                  + "  };\n"
                  + "  </script>\n"
                  + "\n"
                  + "</body>\n"
                  + "</html>\n");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @PatchMapping("/api/payorder/generate")
  @ResponseBody
  public ResponseEntity generatePayOrder(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(
              "<html><head><script type=\"text/javascript\">        function generatePayOrder() {  "
                  + "          var message = \"{\"payorder\":"
                  + " \"ef41d3ac-2e4f-11ed-a261-0242ac120002\"\"competitors\": [{\"name\":"
                  + " \"nombre\",\"id_competitor\": 2,\"category_code\": \"categoria\",\"price\":"
                  + " 4.50,\"event_datetime\": 2022-09-15 23:50:55.999 -0500}]}\";            var"
                  + " domain = 'https://dev.pagando.tech';           "
                  + " parent.postMessage(message,domain);        }        </script></head><body"
                  + " onload=\"generatePayOrder();\">Pay Order Patch</body></html>");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @PostMapping("/api/payorder/confirm")
  @ResponseBody
  public ResponseEntity confirmPayOrder(RequestEntity<String> requestEntity) {
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body("<html><body>Pay Order confirmation disabled!</body></html>");
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
