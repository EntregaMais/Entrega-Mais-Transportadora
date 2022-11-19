package br.com.entrega_mais.transportadora.controller;

import br.com.entrega_mais.transportadora.model.Transportadora;
import br.com.entrega_mais.transportadora.repository.TransportadoraRepository;
import br.com.entrega_mais.transportadora.service.RabbitmqService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/transportadoras")
public class TransportadoraController {

    @Autowired
    TransportadoraRepository transportadoraRepository;

    @Autowired
    private RabbitmqService rabbitmqService;

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
        System.out.println(responseEntityStr);
        //JsonNode root = objectMapper.readTree(responseEntityStr.getBody());
        System.out.println(responseEntityStr.getBody());
        Gson gson = new Gson();
        System.out.println(gson.toJson( responseEntityStr.getBody()));
        Map<String,Object> attributes = gson.fromJson(responseEntityStr.getBody(),Map.class);
        System.out.println(attributes);
        int idcoletado = ((Number)attributes.get("id")).intValue();
        transportadora.setIdusuario(Integer.toString(idcoletado));

        //this.rabbitmqService.enviaMensagem("filacadastroresposta", transportadora);
        return ResponseEntity.ok(transportadoraRepository.save(transportadora));
    }


    @RequestMapping(value = "/transportadoraPorEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<Transportadora> GetByEmail(@PathVariable(value = "email") String email)
    {
        Optional<Transportadora> transportadora = transportadoraRepository.findByEmail(email);
        if(transportadora.isPresent())
            return new ResponseEntity<Transportadora>(transportadora.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
