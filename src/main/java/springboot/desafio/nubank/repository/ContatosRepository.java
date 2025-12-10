package springboot.desafio.nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.desafio.nubank.model.Contatos;

public interface ContatosRepository extends JpaRepository<Contatos, Long> {


}
