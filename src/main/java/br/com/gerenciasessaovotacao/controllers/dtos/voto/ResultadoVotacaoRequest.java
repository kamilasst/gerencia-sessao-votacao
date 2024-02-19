package br.com.gerenciasessaovotacao.controllers.dtos.voto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacaoRequest {

    @NotBlank
    private String tituloPauta;
}
