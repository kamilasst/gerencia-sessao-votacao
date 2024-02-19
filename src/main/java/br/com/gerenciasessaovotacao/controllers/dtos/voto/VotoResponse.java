package br.com.gerenciasessaovotacao.controllers.dtos.voto;

import br.com.gerenciasessaovotacao.domains.models.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoResponse {

    private String associadoInfo;

    private String sessaoInfo;

    private String pautaInfo;

    private VotoEnum voto;

    private LocalDateTime dataVoto;
}
