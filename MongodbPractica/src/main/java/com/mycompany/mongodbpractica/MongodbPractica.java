package com.mycompany.mongodbpractica;

import dao.PersonaDAO;
import modelo.Persona;

/**
 *
 * @author Ryzen 5
 */
public class MongodbPractica {

    public static void main(String[] args) {
        
        PersonaDAO personaDAO = new PersonaDAO();
        
        //Obtiene todas las personas en la coleccion
        for(Persona persona : personaDAO.getPersonas())
        {
            System.out.println(persona.toString());
        }
        
        //Obtiene todas las personas en la coleccion ordenadas por nombre y edad
        for(Persona persona : personaDAO.sortPersonasByNombreAndEdad())
        {
            System.out.println(persona.toString());
        }
        
        //Crea una nueva persona en la coleccion
        Persona nuevaPersona = new Persona("samuel", 19, "masculino");
        
        personaDAO.createPersona(nuevaPersona);
        
        for(Persona persona : personaDAO.getPersonas())
        {
            System.out.println(persona.toString());
        }
        
        //Actualiza una persona dada en la coleccion
        Persona nuevaPersona2 = new Persona("elias", 25, "masculino");
        
        personaDAO.updatePersona("jorge", nuevaPersona2);
        
        for(Persona persona : personaDAO.getPersonas())
        {
            System.out.println(persona.toString());
        }
        
        //Elimina una persona espeficada por su nombre en la coleccion
        personaDAO.deletePersona("samuel");
        
        for(Persona persona : personaDAO.getPersonas())
        {
            System.out.println(persona.toString());
        }
    }
}
