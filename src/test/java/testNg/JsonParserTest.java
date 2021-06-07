package testNg;

import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class JsonParserTest {

    public Cart initialCart;
    public JsonParser parser = new JsonParser();
    public String cartName = "testCart";
    public String extension = ".json";
    Path path = Paths.get("src", "main", "resources", cartName);


    @BeforeClass
    void setUp() {
        initialCart = new Cart(cartName);
    }

    @Test(enabled=false, groups = {"goodTests"})
    void writeToFileTest() {
        parser.writeToFile(initialCart);
        Assert.assertTrue(Files.exists(Paths.get(path + extension)));
    }

    @Parameters ({"realItemPrice", "virtItemPrice"})
    @Test(groups = {"goodTests"})
    public void readFromFileTest(double realItemPrice, double virtItemPrice) {
        VirtualItem vItem = new VirtualItem();
        vItem.setPrice(virtItemPrice);

        RealItem rItem = new RealItem();
        rItem.setPrice(realItemPrice);

        initialCart.addRealItem(rItem);
        initialCart.addVirtualItem(vItem);

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(path + extension)) {
            writer.write(gson.toJson(initialCart));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cart expectedCart = parser.readFromFile(new File(path + extension));
        Assert.assertEquals(initialCart.getTotalPrice(), expectedCart.getTotalPrice());
    }

    @Test(expectedExceptions = NoSuchFileException.class, dataProvider = "extensions for notSuchFileExceptionTest", groups = {"brokenTests"})
    void notSuchFileExceptionTest(String extension) {
        parser.writeToFile(initialCart);
        parser.readFromFile(new File(path + extension));
    }

    @DataProvider(name = "extensions for notSuchFileExceptionTest")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {".txt"},
                {".xml"},
                {".docx"},
                {".xlsx"},
                {".json"}};
    }

    @AfterTest
    void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(path + extension));
        } catch (NoSuchFileException | IOException e) {
            System.out.println("No such file/directory exists");
        }
    }
}

