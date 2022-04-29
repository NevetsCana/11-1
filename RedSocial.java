/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redsocial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SERVER_PC
 */
public class RedSocial {

    /**
     * @param args the command line arguments
     */
    static boolean isLogged = false;
    static String usuario; 
    static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        while (opcion != 3) {
            if (!isLogged) {
                MenuPrincipal();
            } else {
                MenuInicio();
            }
            System.out.print("Ditige una opcion:  ");
            opcion = leer.nextInt();
            gateWay(opcion);
        }
    }

    private static void MenuPrincipal() {
        System.out.println("1-iniciar sesion");
        System.out.println("2-Registrar Usuario");
    }

    private static void MenuInicio() {
        System.out.println("1-cambiar contrseña");
        System.out.println("2-enviar Solicitud");
        System.out.println("3-ver solicitudes");
        System.out.println("4-eliminar cuenta");
        System.out.println("5-cerrar sesion");
    }

    private static void gateWay(int opcion) {
        if (isLogged) {
            switch (opcion) {

            }
        } else {
            if (opcion == 1) {
                iniciarSesion();
            } else {
               registrarUsuario();     
            }
        }
    }

    private static void iniciarSesion() {
        String username, contra; 
        System.out.print("Usuario:");
        username = leer.next();
        System.out.print("Contraseña:");
        contra = leer.next();
        String ruta ="c:\\sesiones\\"+username+".txt";
        File file = new File(ruta);
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(ruta);
                BufferedReader br = new BufferedReader(fr);
                String [] infoUser = br.readLine().split("/");
                loading();
                if (infoUser[1].equals(contra)) {
                    isLogged = true; 
                    usuario = username; 
                    System.out.println("successfully");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RedSocial.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RedSocial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    private static void registrarUsuario() {
        String nombre,contra,username;
        System.out.println("digite nombre:");
        nombre = leer.next();
        System.out.println("digite el usuario:");
        username = leer.next();
        System.out.println("contraseña");
        contra = leer.next();
        toRegistrar(nombre,contra,username);
    }

    private static void toRegistrar(String nombre, String contra, String username) {
        String ruta = "c:\\sesiones\\"+username+".txt";
        File file = new File(ruta);
        if (!file.exists()) {
            try {
                FileWriter fw = new FileWriter(ruta);
                fw.write( nombre+ "/" +contra+ "/" +username);
                fw.close();
            } catch (IOException ex) {
            }
        }
    }

    private static void loading() {
        for (int i = 0; i < 10; i++) {
            System.out.print("▓");
            segundo(500);
        }
        segundo(1000);
        System.out.println("cargado!!!");
    }

    private static void segundo(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            Logger.getLogger(RedSocial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
