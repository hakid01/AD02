/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xestionfranquicia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erifc
 */
public class Main {

    private static Store storeToManage = new Store();
    private static LinkedList<Store> stores = new LinkedList<>();
//    private static LinkedList<Item> items = new LinkedList<>();
//    private static LinkedList<Employee> employees = new LinkedList<>();
    private static LinkedList<Customer> customers = new LinkedList<>();

    private static Franchise franchise = new Franchise(stores, customers);

    private static File franchiseFile;

    public static void main(String[] args) {

        bienvenida();

        mainMenu();

    }

    //---MÉTODOS PROGRAMA PRINCIPAL---
    public static void bienvenida() {
        System.out.println("**************************************");
        System.out.println("** Bienvenido a ManejoFranquiciaPro **");
        System.out.println("**************************************\n");
    }

    public static void mainMenu() {
        System.out.println("Seleccione una opción (1, 2 o 3):");
        System.out.println("1.- Crear un nuevo archivo.");
        System.out.println("2.- Recuperar datos de archivo existente");
        System.out.println("3.- Salir.");

        String option = new Scanner(System.in).nextLine();

        if (option.equalsIgnoreCase("1")) {
            newFile();
            saveObjToJson(franchise, franchiseFile);
            managerMenu();
        } else if (option.equalsIgnoreCase("2")) {
            System.out.println("Importando datos");
            importFile();
            jsonToObj();
            managerMenu();
        } else if (option.equalsIgnoreCase("3")) {
            System.exit(0);
        } else {
            System.out.println("------------------------------\n");
            System.out.println("Introduce una opción correcta.");
            System.out.println("------------------------------\n");
            mainMenu();
        }
    }

    public static void newFile() {
        System.out.println("--------------------------------------------");
        System.out.println("------ Se va a crear un nuevo archivo ------");
        System.out.println("--------------------------------------------");
        System.out.println("Introduce la ruta absoluta del nuevo archivo: ");
        System.out.println("Asegurate de usar \"/\" como separador");
        String absPath = new Scanner(System.in).nextLine();

        File tempFile = new File(absPath);

        if (tempFile.isFile()) {
            System.out.println("----------------------------------------------");
            System.out.println("El path indicado ya corresponde con un archivo");
            System.out.println("Introduzca otro path o elija importar archivo");
            System.out.println("----------------------------------------------");
            mainMenu();
        } else {
            franchiseFile = tempFile;
        }
    }

    public static void saveObjToJson(Franchise franchise, File pathFile) {
        // Convertimos objeto a JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonFranchise = gson.toJson(franchise);

        // Escribimos JSON en fichero
        try {
            FileWriter fr = new FileWriter(pathFile);
            BufferedWriter bw = new BufferedWriter(fr);

            bw.write(jsonFranchise);
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void importFile() {
        System.out.println("-----------------------------------------");
        System.out.println("------ Se va a importar un archivo ------");
        System.out.println("-----------------------------------------");
        System.out.println("Introduce la ruta absoluta del archivo: ");
        String absPath = new Scanner(System.in).nextLine();

        File tempFile = new File(absPath);

        if (!tempFile.isFile()) {
            System.out.println("El path indicado no corresponde con ningún archivo");
            mainMenu();
        } else {
            franchiseFile = tempFile;
        }
    }

    public static void jsonToObj() {

        try {
            FileReader fr = new FileReader(franchiseFile);
            BufferedReader br = new BufferedReader(fr);

            //Leer linea a linea
            StringBuilder jsonBuiler = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                jsonBuiler.append(linea).append("\n");
            }

            br.close();

            //Construimos el string con todas las lineas leídas
            String json = jsonBuiler.toString();
            System.out.println("archivo json: " + json);

            //Pasamos json a clase Java
            Gson gson = new GsonBuilder().create();
            franchise = gson.fromJson(json, Franchise.class);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Objeto franquicia: " + franchise);
        //asignamos las tiendas y los clientes
        stores = franchise.getStores();
        customers = franchise.getCustomers();
        System.out.println("tiendas: " + stores);
        System.out.println("clientes: " + customers);
    }

    public static void managerMenu() {
        System.out.println("Opciones Franquicia:");
        System.out.println("1.- Listar tiendas.");
        System.out.println("2.- Añadir tienda.");
        System.out.println("3.- Eliminar tienda (elimina todo su contenido).");
        System.out.println("4.- Seleccionar y modificar atributo de una tienda.");
        System.out.println("5.- Listar clientes.");
        System.out.println("6.- Añadir cliente.");
        System.out.println("7.- Eliminar cliente.");
        System.out.println("8.- Borrar archivo completo.");
        System.out.println("9.- Crear copia de seguridad.");
        System.out.println("10.- Leer titulares de \"El Pais\".");
        System.out.println("11.- Inicio.");
        System.out.println("12.- Salir.");

        String option = new Scanner(System.in).nextLine();

        if (option.equalsIgnoreCase("1")) {
            listFiles(stores);
            managerMenu();
        } else if (option.equalsIgnoreCase("2")) {
            createStore();
            saveObjToJson(franchise, franchiseFile);
            managerMenu();
        } else if (option.equalsIgnoreCase("3")) {
            removeStore();
            saveObjToJson(franchise, franchiseFile);
            managerMenu();
        } else if (option.equalsIgnoreCase("4")) {
            selectStore();
            storeMenu(storeToManage);
        } else if (option.equalsIgnoreCase("5")) {
            listFiles(customers);
            managerMenu();
        } else if (option.equalsIgnoreCase("6")) {
            createCustomer();
            saveObjToJson(franchise, franchiseFile);
            managerMenu();

        } else if (option.equalsIgnoreCase("7")) {
            removeCustomer();
            saveObjToJson(franchise, franchiseFile);
            managerMenu();

        } else if (option.equalsIgnoreCase("8")) {
            removeAllData();
            saveObjToJson(franchise, franchiseFile);
            managerMenu();

        }else if (option.equalsIgnoreCase("9")){
            backup();
        }else if (option.equalsIgnoreCase("10")){
            
        }
        else if (option.equalsIgnoreCase("11")) {
            mainMenu();
        } else if (option.equalsIgnoreCase("12")) {
            System.exit(0);
        } else {
            System.out.println("------------------------------\n");
            System.out.println("Introduce una opción correcta.");
            System.out.println("------------------------------\n");
            managerMenu();
        }

    }

    public static void storeMenu(Store storeToManage) {
        try {
            storeToManage.getEmployees();
        } catch (Exception e) {
            storeToManage.setEmployees(new LinkedList<>());
        }
        try {
            storeToManage.getItems();
        } catch (Exception e) {
            storeToManage.setItems(new LinkedList<>());
        }

        System.out.println("Opciones de Tienda:");
        System.out.println("1.- Listar trabajadores.");
        System.out.println("2.- Añadir trabajador.");
        System.out.println("3.- Eliminar trabajador.");
        System.out.println("4.- Listar productos.");
        System.out.println("5.- Añadir producto.");
        System.out.println("6.- Eliminar producto.");
        System.out.println("7.- Volver.");
        System.out.println("8.- Inicio.");

        String option = new Scanner(System.in).nextLine();

        if (option.equalsIgnoreCase("1")) {

            if (storeToManage.getEmployees().isEmpty()) {
                System.out.println("No hay ningún trabajador registrado.");
            } else {
                listFiles(storeToManage.getEmployees());
            }

            storeMenu(storeToManage);
        } else if (option.equalsIgnoreCase("2")) {
            createEmployee();
            saveObjToJson(franchise, franchiseFile);
            storeMenu(storeToManage);
        } else if (option.equalsIgnoreCase("3")) {
//            removeStore();
            saveObjToJson(franchise, franchiseFile);
            storeMenu(storeToManage);
        } else if (option.equalsIgnoreCase("4")) {
            listFiles(storeToManage.getItems());
            storeMenu(storeToManage);
        } else if (option.equalsIgnoreCase("5")) {
//            createStore();
            saveObjToJson(franchise, franchiseFile);
            storeMenu(storeToManage);
        } else if (option.equalsIgnoreCase("6")) {
//            removeStore();
            saveObjToJson(franchise, franchiseFile);
            storeMenu(storeToManage);
        } else if (option.equalsIgnoreCase("7")) {
            managerMenu();
        } else if (option.equalsIgnoreCase("8")) {
            mainMenu();
        } else {
            System.out.println("------------------------------\n");
            System.out.println("Introduce una opción correcta.");
            System.out.println("------------------------------\n");
            storeMenu(storeToManage);
        }
    }

    public static void listFiles(LinkedList linkedList) {
        if (linkedList.isEmpty()) {
            System.out.println("No hay elementos. Archivo vacío.");
            System.out.println("--------------------------------");

        } else {
            int count = 1;
            for (Object obj : linkedList) {
                System.out.println("" + count + ".- " + obj.toString());
                count++;
            }
        }
    }

    public static void selectStore() {
//        Store storeToManage = new Store();
        int indexStore;

        do {
            System.out.println("Selecciona la tienda que quiere modificar.");
            listFiles(stores);
            Scanner scan = new Scanner(System.in);

            String storeSelected = scan.nextLine();
            indexStore = Integer.parseInt(storeSelected) - 1;

            if (indexStore < stores.size() && indexStore >= 0) {
                storeToManage = stores.get(indexStore);
                System.out.println("Has seleccionado la tienda " + storeToManage.getName() + " en " + storeToManage.getTown() + ".");
            }
        } while (indexStore > stores.size() && indexStore <= 0);

    }

    public static void createStore() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Introduce el nombre de la tienda.");
        String storeName = scan.nextLine();
        System.out.println("Introduce la ciudad donde se abrirá la tienda.");
        String storeTown = scan.nextLine();

        Store tempStore = new Store(storeName, storeTown);
        stores.add(tempStore);
    }

    public static void createCustomer() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Introduce el nombre de cliente.");
        String customerName = scan.nextLine();
        System.out.println("Introduce los apellidos del cliente.");
        String customerSurname = scan.nextLine();
        System.out.println("Introduce el email del cliente.");
        String customerEmail = scan.nextLine();

        Customer tempCustomer = new Customer(customerEmail, customerName, customerSurname);
        customers.add(tempCustomer);
    }

    public static void createEmployee() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Introduce el nombre de cliente.");
        String employeeName = scan.nextLine();
        System.out.println("Introduce los apellidos del cliente.");
        String employeeSurname = scan.nextLine();

        Employee newEmployee = new Employee(employeeName, employeeSurname);
        storeToManage.getEmployees().add(newEmployee);

    }

    public static void createItem() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Introduce el id del producto.");
        String itemId = scan.nextLine();
        System.out.println("Introduce la descripción del producto.");
        String itemDescription = scan.nextLine();
        System.out.println("Introduce el precio del producto.");
        String itemPrice = scan.nextLine();
        System.out.println("Introduce la cantidad de unidades de producto.");
        String itemAmount = scan.nextLine();

        Item newItem = new Item(itemId, itemDescription, Float.parseFloat(itemPrice), Integer.parseInt(itemAmount));
        storeToManage.getItems().add(newItem);

    }

    public static void removeStore() {
        System.out.println("Selecciona la tienda que quiere eliminar.");
        listFiles(stores);

        Scanner scan = new Scanner(System.in);

        String storeSelected = scan.nextLine();
        int indexStore = Integer.parseInt(storeSelected) - 1;

        if (indexStore < stores.size() && indexStore >= 0) {
            String nameStore = stores.get(indexStore).getName();
            String townStore = stores.get(indexStore).getTown();
            stores.remove(indexStore);
            System.out.println("Se ha eliminado la tienda " + nameStore + " en " + townStore + ".");
        } else {

        }

    }

    public static void removeCustomer() {
        System.out.println("Selecciona la tienda que quiere eliminar.");
        listFiles(customers);

        Scanner scan = new Scanner(System.in);

        String customerSelected = scan.nextLine();
        int indexCustomer = Integer.parseInt(customerSelected) - 1;

        if (indexCustomer < customers.size() && indexCustomer >= 0) {
            String nameCustomer = customers.get(indexCustomer).getName();
            String surnameCustomer = customers.get(indexCustomer).getSurname();
            String emailCustomer = customers.get(indexCustomer).getEmail();
            customers.remove(indexCustomer);
            System.out.println("Se ha eliminado el cliente " + nameCustomer
                    + surnameCustomer + " con email " + emailCustomer + ".");
        } else {

        }

    }

    public static void removeEmployee() {
        if (storeToManage.getEmployees().isEmpty()) {
            System.out.println("No hay ningún trabajador registrado.");
        } else {
            System.out.println("Selecciona el empleado que quiere eliminar.");
            listFiles(storeToManage.getEmployees());
            
            Scanner scan = new Scanner(System.in);

            String employeeSelected = scan.nextLine();
            int indexEmployee = Integer.parseInt(employeeSelected) - 1;

            if (indexEmployee < storeToManage.getEmployees().size() && indexEmployee >= 0) {
                String nameEmployee = storeToManage.getEmployees().get(indexEmployee).getName();
                String surnameEmployee = storeToManage.getEmployees().get(indexEmployee).getSurname();
   
                storeToManage.getEmployees().remove(indexEmployee);
                System.out.println("Se ha eliminado el cliente " + nameEmployee
                        + surnameEmployee + ".");
            } else {

            }
        }
    }
    
    public static void removeItem() {
        if (storeToManage.getItems().isEmpty()) {
            System.out.println("No hay ningún producto registrado.");
        } else {
            System.out.println("Selecciona el producto que quiere eliminar.");
            listFiles(storeToManage.getItems());
            
            Scanner scan = new Scanner(System.in);

            String itemSelected = scan.nextLine();
            int indexItem = Integer.parseInt(itemSelected) - 1;

            if (indexItem < storeToManage.getItems().size() && indexItem >= 0) {
                String idItem = storeToManage.getItems().get(indexItem).getId();
                String descriptionItem = storeToManage.getItems().get(indexItem).getDescription();
                float priceItem = storeToManage.getItems().get(indexItem).getPrice();
                int amountItem = storeToManage.getItems().get(indexItem).getAmount();
   
                storeToManage.getItems().remove(indexItem);
                System.out.println("Se han eliminado todas las unidades ("+ amountItem +")del producto con id: " + idItem
                        +". Descripción de producto eliminado: " + descriptionItem);
            } else {

            }
        }
    }

    public static void removeAllData() {
        System.out.println("Está seguro de que quiere borrar todos los datos del fichero?(Y/N)");
        Scanner scan = new Scanner(System.in);

        String option = scan.nextLine();

        if (option.equalsIgnoreCase("y") || option.equalsIgnoreCase("yes")) {
            System.out.println("Se ha borrado todo el fichero.");
            franchise = new Franchise();

        } else if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {

        } else {
            System.out.println("Introduce una opción correcta");
            removeAllData();
        }
    }
    
    public static void backup(){
        try {
            String pathFile = franchiseFile.getAbsolutePath();
            System.out.println("pathFile: " + pathFile);
            File backupFile = new File("c:/users/erifc/desktop/franchiseFile.backup");
            FileOutputStream fos = new FileOutputStream(backupFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(franchise);
            
            oos.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
