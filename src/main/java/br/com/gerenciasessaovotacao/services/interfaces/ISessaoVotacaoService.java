package br.com.gerenciasessaovotacao.services.interfaces;

import br.com.gerenciasessaovotacao.controllers.dtos.sessaovotacao.SessaoVotacaoResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.sessaovotacao.SessaoVotaocaoRequest;

import java.time.LocalDateTime;

public interface ISessaoVotacaoService {
    SessaoVotacaoResponse abrirSessao(SessaoVotaocaoRequest sessaoVotaocaoRequest);

    boolean isSessaoVotacaoAberta(final Long pautaId, final LocalDateTime dataHora);
}
