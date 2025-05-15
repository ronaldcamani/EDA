public class Recursividad {
    void repetir() {
        repetir();
    }
    
    public static void main(String[] ar) {
        Recursividad re = new Recursividad();
        re.repetir();
    }
} 