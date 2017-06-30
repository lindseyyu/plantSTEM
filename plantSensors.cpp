/* Photocell simple testing sketch. 
 
Connect one end of the photocell to 5V, the other end to Analog 0.
Then connect one end of a 10K resistor from Analog 0 to ground
 
For more information see http://learn.adafruit.com/photocells */
 
int photocellPin = 0;     // the cell and 10K pulldown are connected to a0
int photocellReading;     // the analog reading from the analog resistor divider
 
//Temperature and Humidty set-up, look for file "DELETE"
#include <dht.h>
dht DHT;
#define DHT11_PIN 7
 
//Moisture Set-up
int mSensorPin = 7;    // select the input pin for the potentiometer
int mSensorValue = 0;  // variable to store the value coming from the sensor
 
void setup(void) {
  // We'll send debugging information via the Serial monitor
  Serial.begin(9600);  
}
 
void loop(void) {
   //Delay one minute, rn it's 3 seconds
  delay(200);
  photocellReading = analogRead(photocellPin);  
 
  //Serial.print("Analog reading = ");
 // Serial.print(photocellReading);     // the raw analog reading
 
 //  Photocell, print 1 if sun, 0 if no sun
  if (photocellReading > 500) {
  Serial.print("1,");
  } 
  else{
  Serial.print("0,");
  }
  //delay(500);
 
  //Measure Temperature and Humidity, Temp is scaled 2 under measured Temp for celsius then converted to fahrenheit
  int chk = DHT.read11(DHT11_PIN);
  int celsius = DHT.temperature-2;
  int fahrenheit = ((celsius*1.8)+32);
  
  
  Serial.print(fahrenheit);
  Serial.print(",");
  Serial.print(DHT.humidity);
  Serial.print(",");
  //delay(500);
 
  //Moisture sensor Code
  mSensorValue = analogRead(mSensorPin);                        
  Serial.print(mSensorValue*(2/19));  
  Serial.println("");
 
 
}
