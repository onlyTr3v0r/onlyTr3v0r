#include <Servo.h>

Servo servo;
int count = 0;

void setup() {
  // put your setup code here, to run once:
  servo.attach(9);
  servo.write(0);
}

void loop() {
  // put your main code here, to run repeatedly:
  for (int i = 0; i <= 180; i++) {
    servo.write(i); 
    delay(15);
  }
  for (int i = 0; i >= 0; i--) {
    servo.write(i); 
    delay(15);
  }
  count++;
  if (count >= 5) {
    exit(0);
  }
}
