package br.com.gerenciasessaovotacao.services.interfaces;

import br.com.gerenciasessaovotacao.controllers.dtos.pauta.PautaIdResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.pauta.PautaRequest;
import br.com.gerenciasessaovotacao.domains.models.Pauta;

public interface IPautaService {

    PautaIdResponse save(final PautaRequest pautaRequest);

    Pauta findByTitulo(final String titulo);

    void validarNaoExiste(final Pauta pauta);
}
