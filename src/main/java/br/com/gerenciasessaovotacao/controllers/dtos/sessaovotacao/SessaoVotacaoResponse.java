package br.com.gerenciasessaovotacao.controllers.dtos.sessaovotacao;

import br.com.gerenciasessaovotacao.domains.models.Pauta;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoResponse {

    private String titulo;

    private String pautaInfo;

    private LocalDateTime dataHoraAbertura;

    private LocalDateTime dataHoraFechamento;
}
