package jUnit;

import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JsonParserTest {

    public Cart initialCart;
    public JsonParser parser = new JsonParser();
    public String cartName = "testCart";
    public String extension = ".json";
    Path path = Paths.get("src", "main", "resources", cartName);


    @BeforeAll
    void setUp() {
        initialCart = new Cart(cartName);
    }

    @Disabled
    @Test
    void writeToFileTest() {
        parser.writeToFile(initialCart);
        assertTrue(Files.exists(Paths.get(path + extension)));
    }

    @Test
    public void readFromFileTest() {
        VirtualItem vItem = new VirtualItem();
        vItem.setPrice(50.40);

        RealItem rItem = new RealItem();
        rItem.setPrice(17500.55);

        initialCart.addRealItem(rItem);
        initialCart.addVirtualItem(vItem);

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(path + extension)) {
            writer.write(gson.toJson(initialCart));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cart expectedCart = parser.readFromFile(new File(path + extension));
        assertEquals(initialCart.getTotalPrice(), expectedCart.getTotalPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = {".txt", ".xml", ".docx", ".xlsx", ".json"})
    void notSuchFileExceptionTest(String extension) {
        parser.writeToFile(initialCart);
        Exception exception = assertThrows(NoSuchFileException.class,
                () -> parser.readFromFile(new File(path + extension)));
        assertEquals("File " + path + extension + " not found!", exception.getMessage());
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(path + extension));
        } catch (NoSuchFileException | IOException e) {
            System.out.println("No such file/directory exists");
        }
    }
}

