import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Clase para la manipulación de la información
 *
 * @author Pablo Sao
 * @version 19/03/2019
 */
public class DataManager {

    /***
     * String con el contenido de un archivo
     * @param PATH_FILE dirección donde se encuentra el archivo
     * @return String con datos contenidos dentro del documento
     */
    public static String getDataFile(String PATH_FILE){
        BufferedReader reader;
        String linea,datos = "";

        try{
            reader = new BufferedReader(new FileReader(PATH_FILE));

            while((linea = reader.readLine()) != null){
                datos += linea + "\t";
            }
            // Cerramos la conexion
            reader.close();

        }
        //Tomaremos todo tipo de error en la ejecución del bloque de codigo dentro del catch
        catch(Exception e){
            e.printStackTrace();
        }

        return datos;
    }

    /***
     * Metodo para verificar la existencia del archivo ingresado
     * @return true si el archivo existe, false si el archivo no existe
     */
    public static boolean getExists(String PATH_FILE){
        return (new File(PATH_FILE)).exists();
    }

    /***
     * Separación de los datos de un archivo, a una lista, según delimitador enviado
     * @param DELIMITADOR patron para identificar división de tokens
     * @param PATH_FILE ruta donde se encuentra ubicado el archivo
     * @return lista del contenido del archivo, parseado
     */
    public static List getFileTokens(String DELIMITADOR, String PATH_FILE){

        return Collections.list(new StringTokenizer(DataManager.getDataFile(PATH_FILE), DELIMITADOR)).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }

    /***
     * Separación de un string a una lista, según delimitador enviado
     * @param DELIMITADOR patron para identificar división de tokens
     * @param VALUE dato del que se desea obtener los tokens
     * @return lista del string parseado
     */
    public static List getStringTokens(String DELIMITADOR, String VALUE){

        return Collections.list(new StringTokenizer(VALUE, DELIMITADOR)).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());

    }

    /***
     * Conversión del string a su tipo original
     * @param VALUE String a convertir
     * @return valor en su tipo de dato
     */
    private static Object getAtom(String VALUE) {
        try {
            //Si es entero, retornamos el valor en tipo int
            return Integer.parseInt(VALUE);
        }
        catch (NumberFormatException e) {
            try {
                //Si es flotante, se convierte el string a Float
                return Float.parseFloat(VALUE);
            }
            catch (NumberFormatException e2) {
                try {
                    //Si es un double, se convierte el string a decimal
                    return Double.parseDouble(VALUE);
                }
                catch (NumberFormatException e3) {
                    //Si no es numerico, se retorna el token original en un tipo string
                    return VALUE;
                }
            }
        }
    }
}
