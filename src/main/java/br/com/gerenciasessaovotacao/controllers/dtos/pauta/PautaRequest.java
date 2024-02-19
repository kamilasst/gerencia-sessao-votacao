package br.com.gerenciasessaovotacao.controllers.dtos.pauta;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PautaRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;
}
