package br.com.gerenciasessaovotacao.controllers;

import br.com.gerenciasessaovotacao.controllers.dtos.sessaovotacao.SessaoVotacaoResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.sessaovotacao.SessaoVotaocaoRequest;
import br.com.gerenciasessaovotacao.services.interfaces.ISessaoVotacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sessaoVotacao")
public class SessaoVotacaoController {

    private final ISessaoVotacaoService sessaoVotacaoService;

    public SessaoVotacaoController(final ISessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @PostMapping
    public ResponseEntity<SessaoVotacaoResponse> abrirSessao(@Valid @RequestBody SessaoVotaocaoRequest sessaoVotaocaoRequest) {
        SessaoVotacaoResponse sessaoVotacaoResponse = sessaoVotacaoService.abrirSessao(sessaoVotaocaoRequest);
        return ResponseEntity.ok().body(sessaoVotacaoResponse);
    }
}
