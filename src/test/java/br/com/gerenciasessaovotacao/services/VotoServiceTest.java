package br.com.gerenciasessaovotacao.services;

import br.com.gerenciasessaovotacao.controllers.dtos.voto.ResultadoVotacaoRequest;
import br.com.gerenciasessaovotacao.controllers.dtos.voto.ResultadoVotacaoResponse;
import br.com.gerenciasessaovotacao.domains.models.Associado;
import br.com.gerenciasessaovotacao.domains.models.Pauta;
import br.com.gerenciasessaovotacao.domains.models.Voto;
import br.com.gerenciasessaovotacao.domains.models.VotoEnum;
import br.com.gerenciasessaovotacao.exceptions.ErroConstantes;
import br.com.gerenciasessaovotacao.respositories.VotoRepository;
import br.com.gerenciasessaovotacao.testutils.TestesConstantes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepositoryMock;

    @Test
    void resultadoVotacaoCalcularVotosComSucesso(){

        //arrange
        ResultadoVotacaoRequest resultadoVotacaoRequest = ResultadoVotacaoRequest.builder()
                .tituloPauta(TestesConstantes.TITULO_PAUTA_12)
                .tituloSessao(TestesConstantes.TITULO_SESSAO_1)
                .build();

        Associado associado1 = Associado.builder()
                .id(1L)
                .cpf(TestesConstantes.CPF_VALIDO_20512589020)
                .nome(TestesConstantes.NOME_JOSE_DA_SILVA)
                .build();

        Associado associado2 = Associado.builder()
                .id(2L)
                .cpf("69828228009")
                .nome("Maria José")
                .build();

        Associado associado3 = Associado.builder()
                .id(3L)
                .cpf("69051643055")
                .nome("Carlos Antonio")
                .build();

        Pauta pauta = Pauta.builder()
                .id(1L)
                .titulo(TestesConstantes.TITULO_PAUTA_12)
                .descricao("Descricao Pauta 12")
                .dataCriacao(LocalDateTime.now())
                .build();

        Voto votoSim1 = Voto.builder().id(1L).associado(associado1).pauta(pauta).voto(VotoEnum.SIM).dataVoto(LocalDateTime.now()).build();
        Voto votoNao1 = Voto.builder().id(2L).associado(associado2).pauta(pauta).voto(VotoEnum.NAO).dataVoto(LocalDateTime.now()).build();
        Voto votoSim2 = Voto.builder().id(3L).associado(associado3).pauta(pauta).voto(VotoEnum.SIM).dataVoto(LocalDateTime.now()).build();

        List<Voto> votos = new ArrayList<>();
        votos.add(votoSim1);
        votos.add(votoNao1);
        votos.add(votoSim2);

        when(votoRepositoryMock.resultadoVotacao(resultadoVotacaoRequest.getTituloPauta(), resultadoVotacaoRequest.getTituloSessao())).thenReturn(votos);

        //act
        ResultadoVotacaoResponse resultadoVotacaoResponse = votoService.resultadoVotacao(resultadoVotacaoRequest);

        ResultadoVotacaoResponse resultadoVotacaoResponseEsperado = ResultadoVotacaoResponse.builder()
                .listaVotosSim(Arrays.asList(votoSim1, votoSim2))
                .listaVotosNao(Arrays.asList(votoNao1))
                .totalVotosSim(2)
                .totalVotosNao(1)
                .build();

        //asserts
        Assertions.assertEquals(resultadoVotacaoResponseEsperado.getListaVotosNao().size(), resultadoVotacaoResponse.getListaVotosNao().size());
        Assertions.assertEquals(resultadoVotacaoResponseEsperado.getTotalVotosNao(), resultadoVotacaoResponse.getTotalVotosNao());
        Assertions.assertEquals(resultadoVotacaoResponseEsperado.getListaVotosNao().get(0), resultadoVotacaoResponse.getListaVotosNao().get(0));

        Assertions.assertEquals(resultadoVotacaoResponseEsperado.getListaVotosSim().size(), resultadoVotacaoResponse.getListaVotosSim().size());
        Assertions.assertEquals(resultadoVotacaoResponseEsperado.getTotalVotosSim(), resultadoVotacaoResponse.getTotalVotosSim());
        Assertions.assertEquals(resultadoVotacaoResponseEsperado.getListaVotosSim().get(0), resultadoVotacaoResponse.getListaVotosSim().get(0));
        Assertions.assertEquals(resultadoVotacaoResponseEsperado.getListaVotosSim().get(1), resultadoVotacaoResponse.getListaVotosSim().get(1));
    }

}
