
package com.example.devserver;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SimpleWebServer webServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Starten des Servers beim Start der App
        startServer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stoppe den Server beim Beenden der App
        stopServer();
    }

    private void startServer() {
        webServer = new SimpleWebServer(8080); // Der Server läuft auf Port 8080
        try {
            webServer.start();
            Log.d("WebServer", "Server started successfully.");
        } catch (IOException e) {
            Log.e("WebServer", "Failed to start the server.", e);
        }
    }

    private void stopServer() {
        if (webServer != null) {
            webServer.stop();
            Log.d("WebServer", "Server stopped.");
        }
    }
}
