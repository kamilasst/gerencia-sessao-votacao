package br.com.gerenciasessaovotacao.controllers.dtos.voto;

import br.com.gerenciasessaovotacao.domains.models.Voto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoCalculoVotos {

    private List<Voto> listaVotosSim;
    private List<Voto> listaVotosNao;
}
