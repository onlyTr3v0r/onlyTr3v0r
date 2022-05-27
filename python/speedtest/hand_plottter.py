# I wrote this my hand. It does not work. I spend a long time on this.

from cProfile import label
from math import ceil, floor
import matplotlib
import matplotlib.pyplot as plt
from csv import DictReader
import numpy as np

PATH = "data.csv"

def parse_csv():
    rows = list()
    with open(PATH, 'r') as file:
        reader = DictReader(file)
        for row in reader:
            rows.append({key.split(" ")[0].lower(): value for key, value in row.items()})

    return rows

def plot_line(plot, rows, key):
    xpoints = np.array([row["date"].split("@")[1] for row in rows]) # The hour numbers (00, 01, 02.. 22, 23, 00)
    ypoints = np.sort(np.array([float(rows[index][key]) for index, _ in enumerate(xpoints)]))

    plot.plot(xpoints, ypoints, label=key)

def main():
    rows = parse_csv()
    fig = plt.figure()
    ax = fig.add_subplot(1, 1, 1)

    xpoints = np.array([row["date"].split("@")[1] for row in rows]) # , "upload", "ping"
    values = [float(row[key]) for row in rows for key in ("download", "upload", "ping")] # The data, the values in the rows dictionary, the results of the test, ect.
    inc = (max(values) - min(values)) / len(xpoints) # The value to increment by
    ypoints = np.sort(np.array(np.arange(0, max(values), inc))) # A list of points distributed from the min value to the max
    print(ypoints)
    ax.set_yticks(ypoints)

    plot_line(ax, rows, "download")
    # plot_line(ax, rows, "upload")
    # plot_line(ax, rows, "ping")

    plt.legend()
    plt.show()

if __name__ == "__main__":
    main()