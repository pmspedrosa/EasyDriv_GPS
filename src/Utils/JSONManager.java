package Utils;

import Logic.Data.Booking.Booking;
import Logic.Data.User.UserManager;
import Logic.Data.Vehicle.VehicleManager;
import com.google.gson.Gson;
import jdk.jfr.Frequency;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONManager{
    private static FileWriter file;

    public static void writeToFile(Object o, EntityType entityType) {
        Gson gson = new Gson();
        String json = gson.toJson(o);

        try {
            file = new FileWriter(getPath() + File.separator + getFileName(entityType));
            file.write(json);
            Logger.getInstance().debug("Ficheiro escrito com sucesso");

        } catch (IOException e) {
            Logger.getInstance().error("Erro na escrita do ficheiro");
            e.printStackTrace();
        } finally {
            try{
                file.flush();
                file.close();
                Logger.getInstance().debug("Ficheiro flushed/closed com sucesso.");

            } catch (IOException e) {
                Logger.getInstance().error("Erro no flush/close do ficheiro");
                e.printStackTrace();
            }
        }
    }

    public static Object readFromFile(EntityType entity){
        Gson gson = new Gson();

        try
        {
            String o = Files.readString(Path.of(getPath() + File.separator + getFileName(entity)));
            switch (entity) {
                case USER -> {
                    return gson.fromJson(o, UserManager.class);
                }
                case VEHICLE -> {
                    return gson.fromJson(o, VehicleManager.class);
                }
                case BOOKING -> {
                    return gson.fromJson(o, Booking.class);
                }
            }

        } catch (IOException e) {
            Logger.getInstance().error("Deu cocó a ler do ficheiro");
            e.printStackTrace();
        }
        return null;
    }

    private static String getPath() {
        return System.getProperty("user.dir");
    }

    public static String getFileName(EntityType entity) {
        return switch (entity) {
            case USER -> Constants.USERFILE;
            case VEHICLE -> Constants.VEHICLEFILE;
            case BOOKING -> Constants.BOOKINGFILE;
        };
    }
}
