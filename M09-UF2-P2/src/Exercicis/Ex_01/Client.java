package Exercicis.Ex_01;

public class Client implements Runnable {
    private String nomClient;
    private ZonaTeatre zona;
    private int numeroButaques;

    public Client(String nomClient, ZonaTeatre zona, int numeroButaques) {
        this.nomClient = nomClient;
        this.zona = zona;
        this.numeroButaques = numeroButaques;
    }

    @Override
    public void run() {
        // Intentar reservar
        boolean reservat = zona.reservar(numeroButaques);

        // Mostrar si s'ha pogut o no
        if (reservat) {
            System.out.println(nomClient + " ha reservat " + numeroButaques 
                               + " butaques a " + zona.getNomZona() + ".");
        } else {
            System.out.println(nomClient + " NO ha pogut reservar " + numeroButaques 
                               + " butaques a " + zona.getNomZona() + ".");
        }
    }
}
