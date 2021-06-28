package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class TableSortSearchTest {

    public WebDriver driver = new ChromeDriver();
    public String URL = "https://www.seleniumeasy.com/test/table-sort-search-demo.html";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @ParameterizedTest
    @CsvSource({"35, 350000.00"})
    public void tableSortSearchTest(Integer minAge, Double maxSalary) {
        driver.get(URL);
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='example_length']")));
        dropdown.selectByIndex(0);
        List<Employee> employees = getData(minAge, maxSalary);
        employees.stream().forEach(System.out::println);
    }

    private List<Employee> getData(Integer minAge, Double maxSalary) {
        List<Employee> employees = new ArrayList<>();
        String tableCellPattern = "//table[@id='example']//tbody/tr[%s]/td[%s]";
        int numOfRow = driver.findElements(By.xpath("//table[@id='example']//tbody/tr")).size();
        int numOfCell = driver.findElements(By.xpath("//table[@id='example']//th")).size();
        for (int iRow = 1; iRow <= numOfRow; iRow++) {
            String employeeName = null, employeePosition = null, employeeOffice = null;
            Integer age = null;
            Double salary = null;
            for (int iCell = 1; iCell <= numOfCell; iCell++) {
                switch (iCell) {
                    case 1:
                        employeeName = driver.findElement(By.xpath(String.format(tableCellPattern, iRow, iCell))).getText();
                        break;
                    case 2:
                        employeePosition = driver.findElement(By.xpath(String.format(tableCellPattern, iRow, iCell))).getText();
                        break;
                    case 3:
                        employeeOffice = driver.findElement(By.xpath(String.format(tableCellPattern, iRow, iCell))).getText();
                        break;
                    case 4:
                        age = Integer.valueOf(driver.findElement(By.xpath(String.format(tableCellPattern, iRow, iCell))).getText());
                        break;
                    case 6:
                        String text = driver.findElement(By.xpath(String.format(tableCellPattern, iRow, iCell))).getText();
                        salary = Double.valueOf(text.replaceAll("[^0-9]", ""));
                        break;
                }
            }
            if (age > minAge && salary <= maxSalary) {
                employees.add(new Employee(employeeName, employeePosition, employeeOffice));
            }
        }
        return employees;
    }


    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
