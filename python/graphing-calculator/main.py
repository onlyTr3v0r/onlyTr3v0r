CONSTS = {
    "FORMULA" : "y==" + input("y=").lower().replace("^", "**"), 
    "x" : 20,
    "y" : 20,
    True : "[x]",
    False : "[ ]",
}

print()
line = "OR" + (" " * len(str(CONSTS["x"])))
for x in range(1, CONSTS["x"] + 1): line += str(x) + (" " * (len(str(CONSTS["x"])) - len(str(x)) + 1))
print(line + "+X") 
line = str()
for y in range(1, CONSTS["y"] + 1):
    line += str(y) + (" " * (len(str(CONSTS["y"])) - len(str(y)) + 1))
    for x in range(1, CONSTS["x"] + 1): 
        line += CONSTS[True] if eval(CONSTS["FORMULA"]) else CONSTS[False]
    print(line) 
    line = str()
print("+Y") 