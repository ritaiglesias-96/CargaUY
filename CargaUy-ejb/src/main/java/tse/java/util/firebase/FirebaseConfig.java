package tse.java.util.firebase;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FirebaseConfig {
    public FirebaseConfig() throws IOException {
        // Ruta al archivo de configuración JSON de Firebase
        String pathToFirebaseConfig = "/ruta/al/archivo-de-configuracion-firebase.json";

        // Inicialización de la app de Firebase
        FileInputStream serviceAccount = new FileInputStream(pathToFirebaseConfig);
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();
        FirebaseApp.initializeApp(options);

    }

}