package pl.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;

public class MyIterable implements Iterable<String> {
    private final List<String> strings = new ArrayList();

    public MyIterable() {
        strings.add("a");
        strings.add("b");
    }

    public MyIterable(Collection<String> strs) { // removing this constructor or the status collector() method fixes the test
        this.strings.addAll(strs);
    }

    public static Collector<String, MyIterable, MyIterable> collector() {
        return Collector.of(MyIterable::new, MyIterable::addError, MyIterable::combine);
    }

    @Override
    public Iterator<String> iterator() {
        return strings.iterator();
    }

    public MyIterable addError(String str) {
        strings.add(str);
        return this;
    }

    public MyIterable combine(MyIterable iter) {
        this.strings.addAll(iter.strings);
        return this;
    }
}
