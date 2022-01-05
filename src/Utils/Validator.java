package Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class Validator {

    /**metodos de Validação User*/

//    private boolean admin;    todo --
//    private Booking booking;  todo --
//    private String password;          --


    private static boolean nameValidation(String email) {
        String pattern = "^[a-zA-Z]{3,15}$";
        return email.matches(pattern);
    }
    //^[a-zA-Z]{3,15}$    3 a 15 characteres and only lower and upper case

    private static boolean emailValidation(String email) {
        String pattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return email.matches(pattern);
    }
    /*^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$ restricts the number of characters in top level domain*/


    private static boolean passwordValidation(String pass) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}";
        return pass.matches(pattern);
    }


    private static boolean phoneNumberValidation(String phoneNumber){
        String pattern ="(9[1236][0-9]) ?([0-9]{3}) ?([0-9]{3})";
        return phoneNumber.matches(pattern);
    }

    private static boolean drivingLicenseValidation(String drivingLicense){
        String pattern ="[0-9]";
        return drivingLicense.matches(pattern);
    }

   /*
    ^ start of the line
    (?=.*[0-9]) a digit must occur at least once
	(?=.*[a-z]) a lower case letter must occur at least once
	(?=.*[A-Z]) an upper case letter must occur at least once
	(?=\\S+$) no whitespace allowed in the entire string
	.{6,} at least 6 characters*/




    /**metodos de Validação Vehicle*/


    /*private String make;--
	private String registerPlate;--
	private int numOfSeats;--
	private String fuelType;--
	private String model;--
	todo private boolean avaliable;     --não sei
	todo private Maintenance maintenance;     --não sei
	*/


    private static final HashMap<String,String[]> vehicleList = new HashMap<>(){{
        put("Aston Martin",new String[] {"DB9 Volante", "DBS Coupe", "DBS Volante", "Rapide", "V12 Vantage Coupe", "V8 Vantage Coupe", "V8 Vantage Roadster", "Virage",});
        put("Audi",new String[] {"A1", "A3", "A3 Sedan", "A4 Avant", "A4 Sedã", "A5", "A7", "A8", "Q5", "Q7", "R8", "R8 GT", "RS 3 Sportback", "RS 5", "RS6 Avant", "TT Coupé", "TT Roadster",});
        put("BMW",new String[] {"Série 1", "Série 1 Cabrio", "Série 1 Coupé", "Série 1 M", "Série 3 Cabrio", "Série 3 M3 Coupé", "Série 3 M3 Sedã", "Série 3 Sedã", "Série 5 Gran Turismo", "Série 5 Sedã", "Série 7 Sedã", "X1", "X3", "X5", "X6", "Z4 Roadster",});
        put("Chevrolet",new String[] {"Agile", "Astra Hatch", "Astra Sedan", "Blazer", "Camaro", "Captiva", "Celta", "Classic", "Cobalt", "Corsa Hatch", "Corsa Sedã", "Cruze", "Cruze Sport6", "Malibu", "Meriva", "Montana", "Omega", "Onix", "Prisma", "S10", "Sonic", "Spin", "Tracker", "Trailblazer", "Vectra", "Vectra GT", "Zafira",});
        put("Citroen",new String[] {"Aircross", "C3", "C3 Picasso", "C4", "C4 Pallas", "C4 Picasso", "C5", "C5 Tourer", "DS3", "DS5", "Jumper", "Xsara Picasso",});
        put("Fiat",new String[]{"500","Bravo","Doblò","Doblò Cargo","Ducato","Fiorino","Freemont","Grand Siena","Idea","Linea","Mille","Palio","Palio Adventure","Palio Weekend","Punto","Siena EL","Strada","Uno",});
        put("Ford",new String[]{"Courier","EcoSport","Edge","F-250","Fiesta Rocam Hatch","Fiesta Rocam Sedan","Focus Hatch","Focus Sedan","Fusion","Ka","New Fiesta","New Fiesta Hatch","Ranger","Transit",});
        put("Honda",new String[]{"Accord","CR-V","City","Civic","Fit",});
        put("Hyundai",new String[]{"Azera","Equus","HB20","HB20S","HB20X","HR","Santa Fe","Sonata","Tucson","Veloster","Veracruz","i30","i30 CW","iX35",});
        put("Jeep",new String[]{"Cherokee","Grand Cherokee","Wrangler","Jinbei","Jinbei","Topic Furgão","Topic Passageiro",});
        put("Kia",new String[]{"Bongo","Cadenza","Carens","Carnival","Cerato","Mohave","Optima","Picanto","Sorento","Soul","Sportage",});
        put("Land Rover",new String[]{"Defender","Discovery 4","Freelander 2","Range Rover Evoque","Range Rover Sport","Range Rover Vogue",});
        put("Mercedes-Benz",new String[]{"CLA","Classe A","Classe B","Classe C","Classe C 250 Turbo Sport","Classe C 63 AMG Touring","Classe CL","Classe CLS 63 AMG","Classe E","Classe G","Classe GL","Classe GLK","Classe M","Classe S","Classe S 400 Hybrid","Classe SLK","Classe SLS AMG",});
        put("Mini",new String[]{"Cooper","Cooper Cabrio","Cooper Countryman","Cooper S Clubman-Hampton","One",});
        put("Mitsubishi",new String[]{"ASX","L200 Outdoor","L200 Savana","L200 Triton","Lancer Evolution X","Outlander","Pajero Dakar","Pajero Full","Pajero Sport","Pajero TR4",});
        put("Nissan",new String[]{"Frontier","Grand Livina","Livina","March","Sentra","Tiida Hatch","Tiida Sedan","Vers",});
        put("Peugeot",new String[]{"207","207 SW","207 Sedan","208","3008","307","308","308 CC","408","508","Boxer","Hoggar","Partner","RCZ",});
        put("Porsche",new String[]{"911","Boxster","Boxster S","Cayenne","Cayman","Cayman S","Panamera",});
        put("Renault",new String[]{"Clio","Duster","Fluence","Grand Tour","Kangoo Express","Logan","Master","Sandero","Sandero Stepway","Symbol",});
        put("Smart",new String[]{"Fortwo MHD","Fortwo Passion Cabrio","Fortwo Passion Coupé",});
        put("Suzuki",new String[]{"Grand Vitara","Jimny","SX4",});
        put("Toyota",new String[]{"Camry","Corolla","Etios Hatch","Etios Sedã","Hilux","Prius","RAV4","SW4",});
        put("Volkswagen",new String[]{"Amarok","Crossfox","Fox","Fusca","Gol","Gol G4","Golf","Jetta","Jetta Variant","Kombi","Parati","Passat","Passat Variant","Polo","Polo Sedan","Saveiro","Space Cross","SpaceFox","Tiguan","Touareg","Up!","Voyage",});
        put("Volvo",new String[]{"C30","S60","V40","XC60","XC90",});
    }};


    private static boolean registerPlatevalidation(String rp) {
        String pattern = "^(([A-Z]{2}-\\d{2}-(\\d{2}|[A-Z]{2}))|(\\d{2}-(\\d{2}-[A-Z]{2}|[A-Z]{2}-\\d{2})))$";	//AA-00-00,00-AA-00,00-00-AA,AA-00-AA, com hífen a separar
        return rp.matches(pattern);
    }

    private static boolean numOfSeatsValidation(int num) {
        return num >= 1 && num < 5;
    }

    private static boolean fuelTypeValidation(String fuelType) {
        return fuelType.equalsIgnoreCase("GASOLINA") || fuelType.equalsIgnoreCase("GASOLIO");
    }













}
