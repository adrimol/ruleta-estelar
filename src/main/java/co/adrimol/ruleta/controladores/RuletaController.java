package co.adrimol.ruleta.controladores;

import co.adrimol.ruleta.entidades.Result;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class RuletaController {

  @GetMapping("/iframes/game")
  @ResponseBody
  public ResponseEntity getRuleta(RequestEntity<String> requestEntity) {
    try {
      log.info("getRuleta -> requestEntity: {}", requestEntity);
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
                  + "  <body onload=\"onRuletaLoaded(this)\">\n"
                  + "    <div class=\"contenedor\">\n"
                  + "      <img"
                  + " src=\"https://stellarjson.s3.us-west-2.amazonaws.com/images/roulette.png\"/>\n"
                  + "      <div class=\"texto-encima\">\n"
                  + "        <h3><span style=\"background-color: #914c53; color: #ffffff; padding:"
                  + " 0 3px;\">\n"
                  + "        Ruleta Estelar </br> 2022</span></h3>\n"
                  + "      </div>\n"
                  + "      <div class=\"centrado\">\n"
                  + "        <p><strong>Digita el número al que quieres apostar en el siguiente"
                  + " giro y presiona 'Jugar'</strong></p>\n"
                  + "        <input type=\"number\" id=\"numero\" name=\"numero\" required"
                  + " min=\"1\" max=\"54\" >\n"
                  + "        <button type=\"button\" id=\"btnJugar\">Jugar</button>\n"
                  + "      </div>\n"
                  + "    </div>\n"
                  + "    <script>\n"
                  + " document.getElementById('btnJugar').addEventListener(\"click\",   function(e)"
                  + " {\n"
                  + "   var uuid = crypto.randomUUID();\n"
                  + "   var message = {\n"
                  + "    game_service_request: {\n"
                  + "       cantidad : 1,\n"
                  + "       numero : document.querySelector('input#numero').value,\n"
                  + "       id: uuid    },\n"
                  + "    game_option_data: {\n"
                  + "         giro_info: {\n"
                  + "                 giro_id: crypto.randomUUID(),\n"
                  + "                 slot: document.querySelector('input#numero').value,\n"
                  + "                 datetime: new Date().toString().substring(0,33)\n"
                  + "            }\n"
                  + "     }\n"
                  + "      }\n"
                  + " parent.postMessage(message,'https://dev.pagando.tech');\n"
                  + "  });\n"
                  + "  function onRuletaLoaded() {\n"
                  + " var message =  {\n"
                  + "   ready: true,\n"
                  + "   keyboard_input: {\n"
                  + "       enable : true,\n"
                  + "       key: \"cantidad\",\n"
                  + "       type: \"int\"\n"
                  + "    }\n"
                  + " };\n"
                  + "var domain = 'https://dev.pagando.tech';\n"
                  + "parent.postMessage(message,domain);\n"
                  + "};\n"
                  + "    </script>\n"
                  + "  </body>\n"
                  + "</html>");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @GetMapping("/test/game")
  @ResponseBody
  public ResponseEntity getRuletaTest(RequestEntity<String> requestEntity) {
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
                  + "        Ruleta Test </br> 2022</span></h3>\n"
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
                  + "document.getElementById('btnJugar').addEventListener(\"click\", function(e)"
                  + " {\n"
                  + "   var message = 'A ganar Estelares!';\n"
                  + "   var domain = 'https://ruleta-estelar.herokuapp.com';\n"
                  + "   parent.postMessage(message,domain);\n"
                  + "  }, false);    </script>  </body></html>");
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

  @GetMapping("/test/administrative")
  @ResponseBody
  public ResponseEntity getTestAdmin(RequestEntity<String> requestEntity) {
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
                  + "<iframe src=\"https://ruleta-estelar.herokuapp.com/test/game\""
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
                  + "</body>\n"
                  + "</html>\n");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @PatchMapping(path = "/api/payorder/generate", produces = "application/json")
  @ResponseBody
  public ResponseEntity generatePayOrder(RequestEntity<String> requestEntity) {
    try {
      log.info("generatePayOrder -> requestEntity: {}", requestEntity);
      JSONObject json = new JSONObject(requestEntity.getBody());
      Object uuid = json.get("id");
      log.info("generatePayOrder -> uuid: {}", uuid);
      DateFormat formatter = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
      String fechaActual = formatter.format(new Date());
      log.info("generatePayOrder -> fechaActual: {}", fechaActual);
      // TODO - Save to DB and get status and message
      Result result = new Result("SUCCESS", "Payorder successfully generated.");
      String body =
          "{ "
              + "\"status\": \""
              + result.getStatus()
              + "\","
              + "\"message\": \""
              + result.getMessage()
              + "\","
              + "\"data\": {"
              + "\"payorder_serial\":"
              + " \""
              + uuid
              + "\","
              + "\"competitors\": ["
              + "   {"
              + "     \"id\": "
              + json.get("numero")
              + ","
              + "     \"event_datetime\": \""
              + fechaActual
              + "\","
              + "     \"code\": \"1\","
              + "     \"price\": 4.50,"
              + "     \"alias\": \"RuletaStellar("
              + json.get("numero")
              + ")\""
              + "    }"
              + "  ]"
              + "}"
              + "}";
      log.info("generatePayOrder -> body: {}", body);
      return ResponseEntity.status(HttpStatus.OK).body(body);
    } catch (Exception e) {
      e.printStackTrace();
      log.error("generatePayOrder -> Exception: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
    }
  }

  @PostMapping(path = "/api/payorder/confirm", produces = "application/json")
  @ResponseBody
  public ResponseEntity confirmPayOrder(RequestEntity<String> requestEntity) {
    try {
      log.info("confirmPayOrder -> requestEntity: {}", requestEntity);
      return ResponseEntity.status(HttpStatus.OK).body("{" + "\"status\":\"success\"" + "}");
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
