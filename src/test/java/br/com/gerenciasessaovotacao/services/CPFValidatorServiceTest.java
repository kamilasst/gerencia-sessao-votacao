package br.com.gerenciasessaovotacao.services;

import br.com.gerenciasessaovotacao.exceptions.ErroConstantes;
import br.com.gerenciasessaovotacao.exceptions.NegocioException;
import br.com.gerenciasessaovotacao.testutils.TestesConstantes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CPFValidatorServiceTest {

    @InjectMocks
    private CPFValidatorService cpfValidatorService;

    @Test
    void validarDevereriaLancarExcecaoQuandoCpfForNulo() {
        final NegocioException negocioException =
                assertThrows(
                        NegocioException.class, () -> cpfValidatorService.validar(null));

        assertEquals(ErroConstantes.CPF_INVALIDO, negocioException.getMessage());
    }

    @Test
    void validarDevereriaLancarExcecaoQuandoCpfForVazio() {
        final NegocioException negocioException =
                assertThrows(
                        NegocioException.class, () -> cpfValidatorService.validar(""));

        assertEquals(ErroConstantes.CPF_INVALIDO, negocioException.getMessage());
    }

    @Test
    void validarDevereriaLancarExcecaoQuandoCpfDigitosMenor11() {
        final NegocioException negocioException =
                assertThrows(
                        NegocioException.class, () -> cpfValidatorService.validar("123"));

        assertEquals(ErroConstantes.CPF_INVALIDO, negocioException.getMessage());
    }

    @Test
    void validarDevereriaLancarExcecaoQuandoCpfTodosDigitosIguais() {
        final NegocioException negocioException =
                assertThrows(
                        NegocioException.class, () -> cpfValidatorService.validar("11111111111"));

        assertEquals(ErroConstantes.CPF_INVALIDO, negocioException.getMessage());
    }

    @Test
    void validarDevereriaLancarExcecaoQuandoCpfContendoLetras() {
        final NegocioException negocioException =
                assertThrows(
                        NegocioException.class, () -> cpfValidatorService.validar("ABC12589020"));

        assertEquals(ErroConstantes.CPF_INVALIDO, negocioException.getMessage());
    }

    @Test
    void validarDevereriaLancarExcecaoQuandoCpfErroPrimeiroDigitoVerificacao() {
        final NegocioException negocioException =
                assertThrows(
                        NegocioException.class, () -> cpfValidatorService.validar("07182587487"));

        assertEquals(ErroConstantes.CPF_INVALIDO, negocioException.getMessage());
    }

    @Test
    void validarDevereriaLancarExcecaoQuandoCpfErroSegundoDigitoVerificacao() {
        final NegocioException negocioException =
                assertThrows(
                        NegocioException.class, () -> cpfValidatorService.validar("07182587468"));

        assertEquals(ErroConstantes.CPF_INVALIDO, negocioException.getMessage());
    }

    @Test
    void validarNaoDevereriaLancarExcecaoCpfValido() {
        cpfValidatorService.validar(TestesConstantes.CPF_VALIDO_20512589020);
    }
}
