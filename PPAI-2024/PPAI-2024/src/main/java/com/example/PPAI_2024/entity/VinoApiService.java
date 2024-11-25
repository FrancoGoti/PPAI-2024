package com.example.PPAI_2024.entity;

import java.util.List;

import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class VinoApiService {
    
    private List<Vino> actualizacionesVinos;
    
    public VinoApiService(List<Vino> actualizacionesVinos){
        this.actualizacionesVinos = actualizacionesVinos;
    }
    
    //Metodo 12
    public List<Vino> obtenerActualizaciones(){
        return crearVinosActualizacion();
    }


    public List<Vino> crearVinosActualizacion(){

        //Se crean fechas para el caso
        LocalDate fecha1 = LocalDate.of(2024, 2, 17);
        LocalDate fecha2 = LocalDate.of(2024, 4, 23);
        LocalDate fecha3 = LocalDate.of(2024, 5, 25);
        LocalDate fecha4 = LocalDate.of(2024, 1, 5);
        LocalDate fecha5 = LocalDate.of(2024, 1, 2);


        //Se crean tipos de uva para el caso
        TipoUva uva1 = new TipoUva("Cabernet Sauvignon", "clasico y estructurado");
        TipoUva uva2 = new TipoUva("Mencia", "fresca y afrutada");
        TipoUva uva3 = new TipoUva("PetitVerdot", "concentrado y potente");
        TipoUva uva4 = new TipoUva("Tempranillo", "elegante y versatil");

        //Se crean varietales para el caso de uso
        Varietal varietal1 = new Varietal("Variedad de Sauvignon", 80, uva1);
        Varietal varietal2 = new Varietal("Variedad de Mencia", 83, uva2);
        Varietal varietal3 = new Varietal("Variedad de PetitVerdot", 76, uva3);
        Varietal varietal4 = new Varietal("Variedad de Tempranillo", 89, uva4);

        //Se crean maridajes para el caso de uso
        Maridaje maridaje1 = new Maridaje("Bife de chorizo", "Para acompanar Cabernet Sauvignon");
        Maridaje maridaje2 = new Maridaje("Sorrentinos de salmon", "Para acompanar Mencia");
        Maridaje maridaje3 = new Maridaje("Cordero al horno", "Para acompanar PetitVerdot");
        Maridaje maridaje4 = new Maridaje("Salmon rosado", "Para acompanar Tempranillo");

       //Vinos con modificacion en su precio para la API
        Vino vinoApi1 = new Vino(1998, fecha1, "Malbec1", "Suave", 25500, maridaje1, varietal1);
        Vino vinoApi2 = new Vino(2000, fecha1, "Patito1", "Dulce", 27500, maridaje2, varietal2);
        Vino vinoApi3 = new Vino(2002, fecha2, "Patito2", "Fuerte", 24500, maridaje3, varietal3);
        
        //Vinos sin modificaciones para la API
        Vino vinoApi4 = new Vino(2004, fecha2, "LasPerdices", "Amargo", 90500, maridaje4, varietal4);
        Vino vinoApi5 = new Vino(2006, fecha3, "LasPerdices2", "Suave", 20000, maridaje1, varietal1);
        Vino vinoApi6 = new Vino(2008, fecha3, "VinaBalbo", "Dulce", 4000, maridaje2, varietal2);
        Vino vinoApi7 = new Vino(2010, fecha4, "SantaJulia", "Fuerte", 5500, maridaje3, varietal3);
        Vino vinoApi8 = new Vino(2012, fecha4, "Toro", "Suave", 2000, maridaje4, varietal4);
        Vino vinoApi9 = new Vino(2014, fecha5, "Termidor", "Dulce", 2900, maridaje1, varietal1);
        
        // Varietales nuevos para agregar en la API
        Varietal varietal5 = new Varietal("Variedad de Malbec", 92, uva4);
        Varietal varietal6 = new Varietal("Variedad de Syrah", 85, uva3);
        Varietal varietal7 = new Varietal("Variedad de Merlot", 78, uva4);
        Varietal varietal8 = new Varietal("Variedad de Pinot Noir", 88, uva2);
        Varietal varietal9 = new Varietal("Variedad de Chardonnay", 90, uva2);
        Varietal varietal10 = new Varietal("Variedad de Cabernet Franc", 82, uva1);

        // Maridajes nuevos para la API
        Maridaje maridaje5 = new Maridaje("Queso azul", "Para acompañar Malbec");
        Maridaje maridaje6 = new Maridaje("Estofado de ternera", "Para acompañar Syrah");
        Maridaje maridaje7 = new Maridaje("Pasta con salsa de tomate", "Para acompañar Merlot");
        Maridaje maridaje8 = new Maridaje("Pato a la naranja", "Para acompañar Pinot Noir");
        Maridaje maridaje9 = new Maridaje("Mariscos", "Para acompañar Chardonnay");
        Maridaje maridaje10 = new Maridaje("Pollo al curry", "Para acompañar Cabernet Franc");

        //Vinos nuevos para agregar en la API
        Vino vinoApi10 = new Vino(2016, fecha1, "Trapiche", "Aterciopelado", 6000, maridaje5, varietal5);
        Vino vinoApi11 = new Vino(2018, fecha1, "Catena", "Robusto", 7500, maridaje6, varietal6);
        Vino vinoApi12 = new Vino(2020, fecha1, "Rutini", "Frutal", 8500, maridaje7, varietal7);
        Vino vinoApi13 = new Vino(2011, fecha1, "Luigi Bosca", "Herbáceo", 4000, maridaje8, varietal8);
        Vino vinoApi14 = new Vino(2013, fecha1, "Norton", "Terroso", 3200, maridaje9, varietal9);
        Vino vinoApi15 = new Vino(2015, fecha1, "Salentein", "Especiado", 6700, maridaje10, varietal10); 
   
        //Se añaden los vinos modificados a la lista
        actualizacionesVinos.removeAll(actualizacionesVinos);
        actualizacionesVinos.add(vinoApi1);
        actualizacionesVinos.add(vinoApi2);
        actualizacionesVinos.add(vinoApi3);
        actualizacionesVinos.add(vinoApi4);
        actualizacionesVinos.add(vinoApi5);
        actualizacionesVinos.add(vinoApi6);
        actualizacionesVinos.add(vinoApi7);
        actualizacionesVinos.add(vinoApi8);
        actualizacionesVinos.add(vinoApi9);
        actualizacionesVinos.add(vinoApi10);
        actualizacionesVinos.add(vinoApi11);
        actualizacionesVinos.add(vinoApi12);
        actualizacionesVinos.add(vinoApi13);
        actualizacionesVinos.add(vinoApi14);
        actualizacionesVinos.add(vinoApi15);
   
        return actualizacionesVinos;
    }
    //Vinos con modificacion en su precio para la API
       
    
}

