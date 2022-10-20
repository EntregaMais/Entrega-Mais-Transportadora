package br.com.entrega_mais.transportadora.connections;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {
    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, false, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    //está função é executada assim que nossa classe é instanciada pelo Spring, devido a anotação @Component
    @PostConstruct
    private void adiciona(){
        Queue filaCadastro = this.fila("FILA_CADASTRO_USUARIO");
        Queue filaCadastroResposta = this.fila("filacadastroresposta");

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoCadastro = this.relacionamento(filaCadastro, troca);
        Binding ligacaoCadastroResposta = this.relacionamento(filaCadastroResposta, troca);

        //Criando as filas no RabbitMQ
        this.amqpAdmin.declareQueue(filaCadastro);
        this.amqpAdmin.declareQueue(filaCadastroResposta);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoCadastro);
        this.amqpAdmin.declareBinding(ligacaoCadastroResposta);
    }
}
