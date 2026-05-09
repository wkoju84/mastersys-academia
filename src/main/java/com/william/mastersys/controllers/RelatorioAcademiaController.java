package com.william.mastersys.controllers;

import com.william.mastersys.projections.AlunoPorCidadeProjection;
import com.william.mastersys.projections.FaturamentoMensalProjection;
import com.william.mastersys.projections.FaturasEmAbertoProjection;
import com.william.mastersys.repositories.RelatorioAcademiaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioAcademiaController {

    private final RelatorioAcademiaRepository relatorioAcademiaRepository;

    public RelatorioAcademiaController(RelatorioAcademiaRepository relatorioAcademiaRepository) {
        this.relatorioAcademiaRepository = relatorioAcademiaRepository;
    }

    @GetMapping("/faturamento-mensal")
    public List<FaturamentoMensalProjection> faturamentoMensal(){
        return relatorioAcademiaRepository.faturamentoMensal();
    }

    @GetMapping("/alunos-por-cidade")
    public List<AlunoPorCidadeProjection> alunoPorCidade(){
        return relatorioAcademiaRepository.alunosPorCidade();
    }

    @GetMapping("/faturas-em-aberto")
    public List<FaturasEmAbertoProjection> faturasEmAberto(){
        return relatorioAcademiaRepository.faturasEmAberto();
    }
}
