package br.com.gerenciasessaovotacao.controllers;

import br.com.gerenciasessaovotacao.controllers.dtos.voto.ResultadoVotacaoResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.voto.VotoRequest;
import br.com.gerenciasessaovotacao.controllers.dtos.voto.VotoResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.voto.ResultadoVotacaoRequest;
import br.com.gerenciasessaovotacao.services.interfaces.IVotoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/voto")
public class VotoController {

    private final IVotoService votoService;

    public VotoController(final IVotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping("/votar")
    public ResponseEntity<VotoResponse> votar(@Valid @RequestBody VotoRequest votoRequest) {
        VotoResponse votoResponse = votoService.votar(votoRequest);
        return ResponseEntity.ok().body(votoResponse);
    }

    @GetMapping("/resultadoVotacao")
    public ResponseEntity<ResultadoVotacaoResponse> resultadoVotacao(@Valid @RequestBody ResultadoVotacaoRequest votoTituloRequest){
        ResultadoVotacaoResponse resultadoVotacao = votoService.resultadoVotacao(votoTituloRequest);
        return ResponseEntity.ok().body(resultadoVotacao);
    }
}
