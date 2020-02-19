package javafree;

import java.util.Scanner;

import javafree.Console.*;

public class AppInterpreter implements Interpreter {
    @Override
    public Void visit(WriteLine writeLine) {
        System.out.println(writeLine.text);
        return null;
    }

    @Override
    public String visit(ReadLine ReadLine) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    @Override
    public <A, B> B visit(Map<A, B> map) {
        A a = map.effect.accept(this);
        return map.transform.apply(a);
    }

    @Override
    public <A, B> B visit(FlatMap<A, B> flatMap) {
        A a = flatMap.effect.accept(this);
        return flatMap.bind.apply(a).accept(this);
    }
}