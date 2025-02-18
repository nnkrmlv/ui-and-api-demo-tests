# Use Maven with OpenJDK 21 base image
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /app

# Copy pom.xml to leverage Docker caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project
COPY . .

# please review all the latest versions here:
# https://googlechromelabs.github.io/chrome-for-testing/
# ENV CHROMEDRIVER_VERSION=

# Install required dependencies
RUN apt-get update && apt-get install -y \
    curl \
    unzip \
    wget \
    gnupg \
    ca-certificates \
    libappindicator3-1 \
    fonts-liberation \
    xdg-utils \
    && rm -rf /var/lib/apt/lists/*

# Download and install Google Chrome (Linux Version)
RUN wget -O /tmp/chrome-mac-arm64.zip https://storage.googleapis.com/chrome-for-testing-public/133.0.6943.98/mac-arm64/chrome-mac-arm64.zip
RUN unzip /tmp/chrome-mac-arm64.zip -d /opt/google/
# RUN ln -s /opt/google/chrome-mac-arm64/chrome /usr/local/bin/google-chrome
# RUN rm /tmp/chrome-mac-arm64.zip

# Verify Chrome installation
RUN "/opt/google/chrome-mac-arm64/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing" --version

# RUN apt-get update && apt-get install -y \
#     wget \
#     gnupg \
#     apt-transport-https \
#     ca-certificates

# RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add -

# RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list'

# RUN apt-get update && apt-get install -y google-chrome-stable


# please review all the latest versions here:
# https://googlechromelabs.github.io/chrome-for-testing/
# ENV CHROMEDRIVER_VERSION=133.0.6943.98

### install chrome
# RUN apt-get update && apt-get install -y wget && apt-get install -y zip
# RUN wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
# RUN apt-get install -fy ./google-chrome-stable_current_amd64.deb

# Manually download and install Google Chrome
# RUN wget -O /tmp/google-chrome.deb https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
#     && apt-get install -y /tmp/google-chrome.deb \
#     && rm /tmp/google-chrome.deb

# Expose necessary ports
EXPOSE 4444 5900

# Run tests after starting Selenium Hub and Chrome
CMD ["mvn", "test"]