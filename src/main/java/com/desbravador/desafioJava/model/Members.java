package com.desbravador.desafioJava.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "Membros")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Members {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "idProjeto")
  private Project project;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "idPessoa")
  private Person person;

}
