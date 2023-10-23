package com.sapegin.view;

import java.util.function.Consumer;

public class NodeLocation<T> {
//    String name;
    NodeLocation parent;
    Consumer<T> consumer;


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
