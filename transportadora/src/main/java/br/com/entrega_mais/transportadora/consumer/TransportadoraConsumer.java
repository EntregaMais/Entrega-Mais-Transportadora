package br.com.entrega_mais.transportadora.consumer;

import br.com.entrega_mais.transportadora.controller.TransportadoraController;
import br.com.entrega_mais.transportadora.model.Transportadora;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransportadoraConsumer {

    @Autowired
    TransportadoraController transportadoraController;

    @RabbitListener(queues = "FILA_CADASTRO_USUARIO")
    private void consumidor(String mensagem) throws JsonProcessingException, InterruptedException, JSONException {
        Transportadora transportadora = new ObjectMapper().readValue(mensagem, Transportadora.class);

        transportadoraController.salvar(transportadora);

        System.out.println("Transportadora{" +
                    "id=" + transportadora.getId() +
                    ", email='" + transportadora.getEmail() + '\'' +
                    ", nm_empresa='" + transportadora.getNm_empresa()+ '\'' +
                    ", nm_resp='" + transportadora.getNm_resp() + '\'' +
                    ", cnpj='" + transportadora.getCnpj() + '\'' +
                    ", telefone='" + transportadora.getTelefone() + '\'' +
                    ", setor='" + transportadora.getSetor() + '\'' +
                    ", cobra_embarque=" + transportadora.getCobra_embarque() +
                    ", pix='" + transportadora.getPix() + '\'' +
                    ", idusuario='" + transportadora.getIdusuario() + '\'' +
                    '}');


        System.out.println("------------------------------------");

        throw new IllegalArgumentException("Argumento inválido!");
    }
}
