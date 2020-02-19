package javafree;

import javafree.Console.*;

public class App 
{
    public static final Console<Integer> program =
        new WriteLine("What is your name?")
        .flatMap((v) -> new ReadLine()
            .flatMap((name) -> new WriteLine("Hello " + name)
                .map((v1) -> name.length())));

    public static void main(String[] args)
    {
        Integer length = program.accept(new AppInterpreter());
        System.out.println("The name was " + length + " characters long.");
    }
}
