package br.com.entrega_mais.transportadora.controller;

import br.com.entrega_mais.transportadora.model.Transportadora;
import br.com.entrega_mais.transportadora.repository.TransportadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/transportadoras")
public class TransportadoraController {

    @Autowired
    TransportadoraRepository transportadoraRepository;


    //TODO associar a um usuario
    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public void salvar(@Valid Transportadora transportadora) {
        transportadoraRepository.save(transportadora);
    }

}
