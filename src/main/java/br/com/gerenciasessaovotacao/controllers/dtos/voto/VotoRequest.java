package br.com.gerenciasessaovotacao.controllers.dtos.voto;

import br.com.gerenciasessaovotacao.domains.models.VotoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequest {

    @NotBlank
    private String cpfAssociado;

    @NotBlank
    private String tituloSessao;

    @NotBlank
    private String tituloPauta;

    @NotNull
    private VotoEnum voto;
}
