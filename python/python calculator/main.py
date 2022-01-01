inputStr = input("Enter calculation.")
inputStr.strip()

inputStr = inputStr.replace(" ", "")

i = 0
inputStr = inputStr + "A"
for x in inputStr:
    i = i + 1
    if not x.isdigit():
        print(inputStr[:i-1])
        