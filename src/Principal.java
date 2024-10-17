import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ApiMoneda api = new ApiMoneda();
        while (true){
            System.out.println("*******************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda \n");
            System.out.println("""
                    1. Dólar => Peso argentino
                    2. Peso argentino => Dólar
                    3. Dólar => Real brasileño
                    4. Real brasileño => Dólar
                    5. Dólar => Peso colombiano
                    6. Peso colombiano => Dólar
                    7. Salir
                    """);
            System.out.println("Elija un opcion valida:  ");
            System.out.println("*******************************");
            int busqueda=lectura.nextInt();

            if(busqueda==7){
                System.out.println("Gracias por usar este programa");
                break;
            }

            String base = "";
            String cambio= "";

            switch (busqueda) {
                case 1 -> {
                    base = "USD";
                    cambio = "ARS"; // Peso argentino
                }
                case 2 -> {
                    base = "ARS";
                    cambio = "USD"; // Dólar
                }
                case 3 -> {
                    base = "USD";
                    cambio = "BRL"; // Real brasileño
                }
                case 4 -> {
                    base = "BRL";
                    cambio = "USD"; // Dólar
                }
                case 5 -> {
                    base = "USD";
                    cambio = "COP"; // Peso colombiano
                }
                case 6 -> {
                    base = "COP";
                    cambio = "USD"; // Dólar
                }
                default -> {
                    System.out.println("Opción no válida. Intente nuevamente.");
                    continue;
                }
            }
            try {
               Moneda moneda = api.buscarMoneda(base, cambio);
                System.out.printf("La tasa de cambio de %s a %s es: %.2f%n", moneda.base_code(), moneda.target_code(), moneda.conversion_rate());

                System.out.println("Ingrese el valor que deseas convertir: ");
                double cantidad = lectura.nextDouble();

                double resultado=cantidad*moneda.conversion_rate();
                System.out.printf("La conversión de %.2f %s a %s es: %.2f%n \n", cantidad, moneda.base_code(), moneda.target_code(), resultado);

            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        lectura.close();
    }
}
