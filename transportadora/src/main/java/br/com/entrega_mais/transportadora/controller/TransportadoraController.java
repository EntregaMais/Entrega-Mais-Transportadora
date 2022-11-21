package br.com.entrega_mais.transportadora.controller;

import br.com.entrega_mais.transportadora.model.Transportadora;
import br.com.entrega_mais.transportadora.service.TransportadoraService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.SneakyThrows;
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
    TransportadoraService transportadoraService;

    //TODO add validacoes
    @PostMapping("/salvar")
    @SneakyThrows
    public ResponseEntity<Transportadora > salvar(@RequestBody Transportadora transportadora){
        return ResponseEntity.ok(transportadoraService.salvarTransportadora(transportadora));
    }
    @RequestMapping(value = "/transportadoraPorEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<Transportadora> GetByEmail(@PathVariable(value = "email") String email)
    {
        Optional<Transportadora> transportadora = transportadoraService.encontraTransportadoraPorEmail(email);
        if(transportadora.isPresent())
            return new ResponseEntity<Transportadora>(transportadora.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/transportadoraEdicao/{email}")
    public ResponseEntity<Transportadora> editar (@PathVariable(value = "email") String email, @RequestBody Transportadora transportadora){

        Transportadora transportadoraAtualizada = transportadoraService.atualizarTransportadora(email, transportadora);
        if(transportadora != null) {
            return new ResponseEntity<Transportadora>(transportadoraAtualizada, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

	@GetMapping("/ok")
    public ResponseEntity<String> testandoAPi() {
        return ResponseEntity.ok("ok");
    }

}
