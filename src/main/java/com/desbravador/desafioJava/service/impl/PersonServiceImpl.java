package com.desbravador.desafioJava.service.impl;

import com.desbravador.desafioJava.exceptionhandler.exception.NotFoundException;
import com.desbravador.desafioJava.model.Person;
import com.desbravador.desafioJava.repository.PersonRepository;
import com.desbravador.desafioJava.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.desbravador.desafioJava.util.Constants.*;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

  private final PersonRepository repository;

  @Override
  public List<Person> getPersons() {
    return repository.findAll();
  }

  @Override
  public Person getPersonById(Long id) {
    return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format(PERSON_NOT_FOUND, id)));
  }

  @Override
  public Person getPersonByCpf(String cpf) {
    return repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException(String.format(PERSON_NOT_FOUND, cpf)));
  }

  @Override
  @Transactional
  public Person savePerson(Person person) {
    return repository.save(person);
  }

  @Override
  public List<Person> getPersonByName(String name) {
    return repository.findByNome(name);
  }

  @Override
  @Transactional
  public void deletePerson(Long id) {
    var person = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format(PERSON_NOT_FOUND_TO_DELETE, id)));
    repository.delete(person);
  }

  @Override
  @Transactional
  public Person updatePerson(Person person) {
    var existingPerson =
            repository.findById(person.getId()).orElseThrow(() -> new NotFoundException(String.format(PERSON_NOT_FOUND_TO_UPDATE, person.getId())));
    existingPerson.setNome(person.getNome());
    return repository.save(existingPerson);
  }

}
