# This was generated with Github Copilot. It took 34 minutes.
# The only code I wrote by hand was
# for index, label in enumerate(parsed[0]):
#     if metric.lower() in label.lower():
#         element = index
#         break

# The majority of those 30 minutes was me trying to write my own solutions before writing 1 or 2 comments and letting copilot generate this for me
# . . .

import matplotlib.pyplot as plt
import csv

# parse old_internet_data.csv
with open("data.csv", "r") as csvfile:
    reader = csv.reader(csvfile)
    parsed = list(reader)

# create a list containing any nested lists in parsed if
# the first element of the nested list is "03/13/22"
# [
#   ['03/13/22@21', '9.15', '8.75', '19.74'],
#   ['03/13/22@22', '12.44', '7.32', '66.76'],
#   ['03/13/22@23', '4.35', '5.55', '21.82']
# ]

target = "03/14/22"
to_plot = [row for row in parsed[1:] if row[0].split('@')[0] == target]

def plot(metric, data):
    """
    Data should be plotted to a matplotlib graph.
    The x axis should be the date, and the y axis should be the second index of the nested list
    Example data:
    ['03/16/22@09', '10.01', '9.32', '15.85']
    ['03/16/22@05', '9.54', '9.36', '16.34']
    ['03/15/22@15', '17.33', '9.07', '20.18']
    """

    for index, label in enumerate(parsed[0]):
        if metric.lower() in label.lower():
            element = index
            break

    x = [row[0].split('@')[1] for row in data]
    y = [float(row[element]) for row in data]

    plt.plot(x, y)
    plt.xlabel("Date")
    plt.ylabel(parsed[0][element])

plot("download", to_plot)
plt.show()