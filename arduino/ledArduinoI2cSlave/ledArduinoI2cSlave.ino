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
  Serial.print("Recieved data! ");
  int ledCode = Wire.read();
  Serial.print("The led code is: ");
  Serial.println(ledCode);
}
