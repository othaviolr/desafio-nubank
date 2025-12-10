package springboot.desafio.nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.desafio.nubank.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {


}
