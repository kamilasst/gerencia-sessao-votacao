package br.com.gerenciasessaovotacao.exceptions;

public class NegocioException extends RuntimeException{

    private final String message;
    public NegocioException(final String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
