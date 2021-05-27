package jUnit.Utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {

    public static final List<String> FILE_NAME_LIST = Stream.of(
            "vadim-cart",
            "vadim1-cart",
            "vadim2-cart",
            "vadim3-cart",
            "vadim4-cart",
            "vadim5-cart")
            .collect(Collectors.toList());

}
