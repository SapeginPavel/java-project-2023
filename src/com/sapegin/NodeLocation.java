package com.sapegin;

import java.util.function.Consumer;

public class NodeLocation<T> {
//    String name;
    NodeLocation parent;
    Consumer<T> consumer;
    //NodeLocation child;


    public NodeLocation(NodeLocation parent, Consumer<T> consumer) {
        this.parent = parent;
        this.consumer = consumer;
    }

    public void handle(T arg) {
        consumer.accept(arg);
    }

    public NodeLocation getParent() {
        return parent;
    }
}
