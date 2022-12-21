package br.com.entrega_mais.transportadora;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.entrega_mais.transportadora.model.Transportadora;
import br.com.entrega_mais.transportadora.repository.TransportadoraRepository;
import br.com.entrega_mais.transportadora.service.TransportadoraService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

public class TransportadoraServiceTest {

    @Mock
    private TransportadoraRepository repository;

    private TransportadoraService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new TransportadoraService();
        service.setTransportadoraRepository(repository);
    }

    @AfterEach
    public void tearDown() {
        // Limpa o ambiente de teste depois de cada teste
    }

    @BeforeAll
    static void setupAll() {
        // Configurações que precisam ser realizadas uma vez antes de todos os testes
    }

    @AfterAll
    static void tearDownAll() {
        // Limpa o ambiente de teste depois que todos os testes são executados
    }

    @Test
    public void testSalvarTransportadora() {
        // Cria uma transportadora para simular o retorno do repository
        Transportadora transportadora = new Transportadora();
        transportadora.setEmail("transportadora@example.com");
        transportadora.setPassword("password");

        // Configura o mock do repository para retornar a transportadora acima
        when(repository.save(transportadora)).thenReturn(transportadora);

        // Executa o método salvarTransportadora do serviço com a transportadora acima
        Transportadora transportadoraSalva = service.salvarTransportadora(transportadora);

        // Verifica se a transportadora retornada é a mesma que foi passada
        assertEquals(transportadora, transportadoraSalva);
    }


    @Test
    public void testEncontrarTransportadoraPorEmail() {
        // Cria uma transportadora para simular o retorno do repository
        Transportadora transportadora = new Transportadora();
        transportadora.setEmail("transportadora@example.com");
        transportadora.setPassword("password");

        // Configura o mock do repository para retornar a transportadora acima
        when(repository.findByEmail("transportadora@example.com")).thenReturn(Optional.of(transportadora));

        // Executa o método encontrarTransportadoraPorEmail do serviço com o email "transportadora@example.com"
        Optional<Transportadora> transportadoraEncontrada = service.encontraTransportadoraPorEmail("transportadora@example.com");

        // Verifica se a transportadora retornada é a mesma que foi passada
        assertTrue(transportadoraEncontrada.isPresent());
        assertEquals(transportadora, transportadoraEncontrada.get());
    }
    
}
