package br.com.gerenciasessaovotacao.exceptions;

public class ErroConstantes {

    public static final String ASSOCIADO_NAO_ENCONTRADO = "Associado não encontrado";
    public static final String CPF_INVALIDO = "CPF inválido";
    public static final String CPF_EXISTENTE = "CPF já cadastrado";
    public static final String DATA_ABERTURA_MENOR_DATA_FECHAMENTO = "A data de abertura deve ser antes da data de fechamento.";
    public static final String DATA_ABERTURA_IGUAL_DATA_FECHAMENTO = "A data de abertura não pode ser igual a data de fechamento.";
    public static final String PAUTA_NAO_ENCONTRADA = "Pauta não encontrada";
    public static final String PAUTA_JA_EXISTE = "Pauta já existe";
    public static final String ASSOCIADO_JA_VOTOU_PAUTA = "Associado já votou nesta pauta";
    public static final String SESSAO_VOTACAO_FECHADA = "Sessão de votação fechada para a pauta '%s'";

    private ErroConstantes(){}
}
