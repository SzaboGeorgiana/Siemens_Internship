#!/bin/bash

# Verificare proxy
echo "Using proxy: $CODE_PROXY"
echo "No proxy for: $CODE_NO_PROXY"

# Setăm proxy pentru apt-get, dacă este configurat
if [ -n "$CODE_PROXY" ]; then
  export http_proxy="$CODE_PROXY"
  export https_proxy="$CODE_PROXY"
  export HTTP_PROXY="$CODE_PROXY"
  export HTTPS_PROXY="$CODE_PROXY"
  export no_proxy="$CODE_NO_PROXY"
  export NO_PROXY="$CODE_NO_PROXY"
fi

# Actualizare și instalare pachete necesare
apt-get update && apt-get install -y wget unzip curl gnupg

# Instalare OpenJDK 21
apt-get install -y openjdk-21-jdk

# Instalare Google Chrome
wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add -
echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list
apt-get update && apt-get install -y google-chrome-stable

# Obținem versiunea Google Chrome
CHROME_VERSION=$(google-chrome --version | grep -oP '[0-9]+(\.[0-9]+)+')

# Verificăm dacă am obținut corect versiunea Chrome
if [ -z "$CHROME_VERSION" ]; then
  echo "Eroare: Nu am găsit versiunea Google Chrome."
  exit 1
fi

# # Definim URL-ul pentru ChromeDriver fixat
# CHROME_DRIVER_URL="https://chromedriver.storage.googleapis.com/100.0.4896.20/chromedriver_linux64.zip"

# # Afișăm URL-ul pentru ChromeDriver
# echo "Using ChromeDriver from URL: $CHROME_DRIVER_URL"

# # Descarcă și instalează ChromeDriver
# wget -O /tmp/chromedriver.zip "$CHROME_DRIVER_URL"
# unzip /tmp/chromedriver.zip -d /usr/local/bin/
# chmod +x /usr/local/bin/chromedriver

# Verificăm versiunile instalate
google-chrome --version
# chromedriver --version
