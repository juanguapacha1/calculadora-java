package com.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class CalculadoraLiquidacion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenido a la Calculadora de Liquidación");
        System.out.println("Seleccione el tipo de trabajador:");
        System.out.println("1. Trabajador por días");
        System.out.println("2. Trabajador a tiempo completo");
        System.out.println("0. Salir");
        int opcion = scanner.nextInt();
        
        switch(opcion) {
            case 1:
            calcularLiquidacionPorDias(scanner);
                break;
            case 2:
            calcularLiquidacionTrabajadorTiempoCompleto(scanner);
                break;
            case 0:
            System.out.println("Saliendo del programa...");
            default:
                System.out.println("Opción no válida. Por favor seleccione 1 o 2.");
        }
        
        scanner.close();
    }
    
    private static void calcularLiquidacionPorDias(Scanner scanner) {
        System.out.println("Ingresa la fecha de inicio (DD/MM/AAAA) ");
        String fechaInicioStr = scanner.next();
        System.out.println("Ingresa la fecha de fin (DD/MM/AAAA) ");
        String fechaFinStr = scanner.next();
        System.out.println("Ingresa el sueldo diario ");
        double sueldoDiario = scanner.nextDouble();
        System.out.println("Ingresa el número de días que trabajas a la semana (1-6) ");
        int diasSemana = scanner.nextInt();
        System.out.println("Tiene auxilio de transporte (SI/NO)");
        String auxilioTransporte = scanner.next();
        
        boolean tieneAuxilioTransporte = auxilioTransporte.equalsIgnoreCase("SI");

        // Formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Convertir las cadenas de texto a objetos LocalDate
        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
        LocalDate fechaFin = LocalDate.parse(fechaFinStr, formatter);
        
        // Calcular los días totales trabajados
        long diasTotalesTrabajados = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        System.out.println("Días totales trabajados: " + diasTotalesTrabajados);

        // Calcular el salario mensual
        double salarioMensual = sueldoDiario * diasSemana * 4.33; // Promedio de semanas por mes

        // Calcular salarios pendientes
        double salarioPendiente = (diasTotalesTrabajados % 30) * sueldoDiario;
        System.out.println("Salario pendiente: " + salarioPendiente);
        
        // Calcular cesantías
        double cesantias = (diasTotalesTrabajados / 360.0) * salarioMensual;
        System.out.println("Cesantías: " + cesantias);
        
        // Calcular intereses sobre las cesantías
        double interesesCesantias = cesantias * 0.12 * (diasTotalesTrabajados / 365.0);
        System.out.println("Intereses sobre cesantías: " + interesesCesantias);
        
        // Calcular primas de servicios
        double primasServicios = (diasTotalesTrabajados / 360.0) * salarioMensual / 2;
        System.out.println("Primas de servicios: " + primasServicios);
        
        // Calcular vacaciones
        double vacaciones = (diasTotalesTrabajados / 720.0) * salarioMensual;
        System.out.println("Vacaciones: " + vacaciones);

        // Calcular auxilio de transporte
        double auxilioTransporteMensual = 140606; // Auxilio de transporte para 2024 (puede variar cada año)
        double totalAuxilioTransporte = tieneAuxilioTransporte ? (diasTotalesTrabajados / 30.0) * auxilioTransporteMensual : 0;
        System.out.println("Auxilio de transporte: " + totalAuxilioTransporte);

        // Calcular aportes a la seguridad social
        double pensionMensual = 0.04 * salarioMensual; // 4% del salario mensual
        double saludMensual = 0.04 * salarioMensual; // 4% del salario mensual
        double totalPension = pensionMensual * (diasTotalesTrabajados / 30.0);
        double totalSalud = saludMensual * (diasTotalesTrabajados / 30.0);
        System.out.println("Aportes a pensiones: " + totalPension);
        System.out.println("Aportes a salud: " + totalSalud);

        // Total Liquidación
        double totalLiquidacion = salarioPendiente + cesantias + interesesCesantias + primasServicios + vacaciones + totalAuxilioTransporte - totalPension - totalSalud;
        System.out.println("Total Liquidación: " + totalLiquidacion);
    }
    
    private static void calcularLiquidacionTrabajadorTiempoCompleto(Scanner scanner) {
        System.out.println("Ingresa la fecha de inicio (DD/MM/AAAA) ");
        String fechaInicioStr = scanner.next();
        System.out.println("Ingresa la fecha de fin (DD/MM/AAAA) ");
        String fechaFinStr = scanner.next();
        System.out.println("Ingresa el sueldo mensual ");
        double sueldoMensual = scanner.nextDouble();
        System.out.println("Tiene auxilio de transporte (SI/NO)");
        String auxilioTransporte = scanner.next();

        boolean tieneAuxilioTransporte = auxilioTransporte.equalsIgnoreCase("SI");

        // Formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Convertir las cadenas de texto a objetos LocalDate
        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
        LocalDate fechaFin = LocalDate.parse(fechaFinStr, formatter);
        
        // Calcular los días totales trabajados
        long diasTotalesTrabajados = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        System.out.println("Días totales trabajados: " + diasTotalesTrabajados);

        // Calcular salarios pendientes
        double salarioDiario = sueldoMensual / 30; // Suponiendo 30 días por mes
        double salarioPendiente = (diasTotalesTrabajados % 30) * salarioDiario;
        System.out.println("Salario pendiente: " + salarioPendiente);
        
        // Calcular cesantías
        double cesantias = (diasTotalesTrabajados / 360.0) * sueldoMensual;
        System.out.println("Cesantías: " + cesantias);
        
        // Calcular intereses sobre las cesantías
        double interesesCesantias = cesantias * 0.12 * (diasTotalesTrabajados / 365.0);
        System.out.println("Intereses sobre cesantías: " + interesesCesantias);
        
        // Calcular primas de servicios
        double primasServicios = (diasTotalesTrabajados / 360.0) * sueldoMensual / 2;
        System.out.println("Primas de servicios: " + primasServicios);
        
        // Calcular vacaciones
        double vacaciones = (diasTotalesTrabajados / 720.0) * sueldoMensual;
        System.out.println("Vacaciones: " + vacaciones);

        // Calcular auxilio de transporte
        double auxilioTransporteMensual = 140606; // Auxilio de transporte para 2024 (puede variar cada año)
        double totalAuxilioTransporte = tieneAuxilioTransporte ? (diasTotalesTrabajados / 30.0) * auxilioTransporteMensual : 0;
        System.out.println("Auxilio de transporte: " + totalAuxilioTransporte);

        // Calcular aportes a la seguridad social
        double pensionMensual = 0.04 * sueldoMensual; // 4% del salario mensual
        double saludMensual = 0.04 * sueldoMensual; // 4% del salario mensual
        double totalPension = pensionMensual * (diasTotalesTrabajados / 30.0);
        double totalSalud = saludMensual * (diasTotalesTrabajados / 30.0);
        System.out.println("Aportes a pensiones: " + totalPension);
        System.out.println("Aportes a salud: " + totalSalud);

        // Total Liquidación
        double totalLiquidacion = salarioPendiente + cesantias + interesesCesantias + primasServicios + vacaciones + totalAuxilioTransporte - totalPension - totalSalud;
        System.out.println("Total Liquidación: " + totalLiquidacion);
    }
}
