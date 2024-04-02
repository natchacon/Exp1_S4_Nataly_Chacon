/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cl.duoc.tarea4;

import java.util.Scanner;

/**
 *
 * @author Nataly Chacón
 */
public class TeatroMoro {

    static double DESCUENTO_ESTUDIANTE = 0.9;
    static double DESCUENTO_TERCERA_EDAD = 0.85;
    static String[] opcionesMenuPrincipal = {"Comprar entrada","Salir"};
    static String[] tiposEntrada = {"VIP","Platea baja","Platea Alta","Palcos"};
    static int[] cantidadAsientos = {10,15,15,5};
    static int[] tarifaPublicoGeneral = {25000,19000,11000,7200};

    
    public static double obtenerTarifa(int posicionTipoEntradaAComprar, boolean esEstudiante, boolean esTerceraEdad){
     int tarifa = tarifaPublicoGeneral[posicionTipoEntradaAComprar];
     if(esTerceraEdad){
      return tarifa * DESCUENTO_TERCERA_EDAD; // 15% de descuento
     } else if (esEstudiante){
         return tarifa * DESCUENTO_ESTUDIANTE; //10% de descuento
     }
     return tarifa;
    }
       
    public static void imprimirTipoDeEntrada(){
        System.out.print("\r\rAsientos del Teatro Moro:");
        for(int i = 0; i< tiposEntrada.length; i++){                        
            System.out.println("\r\r" +tiposEntrada[i]);
            for(int j = 0; j< cantidadAsientos[i]; j++){
                System.out.print("\t" + "Asiento_" + (j+1));
            }
            
        }
    }
    
    public static boolean edadCorrecta(String edad){
     try{
        Integer.valueOf(edad);
        return true;
     }
     catch(Exception e){
        System.out.println("Ingrese su edad en formato numero entero");
        return false;
     }
    }
    
    public static void comprarEntrada(){
        Scanner leer = new Scanner(System.in);
        String tipoEntradaAComprar = null;
        double tarifaACobrar = 0;
        String edad = null;
        int posicionTipoEntradaAComprar = -1;
        boolean continuarCiclo = true;
        boolean esTerceraEdad = false;
        imprimirTipoDeEntrada();
        do {
            System.out.println("\r\rIngrese el tipo de la entrada que desea: (VIP, Platea baja, Platea Alta, Palcos)");
            tipoEntradaAComprar = leer.nextLine();
            switch(tipoEntradaAComprar){
                case "VIP":
                    posicionTipoEntradaAComprar = 0;
                    continuarCiclo = false;
                    break;
                case "Platea baja":
                    posicionTipoEntradaAComprar = 1;
                    continuarCiclo = false;
                    break;
                case "Platea Alta":
                    posicionTipoEntradaAComprar = 2;
                    continuarCiclo = false;
                    break;
                case "Palcos":
                    posicionTipoEntradaAComprar = 3;
                    continuarCiclo = false;
                    break;
                default:
                    System.out.println("Valor ingresado " + tipoEntradaAComprar + " no encontrado");
                    continuarCiclo = true;
            }
        } while(continuarCiclo);
        
        System.out.println("Ingrese su edad:");        
        do {
            edad = leer.next();
        }
        while(!edadCorrecta(edad));
        
        esTerceraEdad = Integer.valueOf(edad)>=65;
        boolean esEstudiante = false;
        if(!esTerceraEdad){ // Se consulta si es estudiante solo si no es tercera edad, considerando tercera edad mas de 65 años
            System.out.println("¿Es estudiante? si o no");            
            do {
                String respuestaEsEstudiante = leer.next();
                if(respuestaEsEstudiante.equals("si")){
                    tarifaACobrar += obtenerTarifa(posicionTipoEntradaAComprar, true, esTerceraEdad);
                    continuarCiclo = false;
                    esEstudiante = true;
                } else if(respuestaEsEstudiante.equals("no")) {
                    tarifaACobrar += obtenerTarifa(posicionTipoEntradaAComprar, false, esTerceraEdad);
                    continuarCiclo = false;
                    esEstudiante = false;
                } else {
                    System.out.println("Debe escribir si o no");
                    continuarCiclo = true;
                    esEstudiante = false;
                }
            } while(continuarCiclo);
        } else {
            tarifaACobrar += obtenerTarifa(posicionTipoEntradaAComprar, false, esTerceraEdad);
        }
     
     System.out.println("Compró una entrada " + tipoEntradaAComprar);
     System.out.println("Precio base " + tarifaPublicoGeneral[posicionTipoEntradaAComprar]);
     if(esTerceraEdad){
        System.out.println("Descuento Aplicado 15% por Tercera Edad");
     }
     else if(esEstudiante){
        System.out.println("Descuento Aplicado 10% por Estudiante");
     }     
     System.out.println("Total a Pagar $" + tarifaACobrar);
     System.out.println("Si desea realizar otra compra vuelva a seleccionar opcion 1 desde el menu");
    }
    
    
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        String opcionMenuPrincipal = null;
        boolean mostrarMenu = true;
        do {
            if(mostrarMenu){
                System.out.println("\r");            
                System.out.println("...::: Menu :::...");
                for(int i=0; i<opcionesMenuPrincipal.length;i++){
                System.out.println((i+1) + ") " + opcionesMenuPrincipal[i]);
                }
            }
            System.out.println("Ingrese el numero de su opcion de menú");
            opcionMenuPrincipal = leer.nextLine();
            if(opcionMenuPrincipal!=null && opcionMenuPrincipal.equals("1")){
                comprarEntrada();
                mostrarMenu = true;
            }
            else if(opcionMenuPrincipal!=null && !opcionMenuPrincipal.equals("2")){
                System.out.println("Debe ingresar 1 o 2");
                mostrarMenu = false;
            } 
        }
        while(!opcionMenuPrincipal.equals("2"));//Se mantiene en el menu principal mientra no seleccione opcion 2) Salir
    
    }
}
