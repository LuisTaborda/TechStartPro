package com.olist.desafio.olist.desafio.controller;

import com.olist.desafio.olist.desafio.entity.Category;
import com.olist.desafio.olist.desafio.entity.Product;
import com.olist.desafio.olist.desafio.utils.StateApp;
import com.olist.desafio.olist.desafio.filter.ProductFilter;
import com.olist.desafio.olist.desafio.service.CategoryService;
import com.olist.desafio.olist.desafio.service.ProductService;
import com.olist.desafio.olist.desafio.vo.ProductVO;
import org.apache.log4j.*;

import java.io.IOException;
import java.util.*;

public class AppController {

    private static ProductService productService;
    private static CategoryService categoryService;
    private static StateApp stateApp;
    private static List<Product> productsList = new ArrayList<>();
    private static List<Category> categoriesList = new ArrayList<>();

    public static void run() throws IOException {

        List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
        loggers.add(LogManager.getRootLogger());
        for (Logger logger : loggers) {
            logger.setLevel(Level.OFF);
        }

        System.out.println("\nInitialize...");
        productService = new ProductService();
        categoryService = new CategoryService();
        stateApp = StateApp.EXECUTE;

        refreshListProducts();
        refreshListCategories();
        System.out.println("TECH START PRO\n");
        do {
            switch (stateApp) {
                case EXECUTE: {

                    Date date = new Date();
                    System.out.println(String.format("TOTAL PRODUCTS (%d)\tTOTAL CATEGORIES (%d)\t%s\n", productsList.size(), categoriesList.size(), date.toString()));
                    controllerActions();
                    System.out.println("");
                }
                break;
                case EXIT:
                    break;
            }

        } while (!stateApp.equals(StateApp.EXIT));
    }

    public static void controllerActions() throws IOException {

        System.out.println("1. Add product by id");
        System.out.println("2. Read product by id");
        System.out.println("3. Read all products");
        System.out.println("4. Updated product by id");
        System.out.println("5. Delete product by id");
        System.out.println("6. Import CSV File ");
        System.out.println("7. Find by filter ");
        System.out.println("8. Exit \n");
        System.out.print("Enter key for : ");

        Scanner in = new Scanner(System.in);
        int action = in.nextInt();

        switch (action) {
            case 1:
                createProduct();
                break;
            case 2:
                showProductById();
                stateApp = StateApp.EXECUTE;
                break;
            case 3:
                showAllProducts();
                stateApp = StateApp.EXECUTE;
                break;
            case 4:
                updateProduct();
                stateApp = StateApp.EXECUTE;
                break;
            case 5:
                removeProduct();
                stateApp = StateApp.EXECUTE;
                break;
            case 6:
                importCSVFile();
                stateApp = StateApp.EXECUTE;
                break;
            case 7:
                findByFilter();
                stateApp = StateApp.EXECUTE;
                break;
            case 8:
                stateApp = StateApp.EXIT;
                break;
            default:
                System.out.println("Operation invalid, check your key.");
                break;
        }
    }

    public static void showAllProducts() {

        System.out.print("\nList Products");
        System.out.println(productsList.toString());

    }

    public static void showProductById() {

        System.out.print("\nEnter key id : ");
        Scanner in = new Scanner(System.in);

        Long id = in.nextLong();
        Product product = productService.findId(id);

        if (product != null) {
            System.out.println(product.toString());
        } else System.out.println("please verify id - Product not found!");

    }

    public static void createProduct() {

        Scanner in = new Scanner(System.in);
        ProductVO vo = new ProductVO();
        System.out.print("\nEnter key name product : ");
        vo.setName(in.nextLine());
        System.out.print("\nEnter key description product : ");
        vo.setDescription(in.nextLine());

        Set<Category> categories = new HashSet<>();
        boolean addCategory = true;

        do {
            System.out.println("Want to add categories? YES or NO");
            String op = in.nextLine();
            if (op.toUpperCase().equals("YES")) {
                System.out.print("\nEnter key category Name : ");
                vo.setCategory(in.nextLine());
                categories.add(categoryService.findName(vo.getCategory()));
            }
            if (op.toUpperCase().equals("NO")) {
                addCategory = false;
            }

        } while (addCategory);

        System.out.print("\nEnter key price product : ");
        vo.setPrice(in.nextDouble());

        Product product = new Product();
        product.setName(vo.getName());
        product.setDescription(vo.getDescription());
        product.setPrice(vo.getPrice());
        product.setCategorias(categories);

        productService.add(product);
        refreshListProducts();
    }

    public static void updateProduct() {
        Scanner in = new Scanner(System.in);
        Product product = null;
        ProductVO vo = new ProductVO();
        do {
            System.out.print("Enter key id:  ");
            vo.setId(in.nextLong());
            product = productService.findId(vo.getId());

        } while (product == null);

        System.out.println(String.format("Product with name %s found!", product.getName()));
        in = new Scanner(System.in);

        System.out.println("Do you want to change the name? YES or NO");

        if (in.nextLine().toUpperCase().equals("YES")) {
            System.out.print("\nEnter key name product : ");
            in = new Scanner(System.in);
            product.setName(in.nextLine());
        }

        System.out.println("Do you want to change the description? YES or NO");
        if (in.nextLine().toUpperCase().equals("YES")) {
            System.out.print("\nEnter key description product : ");
            product.setDescription(in.nextLine());
        }

        Set<Category> categories = product.getCategorias();
        System.out.println("Do you want to change the categories? YES or NO");
        if (in.nextLine().toUpperCase().equals("YES")) {
            System.out.println("Create a new list for a replacement");
            boolean addCategory = true;
            do {
                System.out.print("\nEnter key category Name : ");
                vo.setCategory(in.nextLine());
                categories.add(categoryService.findName(vo.getCategory()));
                System.out.println("Do you still want to add categories? YES or NO");
                String op = in.nextLine();
                if (op.equals("NO")) {
                    addCategory = false;
                }

            } while (addCategory);
        }
        product.setCategorias(categories);

        System.out.println("Do you want to change the price? YES or NO");
        if (in.nextLine().toUpperCase().equals("YES")) {
            System.out.print("\nEnter key price product : ");
            product.setPrice(in.nextDouble());
        }


        productService.update(product);
        refreshListProducts();
    }

    public static void findByFilter() {
        Scanner in = new Scanner(System.in);
        ProductFilter filter = new ProductFilter();
        in = new Scanner(System.in);

        System.out.println("Do you want to change the name? YES or NO");

        if (in.nextLine().toUpperCase().equals("YES")) {
            System.out.print("\nEnter key name product : ");
            filter.setName(in.nextLine());
        }

        System.out.println("Do you want to change the description? YES or NO");
        if (in.nextLine().toUpperCase().equals("YES")) {
            System.out.print("\nEnter key description product : ");
            filter.setDescription(in.nextLine());
        }

        System.out.println("Do you want to change the categories? YES or NO");
        if (in.nextLine().toUpperCase().equals("YES")) {
            System.out.println("Create a new list for a replacement");
            boolean addCategory = true;
            do {
                System.out.print("\nEnter key category Name : ");
                String cat_name = in.nextLine();
                filter.getCategory().add(categoryService.findName(cat_name));
                System.out.println("Do you still want to add categories? YES or NO");
                String op = in.nextLine();
                if (op.toUpperCase().equals("NO")) {
                    addCategory = false;
                }

            } while (addCategory);
        }

        System.out.println("Do you want to change the price? YES or NO");
        if (in.nextLine().toUpperCase().equals("YES")) {
            System.out.print("\nEnter key price product : ");
            filter.setPrice(in.nextDouble());
        }

        List<Product> ps = productService.findByFilter(filter);
        System.out.println(ps.toString());
        System.out.println("Enter any key to continue...");
        in.nextLine();
        refreshListProducts();
    }

    public static void removeProduct() {
        System.out.print("Enter key id:");
        Scanner in = new Scanner(System.in);
        Product product = null;
        do {
            Long id = in.nextLong();
            product = productService.findId(id);
            if (product == null) {
                System.out.print("id invalid, enter id again:");
            }
        } while (product == null);
        productService.delete(product);
        refreshListProducts();
    }

    public static void importCSVFile() throws IOException {

        System.out.print("Please enter path absolute file:");

        Scanner in = new Scanner(System.in);
        String pathAbsolute = in.nextLine();

        categoryService.addCategoryFileCSV(pathAbsolute);

    }

    public static void refreshListProducts() {
        productsList = productService.findByFilter(new ProductFilter());
    }

    public static void refreshListCategories() {
        categoriesList = categoryService.findAll();
    }
}
