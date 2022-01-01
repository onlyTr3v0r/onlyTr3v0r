#single line comment
"""
multi
line
comment
"""

print("Hello past, present and future!") #comemnt at the end of the line

multiLine = """an amazing
story would go
here if i could be bothered"""

print(multiLine)

name = input("enter name: ")
age = input("enter age: ")

name = name.replace(" ", "")

beforeFormat = "{0}, you are {1} years old."
afterFormat = beforeFormat.format(name.capitalize(), age)
print(afterFormat)