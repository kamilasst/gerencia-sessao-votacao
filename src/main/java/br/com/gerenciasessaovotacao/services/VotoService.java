package br.com.gerenciasessaovotacao.services;

import br.com.gerenciasessaovotacao.controllers.dtos.voto.*;
import br.com.gerenciasessaovotacao.domains.models.*;
import br.com.gerenciasessaovotacao.exceptions.ErroConstantes;
import br.com.gerenciasessaovotacao.exceptions.NegocioException;
import br.com.gerenciasessaovotacao.respositories.VotoRepository;
import br.com.gerenciasessaovotacao.services.interfaces.IAssociadoService;
import br.com.gerenciasessaovotacao.services.interfaces.IPautaService;
import br.com.gerenciasessaovotacao.services.interfaces.ISessaoVotacaoService;
import br.com.gerenciasessaovotacao.services.interfaces.IVotoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VotoService implements IVotoService {

    private final VotoRepository votoRepository;
    private final IAssociadoService associadoService;
    private final IPautaService pautaService;
    private final ISessaoVotacaoService sessaoVotacaoService;

    public VotoService(final VotoRepository votoRepository, final IAssociadoService associadoService, final IPautaService pautaService, final ISessaoVotacaoService sessaoVotacaoService) {
        this.votoRepository = votoRepository;
        this.associadoService = associadoService;
        this.pautaService = pautaService;
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @Override
    public VotoResponse votar(final VotoRequest votoRequest) {

        Associado associado = associadoService.findByCPF(votoRequest.getCpfAssociado());
        associadoService.validarNaoExiste(associado);

        Pauta pauta = pautaService.findByTitulo(votoRequest.getTituloPauta());
        pautaService.validarNaoExiste(pauta);

        SessaoVotacao sessaoVotacao = sessaoVotacaoService.findByTituloEPauta(votoRequest.getTituloSessao(), pauta.getId());
        sessaoVotacaoService.validarNaoExiste(sessaoVotacao);

        validarSessaoVotacaoAberta(pauta.getId(), pauta.getTitulo(), votoRequest.getTituloSessao(), LocalDateTime.now());
        validarAssociadoJaVotouPauta(votoRequest.getCpfAssociado(), votoRequest.getTituloPauta());

        Voto voto = Voto.builder().associado(associado).
                pauta(pauta).sessaoVotacao(sessaoVotacao).voto(votoRequest.getVoto()).
                dataVoto(LocalDateTime.now()).build();

        votoRepository.save(voto);

        return VotoResponse.builder()
                .associadoInfo("Associado info: " + associado.toString())
                .sessaoInfo("Sessao Votacao info: " + sessaoVotacao)
                .pautaInfo("Pauta info: " + pauta)
                .voto(voto.getVoto())
                .dataVoto(voto.getDataVoto())
                .build();
    }

    private void validarSessaoVotacaoAberta(final Long pautaId, final String tituloPauta, final String tituloSessao, final LocalDateTime dataAtual) {
        if (!sessaoVotacaoService.isSessaoVotacaoAberta(pautaId, tituloSessao, dataAtual)) {
            throw new NegocioException(String.format(ErroConstantes.SESSAO_VOTACAO_FECHADA, tituloPauta));
        }
    }

    private void validarAssociadoJaVotouPauta(final String cpf, final String tituloPauta) {
        if (associadoJaVotouPauta(cpf, tituloPauta)) {
            throw new NegocioException(ErroConstantes.ASSOCIADO_JA_VOTOU_PAUTA);
        }
    }

    private boolean associadoJaVotouPauta(final String cpf, final String tituloPauta) {
        return votoRepository.associadoJaVotouPauta(cpf, tituloPauta);
    }

    @Override
    public ResultadoVotacaoResponse resultadoVotacao(final ResultadoVotacaoRequest resultadoVotacaoRequest) {
        List<Voto> votos = votoRepository.resultadoVotacao(resultadoVotacaoRequest.getTituloPauta(), resultadoVotacaoRequest.getTituloSessao());

        ResultadoCalculoVotos resultadoCalculoVotos = calcularvotos(votos);
        return ResultadoVotacaoResponse.builder()
                .listaVotosSim(resultadoCalculoVotos.getListaVotosSim())
                .listaVotosNao(resultadoCalculoVotos.getListaVotosNao())
                .totalVotosSim(resultadoCalculoVotos.getListaVotosSim().size())
                .totalVotosNao(resultadoCalculoVotos.getListaVotosNao().size())
                .build();
    }

    private static ResultadoCalculoVotos calcularvotos(final List<Voto> votos) {

        List<Voto> listaVotosSim = new ArrayList<>();
        List<Voto> listaVotosNao = new ArrayList<>();

        for (Voto voto: votos) {
            if (voto.getVoto().equals(VotoEnum.SIM)){
                listaVotosSim.add(voto);
            } else {
                listaVotosNao.add(voto);
            }
        }

        ResultadoCalculoVotos resultadoCalculoVotos = new ResultadoCalculoVotos();
        resultadoCalculoVotos.setListaVotosSim(listaVotosSim);
        resultadoCalculoVotos.setListaVotosNao(listaVotosNao);
        return resultadoCalculoVotos;
    }
}
