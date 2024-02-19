package br.com.gerenciasessaovotacao.services;

import br.com.gerenciasessaovotacao.controllers.dtos.sessaovotacao.SessaoVotacaoResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.sessaovotacao.SessaoVotaocaoRequest;
import br.com.gerenciasessaovotacao.domains.models.Pauta;
import br.com.gerenciasessaovotacao.domains.models.SessaoVotacao;
import br.com.gerenciasessaovotacao.exceptions.ErroConstantes;
import br.com.gerenciasessaovotacao.exceptions.NegocioException;
import br.com.gerenciasessaovotacao.respositories.SessaoVotacaoRepository;
import br.com.gerenciasessaovotacao.services.interfaces.IPautaService;
import br.com.gerenciasessaovotacao.services.interfaces.ISessaoVotacaoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class SessaoVotacaoService implements ISessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final IPautaService pautaService;

    public SessaoVotacaoService (final SessaoVotacaoRepository sessaoVotacaoRepository, final IPautaService pautaService) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaService = pautaService;
    }

    public SessaoVotacaoResponse abrirSessao(final SessaoVotaocaoRequest sessaoVotaocaoRequest) {

        Pauta pauta = pautaService.findByTitulo(sessaoVotaocaoRequest.getTituloPauta());
        pautaService.validarNaoExiste(pauta);

        validarSessaoParaPauta(pauta.getId(), sessaoVotaocaoRequest.getTitulo());

        LocalDateTime dataHoraFechamento = calcularDataFechamento(sessaoVotaocaoRequest.getDataHoraAbertura(),
                sessaoVotaocaoRequest.getDataHoraFechamento());
        validarDatas(sessaoVotaocaoRequest.getDataHoraAbertura(),
                dataHoraFechamento);

        SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
                .titulo(sessaoVotaocaoRequest.getTitulo())
                .pauta(pauta)
                .dataHoraAbertura(sessaoVotaocaoRequest.getDataHoraAbertura())
                .dataHoraFechamento(dataHoraFechamento)
                .build();

        sessaoVotacaoRepository.save(sessaoVotacao);

        SessaoVotacaoResponse sessaoVotacaoResponse = SessaoVotacaoResponse.builder()
                .titulo(sessaoVotacao.getTitulo())
                .pautaInfo("Pauta Info: " + sessaoVotacao.getPauta().toString())
                .dataHoraAbertura(sessaoVotacao.getDataHoraAbertura())
                .dataHoraFechamento(sessaoVotacao.getDataHoraFechamento())
                .build();

        return sessaoVotacaoResponse;
    }

    private void validarSessaoParaPauta(final Long pautaId, final String tituloSessao) {
        if (sessaoVotacaoRepository.existeSessaoParaPauta(pautaId,tituloSessao)){
            throw new NegocioException(ErroConstantes.SESSAO_PARA_PAUTA_JA_EXISTE);
        }
    }

    private LocalDateTime calcularDataFechamento(final LocalDateTime dataHoraAbertura, final LocalDateTime dataHoraFechamento) {
        if (Objects.isNull(dataHoraFechamento)) {
            return dataHoraAbertura.plusMinutes(1);
        }
        return dataHoraFechamento;
    }

    private void validarDatas(final LocalDateTime dataHoraAbertura, final LocalDateTime dataHoraFechamento) {
        if (dataHoraAbertura.compareTo(dataHoraFechamento) == 0) {
            throw new NegocioException(ErroConstantes.DATA_ABERTURA_IGUAL_DATA_FECHAMENTO);
        }
        if (dataHoraAbertura.compareTo(dataHoraFechamento) > 0) {
            throw new NegocioException(ErroConstantes.DATA_ABERTURA_MENOR_DATA_FECHAMENTO);
        }
    }

    public boolean isSessaoVotacaoAberta(final Long pautaId, final String tituloSessao, final LocalDateTime dataHora) {
        return sessaoVotacaoRepository.isSessaoVotacaoAberta(pautaId, tituloSessao, dataHora);
    }

    @Override
    public SessaoVotacao findByTituloEPauta(final String titulo, final Long pautaId) {
        return sessaoVotacaoRepository.findByTituloEPauta(titulo, pautaId);
    }

    @Override
    public void validarNaoExiste(final SessaoVotacao sessaoVotacao) {
        if (Objects.isNull(sessaoVotacao)) {
            throw new NegocioException(ErroConstantes.SESSAO_VOTACAO_NAO_ENCONTRADA);
        }
    }
}
