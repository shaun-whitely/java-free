package javafree;

import java.util.function.Function;

public abstract class Console<A> {
    public abstract A accept(Interpreter interpreter);

    public <B> Console<B> map(Function<A, B> transform) {
        return new Map<>(transform, this);
    }

    public <B> Console<B> flatMap(Function<A, Console<B>> bind) {
        return new FlatMap<>(bind, this);
    }

    public static class WriteLine extends Console<Void> {

        public final String text;

        public WriteLine(String text) {
            this.text = text;
        }

        @Override
        public Void accept(Interpreter interpreter) {
            return interpreter.visit(this);
        }
    }

    public static class ReadLine extends Console<String> {
        @Override
        public String accept(Interpreter interpreter) {
            return interpreter.visit(this);
        }
    }

    public static class Map<A, B> extends Console<B> {
        public final Function<A, B> transform;
        public final Console<A> effect;

        public Map(Function<A, B> transform, Console<A> effect) {
            this.transform = transform;
            this.effect = effect;
        }

        @Override
        public B accept(Interpreter interpreter) {
            return interpreter.visit(this);
        }
    }

    public static class FlatMap<A, B> extends Console<B> {
        public final Function<A, Console<B>> bind;
        public final Console<A> effect;

        public FlatMap(Function<A, Console<B>> bind, Console<A> effect) {
            this.bind = bind;
            this.effect = effect;
        }

        @Override
        public B accept(Interpreter interpreter) {
            return interpreter.visit(this);
        }
    }
}