package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class TableSortSearchTest {

    private WebDriver driver = new ChromeDriver();
    private static final String URL = "https://www.seleniumeasy.com/test/table-sort-search-demo.html";
    private String headerPattern = "//table//th[normalize-space()='%s']/preceding-sibling::th";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @ParameterizedTest
    @CsvSource({"35, 350000.00"})
    public void tableSortSearchTest(int minAge, double maxSalary) {
        driver.get(URL);
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='example_length']")));
        dropdown.selectByValue("10");
        List<Employee> employees = getData(minAge, maxSalary);
        employees.stream().forEach(System.out::println);
    }

    private List<Employee> getData(int minAge, double maxSalary) {
        List<Employee> employees = new ArrayList<>();
        int numOfRows = driver.findElements(By.xpath("//table[@id='example']//tbody/tr")).size();
        for (int i = 1; i <= numOfRows; i++) {
            String employeeName = findTableCellValue(i, findHeaderPosition("Name"));
            String employeeOffice = findTableCellValue(i, findHeaderPosition("Office"));
            String employeePosition = findTableCellValue(i, findHeaderPosition("Position"));
            int employeeAge = Integer.parseInt(findTableCellValue(i, findHeaderPosition("Age")));
            double employeeSalary = Double.parseDouble(findTableCellValue(i, findHeaderPosition("Salary")).replaceAll("[^0-9]", ""));

            if (employeeAge > minAge && employeeSalary <= maxSalary) {
                employees.add(new Employee(employeeName, employeePosition, employeeOffice));
            }
        }
        return employees;
    }

    private Integer findHeaderPosition(String headerName) {
        return driver.findElements(By.xpath(String.format(headerPattern, headerName))).size() + 1;
    }

    private String findTableCellValue(Integer rowIndex, Integer headerIndex) {
        return driver.findElement(By.xpath(String.format("//table[@id='example']//tbody/tr[%d]/td[%d]", rowIndex, headerIndex))).getText();
    }


    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
