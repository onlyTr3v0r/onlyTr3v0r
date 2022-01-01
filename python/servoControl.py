#imports
import RPi.GPIO as GPIO
from time import sleep

#setup
GPIO.setmode(GPIO.BOARD)
GPIO.setup(03, GPIO.OUT)
pwm-GPIO.PWM(03, 50)
pwm.start(0)

#angle math
def setAngle(angle):
    duty = angle / 18 + 2
    GPIO.output(03, True)
    pwm.CHangeDutyCycle(duty)
    sleep(1)
    GPIO.output(03, False)
    pwm.ChangeDutyCycle(0)

#control
angle = raw_input("Enter angle")
setAngle(angle)
