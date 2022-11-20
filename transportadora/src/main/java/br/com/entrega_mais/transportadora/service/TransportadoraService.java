package br.com.entrega_mais.transportadora.service;

import br.com.entrega_mais.transportadora.model.Transportadora;
import br.com.entrega_mais.transportadora.repository.TransportadoraRepository;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    @Transactional
    @SneakyThrows
    public Transportadora salvarTransportadora (Transportadora transportadora){
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
        System.out.println(responseEntityStr.getBody());

        Gson gson = new Gson();
        System.out.println(gson.toJson( responseEntityStr.getBody()));
        Map<String,Object> attributes = gson.fromJson(responseEntityStr.getBody(),Map.class);

        System.out.println(attributes);

        int idcoletado = ((Number)attributes.get("id")).intValue();
        transportadora.setIdusuario(Integer.toString(idcoletado));
        return transportadoraRepository.save(transportadora);
    }

    public Optional<Transportadora> encontraTransportadoraPorEmail(String email){
        return transportadoraRepository.findByEmail(email);
    }

    @Transactional
    public Transportadora atualizarTransportadora(String email, Transportadora transportadora_atualizada) {

        if (transportadoraRepository.findByEmail(email).isPresent()){
            Transportadora transportadoraExistente = transportadoraRepository.findByEmail(email).get();

            transportadoraExistente.setNm_resp(transportadora_atualizada.getNm_resp());
            transportadoraExistente.setTelefone(transportadora_atualizada.getTelefone());
            transportadoraExistente.setPix(transportadora_atualizada.getPix());
            transportadoraExistente.setCobra_embarque(transportadora_atualizada.getCobra_embarque());

            Transportadora transportadoraAtualizada = transportadoraRepository.save(transportadoraExistente);

            return transportadoraExistente;

        }else{
            return null;
        }
    }

}