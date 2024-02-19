package br.com.gerenciasessaovotacao.services;

import br.com.gerenciasessaovotacao.controllers.dtos.pauta.PautaIdResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.pauta.PautaRequest;
import br.com.gerenciasessaovotacao.domains.models.Pauta;
import br.com.gerenciasessaovotacao.exceptions.ErroConstantes;
import br.com.gerenciasessaovotacao.exceptions.NegocioException;
import br.com.gerenciasessaovotacao.respositories.PautaRepository;
import br.com.gerenciasessaovotacao.services.interfaces.IPautaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PautaService implements IPautaService {

    private final PautaRepository pautaRepository;

    public PautaService(final PautaRepository pautaRepository){
        this.pautaRepository = pautaRepository;
    }

    public PautaIdResponse save(final PautaRequest pautaRequest) {

        validarExiste(pautaRequest.getTitulo());

        Pauta pauta = Pauta.builder()
                .titulo(pautaRequest.getTitulo())
                .descricao(pautaRequest.getDescricao())
                .dataCriacao(LocalDateTime.now())
                .build();

        pautaRepository.save(pauta);

        return PautaIdResponse.builder()
                .id(pauta.getId())
                .build();
    }

    @Override
    public Pauta findByTitulo(final String titulo) {
        return pautaRepository.findByTitulo(titulo);
    }

    private void validarExiste(final String titulo) {
        Pauta pauta = findByTitulo(titulo);
        if (Objects.nonNull(pauta)) {
            throw new NegocioException(ErroConstantes.PAUTA_JA_EXISTE);
        }
    }

    @Override
    public void validarNaoExiste(final Pauta pauta) {
        if (Objects.isNull(pauta)) {
            throw new NegocioException(ErroConstantes.PAUTA_NAO_ENCONTRADA);
        }
    }
}
