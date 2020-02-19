package javafree;

import javafree.Console.*;

public interface Interpreter {
    public Void visit(WriteLine writeLine);
    public String visit(ReadLine readLine);
    public <A, B> B visit(Map<A, B> map);
    public <A, B> B visit(FlatMap<A, B> flatMap);
}