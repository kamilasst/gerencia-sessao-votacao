package br.com.gerenciasessaovotacao.services.interfaces;

import br.com.gerenciasessaovotacao.controllers.dtos.associado.AssociadoIdResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.associado.AssociadoRequest;
import br.com.gerenciasessaovotacao.domains.models.Associado;

public interface IAssociadoService {
    AssociadoIdResponse save(final AssociadoRequest associadoRequest);

    Associado findByCPF(final String cpf);

    void validarNaoExiste(final Associado associado);
}
