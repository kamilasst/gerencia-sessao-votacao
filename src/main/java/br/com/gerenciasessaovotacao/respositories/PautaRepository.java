package br.com.gerenciasessaovotacao.respositories;

import br.com.gerenciasessaovotacao.domains.models.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    Pauta findByTitulo(final String titulo);
}
