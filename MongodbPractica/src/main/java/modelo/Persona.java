package modelo;

import org.bson.types.ObjectId;

/**
 *
 * @author Ryzen 5
 */
public class Persona 
{
    private ObjectId id;
    private String nombre;
    private int edad;
    private String sexo;

    public Persona() 
    {
    }
    
    public Persona(String nombre, int edad, String sexo) 
    {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", edad=" + edad + ", sexo=" + sexo + '}';
    }
    
    
    
}
