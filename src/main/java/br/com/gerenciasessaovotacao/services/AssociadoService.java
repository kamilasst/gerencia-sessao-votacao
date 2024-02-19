package br.com.gerenciasessaovotacao.services;

import br.com.gerenciasessaovotacao.controllers.dtos.associado.AssociadoIdResponse;
import br.com.gerenciasessaovotacao.controllers.dtos.associado.AssociadoRequest;
import br.com.gerenciasessaovotacao.domains.models.Associado;
import br.com.gerenciasessaovotacao.exceptions.ErroConstantes;
import br.com.gerenciasessaovotacao.exceptions.NegocioException;
import br.com.gerenciasessaovotacao.respositories.AssociadoRepository;
import br.com.gerenciasessaovotacao.services.interfaces.IAssociadoService;
import br.com.gerenciasessaovotacao.services.interfaces.ICPFValidatorService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AssociadoService implements IAssociadoService {

    private final AssociadoRepository associadoRespository;
    private final ICPFValidatorService cpfValidatorService;

    public AssociadoService(final AssociadoRepository associadoRespository,
                            final ICPFValidatorService cpfValidatorService) {
        this.associadoRespository = associadoRespository;
        this.cpfValidatorService = cpfValidatorService;
    }

    @Override
    public AssociadoIdResponse save(final AssociadoRequest associadoRequest) {

        validarExisteCpf(associadoRequest.getCpf());
        cpfValidatorService.validar(associadoRequest.getCpf());

        final Associado associado = Associado.builder()
                .nome(associadoRequest.getNome())
                .cpf(associadoRequest.getCpf())
                .build();

        associadoRespository.save(associado);

        return AssociadoIdResponse.builder()
                .id(associado.getId())
                .build();
    }

    @Override
    public Associado findByCPF(final String cpf) {
        Associado associado = associadoRespository.findByCpf(cpf);
        return associado;
    }

    private void validarExisteCpf(final String cpf) {
        Associado associadoDataBase = associadoRespository.findByCpf(cpf);
        if (Objects.nonNull(associadoDataBase)){
            throw new NegocioException(ErroConstantes.CPF_EXISTENTE);
        }
    }

    @Override
    public void validarNaoExiste(final Associado associado) {
        if (Objects.isNull(associado)) {
            throw new NegocioException(ErroConstantes.ASSOCIADO_NAO_ENCONTRADO);
        }
    }
}
