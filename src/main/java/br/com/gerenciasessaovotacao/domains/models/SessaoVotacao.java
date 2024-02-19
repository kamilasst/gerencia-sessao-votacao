package br.com.gerenciasessaovotacao.domains.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "SESSAO_VOTACAO")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "ID_PAUTA", nullable = false)
    private Pauta pauta;

    @Column(name = "DATA_HORA_ABERTURA", nullable = false)
    private LocalDateTime dataHoraAbertura;

    @Column(name = "DATA_HORA_FECHAMENTO", nullable = false)
    private LocalDateTime dataHoraFechamento;
}
