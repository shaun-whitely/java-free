package javafree;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import javafree.Console.FlatMap;
import javafree.Console.Map;
import javafree.Console.ReadLine;
import javafree.Console.WriteLine;

public class AppTest 
{
    @Test
    public void shouldPrintCorrectMessages() {
        TestInterpreter interpreter = new TestInterpreter("Shaun");
        App.program.accept(interpreter);

        List<String> expectedOutput = Arrays.asList("What is your name?", "Hello Shaun");

        assertEquals(expectedOutput, interpreter.outputBuffer);
    }

    @Test
    public void shouldReturnNameLength() {
        TestInterpreter interpreter = new TestInterpreter("Shaun");
        Integer result = App.program.accept(interpreter);

        assertEquals(5, result.intValue());
    }

    private static class TestInterpreter implements Interpreter {
        public List<String> outputBuffer = new ArrayList<>();
        private final Queue<String> inputBuffer;

        public TestInterpreter(final String... inputs) {
            this.inputBuffer = new LinkedList<>(Arrays.asList(inputs));
        }

        @Override
        public Void visit(final WriteLine writeLine) {
            outputBuffer.add(writeLine.text);
            return null;
        }

        @Override
        public String visit(final ReadLine readLine) {
            return inputBuffer.remove();
        }

        @Override
        public <A, B> B visit(final Map<A, B> map) {
            A a = map.effect.accept(this);
            return map.transform.apply(a);
        }

        @Override
        public <A, B> B visit(final FlatMap<A, B> flatMap) {
            A a = flatMap.effect.accept(this);
            return flatMap.bind.apply(a).accept(this);
        }
    }
}
