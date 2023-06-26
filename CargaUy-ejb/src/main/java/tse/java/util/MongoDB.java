package tse.java.util;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.eq;



public class MongoDB {
    public static void main(String[] args) {
        String connectionString = "mongodb://localhost:27017";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                MongoCollection<Document> collection = database.getCollection("testeando");
               /* Document document = new Document("Auto", "violeta")
                        .append("Manzana", "roja")
                        .append("martillo", "rosado");
                collection.insertOne(document);
*/

                Bson filter = eq("Auto", "rojo");

                try {
                    DeleteResult result = collection.deleteOne(filter);
                    System.out.println("Deleted document count: " + result.getDeletedCount());
                } catch (MongoException me) {
                    System.err.println("Unable to delete due to an error: " + me);
                }
                FindIterable<Document> documents = collection.find();
                for (Document doc : documents) {
                    System.out.println(doc);
                }

            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}
