package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ejecutarMenu();
    }
    public static Object[][] matrizProductos() {
        Object[][]productos = new Object[10][3];
        return productos;
    }
    public static void mostrarMenu() {
        System.out.println("\nTienda");
        System.out.println("1. Agregar producto/existencias");
        System.out.println("2. Restar producto");
        System.out.println("3. Consultar disponibilidad");
        System.out.println("4. Listar productos");
        System.out.println("5. Salir");
        System.out.print("\nIngrese una opcion: ");
    }
    public static int leerOpcion() {
        int opcion = 0;
        while (true) {
            Scanner scanner = crearScanner();
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 5) {
                    break;
                } else {
                    System.out.print("Opción inválida. Intente nuevamente: ");
                }
            } else {
                System.out.print("Entrada no válida. Ingrese un número: ");
                scanner.next();
            }
        }

        return opcion;
    }
    public static Scanner crearScanner() {
        return new Scanner(System.in);
    }
    public static void ejecutarOpcion(int opcion,Object[][] matrizProductos) {


        switch (opcion) {
            case 1:
                agregarProducto(matrizProductos,preguntarNombre(),preguntarId(),preguntarCantidad());
                break;
            case 2:
                restarCantidadProducto(matrizProductos,preguntarNombre(),preguntarId(),preguntarCantidad());
                break;
            case 3:
                consultarDisponibilidad(matrizProductos, preguntarId());
                break;
            case 4:
                listarProductos(matrizProductos);
                break;
            case 5:
                System.out.println("Saliendo del sistema. ¡Hasta luego!");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
    public static void ejecutarMenu(){
        Object[][] matrizProductos = matrizProductos();
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            ejecutarOpcion(opcion,matrizProductos);
        } while (opcion != 5);
    }
    public static int preguntarId() {
        int idProducto = 0;
        while (true) {
            System.out.print("Ingrese un id del producto: ");
            Scanner scanner = crearScanner();
            if (scanner.hasNextInt()) {
                idProducto = scanner.nextInt();
                return idProducto;
            } else {
                System.out.println("Ingrese un id de producto valido.");
                scanner.next();
            }
        }
    }
    public static int preguntarCantidad(){
        int cantidad = 0;
        while (true) {
            System.out.print("Ingrese la cantidad del producto: ");
            Scanner scanner = crearScanner();
            if (scanner.hasNextInt()) {
                cantidad = scanner.nextInt();
                if (cantidad >= 0)
                    return cantidad;
                else {
                    System.out.println("Ingrese una cantidad valida.");
                    scanner.next();
                }
            }else {
                System.out.println("Ingrese un cantidad de producto valida.");
                scanner.next();
            }
        }
    }
    public static String preguntarNombre() {
        String nombreProducto = "";
        System.out.print("Ingrese el nombre del producto: ");
        Scanner scanner = crearScanner();
        nombreProducto = scanner.nextLine();
        return nombreProducto;
    }
    public static boolean agregarProducto(Object[][] productos, String nombreProducto, int idProducto, int cantidadProducto) {
        for (int i = 0; i < productos.length ; i++) {
            if (productos[i][0] != null) {
                if (productos[i][0].equals(nombreProducto) && productos[i][1].equals(idProducto)) {
                    agregarCantidadProducto(productos,nombreProducto,idProducto,cantidadProducto,i);
                    return true;
                }
            }else {
                agregarProductoNuevo(productos,nombreProducto,idProducto,cantidadProducto,i);
                return true;
            }
        }
        return false;
    }
    public static int agregarCantidadProducto(Object[][] productos, String nombreProducto, int idProducto,int cantidadProducto,int fila) {
        productos[fila][2] = (int) productos[fila][2] + cantidadProducto;
        int cantidadActual = (int) productos[fila][2];
        System.out.println("Han sido agregadas " + cantidadProducto + " existensias.");
        return cantidadActual;

    }
    public static void agregarProductoNuevo(Object[][] productos, String nombreProducto, int idProducto, int cantidadProducto,int fila) {
        try {
            productos[fila][0] = nombreProducto;
            productos[fila][1] = idProducto;
            productos[fila][2] = cantidadProducto;
            System.out.println("El producto " + nombreProducto + " ha sido agregado.");
        }catch (Exception e) {
            System.out.println("El producto " + nombreProducto + " no se puede agregar debido al Error " + e.getMessage());
        }
    }
    public static boolean restarCantidadProducto(Object[][] productos, String nombreProducto, int idProducto, int cantidadProductoRestar) {
        for (int i = 0; i < productos.length ; i++) {
            if (productos[i][0] != null) {
                if (productos[i][0].equals(nombreProducto) && productos[i][1].equals(idProducto)) {
                    if (validarCantidadRestar(productos,nombreProducto,idProducto,cantidadProductoRestar,i)) {
                        productos[i][2] = (int) productos[i][2] - cantidadProductoRestar;
                        System.out.println("Han sido eliminadas " + cantidadProductoRestar + " existensias.");
                        return true;
                    }else {
                        System.out.println("No es posible eliminar mas existencias de las que hay.");
                        return false;
                    }
                }else {
                    System.out.println("El producto no se ha encotrado.");
                    return false;
                }
            }else{
                break;
            }
        }
        return false;
    }
    public static boolean validarCantidadRestar (Object[][] productos, String nombreProducto, int idProducto, int cantidadProductoRestar, int fila) {
        return (int) productos[fila][2] >= cantidadProductoRestar;
    }
    public static int calcularDisponibilidad(Object[][] productos,int idProducto) {
        for (int i = 0; i < productos.length ; i++) {
            if (productos[i][0] != null) {
                if (productos[i][1].equals(idProducto)) {
                    return (int) productos[i][2];
                }
            } else {
                break;
            }
        }
        return 0;
    }
    public static void consultarDisponibilidad(Object[][] productos,int idProducto) {
        if (calcularDisponibilidad(productos,idProducto) == 0) {
            System.out.println("No se han encontrado existencias del producto.");
        }else {
            System.out.println("Hay " + calcularDisponibilidad(productos,idProducto) + " existencias del producto.");
        }
    }
    public static void listarProductos(Object[][] productos) {
        for (int i = 0; i < productos.length ; i++) {
                if (productos[i][0] != null) {
                    System.out.println("/  Id: " + productos[i][0] + "  /  Nombre: " + productos[i][1] + "  /  Cantidad: " + productos[i][2]);
                }
                else {
                    break;
                }
            }
    }
}