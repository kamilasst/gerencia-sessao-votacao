package br.com.gerenciasessaovotacao.controllers;

import br.com.gerenciasessaovotacao.controllers.dtos.associado.AssociadoIdResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.associado.AssociadoRequest;
import br.com.gerenciasessaovotacao.services.interfaces.IAssociadoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/associado")
public class AssociadoController {

    private final IAssociadoService associadoService;

    public AssociadoController(final IAssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping
    public ResponseEntity<AssociadoIdResponse> save(@Valid @RequestBody AssociadoRequest associadoRequest) {
        AssociadoIdResponse associadoIdResponse = associadoService.save(associadoRequest);
        return ResponseEntity.ok().body(associadoIdResponse);
    }
}
