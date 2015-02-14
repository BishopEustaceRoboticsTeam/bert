#include <Wire.h>
#include <Adafruit_NeoPixel.h>
#include <avr/power.h>

#define PIN 6




// Parameter 1 = number of pixels in strip
// Parameter 2 = Arduino pin number (most are valid)
// Parameter 3 = pixel type flags, add together as needed:
//   NEO_KHZ800  800 KHz bitstream (most NeoPixel products w/WS2812 LEDs)
//   NEO_KHZ400  400 KHz (classic 'v1' (not v2) FLORA pixels, WS2811 drivers)
//   NEO_GRB     Pixels are wired for GRB bitstream (most NeoPixel products)
//   NEO_RGB     Pixels are wired for RGB bitstream (v1 FLORA pixels, not v2)
Adafruit_NeoPixel strip = Adafruit_NeoPixel(100, PIN, NEO_GRB + NEO_KHZ800);




boolean animation_completed = true;
int current_led_mode = -1;

void setup()
{
  
  strip.begin();
  strip.show(); // Initialize all pixels to 'off'
  
  Wire.begin(4);
  Wire.onReceive(receiveEvent);
  Serial.begin(9600);

}

void loop()
{
    //colorFromCenter(50);
//  builder(10, strip.Color(255,0,0));  
//  theaterChaseRainbow(50);

  phaser2(20, strip.Color(177,0,0),strip.Color(0,0,177),strip.Color(0,255,0));

  if(animation_completed){
    //notCompleted();
    switch(current_led_mode){
      case 0:
        colorWipe(strip.Color(0, 0, 255), 20); // Blue
        break;
      case 1:
        colorWipe(strip.Color(247, 247, 20), 20); // Yellow
        break;
      case 2:
        colorWipe(strip.Color(0, 255, 0), 20); // Green
        break;
      case 3:
        colorWipe(strip.Color(255, 0, 255), 20); // PURPLE
        break;
      case 4:
        colorWipe(strip.Color(255, 0, 0), 20); // Blue
        break;
      case 5:
        colorFromCenter(20);
        break;  
      default:
        completed();
        break;
      //Serial.print("E");
    }  
  }
}

//this function gets called when data comes in
void receiveEvent(int howMany){
  int addr = Wire.read();
  //Serial.println(addr);
  //the robot wants to read
  //if(addr == 0x20){
    //if(animation_completed){
    //  Wire.write(116);
      //current_led_mode = 1;
      
    //}
    //else{
      //Wire.write('f');
    //}
  //}
  
  //the robot wants to write
  if(addr == 0x10){
    //if(animation_completed){
      current_led_mode = Wire.read();
    //}
    //Wire.read();
  }
   else{
     current_led_mode = -1;
   } 
}

void completed(){
   animation_completed = true;
   current_led_mode = -1;
}
void notCompleted(){
   animation_completed = false; 
}



// Fill the dots one after the other with a color
void colorWipe(uint32_t c, uint8_t wait) {
  for(uint16_t i=0; i<strip.numPixels(); i++) {
      strip.setPixelColor(i, c);
      strip.show();
      delay(wait);
  }
  completed();
}

void reverseColorWipe(uint32_t c, uint8_t wait) {
  for(uint16_t i=strip.numPixels(); i >= 0; i--) {
      strip.setPixelColor(i, c);
      strip.show();
      delay(wait);
  }
  completed();
}

void rainbow(uint8_t wait) {
  uint16_t i, j;

  for(j=0; j<256; j++) {
    for(i=0; i<strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel((i+j) & 255));
    }
    strip.show();
    delay(wait);
  }
  completed();
}

// Slightly different, this makes the rainbow equally distributed throughout
void rainbowCycle(uint8_t wait) {
  uint16_t i, j;

  for(j=0; j<256*5; j++) { // 5 cycles of all colors on wheel
    for(i=0; i< strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel(((i * 256 / strip.numPixels()) + j) & 255));
    }
    strip.show();
    delay(wait);
  }
  completed();
}

//Theatre-style crawling lights.
void theaterChase(uint32_t c, uint8_t wait) {
  for (int j=0; j<10; j++) {  //do 10 cycles of chasing
    for (int q=0; q < 3; q++) {
      for (int i=0; i < strip.numPixels(); i=i+3) {
        strip.setPixelColor(i+q, c);    //turn every third pixel on
      }
      strip.show();
     
      delay(wait);
     
      for (int i=0; i < strip.numPixels(); i=i+3) {
        strip.setPixelColor(i+q, 0);        //turn every third pixel off
      }
    }
  }
  completed();
}

void skipColorWipe(uint32_t c, uint8_t wait) {
  for(uint16_t i=0; i<strip.numPixels(); i + 2) {
      strip.setPixelColor(i, c);
      strip.show();
      delay(wait);
  }
  completed();
}

//Theatre-style crawling lights with rainbow effect
void theaterChaseRainbow(uint8_t wait) {
  for (int j=0; j < 256; j++) {     // cycle all 256 colors in the wheel
    for (int q=0; q < 3; q++) {
        for (int i=0; i < strip.numPixels(); i=i+3) {
          strip.setPixelColor(i+q, Wheel( (i+j) % 255));    //turn every third pixel on
        }
        strip.show();
       
        delay(wait);
       
        for (int i=0; i < strip.numPixels(); i=i+3) {
          strip.setPixelColor(i+q, 0);        //turn every third pixel off
        }
    }
  }
  completed();
}

void colorFromCenter(uint8_t wait){
  for(int i=0; i<=((strip.numPixels())-1)/2; i++){
    strip.setPixelColor((strip.numPixels()-1)/2+i,Wheel((512*i)/(strip.numPixels()-1)));
    strip.setPixelColor((strip.numPixels()-1)/2-i,Wheel((512*i)/(strip.numPixels()-1)));
    strip.show();
    delay(wait);
  }
  for(int i=((strip.numPixels())-1)/2; i>=0; i=i-1){
    strip.setPixelColor((strip.numPixels()-1)/2+i, 0);
    strip.setPixelColor((strip.numPixels()-1)/2-i, 0);
    strip.show();
    delay(wait);
  }
  completed();
}

void builder(uint8_t wait, uint32_t c){
  for(int j=0; j<=(strip.numPixels()-1)/2; j += 2){
    
    for(int i=0; i<=((strip.numPixels())-1)/2 - j; i++){
    
      strip.setPixelColor((strip.numPixels()-1)/2+i, c);
      strip.setPixelColor((strip.numPixels()-1)/2-i, c);
      if(i >= 2){
        strip.setPixelColor((strip.numPixels()-1)/2+i-2, 0);
        strip.setPixelColor((strip.numPixels()-1)/2-i+2, 0);
      }
      strip.show();
      delay(wait);
    
    
    }
  }
  for(int i=0; i<=(strip.numPixels()-1)/2; i++){
    strip.setPixelColor((strip.numPixels()+1)/2+i, 0);
    strip.setPixelColor((strip.numPixels()+1)/2-i, 0);
    strip.show();
    delay(wait);
  }
  completed();
}

void phaser(uint8_t wait, uint32_t c1, uint32_t c2, uint32_t c3){
  for(int i=0; i<=(strip.numPixels()-10); i++){
    for(int j=0; j<=9; j++){

      strip.setPixelColor(i+j,c1);
        if(i>0){ 
          strip.setPixelColor(i-1,0);
        }
        
        strip.setPixelColor(strip.numPixels()-i-j,c2);
        if(i>0){
          strip.setPixelColor(strip.numPixels()-i+1,0);
        }
        
        int s = (i+9)-(strip.numPixels()-(i+9))+1;
        int m = strip.numPixels()/2-1;
        
        if(i >= strip.numPixels()-(i+9) - 9){
          for(int k=m-(s/2-1);k<=m+(s/2);k++){
            strip.setPixelColor(k,c3);
          }
        }
        
    }
    strip.show();
    delay(wait);
  }
    
}

void shift(boolean directions, int start,int final, uint32_t c){
  //True = towards the end, false, is towards the beginning.
  if(directions){
    strip.setPixelColor(final+1,c);
    strip.setPixelColor(start, 0);
  }
  else{
    strip.setPixelColor(start-1,c);
    strip.setPixelColor(final,0);
  }
}



void phaser2(uint8_t wait, uint32_t c1, uint32_t c2, uint32_t c3){
  boolean direc = true;
  
  for(int i=0; i<=9; i++){
    strip.setPixelColor(i,c1);
    strip.setPixelColor(strip.numPixels()-1-i,c2);
  }  

  for(int i=0; i < strip.numPixels()-10 ; i++){
    shift(direc,i,9+i,c1);
    shift(!direc,strip.numPixels()-10-i,strip.numPixels()-1-i,c2);
    
    int s = (i+9)-(strip.numPixels()-i-10)+1;
    int m = strip.numPixels()/2-1;
       
    if(i+9 >= strip.numPixels()-i-10 && i <= strip.numPixels()-i-1){
      for(int j=m-(s/2-1);j<=m+(s/2);j++){
        strip.setPixelColor(j,c3);
      }
    }

    strip.show();
    delay(wait);
    
  }

  direc = false;
  for(int i=strip.numPixels()-10; i>=0 ; i--){
    shift(direc,i,i+9,c1);
    shift(!direc,strip.numPixels()-10-i,strip.numPixels()-1-i,c2);
    
    int s = (i+9)-(strip.numPixels()-i-10)+1;
    int m = strip.numPixels()/2-1;
       
    if(i+9 >= strip.numPixels()-i-10 && i <= strip.numPixels()-i-1){
      for(int j=m-(s/2-1);j<=m+(s/2);j++){
        strip.setPixelColor(j,c3);
      }
    }    
    
    strip.show();
    delay(wait);
  }
//    shiftBeginning(strip.numPixels()-10,1,c2);
    
    
//    for(int j=0; j<=9; j++){
//
//      strip.setPixelColor(i+j,c1);
//        if(i>0){ 
//          strip.setPixelColor(i-1,0);
//        }
        
//        strip.setPixelColor(strip.numPixels()-i-j,c2);
//        if(i>0){
//          strip.setPixelColor(strip.numPixels()-i+1,0);
//        }
        

        
//    }  
    
}



// Input a value 0 to 255 to get a color value.
// The colours are a transition r - g - b - back to r.
uint32_t Wheel(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
   return strip.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  } else if(WheelPos < 170) {
    WheelPos -= 85;
   return strip.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  } else {
   WheelPos -= 170;
   return strip.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
  }
}



