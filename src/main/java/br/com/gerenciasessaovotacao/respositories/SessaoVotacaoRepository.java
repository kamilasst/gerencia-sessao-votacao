package br.com.gerenciasessaovotacao.respositories;

import br.com.gerenciasessaovotacao.domains.models.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

    @Query("SELECT COUNT(s) > 0 FROM SessaoVotacao s WHERE s.pauta.id = :pautaId AND :dataHora BETWEEN s.dataHoraAbertura AND s.dataHoraFechamento")
    boolean isSessaoVotacaoAberta(@Param("pautaId") Long pautaId, @Param("dataHora") LocalDateTime dataHora);
}
