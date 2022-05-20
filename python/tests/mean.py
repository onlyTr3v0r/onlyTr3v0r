from math import ceil, floor

def get_outliers(list):
    sort = sorted(list)

    q2, index = get_median(sort)
    q1, _ = get_median(sort[:floor(index)])
    q3, _ = get_median(sort[ceil(index):])
    iq = q3 - q1

    inner = (q1 - iq * 1.5, q3 + iq * 1.5)
    outer = (q1 - iq * 3, q3 + iq * 3)

    minor = [element for element in list if not (inner[0] < element < inner[1])]
    major = [element for element in list if not (outer[0] < element < outer[1])]

    return minor, major

def get_median(sorted_list):
    if not len(sorted_list) % 2:
        lower = floor(len(sorted_list) / 2) - 1
        upper = floor(len(sorted_list) / 2) - 1 + 1 # -1 because 0 indexing
        median = (sorted_list[lower] + sorted_list[upper]) / 2
    else:
        median = sorted_list[floor(len(sorted_list) / 2)]

    index = len(sorted_list) / 2
    return median, index

def get_mean(list: list, keep_minor=False):
    minor, major = get_outliers(list)

    if not keep_minor:
        for element in minor:
            list.remove(element)
    else:
        for element in major:
            list.remove(element)

    return sum(list) / len(list)