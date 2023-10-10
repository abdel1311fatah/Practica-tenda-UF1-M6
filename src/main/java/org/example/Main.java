package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("1. Demanar dades del producte i insertar-les al fitxer, mantenint els valors anteriors.\n" +
                "2. Mostrar el preu d'un determinat producte.\n" +
                "3. Modificar el preu d'un determinat producte.\n" +
                "4. Mostrar tots els productes.\n" +
                "5. Esborrar un determinat producte.\n" +
                "6. Sortir.");

        ArrayList<Producte> productes = new ArrayList<>();
        int opcio = 0;

        FileOutputStream fos = new FileOutputStream("productes.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        FileInputStream fis = new FileInputStream("productes.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        do {
            opcio = obtindreInt("Que vols fer? ");
            if (opcio == 1) {
                int c1 = obtindreInt("Quants productes vols insertar? ");
                for (int i = 0; i < c1; i++) {
                    Producte producte = new Producte();
                    producte.setPreu(obtindreDouble("Preu: "));
                    producte.setNom(obtindreString("Nom: "));
                    producte.setId(obtindreInt("Id: "));
                    productes.add(producte);
                    oos.writeObject(producte);
                    System.out.println("--------------------------------------");
                }
            } else if (opcio == 2) {

                int c1 = obtindreInt("Id del producte que vols veure: ");
                boolean trobat = false;

                for (Producte producte : productes) {
                    producte = (Producte) ois.readObject();
                    if (producte.getId() == c1) {
                        System.out.println("Producte amb el nom: " + producte.getNom() + " amb el id: " + producte.getId() + " : ");
                        System.out.println(producte.getPreu());
                        System.out.println("-----------------------------------------------------------------------------------");
                        trobat = true;
                        break; // per a que no recorreixi tot el arraylist i nomes en printi un
                    }
                }

                if (!trobat) {
                    System.out.println("No s ha trobat cap producte");
                }

            } else if (opcio == 3) {

                int c1 = obtindreInt("Id del producte que vols cambiar el preu: ");
                boolean trobat = false;

                oos.close();

                for (Producte producte : productes) {
                    producte = (Producte) ois.readObject();
                    if (producte.getId() == c1 && !trobat) {
                        System.out.println("Producte amb el nom: " + producte.getNom() + " amb el id: " + producte.getId());
                        System.out.println("Preu vell: " + producte.getPreu());
                        System.out.println("-----------------------------------------------------------------------------------");
                        producte.setPreu(obtindreDouble("Introdueix el nou preu del producte amb el id " + producte.getId()));
                        System.out.println("Preu nou: " + producte.getPreu());
                        oos.writeObject(producte);

                        trobat = true;
                    }
                }

                if (!trobat) {
                    System.out.println("No s ha trobat cap producte");
                }

            } else if (opcio == 4) {
                for (Producte producte : productes) {
                    producte = (Producte) ois.readObject();
                    System.out.println(producte.toString());
                    System.out.println("------------------------------------------------------------------------------------");
                }
            } else if (opcio == 5) {

                int c1 = obtindreInt("Id del producte que vols borrar ");
                boolean trobat = false;

                for (Producte producte : productes) {
                    producte = (Producte) ois.readObject();
                    if (producte.getId() == c1 && !trobat) {
                        System.out.println("Producte amb el nom: " + producte.getNom() + " amb el id: " + producte.getId() + " i preu: " + producte.getPreu());
                        System.out.println("-----------------------------------------------------------------------------------");
                        producte.setPreu(null);
                        producte.setId(-1);
                        producte.setNom(null);
                        productes.remove(producte);
                        oos.writeObject(producte);

                        trobat = true;
                    }
                }

                if (!trobat) {
                    System.out.println("No s ha trobat cap producte");
                }

            } else if (opcio == 6) {
                System.out.println("Has sortit de la tenda");
            } else if (opcio == 7) {
                Producte producte = new Producte();
                producte.setPreu(43.0);
                producte.setNom(("a"));
                producte.setId((1));
                productes.add(producte);
                oos.writeObject(producte);
                Producte producte2 = new Producte();
                producte2.setPreu(44.0);
                producte2.setNom(("b"));
                producte2.setId((2));
                productes.add(producte2);
                oos.writeObject(producte2);
            } else {
                System.out.println("Introdueix un número valid, ha de ser del 1 al 6");
            }
        } while (opcio != 6);

        oos.close();
        fos.close();
        ois.close();
        fis.close();
    }

    public static String obtindreString(String text) {
        Scanner sc = new Scanner(System.in);

        System.out.print(text);
        String text_usuari = sc.nextLine();

        return text_usuari;
    }

    public static Character obtindreChar(String text) {
        Scanner sc = new Scanner(System.in);

        System.out.print(text);
        Character text_usuari = sc.nextLine().charAt(0);

        return text_usuari;
    }

    public static double obtindreDouble(String text) {
        boolean TipusCorrecte;
        Scanner sc = new Scanner(System.in);
        double num_usuari = 0.0;

        do {
            System.out.print(text);
            TipusCorrecte = sc.hasNextDouble();
            if (!TipusCorrecte) {
                sc.nextLine();
                System.out.println("Error Valor no valid");
            } else {
                num_usuari = sc.nextDouble();
            }
        } while (!TipusCorrecte);
        return num_usuari;
    }

    public static int obtindreInt(String text) {

        boolean TipusCorrecte;
        Scanner sc = new Scanner(System.in);
        int num_usuari = 0;

        do {
            System.out.print(text);
            TipusCorrecte = sc.hasNextInt();
            if (!TipusCorrecte) {
                sc.nextLine();
                System.out.println("Error: Valor no válido");
            } else {
                num_usuari = sc.nextInt();
            }
        } while (!TipusCorrecte);

        return num_usuari;
    }

}