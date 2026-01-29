orangehrm-automation
│
├── pom.xml
├── testng.xml
├── README.md
│
├── src/main/java
│   ├── base
│   │   ├── BasePage.java
│   │   └── Hooks.java
│   │
│   ├── pages
│   │   ├── LoginPage.java
│   │   ├── DashboardPage.java
│   │   ├── pim
│   │   │   ├── EmployeeListPage.java
│   │   │   └── AddEmployeePage.java
│   │   └── admin
│   │       └── UserManagementPage.java
│   │
│   └── utils
│       ├── DriverFactory.java
│       ├── EnvUtils.java
│       ├── JsonUtils.java
│       └── WaitUtils.java
│
├── src/test/java
│   ├── runners
│   │   └── CucumberTestRunner.java
│   │
│   ├── stepdefinitions
│   │   ├── CommonSteps.java
│   │   ├── EmployeeSteps.java
│   │   └── UserSteps.java
│   │
│   └── api
│       └── UserApiSteps.java
│
└── src/test/resources
├── features
│   ├── employee.feature
│   └── user.feature
│
├── data
│   ├── employee.json
│   └── user.json
│
└── upload
└── avatar.png
