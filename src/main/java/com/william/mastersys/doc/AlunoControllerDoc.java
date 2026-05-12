package com.william.mastersys.doc;

import com.william.mastersys.dtos.AlunoFiltroRequest;
import com.william.mastersys.dtos.AlunoRequest;
import com.william.mastersys.dtos.AlunoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "alunos",
        description = "Operações para cadastro, consulta, atualizações, exclusões e filtragem de alunos"
)
public interface AlunoControllerDoc {

    @Operation(
            summary = "Cadastrar aluno",
            description = "Cria um novo aluno no sistema de academia",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Aluno cadastrado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou regra de negócio",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    AlunoResponse cadastrar(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para cadastrar um aluno",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AlunoRequest.class),
                    examples = @ExampleObject(
                            name = "Aluno válido",
                            value = """
                                    {
                                          "nome" : "Fábio Barbosa",
                                          "dataNascimento" : "2001-11-16",
                                          "sexo" : "M",
                                          "telefone" : "1122334455",
                                          "celular" : "11911223344",
                                          "email" : "fabio@email.com",
                                          "observacao" : "Aluno iniciante",
                                          "endereco" : "Rua das Flores",
                                          "numero" : "123",
                                          "complemento" : "Casa 3",
                                          "bairro" : "centro",
                                          "cidade" : "Diadema",
                                          "estado" : "SP",
                                          "cep" : "09911575"
                                    }
                                    """
                    ))
            )
            AlunoRequest request);

    @Operation(
            summary = "Listar alunos",
            description = "Listar alunos de forma páginada, permitindo filtros opcionais por " +
                    "nome, email, celular, cidade e estado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso")
            }
    )
    Page<AlunoResponse> listar(
            @Parameter(description = "Filtros opcionais para busca de alunos")
            AlunoFiltroRequest filtro,

            @Parameter(description = "Informações de paginação e ordenação")
            Pageable pageable
    );

    @Operation(
            summary = "Buscar aluno por ID",
            description = "Retorna os dados resumidos de um aluno específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Aluno encontrado"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Aluno não encontrado",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    AlunoResponse buscarPorId(
            @Parameter(description = "ID do aluno", example = "2", required = true)
            Long id
    );
}
