package br.com.entrega_mais.transportadora.controller;

import br.com.entrega_mais.transportadora.model.Transportadora;
import br.com.entrega_mais.transportadora.repository.TransportadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/transportadoras")
public class TransportadoraController {

    @Autowired
    TransportadoraRepository transportadoraRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getForm(Transportadora transportadora, ModelAndView mav) {
        mav.addObject("transportadora", transportadora);
        mav.setViewName("/transportadoras/form");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ModelAndView salvar(@Valid Transportadora transportadora, BindingResult validation, ModelAndView mav, RedirectAttributes attrs) {

        if (validation.hasErrors()) {
            mav.setViewName("/transportadoras/form");
            return mav;
        } else {
            //TODO colocar ROLE e chamar método que forma UsuarioModel p fzr associação

            transportadoraRepository.save(transportadora);

            mav.setViewName("redirect:/login");
            attrs.addFlashAttribute("mensagem", "Transportadora cadastrado com sucesso!");

            return mav;
        }

    }

}
