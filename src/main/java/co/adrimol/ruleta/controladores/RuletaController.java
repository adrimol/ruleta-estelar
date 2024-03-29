package co.adrimol.ruleta.controladores;

import co.adrimol.ruleta.entidades.Result;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
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
                  + "        width:90%;\n"
                  + "        height:90%;\n"
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

  @PostMapping(path = "/api/payorders", produces = "application/json")
  @ResponseBody
  public ResponseEntity generatePayOrder(RequestEntity<String> requestEntity) {
    try {
      log.info("generatePayOrder -> requestEntity: {}", requestEntity);
      JSONObject json = new JSONObject(requestEntity.getBody());
      Object uuid = json.get("id");
      log.info("generatePayOrder -> uuid: {}", uuid);
      Object cantidad = json.get("cantidad");
      log.info("generatePayOrder -> cantidad: {}", cantidad);
      DateFormat formatter = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
      String fechaActual = formatter.format(new Date());
      log.info("generatePayOrder -> fechaActual: {}", fechaActual);
      // Save to DB and get status and message
      Map<String, Object> data = new HashMap<>();
      data.put("uuid", uuid);
      data.put("cantidad", cantidad);
      data.put("fechaActual", fechaActual);
      data.put("body", requestEntity.getBody());
      data.put("address", requestEntity.getHeaders().get("X-Forwarded-For"));
      boolean saveOk = saveData("api-payorder-generate", data);

      if (saveOk) {
        Result result = new Result("success", "Payorder successfully generated.");
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
                + "     \"alias\": \"RuletaStellar("
                + json.get("numero")
                + ")\","
                + "     \"id\": "
                + json.get("numero")
                + ","
                + "     \"code\": \"RUL001\","
                + "     \"price\": "
                + cantidad
                + ","
                + "     \"event_datetime\": \""
                + fechaActual
                + "\""
                + "    }"
                + "  ]"
                + " }"
                + "}";
        log.info("generatePayOrder -> body: {}", body);
        return ResponseEntity.status(HttpStatus.OK).body(body);
      } else {
        Result result = new Result("failed", "Payorder generation failed.");
        String body =
            "{ "
                + "\"status\": \""
                + result.getStatus()
                + "\","
                + "\"message\": \""
                + result.getMessage()
                + "\","
                + "\"data\": {"
                + " }"
                + "}";
        log.info("generatePayOrder -> body: {}", body);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("{" + "\"status\":\"failed\"" + "}");
      }

    } catch (Exception e) {
      log.error("generatePayOrder -> Exception: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  @PostMapping(path = "/api/payorders/{payorder}/products", produces = "application/json")
  @ResponseBody
  public ResponseEntity addCompetitor(
      RequestEntity<String> requestEntity, @PathVariable String payorder) {
    try {
      log.info("addCompetitor -> requestEntity: {}", requestEntity);
      log.info("addCompetitor -> payorder: {}", payorder);
      JSONObject json = new JSONObject(requestEntity.getBody());
      Object id = json.get("id");
      log.info("addCompetitor -> id: {}", id);
      Object cantidad = json.get("cantidad");
      log.info("generatePayOrder -> cantidad: {}", cantidad);
      DateFormat formatter = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
      String fechaActual = formatter.format(new Date());
      log.info("generatePayOrder -> fechaActual: {}", fechaActual);

      String errorMessage = StringUtil.EMPTY_STRING;
      if (Objects.isNull(id)) {
        errorMessage = "Id not found.";
      }
      if (Objects.isNull(payorder)) {
        errorMessage = "Payorder not found.";
      }

      // Save to DB and get status and message
      Map<String, Object> data = new HashMap<>();
      data.put("id", id);
      data.put("payorder", payorder);
      data.put("body", requestEntity.getBody());
      data.put("message", errorMessage);
      data.put("address", requestEntity.getHeaders().get("X-Forwarded-For"));

      boolean saveOk = saveData("api-payorders-add", data);
      if (saveOk && Strings.isEmpty(errorMessage)) {
        Result result = new Result("success", "Payorder successfully added.");
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
                + payorder
                + "\","
                + "\"competitors\": ["
                + "   {"
                + "     \"alias\": \"RuletaStellar("
                + json.get("numero")
                + ")\","
                + "     \"id\": "
                + json.get("numero")
                + ","
                + "     \"code\": \"RUL001\","
                + "     \"price\": "
                + cantidad
                + ","
                + "     \"event_datetime\": \""
                + fechaActual
                + "\""
                + "    }"
                + "  ]"
                + " }"
                + "}";
        log.info("generatePayOrder -> body: {}", body);
        return ResponseEntity.status(HttpStatus.OK).body(body);
      } else {
        Result result = new Result("failed", "Payorder generation failed.");
        String body =
            "{ "
                + "\"status\": \""
                + result.getStatus()
                + "\","
                + "\"message\": \""
                + result.getMessage()
                + "\","
                + "\"data\": {"
                + " }"
                + "}";
        log.info("generatePayOrder -> body: {}", body);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("{" + "\"status\":\"failed\"" + "}");
      }

    } catch (Exception e) {
      log.error("addCompetitor Exception: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }

  @DeleteMapping(path = "/api/payorders/{payorder}", produces = "application/json")
  @ResponseBody
  public ResponseEntity removePayorder(
      RequestEntity<String> requestEntity, @PathVariable String payorder) {
    try {
      log.info("removeCompetitor -> requestEntity: {}", requestEntity);
      log.info("removeCompetitor -> payorder: {}", payorder);

      // Save to DB and get status and message
      Map<String, Object> data = new HashMap<>();
      data.put("payorder", payorder);
      data.put("body", requestEntity.getBody());

      boolean saveOk = saveData("api-payorders-delete", data);
      if (saveOk) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body("{" + "\"status\":\"success\"" + "}");
      } else {
        String body = "{ " + "\"status\": \"failed\"," + "\"message\": \"" + "N/A" + "\"" + "}";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
      }

    } catch (Exception e) {
      log.error("removeCompetitor Exception: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }

  @DeleteMapping(
      path = "/api/payorders/{payorder}/products/{competitor}",
      produces = "application/json")
  @ResponseBody
  public ResponseEntity removeCompetitor(
      RequestEntity<String> requestEntity,
      @PathVariable String payorder,
      @PathVariable String competitor) {
    try {
      log.info("removeCompetitor -> requestEntity: {}", requestEntity);
      log.info("removeCompetitor -> payorder: {}", payorder);
      log.info("removeCompetitor -> competitor: {}", competitor);

      // Save to DB and get status and message
      Map<String, Object> data = new HashMap<>();
      data.put("payorder", payorder);
      data.put("competitor", competitor);
      data.put("body", requestEntity.getBody());

      boolean saveOk = saveData("api-competitors-delete", data);
      if (saveOk) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body("{" + "\"status\":\"success\"" + "}");
      } else {
        String body = "{ " + "\"status\": \"failed\"," + "\"message\": \"" + "N/A" + "\"" + "}";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
      }

    } catch (Exception e) {
      log.error("removeCompetitor Exception: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }

  @PostMapping(path = "/api/number", produces = "application/json")
  @ResponseBody
  public ResponseEntity setRuletaNumber(RequestEntity<String> requestEntity) {
    try {
      log.info("setRuletaNumber -> requestEntity: {}", requestEntity);
      JSONObject json = new JSONObject(requestEntity.getBody());
      Object number = json.get("number");
      log.info("setRuletaNumber -> number: {}", number);

      Map<String, Object> data = new HashMap<>();
      data.put("fecha", new Timestamp(System.currentTimeMillis()));
      data.put("numero", number);
      data.put("body", requestEntity.getBody());
      data.put("address", requestEntity.getHeaders().get("X-Forwarded-For"));

      boolean saveOk = saveData("api-number", data);

      if (saveOk) {
        return ResponseEntity.status(HttpStatus.OK).body("{" + "\"status\":\"success\"" + "}");
      } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("{" + "\"status\":\"failed\"" + "}");
      }

    } catch (Exception e) {
      log.error("setRuletaNumber Exception: {}", e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  private boolean saveData(final String collection, final Map<String, Object> data) {
    // Use the application default credentials
    FirebaseOptions options = null;
    try {
      options =
          FirebaseOptions.builder()
              .setCredentials(GoogleCredentials.getApplicationDefault())
              .setProjectId("stellariumfirebase")
              .build();
    } catch (IOException e) {
      log.error("saveData IOException: {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }
    try {
      FirebaseApp.getInstance();
    } catch (IllegalStateException e) {
      // Firebase not initialized automatically, do it manually
      FirebaseApp.initializeApp(options);
    }

    Firestore db = FirestoreClient.getFirestore();
    DocumentReference docRef = db.collection(collection).document(getTimestamp());
    // Add document data  with id set on previous document attr using a hashmap
    // asynchronously write data
    ApiFuture<WriteResult> result = docRef.set(data);
    // result.get() blocks on response
    try {
      log.info("Update time : " + result.get().getUpdateTime());
    } catch (InterruptedException e) {
      log.error("saveData InterruptedException: {}", e.getMessage(), e);
      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      log.error("saveData ExecutionException: {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }

    return result.isDone();
  }

  private String getTimestamp() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    return timestamp.toString();
  }

  private String actualDateToString() {
    SimpleDateFormat DateFor = new SimpleDateFormat("dd-MMM-yyyy");
    return DateFor.format(new Date());
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
