"""
There are four collection data types in the Python programming language:

    List is a collection which is ordered and changeable. Allows duplicate members.
    Tuple is a collection which is ordered and unchangeable. Allows duplicate members.
    Set is a collection which is unordered and unindexed. No duplicate members.
    Dictionary is a collection which is ordered* and changeable. No duplicate members.

*As of Python version 3.7, dictionaries are ordered. In Python 3.6 and earlier, dictionaries are unordered.
~ w3schools
"""

aList = [ "hello", "$junk" , "," " ", "$randomstuff", "world", "!"]
result = ""

"""
for x in aList:
    result += x

result = ""
for i in range(len(aList)):
    result += aList[i]

result = ""
i = 0
while i < len(aList):
    result += aList[i]
    i += 1
"""

# list comprehension
newList = []
# newlist = [expression for item in iterable if condition == True]
# eg.
# newList = [x for x in aList if ! x.startsWith("$")]
# condition is optional:
# newList = [x for x in aList]

newList = [x for x in aList if not x.startswith("$")]
for x in newList:
    result += x

print(result)