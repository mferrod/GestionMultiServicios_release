#include <WiFiS3.h>
#include <ArduinoGraphics.h>
#include <Arduino_LED_Matrix.h>
#include <SoftwareSerial.h>
#include <TinyGPS.h>

char ssid[] = "MIWIFI_6C34";      // Nombre de tu red WiFi
char pass[] = "hxtnxtuQ";  // Contraseña de tu red WiFi
int status = WL_IDLE_STATUS;

char server[] = "YOUR_IP_SERVER";  // Dirección del servidor
WiFiClient client;
ArduinoLEDMatrix matrix;
TinyGPS gps;
SoftwareSerial softSerial(1,0);

void setup() {
  Serial.begin(115200);
  softSerial.begin(9600);
  iniciaLed();
  while (!Serial) {
    ;  // Esperar a que se establezca la conexión serial
  }

  // Conexión a la red WiFi
  while (status != WL_CONNECTED) {
    Serial.print("Conectando a la red WiFi...\n");
    status = WiFi.begin(ssid, pass);
    delay(5000);  // Esperar 5 segundos para volver a intentar la conexión
  }

  Serial.println("Conexión WiFi establecida\n");
}

void loop() {
  if (client.connect(server, 9443)) {  // Conexión al servidor en el puerto 9443 (HTTPs)
    Serial.println("Conectado al servidor\n");
    // Construir el cuerpo del JSON
    gpsin();
    delay(5000);
  } else {
    Serial.println("Error al conectarse al servidor\n");
  }
  textoLed("    PAFERGONSL    ");
  delay(5000);  // Esperar 5 segundos antes de enviar otra solicitud
}

void hacerPeticion(int length, String json) {
client.println("POST /gms2api/api/gps/postPosition HTTP/1.1");
client.println("Host: ");
client.println("Content-Type: application/json");
client.print("Content-Length: ");
client.println(length);
client.println("ApiKey: YOUR_OWN_API_KEY"); // Añade el header ApiKey aquí
client.println();
client.println(json);
}

void iniciaLed() {
  matrix.begin();

  matrix.beginDraw();

  matrix.stroke(0xFFFFFFFF);
  matrix.textScrollSpeed(100);

  const char text[] = "  UNO r4  ";
  matrix.textFont(Font_4x6);
  matrix.beginText(0, 1, 0xFFFFFF);
  matrix.println(text);
  matrix.endText(SCROLL_LEFT);

  matrix.endDraw();
}

void gpsin() {
  bool newData = false;
  unsigned long chars;
  unsigned short sentences, failed;
  
  // Intentar recibir secuencia durante un segundo
  for (unsigned long start = millis(); millis() - start < 1000;)
  {
    while (softSerial.available())
    {
      char c = softSerial.read();
      if (gps.encode(c)) // Nueva secuencia recibida
        newData = true;
    }
  }

  if (newData)
  {
    float flat, flon;
    unsigned long age;
    gps.f_get_position(&flat, &flon, &age);

    // Comprobar si flat y flon son valores válidos
    flat = (flat == TinyGPS::GPS_INVALID_F_ANGLE) ? 0.0 : flat;
    flon = (flon == TinyGPS::GPS_INVALID_F_ANGLE) ? 0.0 : flon;

    // Crear el JSON string usando la clase String
    String json = "{\"nombre\": \"Camion1\", \"latitud\": " + String(flat, 6) + ", \"longitud\": " + String(flon, 6) + "}";
    Serial.println(json);
    hacerPeticion(json.length(), json);
  }
}

void textoLed(const char text[]) {
  matrix.beginDraw();

  matrix.stroke(0xFFFFFFFF);
  matrix.textScrollSpeed(50);

  matrix.textFont(Font_5x7);
  matrix.beginText(0, 1, 0xFFFFFF);
  matrix.println(text);
  matrix.endText(SCROLL_LEFT);

  matrix.endDraw();
}