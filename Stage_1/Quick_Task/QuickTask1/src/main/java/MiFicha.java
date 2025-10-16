import java.util.ArrayList;
import java.util.Scanner;

public class MiFicha {

    public static void main (String[] args) {

        String nombre = "Maria";
        // Utilizo wrapper (clase envoltorio que representa un tipo primitivo (int, boolean) como un objeto
        // para poder utilizar más adelante los métodos .getClass().getSimple sin que me de error)
        Integer edad = 39;
        Boolean estudiaAPIs = true;

        ArrayList<String> hobbies = new ArrayList<>();
        hobbies.add("Viajar");
        hobbies.add("Leer");
        hobbies.add("Bailar");

        System.out.println("Nombre:" + nombre);
        System.out.println("Edad:" + edad);
        System.out.println("¿Estudia automatización en APIs?:" + estudiaAPIs);
        System.out.println("Hobbies:" + hobbies);

        System.out.println("Nombre:" + nombre.getClass().getSimpleName());
        System.out.println("Edad:" + edad.getClass().getSimpleName());
        System.out.println("¿Estudia automatización en APIs?:" + estudiaAPIs.getClass().getSimpleName());
        System.out.println("Hobbies:" + hobbies.getClass().getSimpleName());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Hobby Favorito: ");
        String hobbyFavorito = scanner.nextLine();
        hobbies.add(hobbyFavorito);

        System.out.println("Total hobbies:" + hobbies.size());

        edad = edad + 1;
        System.out.println("Edad + 1: " + edad);

        scanner.close();
    }
}
