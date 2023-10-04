package com.sapegin.view;

import com.sapegin.NodeLocation;
import com.sapegin.structures.Department;
import com.sapegin.structures.Product;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleView<T> extends View {
// надо было через отдельные классы каждый узел делать, наверное
    Scanner scanner = new Scanner(System.in);
    NodeLocation currentNodeLocation;

    final String BACK = "-back";
    final String HELP = "-help";
    final String ADD = "add";
    final String DELETE = "del";
    final String UPDATE = "upd";
    final String WAIT = "---";

    public ConsoleView() {
        welcome = new NodeLocation<String>(null, new Consumer() {
            @Override
            public void accept(Object o) {
                String d = "d";
                String p = "p";
                System.out.println("\n\nYou are welcome!");
                printWhatDo();
                System.out.println(printInQuotes(d) + "show departments");
                System.out.println(printInQuotes(p) + "show products");

                HashMap<String, String> commandsMap = new HashMap<>();
                addCommandsToMap(commandsMap, new String[]{d, p});

                String answer = exceptInput(scanner.nextLine(), welcome, commandsMap);
                if (answer.equals("")) {
                    return;
                } else {
                    if (answer.equals(commandsMap.get(d))) {
                        setCurrentNodeLocation(showDepartments, "");
                    } else if (answer.equals(commandsMap.get(p))) {
                        setCurrentNodeLocation(showProducts, "");
                    }
                }
            }
        });
        showDepartments = new NodeLocation<String>(welcome, new Consumer() {
            @Override
            public void accept(Object o) {
                for (Department department : shop.getDataBaseManager().getDepartments()) {
                    System.out.println(department);
                }
                String go = "go";

                HashMap<String, String> commandsMap = new HashMap<>();
                addCommandsToMap(commandsMap, new String[]{go, ADD, DELETE});

                printWhatDo();
                System.out.println(printInQuotes(commandsMap.get(go) + " <id>") + "go to department");
                System.out.println(printInQuotes(commandsMap.get(ADD)) + "add a new department");
                System.out.println(printInQuotes(commandsMap.get(DELETE) + " <id>") + "delete a department");

                String answer = exceptInput(scanner.nextLine(), showDepartments, commandsMap);

                if (!answer.equals("")) {
                    String[] answers = answer.split("\s");
                    if (answers[0].equals(commandsMap.get(go))) {
                        setCurrentNodeLocation(goToDepartment, answers[1]);
                    } else if (answers[0].equals(commandsMap.get(ADD))) {
                        //todo
                    } else if (answers[0].equals(commandsMap.get(DELETE))) {
                        //todo
                    }
                }
            }
        });
        showProducts = new NodeLocation<String>(welcome, new Consumer() {
            @Override
            public void accept(Object o) {
                for (Product product : shop.getDataBaseManager().getProducts()) {
                    System.out.println(product);
                }
            }
        });
        goToDepartment = new NodeLocation(showDepartments, new Consumer() {
            @Override
            public void accept(Object o) {
                for (Product p : shop.getDataBaseManager().getProductsForDepartment(shop.getDataBaseManager().getDepartmentByID(Integer.parseInt((String) o)))) {
                    System.out.println(p);
                }

                HashMap<String, String> commandsMap = new HashMap<>();
                addCommandsToMap(commandsMap, new String[]{UPDATE, ADD, DELETE});

                printWhatDo();
                System.out.println(printInQuotes(commandsMap.get(UPDATE)) + "dep update info about department");
                System.out.println(printInQuotes(commandsMap.get(ADD) + "<name> <price>") + "add a new product");
                System.out.println(printInQuotes(commandsMap.get(UPDATE) + "pr <id>") + "update info about product");
                System.out.println(printInQuotes(commandsMap.get(DELETE) + "<id>") + "delete this product");

                String answer = exceptInput(scanner.nextLine(), showDepartments, commandsMap);

                if (!answer.equals("")) {
                    String[] answers = answer.split("\s");
                    if (answers[0].equals(commandsMap.get(UPDATE))) {
                        if (answers[1].equals("dep")) {

                        } else if (answers[1].equals("pr")) {
                            //todo
                        }
                    } else if (answers[0].equals(commandsMap.get(ADD))) {
                        //todo
                    } else if (answers[0].equals(commandsMap.get(DELETE))) {
                        //todo
                    }
                }
            }
        });
        editDepartment = new NodeLocation(goToDepartment, new Consumer() {
            @Override
            public void accept(Object o) {

            }
        });
        currentNodeLocation = welcome;
    }

    private void setCurrentNodeLocation(NodeLocation<String> nodeLocation, String arg) {
        currentNodeLocation = nodeLocation;
        currentNodeLocation.handle(arg);
    }

    private void addCommandToMap(HashMap<String, String> map, String command) {
        map.put(command, command);
    }

    private void addCommandsToMap(HashMap<String, String> map, String[] commands) {
        for (String command : commands) {
            map.put(command, command);
        }
    }

    private String exceptInput(String arg, NodeLocation<String> nodeLocation, HashMap<String, String> availableCommandsMap) {
        System.out.println();
        int size = arg.length();
        if (size < 1) { //todo: 1 или 2?
            return "";
        } else if (arg.charAt(0) == '-') {
            if (arg.equals(BACK)) {
                if (nodeLocation.getParent() != null) {
                    setCurrentNodeLocation(nodeLocation.getParent(), "");
                } else {
                    printError("");
                    currentNodeLocation.handle(arg);
                }
            } else if (arg.equals(HELP)) {
                printHelp();
                currentNodeLocation.handle(arg);
            }
        } else {
            if (availableCommandsMap.containsKey(arg.split("\s")[0])) {
                return arg;
            } else {
                printError(arg);
                System.out.println("Please check and try again");
                return exceptInput(scanner.nextLine(), nodeLocation, availableCommandsMap);
            }

        }
        return "";
    }

    private String printInQuotes(String str) {
        return "'" + str + "' - ";
    }

    private void printError(String commandError) {
        System.out.println("You made a mistake in command " + commandError);
    }

    private void printHelp() {
        System.out.println("PRINTING THE HELP..."); //todo
    }

    private void printWhatDo() {
        System.out.println("\nWhat would you do?");
    }

    @Override
    public void showWelcome() {
        welcome.handle("");
    }
}
