package br.com.gerenciasessaovotacao.domains.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
@Table(name = "PAUTA")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pauta pauta)) return false;
        return Objects.equals(titulo, pauta.titulo) && Objects.equals(descricao, pauta.descricao) && Objects.equals(dataCriacao, pauta.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, dataCriacao);
    }
}
