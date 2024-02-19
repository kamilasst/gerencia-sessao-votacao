package br.com.gerenciasessaovotacao.controllers.dtos.voto;

import br.com.gerenciasessaovotacao.domains.models.Voto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacaoResponse {

    private List<Voto> listaVotosSim;

    private List<Voto> listaVotosNao;

    private Integer totalVotosSim;

    private Integer totalVotosNao;
}
