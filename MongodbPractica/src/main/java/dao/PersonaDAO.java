package dao;

/**
 *
 * @author Ryzen 5
 */
import modelo.Persona;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Sorts.orderBy;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

public class PersonaDAO {

    private final MongoDatabase dataBase;
    private MongoCollection<Persona> collection;

    public PersonaDAO() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        ConnectionString cadenaConexion = new ConnectionString("mongodb://localhost/27017");

        MongoClientSettings clientsSettings = MongoClientSettings.builder().applyConnectionString(cadenaConexion).codecRegistry(codecRegistry).build();

        MongoClient dbServer = MongoClients.create(clientsSettings);

        this.dataBase = dbServer.getDatabase("practicedb");
        this.collection = dataBase.getCollection("personas", Persona.class);
    }

    public List<Persona> getPersonas() {
        List<Persona> personas = new ArrayList<>();
        collection.find().into(personas);

        return personas;
    }

    public List<Persona> getPersonasOfAge() {
        List<Persona> personas = new ArrayList<>();
        Bson filter = Filters.gte("edad", 18);
        collection.find(filter).into(personas);

        return personas;
    }

    public List<Persona> sortPersonasByNombreAndEdad() {
        List<Persona> personas = new ArrayList<>();
        Bson orderBySort = orderBy(descending("edad"), descending("nombre"));
        collection.find().sort(orderBySort).into(personas);

        return personas;
    }

    public void createPersona(Persona persona) {
        collection.insertOne(persona);
    }

    public void updatePersona(String nombre, Persona persona) 
    {
        Bson filter = Filters.eq("nombre", nombre);
        Bson newData = new Document("$set", new Document()
                .append("edad", persona.getEdad())
                .append("nombre", persona.getNombre())
                .append("sexo", persona.getSexo())
        );

        collection.findOneAndUpdate(filter, newData);
    }
    
    public void deletePersona(String nombre)
    {
        Bson filter = Filters.eq("nombre", nombre);
        collection.deleteOne(filter);
    }

}
