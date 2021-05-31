package jUnit;

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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JsonParserTest {

    Cart initialCart;
    JsonParser parser;

    List<String> fileNameList = Stream.of(
            "vadim-cart",
            "vadim1-cart",
            "vadim2-cart")
            .collect(Collectors.toList());

    @BeforeAll
    void setUp() {
        parser = new JsonParser();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void writeToFileTest(int i) {
        initialCart = new Cart(fileNameList.get(i));
        parser.writeToFile(initialCart);
        Cart actualCart = parser.readFromFile(new File("src/main/resources/" + fileNameList.get(i) + ".json"));
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
        assertEquals("File " + path + ".json not found!", exception.getMessage()); //the first test should be fail because path is correct
    }

    static Stream<String> getPath() {
        return Stream.of("src\\main\\resources\\andrew-cart.json", "vadim-cart.json", "vadim-cart", "resources\\vadim-cart", "vadim cart");
    }

    @Disabled
    @Test
    void isInstantiated() {
        initialCart = new Cart("testCart");
        assertNotNull(initialCart);
    }

    @AfterAll
    void tearDown() {
        for (String fileName : fileNameList) {
            try {
                Files.deleteIfExists(Paths.get("src/main/resources/" + fileName + ".json"));
            } catch (NoSuchFileException | IOException e) {
                System.out.println("No such file/directory exists");
            }
        }
    }
}
