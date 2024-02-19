package br.com.gerenciasessaovotacao.services;

import br.com.gerenciasessaovotacao.exceptions.ErroConstantes;
import br.com.gerenciasessaovotacao.exceptions.NegocioException;
import br.com.gerenciasessaovotacao.services.interfaces.ICPFValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CPFValidatorService implements ICPFValidatorService {
    @Override
    public void validar(final String cpf) {
        if (!isCpfValido(cpf)) {
            throw new NegocioException(ErroConstantes.CPF_INVALIDO);
        }
    }
    private static boolean isCpfValido(String cpf) {

        try {
            if(StringUtils.isBlank(cpf)) {
                return false;
            }

            cpf = normalizar(cpf);
            if (!isTamanhoValido(cpf) || containsNumerosRepetidos(cpf)) {
                return false;
            }
            return isDigitosVerificacaoValidos(cpf);
        } catch (Exception e) {
            log.error("Erro ao validar CPF", e);
            return false;
        }
    }

    private static String normalizar(final String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }

    private static boolean isTamanhoValido(final String cpf) {
        return cpf.length() == 11;
    }

    private static boolean containsNumerosRepetidos(final String cpf) {
        return cpf.matches("(\\d)\\1{10}");
    }

    private static boolean isDigitosVerificacaoValidos(final String cpf) {
        int[] numbers = cpf.chars().map(Character::getNumericValue).toArray();
        return isPrimeiroDigitoVerificacaoValido(numbers) && isSegundoDigitoVerificacaoValido(numbers);
    }

    private static boolean isPrimeiroDigitoVerificacaoValido(int[] numbers) {
        return calcularDigitoVerificacao(numbers, 9, 10) == numbers[9];
    }

    private static boolean isSegundoDigitoVerificacaoValido(int[] numbers) {
        return calcularDigitoVerificacao(numbers, 10, 11) == numbers[10];
    }

    private static int calcularDigitoVerificacao(int[] numbers, int end, int factorLimit) {
        int sum = 0;
        for (int i = 0; i < end; i++) {
            sum += numbers[i] * (factorLimit - i);
        }
        return sum % 11 < 2 ? 0 : 11 - (sum % 11);
    }
}
