package br.com.gerenciasessaovotacao.controllers.dtos.associado;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    public String getCpf() {
        return StringUtils.trim(cpf);
    }
}
