from schedule import every, run_pending
from speedtest import Speedtest
from datetime import datetime
from time import sleep
import pandas as pd

COLUMNS = ["Download (Mb/s)", "Upload (Mb/s)", "Ping (ms)"]
PATH = "data.csv"

def test_speed():
    test = Speedtest()
    test.get_best_server()

    download, upload, ping = (
        round(test.download() / (10 ** 6), 2), # Download
        round(test.upload() / (10 ** 6), 2),   # Upload
        round(test.results.ping, 2)            # Ping
    )

    return download, upload, ping

# Entirely copied from this blog post!
# Full credit!!
# https://medium.com/analytics-vidhya/record-your-internet-speeds-using-python-6a9827f8eec
def write_to_csv(download, upload, ping):
    try: # Attempting to read the csv
        csv_dataset = pd.read_csv(PATH, index_col="Date")
    except: # If the csv does not exist, make it from scratch
        csv_dataset = pd.DataFrame(
            list(),
            columns=COLUMNS
        )

    # Make a new dataframe to hold the new entry
    results_df = pd.DataFrame(
        [[download, upload, ping]],
        columns=COLUMNS,
        index=[datetime.now().strftime("%x@%H")]
    )

    # Add this new entry to the old data
    final_df = pd.concat([csv_dataset, results_df], sort=False)

    # Write this to the csv file
    # https://stackoverflow.com/a/34297689/9263761
    (
        final_df
        .loc[~ final_df.index.duplicated(keep="last")]
        .to_csv(PATH, index_label="Date")
    )

def main():
    print("Running speed test now!\n")
    download, upload, ping = test_speed()

    # Print results
    print((res_text := f"Results at: {datetime.now().strftime('%c')}"))
    print("-" * len(res_text))
    print(f"Download: {download}")
    print(f"Upload:   {upload}")
    print(f"Ping:     {ping}")

    # Export data
    write_to_csv(download, upload, ping)

if __name__ == "__main__":
    every(1).hour.do(main)
    while True:
        run_pending()
        sleep(30)