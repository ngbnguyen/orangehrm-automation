# Project Structure:
orangehrm-automation
└── README.md
───allure-results
├───logs
├───src
│   ├───main
│   │   ├───java
│   │   │   ├───api
│   │   │   ├───base
│   │   │   ├───context
│   │   │   ├───driver
│   │   │   ├───hooks
│   │   │   ├───pages
│   │   │   └───utils
│   │   └───resources
│   └───test
│       ├───java
│       │   ├───runners
│       │   └───stepDefinitions
│       │       ├───api
│       │       └───ui
│       └───resources
│           ├───config
│           ├───data
│           │   └───api
│           │       ├───Request
│           │       │   ├───Login
│           │       │   └───PIM
│           │       └───Schema
│           │           └───PIM
│           └───features
│               ├───api
│               │   └───PIM
│               └───ui
│                   └───PIM
└───target

 ---
# Tech Stack:
| Layer | Technology |
|-----|-----------|
| Language | Java (OpenJDK 21) |
| Build Tool | Maven |
| BDD | Cucumber |
| Test Runner | TestNG |
| API Automation | Rest Assured |
| UI Automation | Selenium WebDriver |
| Design Pattern | Page Object Model (POM) |
| Reporting | Allure |
| Data | JSON (resources folder) |
---
# How To Run 
```azure
mvn clean test -DORANGE_USERNAME={username} -DORANGE_PASSWORD={password}
```
# Allure Report
```azure
mvn allure:serve
```

