package com.sapegin.view;

import com.sapegin.dependencies.annotation.Component;
import com.sapegin.structures.Department;
import com.sapegin.structures.OpeningHours;
import com.sapegin.structures.Product;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

@Component
public class ConsoleView<T> extends View { //todo: почему список товаров вместе со списком отделов
    Scanner scanner = new Scanner(System.in);
    NodeLocation currentNodeLocation;

    final String _BACK = "-back";
    final String _HELP = "-help";
    final String _EXIT = "-exit";
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

                String answer = acceptInput(scanner.nextLine(), welcome, commandsMap);
                if (answer.equals("")) {
                    welcome.handle("");
                } else {
                    if (answer.equals(commandsMap.get(d))) {
                        setCurrentNodeLocation(showDepartments, "");
                    } else if (answer.equals(commandsMap.get(p))) {
                        setCurrentNodeLocation(showProducts, "");
                    }
                }
                return;
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
                System.out.println(printInQuotes(commandsMap.get(ADD) + " <name> <sh> <sm> <eh> <em>") + " add a new department");
                System.out.println(printInQuotes(commandsMap.get(DELETE) + " <id>") + "delete a department");

                String answer = acceptInput(scanner.nextLine(), showDepartments, commandsMap);

                if (!answer.equals("")) {
                    String[] answers = answer.split("\s");
                    if (answers[0].equals(commandsMap.get(go))) {
                        setCurrentNodeLocation(goToDepartment, answers[1]);
                    } else if (answers[0].equals(commandsMap.get(ADD))) {
                        shop.getDataBaseManager().addNewDepartment(answers[1], new OpeningHours(Integer.parseInt(answers[2]), Integer.parseInt(answers[3]), Integer.parseInt(answers[4]), Integer.parseInt(answers[5])));
                    } else if (answers[0].equals(commandsMap.get(DELETE))) {
                        shop.getDataBaseManager().deleteDepartment(Integer.parseInt(answers[1]));
                    }
                }
                setCurrentNodeLocation(showDepartments, (String) o);
            }
        });
        showProducts = new NodeLocation<String>(welcome, new Consumer() {
            @Override
            public void accept(Object o) {
                for (Department d : shop.getDataBaseManager().getDepartments()) {
                    System.out.println(d.getName());
                    System.out.println("\t______");
                    for (Product p : shop.getDataBaseManager().getProductsForDepartment(d)) {
                        System.out.println("\t|\t" + p);
                    }
                    System.out.println("\t|_____");
                }
//                for (Product product : shop.getDataBaseManager().getProducts()) {
//                    System.out.println(product);
//                }
                setCurrentNodeLocation(welcome, (String) o);
            }
        });
        goToDepartment = new NodeLocation(showDepartments, o -> {
            Department department = shop.getDataBaseManager().getDepartmentByID(Integer.parseInt((String) o));
            if (department == null) {
                setCurrentNodeLocation(goToDepartment.getParent(), "");
                return;
            }
            System.out.println("--- Department: " + department + " ---");
            for (Product p : shop.getDataBaseManager().getProductsForDepartment(department)) {
                System.out.println(p);
            }

            HashMap<String, String> commandsMap = new HashMap<>();
            addCommandsToMap(commandsMap, new String[]{UPDATE, ADD, DELETE});

            printWhatDo();
            System.out.println(printInQuotes(commandsMap.get(UPDATE) + " dep name <new name>") + "update name of the department");
            System.out.println(printInQuotes(commandsMap.get(UPDATE) + " dep h <sh> <sm> <eh> <em>") + "update opening hours of the department");
            System.out.println(printInQuotes(commandsMap.get(UPDATE) + " pr name <id> <new name>") + "update name of the product");
            System.out.println(printInQuotes(commandsMap.get(UPDATE) + " pr pr <id> <new price>") + "update price of the product");
            System.out.println(printInQuotes(commandsMap.get(ADD) + " <name> <price>") + "add a new product");
            System.out.println(printInQuotes(commandsMap.get(DELETE) + " <id>") + "delete this product");

            String answer = acceptInput(scanner.nextLine(), showDepartments, commandsMap);

            if (!answer.equals("")) {
                String[] answers = answer.split("\s");
                if (answers[0].equals(commandsMap.get(UPDATE))) {
                    if (answers[1].equals("dep")) {
                        if (answers[2].equals("name")) {
                            shop.getDataBaseManager().setNewNameForDepartment(department, answers[3]);
                        } else if (answers[2].equals("h")) {
                            shop.getDataBaseManager().setNewOpeningHoursDepartment(department, new OpeningHours(Integer.parseInt(answers[3]), Integer.parseInt(answers[4]), Integer.parseInt(answers[5]), Integer.parseInt(answers[6])));
                        }
                    } else if (answers[1].equals("pr")) {
                        if (answers[2].equals("name")) {
                            Product p = shop.getDataBaseManager().getProductByID(Integer.parseInt(answers[3]));
                            shop.getDataBaseManager().setNewNameForProduct(p, answers[4]);
                        } else if (answers[2].equals("pr")) {
                            Product p = shop.getDataBaseManager().getProductByID(Integer.parseInt(answers[3]));
                            shop.getDataBaseManager().setNewPriceForProduct(p, Double.parseDouble(answers[4]));
                        }
                    }
                } else if (answers[0].equals(commandsMap.get(ADD))) {
                    shop.getDataBaseManager().addNewProduct(department, answers[1], Double.parseDouble(answers[2]));
                } else if (answers[0].equals(commandsMap.get(DELETE))) {
                    shop.getDataBaseManager().deleteProduct(Integer.parseInt(answers[1]), department.getID());
                }
            }
            setCurrentNodeLocation(goToDepartment, (String) o);
        });
        currentNodeLocation = welcome;
    }

    private void setCurrentNodeLocation(NodeLocation<String> nodeLocation, String arg) {
        currentNodeLocation = nodeLocation;
        currentNodeLocation.handle(arg);
    }

    private void addCommandsToMap(HashMap<String, String> map, String[] commands) {
        for (String command : commands) {
            map.put(command, command);
        }
    }

    private String acceptInput(String arg, NodeLocation<String> nodeLocation, HashMap<String, String> availableCommandsMap) {
        System.out.println();
        int size = arg.length();
        if (size < 1) { //todo: 1 или 2?
            return "";
        } else if (arg.charAt(0) == '-') {
            if (arg.equals(_BACK)) {
                if (nodeLocation.getParent() != null) {
                    setCurrentNodeLocation(currentNodeLocation.getParent(), "");
                } else {
                    printError(_BACK);
                    currentNodeLocation.handle(arg);
                }
            } else if (arg.equals(_HELP)) {
                printHelp();
                currentNodeLocation.handle(arg);
            } else if (arg.equals(_EXIT)) {
                exitFromProgram();
            } else {
                printError(arg);
                System.out.println("Please check and try again");
                return acceptInput(scanner.nextLine(), nodeLocation, availableCommandsMap);
            }
        } else {
            if (availableCommandsMap.containsKey(arg.split("\s")[0])) {
                return arg;
            } else {
                printError(arg);
                System.out.println("Please check and try again");
                return acceptInput(scanner.nextLine(), nodeLocation, availableCommandsMap);
            }

        }
        return "";
    }

    private String printInQuotes(String str) {
        return "'" + str + "' - ";
    }

    private void printError(String commandError) {
        System.out.println("You made a mistake in command \"" + commandError + "\"");
    }

    private void printHelp() {
        System.out.println("PRINTING THE HELP..."); //todo
    }

    private void printWhatDo() {
        System.out.println("\nWhat would you do?");
    }

    private void exitFromProgram() {
        System.out.println("Good bye!");
        System.exit(0);
    }

    @Override
    public void showWelcome() {
        welcome.handle("");
    }
}
