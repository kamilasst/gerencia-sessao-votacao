package br.com.gerenciasessaovotacao.controllers.dtos.sessaovotacao;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotaocaoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String tituloPauta;

    @NotNull
    private LocalDateTime dataHoraAbertura;

    private LocalDateTime dataHoraFechamento;
}
