import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Stack;

public class Main{

    private static String fileFormat = ".*\\.csv";
    private static String parentDirectory = "/Users/mattiamac/Documents/GitHub/COVID-19";
    private static String pathNameProvince = "/Users/mattiamac/Documents/GitHub/COVID-19/dati-province/";
    private static File provinceDirectory = new File("/Users/mattiamac/Documents/coronaVisualiser/dati");
    private static LocalDate dataFile;
    private static ArrayList<String> fileNames;
    private static File[] filesArr = provinceDirectory.listFiles();
    
    /**
     * 
     * @param date 2020-01-01
     * @return LocalDate 2020-01-01
     */
    public static LocalDate buildDate(String date){
        //System.out.println("[buildDate]String received: "+date);
        dataFile = LocalDate.parse(date);
        return dataFile;
    }

    public static String regionReader(String fileName, String nameOfRegion) throws FileNotFoundException{
        String regionFound = "";
        FileHandler fHandler = new FileHandler();
        File f = new File(fHandler.buildFilePath(fileName));
        String regex = ",";
        String str = "";
        try{
            ArrayList<String> arrayToCheck = fHandler.readFile(f);
            for(String strToCheck : arrayToCheck){
                if(strToCheck != null){
                    String[] sArr = new String[12];
                    sArr = strToCheck.split(regex);
                    regionFound = sArr[3];
                    if(regionFound.equals(nameOfRegion))
                        return regionFound;
                }
            }
            /* for(int i = 0; i < arrayToCheck.length-1; i++){
                str = arrayToCheck[i];
                if(str != null){
                    String sArr[] = new String[arrayToCheck.length];
                    sArr = str.split(regex);
                    regionFound = sArr[3];
                    //System.out.println(regionFound);
                    //System.out.println("sArrRegione: "+sArrRegione);
                    if(regionFound.equals(nameOfRegion))
                        return regionFound;
                }else{
                    System.out.println("numero riga: "+i);
                }
            } */
        }catch(IOException e){
            e.printStackTrace();
        }catch(NullPointerException e){
            System.out.println("Ultima stringa letta: "+str);
            System.out.println("Leggevo Questo file: "+f.getName());
            e.printStackTrace();
        }
        return null;
    }

    public static String provinceFinder(String fileName, String nomeOfProvince) throws FileNotFoundException{
        String provinceFound = "";
        try{
            FileHandler fHandler = new FileHandler();
            File f = new File(fHandler.buildFilePath(fileName));
            String regex = ",";
            String str = "";
            ArrayList<String> arrayToCheck = fHandler.readFile(f);
            for(String strToCheck : arrayToCheck){
                if(strToCheck != null){
                    String[] sArr = new String[12];
                    sArr = strToCheck.split(regex);
                    provinceFound = sArr[5];
                    if(provinceFound.equals(nomeOfProvince))
                        return provinceFound;
                    }
            }
            /* for(int i = 0; i < arrayToCheck.length-1; i++){
                str = arrayToCheck[i];
                if(str != null){
                    String[] strArr = new String[10];
                    strArr = str.split(regex);
                    provinceFound = strArr[5];
                    if(provinceFound.equals(nomeOfProvince))
                        return provinceFound;
                }
            } */
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int casiFinder(String fileName, String regione, String province){
        int totaleCasi = 0;
        try{
            FileHandler fHandler = new FileHandler();
            File f = new File(fHandler.buildFilePath(fileName));
            String regex = ",";
            String str = "";
            ArrayList<String> arrayToCheck = fHandler.readFile(f);
            for(String strToCheck : arrayToCheck){
                if(strToCheck != null){
                    String[] sArr = new String[12];
                    sArr = strToCheck.split(regex);
                    String regioneFound = sArr[3];
                    String provinciaFound = sArr[5];
                    if(regioneFound.equals(regione) && provinciaFound.equals(province))
                        totaleCasi = Integer.parseInt(sArr[9]);
                }
            }
            /* for(int i = 0; i < arrayToCheck.length-1; i++){
                str = arrayToCheck[i];
                if(str != null){
                    String[] strArr = new String[10];
                    strArr = str.split(regex);
                    String strRegione = strArr[3];
                    String strProvincia = strArr[5];
                    if(strRegione.equals(regione) && strProvincia.equals(province))
                        totaleCasi = Integer.parseInt(strArr[9]);
                }
            } */
        }catch(IOException e){
            e.printStackTrace();
        }
        return totaleCasi;
    }

    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("file names in: [" +provinceDirectory.toString()+"]");
        FileHandler fHandler = new FileHandler();
        Stack<File> fileStack = fHandler.getFileStack();
        ArrayList<Node> nList = new ArrayList<Node>();
        Tree t = new Tree();
        
        try{
            for(File f : fileStack){
                LocalDate dateFile = buildDate(fHandler.buildStringDate(f.getName()));
                t.insert(dateFile);
                nList.add(t.get(dateFile));
            }
            for(Node n : nList){
                File f = fileStack.pop();
                //System.out.println("Nodo: "+n.getDataNodo().toString());
                String regione = regionReader(f.getName(), "Lombardia");
                String provincia = provinceFinder(f.getName(), "Varese");
                int totaleCasi = casiFinder(f.getName(), regione, provincia);
                //System.out.println("here"+regione+provincia+totaleCasi);
                n.setNomeRegione(regione);
                n.setNomeProvincia(provincia);
                n.setTotaleCasi(totaleCasi);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        t.traverseInOrder();
    }
}
