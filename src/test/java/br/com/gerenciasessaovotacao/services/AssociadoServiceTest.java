package br.com.gerenciasessaovotacao.services;

import br.com.gerenciasessaovotacao.controllers.dtos.associado.AssociadoIdResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.associado.AssociadoRequest;
import br.com.gerenciasessaovotacao.domains.models.Associado;
import br.com.gerenciasessaovotacao.exceptions.ErroConstantes;
import br.com.gerenciasessaovotacao.exceptions.NegocioException;
import br.com.gerenciasessaovotacao.respositories.AssociadoRepository;
import br.com.gerenciasessaovotacao.testutils.TestesConstantes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssociadoServiceTest {

    @InjectMocks
    private AssociadoService associadoService;

    @Mock
    private AssociadoRepository associadoRepositoryMock;

    @Mock
    private CPFValidatorService cpfValidatorServiceMock;

    @Test
    void salvarDeveriaSalvarAsssociadoBancoDados() {

        // arrange
        AssociadoRequest associadoRequest = AssociadoRequest.builder().nome(TestesConstantes.NOME_JOSE_DA_SILVA).cpf(TestesConstantes.CPF_VALIDO_20512589020).build();
        Associado associado = Associado.builder().id(1L).nome(TestesConstantes.NOME_JOSE_DA_SILVA).cpf(TestesConstantes.CPF_VALIDO_20512589020).build();

        when(associadoRepositoryMock.findByCpf(associadoRequest.getCpf())).thenReturn(null);
        doNothing().when(cpfValidatorServiceMock).validar(associadoRequest.getCpf());
        when(associadoRepositoryMock.save(associado)).thenAnswer(invocation -> {
            Associado associadoSalvo = invocation.getArgument(0);
            associadoSalvo.setId(1L);
            return associadoSalvo;
        });


        // act
        AssociadoIdResponse associadoIdResponse = associadoService.save(associadoRequest);

        // asserts
        assertEquals(1L, associadoIdResponse.getId());
    }

    @Test
    void findByCPFDeveriaRetornarAssociado() {

        // arrange
        Associado associadoMock = Associado.builder().id(1L).nome(TestesConstantes.NOME_JOSE_DA_SILVA).cpf(TestesConstantes.CPF_VALIDO_20512589020).build();
        when(associadoRepositoryMock.findByCpf(TestesConstantes.CPF_VALIDO_20512589020)).thenReturn(associadoMock);

        // act
        Associado associadoConsultado = associadoService.findByCPF(TestesConstantes.CPF_VALIDO_20512589020);

        // asserts
        assertEquals(1L, associadoConsultado.getId());
        assertEquals(TestesConstantes.NOME_JOSE_DA_SILVA, associadoConsultado.getNome());
        assertEquals(TestesConstantes.CPF_VALIDO_20512589020, associadoConsultado.getCpf());
    }


    @Test
    void validarDevereriaLancarExcecaoAssociadoNaoEncontrado() {
        final NegocioException negocioException =
                assertThrows(
                        NegocioException.class, () -> associadoService.validarNaoExiste(null));

        assertEquals(ErroConstantes.ASSOCIADO_NAO_ENCONTRADO, negocioException.getMessage());
    }

    @Test
    void validarNaoDevereriaLancarExcecao() {
        Associado associado = Associado.builder().id(1L).nome(TestesConstantes.NOME_JOSE_DA_SILVA).cpf(TestesConstantes.CPF_VALIDO_20512589020).build();
        associadoService.validarNaoExiste(associado);
    }
}
