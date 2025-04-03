package com.example.api.apirest.Person;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepo;

    public void createPersona(Person person)
    {
       personRepo.save(person);
    }
    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }

    public Optional<Person> getPersonById(Integer id) {
        return personRepo.findById(id);
    }

    public void deletePerson(Integer id) {
        personRepo.deleteById(id);
    }

}
