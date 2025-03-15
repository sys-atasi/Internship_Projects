import requests
import csv
import os
from bs4 import BeautifulSoup

def fetch_data(url):
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
    }
    try:
        response = requests.get(url, headers=headers, timeout=10)
        response.raise_for_status()  # Raise an error for bad status codes
        return response.text
    except requests.exceptions.RequestException as e:
        print(f"Error fetching data: {e}")
        return None

def parse_data(html):
    soup = BeautifulSoup(html, "html.parser")
    data = []
    
    # Example website: Books to Scrape
    items = soup.find_all("article", class_="product_pod")  # Updated selector
    for item in items:
        title = item.h3.a["title"]
        price = item.find("p", class_="price_color").text.strip()
        link = "https://books.toscrape.com/" + item.h3.a["href"]
        data.append([title, price, link])
    
    return data

def save_to_csv(data, filename="scraped_data.csv"):
    with open(filename, mode="w", newline="", encoding="utf-8") as file:
        writer = csv.writer(file)
        writer.writerow(["Title", "Price", "Link"])  # Column headers
        writer.writerows(data)
    print(f"Data saved to {filename}")

def main():
    url = "https://books.toscrape.com/"  # Updated working website URL
    html = fetch_data(url)
    if html:
        data = parse_data(html)
        if data:
            save_to_csv(data)
        else:
            print("No data found.")
    input("Press Enter to exit...")

if __name__ == "__main__":
    main()
