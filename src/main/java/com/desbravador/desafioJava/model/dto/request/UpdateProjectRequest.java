package com.desbravador.desafioJava.model.dto.request;

import com.desbravador.desafioJava.model.ProjectRiskEnum;
import com.desbravador.desafioJava.model.ProjectStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProjectRequest {

  @NotNull(message =  "O campo 'id' deve ser informado.")
  private Long id;

  private String nome;
  private String descricao;
  private ProjectStatusEnum status;
  private BigDecimal orcamento;
  private ProjectRiskEnum risco;
  private String cpfGerente;
}
