#include <Wire.h>


void setup()
{
  Wire.begin(4);
  Wire.onReceive(receiveEvent);
  Serial.begin(9600);
}

void loop()
{
  //all the magic happens in the recieveEvent function
  delay(100);
  
}


void receiveEvent(int howMany){
  for(int i = howMany; i > 0; i--){
    //Serial.print("Recieved data: ");
    Serial.println(Wire.read());
  }
}
