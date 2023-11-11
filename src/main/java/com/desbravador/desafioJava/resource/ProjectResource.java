package com.desbravador.desafioJava.resource;

import com.desbravador.desafioJava.model.Project;
import com.desbravador.desafioJava.model.dto.request.CreateProjectRequest;
import com.desbravador.desafioJava.model.dto.request.UpdateProjectRequest;
import com.desbravador.desafioJava.model.dto.response.ProjectResponse;
import com.desbravador.desafioJava.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Projeto", description = "Crud de projetos")
@RestController
@RequestMapping(value = "/projetos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class ProjectResource {


    private final ProjectService service;

    @Operation(summary = "Retornar todos Projetos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProjectResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Não existem projetos", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public List<ProjectResponse> findAllProjects() {
        return service.getProjects().stream().map(ProjectResponse::of).collect(Collectors.toList());
    }

    @Operation(summary = "Retornar Projeto filtrando pelo Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProjectResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Projeto não existente", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/{id}")
    public ProjectResponse findProjectById(@PathVariable final Long id) {
        return ProjectResponse.of(service.getProjectById(id));
    }

    
    @Operation(summary = "Retornar projetos filtrando pelo Nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProjectResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/findByName")
    public  List<ProjectResponse> findProjectByName(@RequestParam final String name) {
        return service.getProjectByName(name).stream().map(ProjectResponse::of).collect(Collectors.toList());
    }

    @Operation(summary = "Criar um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Projeto Criado", content = {
                    @Content(schema = @Schema(implementation = ProjectResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProjectResponse addProject(@RequestBody @Valid final CreateProjectRequest request) {
        return ProjectResponse.of(service.saveProject(Project.of(request)));
    }

    @Operation(summary = "Atualizar um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto Atualizado", content = {
                    @Content(schema = @Schema(implementation = ProjectResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Projeto não existente", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping
    public ProjectResponse updateProject(@RequestBody @Valid final UpdateProjectRequest request) {
        return ProjectResponse.of(service.updateProject(Project.of(request)));
    }

    @Operation(summary = "Excluir um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Projeto Excluido", content = {
                    @Content(schema = @Schema(implementation = ProjectResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Projeto não existente", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable final Long id) {
        service.deleteProject(id);
    }
}