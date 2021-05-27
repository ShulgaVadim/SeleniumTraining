package jUnit;

import jUnit.Utils.Utils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static jUnit.Utils.Constants.FILE_NAME_LIST;
import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    static Cart initialCart;
    static JsonParser parser;

    @BeforeAll
    static void setUp() {
        parser = new JsonParser();
        initialCart = Utils.initCart(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    void writeToFileTest(int i) {
        parser.writeToFile(initialCart);
        Cart actualCart = parser.readFromFile(new File("src/main/resources/" + FILE_NAME_LIST.get(i) + ".json"));
        assertAll(() -> assertEquals(initialCart.getCartName(), actualCart.getCartName()),
                () -> assertEquals(initialCart.getTotalPrice(), actualCart.getTotalPrice()));
    }

    @Test
    public void readFromFileTest() {
        Cart newCart = parser.readFromFile(new File("src/main/resources/eugen-cart.json"));
        assertAll(() -> assertEquals("eugen-cart", newCart.getCartName()),
                () -> assertEquals(26560.68, newCart.getTotalPrice()));
    }

    @ParameterizedTest
    @MethodSource("getPath")
    void notSuchFileExceptionTest(String path) {
        Exception exception = assertThrows(NoSuchFileException.class,
                () -> parser.readFromFile(new File(path)));
        assertEquals("File " + path + ".json not found!", exception.getMessage());
    }

    static Stream<String> getPath() {
        return Stream.of("src\\main\\resources\\vadim-cart", "vadim-cart.json", "vadim-cart", "resources\\vadim-cart", "vadim cart");
    }

    @Disabled
    @Test
    void isInstantiated() {
        assertNotNull(initialCart);
    }

    @AfterAll
    public static void tearDown() {
        try {
            Files.deleteIfExists(Paths.get("src/main/resources/" + initialCart.getCartName() + ".json"));
        } catch (NoSuchFileException | IOException e) {
            System.out.println("No such file/directory exists");
        }
    }
}
