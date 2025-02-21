package Exercicis.Ex_05;

import Exercicis.Utilities;

public class Ex_05 {
    public static void main(String[] args) {
        // Creació d'una sala d'espera amb capacitat per a 2 pacients (es pot modificar segons sigui necessari).
        SalaEspera sala = new SalaEspera(2);

        // Creem i iniciem el fil del doctor.
        Doctor doctor = new Doctor("Dr. Strange", sala);
        Thread filDoctor = new Thread(doctor, "HiloDoctor");
        filDoctor.start();

        // Creació d'una combinació de pacients (normals i urgents).
        Pacient p1 = new Pacient("Pacient1", false, sala); // Pacient normal.
        Pacient p2 = new Pacient("Pacient2", true, sala);  // Pacient urgent.
        Pacient p3 = new Pacient("Pacient3", false, sala); // Pacient normal.
        Pacient p4 = new Pacient("Pacient4", false, sala); // Pacient normal.
        Pacient p5 = new Pacient("Pacient5", true, sala);  // Pacient urgent.
        Pacient p6 = new Pacient("Pacient6", false, sala); // Pacient normal.

        // Llancem cada pacient en un fil separat per simular concurrència.
        Thread t1 = new Thread(p1, "HiloPacient1");
        Thread t2 = new Thread(p2, "HiloPacient2");
        Thread t3 = new Thread(p3, "HiloPacient3");
        Thread t4 = new Thread(p4, "HiloPacient4");
        Thread t5 = new Thread(p5, "HiloPacient5");
        Thread t6 = new Thread(p6, "HiloPacient6");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        try {
            // Esperem que tots els pacients finalitzin els seus intents.
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            Utilities.error("Error en esperar que els pacients acabin: " + e.getMessage());
        }
        Utilities.info("Tots els pacients han fet els seus intents.");

        // Esperem fins que la sala estigui buida abans d'acabar la jornada del doctor.
        while (!sala.estaBuida()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Utilities.error("Error durant l'espera de buidatge de la sala: " + e.getMessage());
            }
        }

        // Finalitzem la jornada del doctor.
        doctor.acabarTorn();
        synchronized (sala) {
            sala.notifyAll(); // Notifiquem perquè si hi ha fils bloquejats puguin continuar.
        }

        try {
            // Esperem que el doctor finalitzi el seu fil.
            filDoctor.join();
        } catch (InterruptedException e) {
            Utilities.error("Error en esperar que el doctor acabi: " + e.getMessage());
        }

        // Missatge de finalització de l'execució.
        Utilities.printHeader("--- Fi de l'execució ---");
    }
}
