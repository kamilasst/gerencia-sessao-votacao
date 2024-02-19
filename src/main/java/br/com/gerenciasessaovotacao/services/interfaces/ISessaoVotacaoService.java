package br.com.gerenciasessaovotacao.services.interfaces;

import br.com.gerenciasessaovotacao.controllers.dtos.sessaovotacao.SessaoVotacaoResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.sessaovotacao.SessaoVotaocaoRequest;
import br.com.gerenciasessaovotacao.domains.models.SessaoVotacao;

import java.time.LocalDateTime;

public interface ISessaoVotacaoService {
    SessaoVotacaoResponse abrirSessao(SessaoVotaocaoRequest sessaoVotaocaoRequest);

    boolean isSessaoVotacaoAberta(final Long pautaId,final String tituloSessao, final LocalDateTime dataHora);

    SessaoVotacao findByTituloEPauta(final String tituloSessao, final Long pautaId);

    void validarNaoExiste(final SessaoVotacao sessaoVotacao);
}
