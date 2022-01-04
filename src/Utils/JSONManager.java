package Utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class JSONManager{
    private static FileWriter file;

    public void writeToFile(JSONArray list) {
        try {
            file = new FileWriter(getPath());
            file.write(list.toJSONString());
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

    public JSONArray readFromFile(EntityType entity){
        JSONParser jsonParser = new JSONParser();
        JSONArray objectList = new JSONArray();
        try {
            FileReader reader = new FileReader(getPath());
            Object object = jsonParser.parse(reader);

            objectList = (JSONArray)object;

        } catch (FileNotFoundException e) {
            Logger.getInstance().error("Ficheiro não encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            Logger.getInstance().error("Erro não sei bem onde");
            e.printStackTrace();
        } catch (ParseException e) {
            Logger.getInstance().error("Erro no parse");
            e.printStackTrace();
        }

        return objectList;
    }

    private String getPath() {
        return System.getProperty("user.dir");
    }

    public String getFileName(EntityType entity) {
        return switch (entity) {
            case USER -> Constants.USERFILE;
            case VEHICLE -> Constants.VEHICLEFILE;
            case BOOKING -> Constants.BOOKINGFILE;
        };
    }
}
