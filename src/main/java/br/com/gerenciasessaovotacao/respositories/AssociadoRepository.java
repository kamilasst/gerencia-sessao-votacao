package br.com.gerenciasessaovotacao.respositories;

import br.com.gerenciasessaovotacao.domains.models.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Associado findByCpf(final String cpf);
}
