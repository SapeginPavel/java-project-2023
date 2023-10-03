package com.sapegin.view;

import com.sapegin.NodeLocation;

import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleView<T> extends View {

    Scanner scanner = new Scanner(System.in);

    final String BACK = "-back";
    final String HELP = "-help";

    public ConsoleView() {
        welcome = new NodeLocation<String>(null, new Consumer() {
            @Override
            public void accept(Object o) {
                String d = "d";
                String p = "p";
                System.out.println("You are welcome! What would you do?");
                System.out.println(printInQuotes(d) + " - show departments");
                System.out.println(printInQuotes(p) + " - show products");
                String answer = exceptInput(scanner.nextLine());
                if (answer.equals("")) {
                    return;
                }
            }
        });
        showDepartments = new NodeLocation<String>(welcome, new Consumer() {
            @Override
            public void accept(Object o) {

            }
        });
        showProducts = new NodeLocation<String>(welcome, new Consumer() {
            @Override
            public void accept(Object o) {

            }
        });
    }

    private String printInQuotes(String str) {
        return "'" + str + "'";
    }

    private String exceptInput(String arg) {
        int size = arg.length();
        if (size < 1) //todo: 1 или 2?
        return "";
    }

    @Override
    public void showWelcome() {
        super.showWelcome();
    }
}
