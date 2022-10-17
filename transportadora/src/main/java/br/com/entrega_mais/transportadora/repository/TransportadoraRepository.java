package br.com.entrega_mais.transportadora.repository;

import br.com.entrega_mais.transportadora.model.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Integer> {
}
