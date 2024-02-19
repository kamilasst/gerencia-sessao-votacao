package br.com.gerenciasessaovotacao.respositories;

import br.com.gerenciasessaovotacao.domains.models.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query("SELECT COUNT(v) > 0 FROM Voto v WHERE v.associado.cpf = :cpf AND v.pauta.titulo = :tituloPauta")
    boolean associadoJaVotouPauta(@Param("cpf") final String cpf, final String tituloPauta);

    @Query("SELECT v FROM Voto v INNER JOIN v.pauta p WHERE p.titulo = :tituloPauta")
    List<Voto> resultadoVotacao(String tituloPauta);
}
