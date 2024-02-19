package br.com.gerenciasessaovotacao.controllers;

import br.com.gerenciasessaovotacao.controllers.dtos.pauta.PautaRequest;
import br.com.gerenciasessaovotacao.controllers.dtos.pauta.PautaIdResponse;
import br.com.gerenciasessaovotacao.services.interfaces.IPautaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {

    private final IPautaService pautaService;

    public PautaController(final IPautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity<PautaIdResponse> save(@Valid @RequestBody PautaRequest pautaRequest){
        PautaIdResponse pautaIdResponse = pautaService.save(pautaRequest);
        return ResponseEntity.ok().body(pautaIdResponse);
    }
}
