# Selenium Automation Framework - Selenium WebDriver with Java Basics to Advanced

This repository contains a complete Selenium WebDriver automation framework developed as part of the Udemy course:
**"Selenium WebDriver with Java - Basics to Advanced + Frameworks By Rahul Shetty"**

---

## 🧑‍💻 Framework Architecture

Here’s a visual overview of the framework design:

![Framework Architecture](https://github.com/ravinduheshan99/Selenium-Framework-Design-Udemy/blob/main/Framework%20Architectural%20Diagram/Selenium%20Automation%20Framework%20Architecture%20Diagram.png)

### 🔧 Components Breakdown

- **Test Classes**: Contain step definitions and test logic to drive UI automation.
- **Page Objects**: Represent individual pages; contain locators and page-specific actions.
- **Abstract Component**: A base utility class used by all Page Objects for reusable logic like waits, navigation, etc.
- **BaseTest**: Sets up and tears down WebDriver instances. All tests extend from this.
- **Data Providers**: Fetch test data from external JSON files, convert to `HashMap`, and inject using TestNG `@DataProvider`.
- **Listeners**: Used to hook into test execution lifecycle. Responsible for reporting, capturing screenshots, retrying failed tests.
- **TestNG XML**: Manages test suites and controls parallel execution.
- **pom.xml + Maven Profiles**: Drive build and execution, including running TestNG XML files.
- **ExtentReports Integration**: Test results are sent to extent reports using Listeners.
- **Retry Logic**: Retry failed tests automatically using custom retry listeners.
- **ThreadLocal WebDriver**: Used for safe parallel test execution.

---

## 🛠 Technologies Used

| Tool/Framework       | Purpose                                |
|----------------------|----------------------------------------|
| Java                 | Core programming language              |
| Selenium WebDriver   | Web automation                         |
| TestNG               | Test management framework              |
| Maven                | Dependency & build tool                |
| Jenkins              | Continuous Integration                 |
| JSON                 | External test data source              |
| Apache POI           | Excel-driven data handling (optional)  |
| log4j                | Logging utility                        |
| Cucumber (optional)  | BDD Layer with Feature Files           |
| Selenium Grid        | Parallel & distributed execution       |

---

## 🚀 How to Run the Framework

1. Clone the repo:
```bash
git clone https://github.com/ravinduheshan99/Selenium-Framework-Design-Udemy.git
```

2. Import into Eclipse or IntelliJ

3. Run Maven to install dependencies:
```bash
mvn clean install
```

4. Trigger execution:
```bash
mvn test -P<profile-name>
```

---

## 📂 Directory Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── pageObjects
│   │   │   ├── abstractComponents
│   │   │   ├── resources
│   ├── test
│   │   ├── testCases
│   │   ├── data
│   │   ├── listeners
├── testng.xml
├── pom.xml
└── README.md
```

---

## 📌 Notes for Interviews

- Explain architecture using the diagram above.
- Discuss use of OOP principles: Inheritance (BaseTest, AbstractComponent), Encapsulation (PageObjects), and Interfaces (WebDriver).
- Mention ThreadLocal for parallel test safety.
- Discuss how Listeners enable real-time logging, retry logic, and reporting.

---

## 📧 Contact

📌 Developed by: **Ravindu Haputhanthri**  
📫 GitHub: [ravinduheshan99](https://github.com/ravinduheshan99)

---

✅ *Feel free to fork this repo or raise an issue for improvements or bugs.*
