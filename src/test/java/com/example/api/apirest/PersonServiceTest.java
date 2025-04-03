package com.example.api.apirest;

import com.example.api.apirest.Person.Person;
import com.example.api.apirest.Person.PersonRepository;
import com.example.api.apirest.Person.PersonService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)  // Habilita el soporte de Mockito para JUnit 5
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;  // Mock del repositorio

    @InjectMocks
    private PersonService personService;  // La clase a probar

    @Test
    void testCreatePersona() {
        // Crear una persona para la prueba
        Person person = new Person(null, "John", "Doe", "john.doe@example.com");

        // Simula que el repositorio guarda la persona
        when(personRepository.save(any(Person.class))).thenReturn(person);

        // Ejecuta el método a probar
        personService.createPersona(person);

        // Verifica que el repositorio haya sido llamado correctamente
        verify(personRepository, times(1)).save(person);

        // Imprime el resultado en la consola
        System.out.println("Test 'testCreatePersona': Passed - Persona creada correctamente.");
    }

    @Test
    void testGetAllPersons() {
        // Crear personas de ejemplo
        Person person1 = new Person(1, "John", "Doe", "john.doe@example.com");
        Person person2 = new Person(2, "Jane", "Smith", "jane.smith@example.com");

        // Simula que el repositorio devuelve una lista de personas
        when(personRepository.findAll()).thenReturn(List.of(person1, person2));

        // Ejecuta el método a probar
        List<Person> persons = personService.getAllPersons();

        // Verifica que la lista tiene dos personas
        assertEquals(2, persons.size(), "Se esperaban 2 personas en la lista.");

        // Imprime el resultado en la consola
        System.out.println("Test 'testGetAllPersons': Passed - Se recuperaron 2 personas.");
    }

    @Test
    void testGetPersonById() {
        // Crear una persona de ejemplo
        Person person = new Person(1, "John", "Doe", "john.doe@example.com");

        // Simula que el repositorio devuelve la persona con ID 1
        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        // Ejecuta el método a probar
        Optional<Person> result = personService.getPersonById(1);

        // Verifica que la persona fue encontrada
        assertTrue(result.isPresent(), "La persona con ID 1 debería estar presente.");
        assertEquals("John", result.get().getFirstName(), "El nombre de la persona debería ser 'John'.");

        // Imprime el resultado en la consola
        System.out.println("Test 'testGetPersonById': Passed - Persona recuperada con éxito.");
    }

    @Test
    void testDeletePerson() {
        // Simula que el repositorio elimina una persona
        doNothing().when(personRepository).deleteById(1);

        // Ejecuta el método a probar
        personService.deletePerson(1);

        // Verifica que el repositorio haya sido llamado para eliminar la persona
        verify(personRepository, times(1)).deleteById(1);

        // Imprime el resultado en la consola
        System.out.println("Test 'testDeletePerson': Passed - Persona eliminada correctamente.");
    }
}
