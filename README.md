# Mobile Testing using Appium

This repository contains automated tests for a mobile Android application using Appium, Java, and TestNG. It uses the Page Object Model (POM) design pattern for better maintainability and Allure for reporting.

## 🛠 Tech Stack

- **Language:** Java 21
- **Framework:** Appium (Service: `io.appium:java-client:10.0.0`)
- **Test Runner:** TestNG (`7.11.0`)
- **Build Tool:** Maven
- **Reporting:** Allure Report

## 📋 Prerequisites

Before running the tests, ensure you have the following installed:

1.  **Java Development Kit (JDK) 21**
2.  **Maven**
3.  **Appium Server** (installed via NPM: `npm install -g appium`)
4.  **Android Studio & Android SDK** (with `ANDROID_HOME` set)
5.  **Appium UIAutomator2 Driver** (`appium driver install uiautomator2`)
6.  **Emulator or Real Device** (configured with Android 13)

## ⚙️ Configuration

The test configuration is located in `src/test/java/test/BaseTest.java`. You **MUST** update the following path to match your local environment before running tests:

```java
// Update this path to where your APK is located
options.setApp("C:\\Users\\omar2\\Desktop\\java\\app-debug.apk");
```

**Default Capabilities:**
- **Device Name:** Pixel4
- **Platform Version:** 13
- **Automation Name:** UiAutomator2
- **Appium Server:** `http://127.0.0.1:4723`

## 🚀 Running Tests

### 1. Start Appium Server
Start the Appium server properly with the necessary CORS adjustment if using local reporing tools, or simply:
```bash
appium
```

### 2. Run All Tests
Execute all tests using Maven:
```bash
mvn clean test
```

### 3. Run Specific Test
To run a specific test class (e.g., `CreateTaskTest`):
```bash
mvn clean test -Dtest=CreateTaskTest
```

## 📊 Viewing Reports

This project uses **Allure** for reporting.

To generate and view the report after a test run:

```bash
mvn allure:serve
```
Or manually via the Allure CLI (if installed):
```bash
allure serve target/allure-results
```

## 📂 Project Structure

```
├── src
│   ├── main/java/pages       # Page Object Classes
│   └── test/java/test        # Test Classes (BaseTest, CreateTaskTest)
├── pom.xml                   # Maven Dependencies & Plugins
└── README.md                 # Project Documentation
```
