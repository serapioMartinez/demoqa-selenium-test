# Selenium Test Project

This project contains automated tests using Selenium WebDriver.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- WebDriver for the browser you are testing (e.g., ChromeDriver for Chrome)

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/selenium-test.git
    ```
2. Navigate to the project directory:
    ```sh
    cd selenium-test
    ```
3. Install the dependencies:
    ```sh
    mvn clean install
    ```

## Running Tests

Before running the test ensure to set the desired options into the data.properties file.
- `browser`: **chrome** value to run the test into Chrome Browser; **edge** value to run the test into Microsoft Edge Browser
- `headless`: **true** to run test in headless mode.
To run the tests, use the following command:
```sh
mvn clean test
```

> [!IMPORTANT]  
> Since the web page that is been tested uses Captcha on the Register Form some tests are prone to fail.

## Reports

This project generates various reports. You can see each report using the file path and paste it into your browser.
- `target\TestReportSpark.html`: This is a report generated using ExtentReport Library. It log the PASSED, FAILED and SKIPPED test in a step procedurable way.
- `target\surefire-reports\index.html`: This is a report generated using surefire/testng plugin. Here are logged all test status aloong with more technical information. This report has a user friendly interface.
- `target\surefire-reports\emailable-report.html`: This report is a emailable version of the previous report.

## Project Structure

- `src/test/java`: Contains the test cases
- `src/main/resources`: Contains configuration files

## Contributing

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Create a new Pull Request

## License

This project is licensed under the MIT License.