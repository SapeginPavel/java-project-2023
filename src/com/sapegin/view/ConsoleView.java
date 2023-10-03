package com.sapegin.view;

import com.sapegin.NodeLocation;
import com.sapegin.structures.Department;

import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleView<T> extends View {

    Scanner scanner = new Scanner(System.in);
    NodeLocation currentNodeLocation = welcome;

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
                String answer = exceptInput(scanner.nextLine(), welcome);
                if (answer.equals("")) {
                    return;
                } else {
                    if (answer.equals(d)) {
                        for (Department department : shop.getDataBaseManager().getDepartments()) {
                            System.out.println(department);
                        }
                    }
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

    private void setCurrentNodeLocation(NodeLocation<String> nodeLocation) {
        currentNodeLocation = nodeLocation;
        currentNodeLocation.handle("");
    }

    private String exceptInput(String arg, NodeLocation<String> nodeLocation) {
        int size = arg.length();
        if (size < 1) { //todo: 1 или 2?
            return "";
        } else if (arg.charAt(0) == '-') {
            if (arg.equals(BACK)) {
                if (nodeLocation.getParent() != null) {
                    setCurrentNodeLocation(nodeLocation.getParent());
                } else {
                    printError("");
                }
            } else if (arg.equals(HELP)) {
                printHelp();
            }
        } else {
            return arg;
        }
        return "";
    }

    private String printInQuotes(String str) {
        return "'" + str + "'";
    }

    private void printError(String commandError) {
        System.out.println("You made a mistake in command " + commandError);
    }

    private void printHelp() {
        System.out.println("PRINTING THE HELP..."); //todo
    }

    @Override
    public void showWelcome() {
        welcome.handle("");
    }
}
