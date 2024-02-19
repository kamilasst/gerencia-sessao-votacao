package br.com.gerenciasessaovotacao.services.interfaces;

import br.com.gerenciasessaovotacao.controllers.dtos.voto.ResultadoVotacaoResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.voto.VotoRequest;
import br.com.gerenciasessaovotacao.controllers.dtos.voto.VotoResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.voto.ResultadoVotacaoRequest;

public interface IVotoService {
    VotoResponse votar(final VotoRequest votoRequest);

    ResultadoVotacaoResponse resultadoVotacao(ResultadoVotacaoRequest votoTituloRequest);
}
