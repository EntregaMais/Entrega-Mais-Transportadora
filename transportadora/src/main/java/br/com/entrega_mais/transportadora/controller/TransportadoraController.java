package br.com.entrega_mais.transportadora.controller;

import br.com.entrega_mais.transportadora.model.Transportadora;
import br.com.entrega_mais.transportadora.repository.TransportadoraRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/api/transportadoras")
public class TransportadoraController {

    @Autowired
    TransportadoraRepository transportadoraRepository;


    //TODO add validacioes
    @Transactional
    @PostMapping("/salvar")
    public ResponseEntity<Transportadora > salvar(@RequestBody Transportadora transportadora) throws JSONException, JsonProcessingException {
        //ObjectMapper objectMapper = new ObjectMapper();

        String uri = "http://localhost:8080/api/usuario/salvar";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject usuarioJsonObject = new JSONObject();
        usuarioJsonObject.put("id", "");
        usuarioJsonObject.put("email", transportadora.getEmail());
        usuarioJsonObject.put("password", transportadora.getPassword());
        HttpEntity<String> request = new HttpEntity<String>(usuarioJsonObject.toString(), headers);

        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(uri, request, String.class);
        //JsonNode root = objectMapper.readTree(responseEntityStr.getBody());
        Gson gson = new Gson();
        Map<String,Object> attributes = gson.fromJson(gson.toJson( responseEntityStr.getBody()),Map.class);

        transportadora.setIdusuario(attributes.get("id").toString());


        return ResponseEntity.ok(transportadoraRepository.save(transportadora));
    }

}
