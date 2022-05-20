#include <Servo.h>

Servo servo;
String input;
bool check;

void setup() {
  Serial.begin(9600);
  servo.attach(9);
  servo.write(0);
  Serial.println("Starting servo program :D\n\n");
  Serial.println("Enter the position for the servo to move to");
}

void loop() {
  while (Serial.available() > 0) {
    check = true;
    input = Serial.readString();
    input.trim();
    if (input == "bugger off") {
        Serial.println("Calm down sheeeeesh");
        delay(1000);
        exit(0);
    }

    for (int i = 0; i < input.length(); i++) {
      if (!isDigit(input.charAt(i))) {
        Serial.println("Enter a number smh");        
        check = false;
        break;
      }
    }

    if (check) {
      Serial.println("Moving to position: " + input);
      if (input.toInt() >= 0 && input.toInt() <= 360) {
        servo.write(input.toInt());
        delay(15);
      } else {
        Serial.println("Position should be between 0 & 360");
        continue;
      }
    }

    Serial.println("Enter the position for the servo to move to");
  }
}
