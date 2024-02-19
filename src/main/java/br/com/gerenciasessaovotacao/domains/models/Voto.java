package br.com.gerenciasessaovotacao.domains.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.util.Objects;

@Entity
@Table(name = "VOTO")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_ASSOCIADO", nullable = false)
    private Associado associado;

    @ManyToOne
    @JoinColumn(name = "ID_PAUTA", nullable = false)
    private Pauta pauta;

    @Column(name = "VOTO", nullable = false)
    @Enumerated(EnumType.STRING)
    private VotoEnum voto;

    @Column(name = "DATA_VOTO", nullable = false)
    private LocalDateTime dataVoto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto1 = (Voto) o;
        return Objects.equals(associado, voto1.associado) && Objects.equals(pauta, voto1.pauta) && voto == voto1.voto && Objects.equals(dataVoto, voto1.dataVoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(associado, pauta, voto, dataVoto);
    }
}
