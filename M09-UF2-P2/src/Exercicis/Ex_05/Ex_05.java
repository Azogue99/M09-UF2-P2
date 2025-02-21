package Exercicis.Ex_05;

public class Ex_05 {
    public static void main(String[] args) {
        // 1) Creem la sala d'espera amb capacitat 3
        SalaEspera sala = new SalaEspera(3);

        // 2) Creem i llancem el fil Doctor
        Doctor doctor = new Doctor("Dr. Strange", sala);
        Thread filDoctor = new Thread(doctor, "FilDoctor");
        filDoctor.start();

        // 3) Creem alguns pacients, normals i urgents
        Pacient p1 = new Pacient("Pacient1", false, sala);
        Pacient p2 = new Pacient("Pacient2", true,  sala);  // urgent
        Pacient p3 = new Pacient("Pacient3", false, sala);
        Pacient p4 = new Pacient("Pacient4", false, sala);
        Pacient p5 = new Pacient("Pacient5", true,  sala);  // urgent
        Pacient p6 = new Pacient("Pacient6", false, sala);

        // 4) Llançar cada pacient en un fil separat
        new Thread(p1, "FilPacient1").start();
        new Thread(p2, "FilPacient2").start();
        new Thread(p3, "FilPacient3").start();
        new Thread(p4, "FilPacient4").start();
        new Thread(p5, "FilPacient5").start();
        new Thread(p6, "FilPacient6").start();

        // 5) En aquest exemple, esperem uns segons i després tanquem el Doctor
        try {
            Thread.sleep(4000);  // Podem canviar el temps o eliminar-lo
        } catch (InterruptedException e) {
            System.out.println("Error en el sleep");
        }

        // Indiquem que el doctor acabi (no és estrictament necessari, però
        // així finalitzem l'execució de manera controlada)
        doctor.acabarTorn();

        // Notifiquem el doctor si està esperant
        synchronized (sala) {
            sala.notifyAll();
        }

        // Esperem que el fil del doctor acabi
        try {
            filDoctor.join();
        } catch (InterruptedException e) {
            System.out.println("Error en el join");
        }

        System.out.println("--- Fi de l'execució ---");
    }
}
