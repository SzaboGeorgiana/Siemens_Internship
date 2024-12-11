#!/bin/bash

# Exit on any error
set -e

# Check if CODE_PROXY is defined
if [ -z "$CODE_PROXY" ]; then
  echo "CODE_PROXY environment variable is not set. Exiting..."
  exit 1
fi

# Extract proxy host and port from CODE_PROXY
PROXY_HOST=$(echo $CODE_PROXY | sed -e 's|http://||' -e 's|https://||' -e 's/:.*//')
PROXY_PORT=$(echo $CODE_PROXY | sed -e 's|.*:||')

# Generate Maven settings.xml with proxy configuration
mkdir -p ~/.m2
cat > ~/.m2/settings.xml <<EOL
<settings xmlns='http://maven.apache.org/SETTINGS/1.0.0'
          xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
          xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd'>
  <proxies>
    <proxy>
      <id>http</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>${PROXY_HOST}</host>
      <port>${PROXY_PORT}</port>
    </proxy>
    <proxy>
      <id>https</id>
      <active>true</active>
      <protocol>https</protocol>
      <host>${PROXY_HOST}</host>
      <port>${PROXY_PORT}</port>
    </proxy>
  </proxies>
</settings>
EOL

# Output the settings file for debugging (optional)
echo "Generated Maven settings.xml:"
cat ~/.m2/settings.xml
