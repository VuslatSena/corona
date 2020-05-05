import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;



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

    public static Node buildNode(Node temp, String data){
        String regex = ",";
        System.out.println("Saving data into Node: "+temp.getDataNodo());
        String[] tempArray = data.split(regex);
        String stato = tempArray[1];
        temp.setStato(stato);
        int codRegione = Integer.parseInt(tempArray[2]);
        temp.setCodiceRegione(codRegione);
        String nomeRegione = tempArray[3];
        temp.setNomeRegione(nomeRegione);
        //System.out.println("NomeRegione: "+nomeRegione);
        int codiceProvincia = Integer.parseInt(tempArray[4]);
        temp.setCodiceProvincia(codiceProvincia);
        String nomeProvincia = tempArray[5];
        temp.setNomeProvinia(nomeProvincia);
        System.out.println("nome provincia : "+nomeProvincia);
        String siglaProvincia = tempArray[6];
        temp.setSiglaProvincia(siglaProvincia);
        int totaleCasi = Integer.parseInt(tempArray[9]);
        temp.setTotaleCasi(totaleCasi);
        System.out.println("totale casi :"+totaleCasi);
        
        return temp;
    }

    public static ArrayList<String> regionReader(String fileName, String nameOfRegion) throws FileNotFoundException{
        ArrayList<String> regioneList = new ArrayList<String>();
        FileHandler fHandler = new FileHandler();
        File f = new File(fHandler.buildFilePath(fileName));
        String regex = ",";
        String str = "";
        String sArrRegione = "";
        try{
            String[] arrayToCheck = fHandler.readFile(f);
            for(int i = 0; i < arrayToCheck.length-1; i++){
                str = arrayToCheck[i];
                if(str != null){
                    String sArr[] = new String[arrayToCheck.length];
                    sArr = str.split(regex);
                    sArrRegione = sArr[3];
                    //System.out.println("sArrRegione: "+sArrRegione);
                    if(sArrRegione.equals(nameOfRegion))
                        regioneList.add(str);
                }else{
                    System.out.println("numero riga: "+i);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(NullPointerException e){
            System.out.println("Ultima stringa letta: "+str);
            System.out.println("Leggevo Questo file: "+f.getName());
            e.printStackTrace();
        }
        return regioneList;
    }

    public String provinceReader(String fileName, String nomeOfProvince) throws FileNotFoundException{
        String provinceFound = "";
        try{
            FileHandler fHandler = new FileHandler();
            File f = new File(fHandler.buildFilePath(fileName));
            String regex = ",";
            String str = "";
            String[] arrayToCheck = fHandler.readFile(f);
            for(int i = 0; i < arrayToCheck.length-1; i++){
                str = arrayToCheck[i];
                if(str != null){
                    String[] strArr = new String[10];
                    strArr = str.split(regex);
                    provinceFound = strArr[5];
                    if(provinceFound.equals(nomeOfProvince))
                        return provinceFound;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("file names in: [" +provinceDirectory.toString()+"]");
        FileHandler fHandler = new FileHandler();
        ArrayList<File> fList = fHandler.getFileList();
        ArrayList<Node> nList = new ArrayList<Node>();
        String[] strLette = new String[1024];
        Tree t = new Tree();
        try{
            File prova = fList.get(1);
            LocalDate dateOfProva = buildDate(fHandler.buildStringDate(prova.getName()));
            t.insert(dateOfProva);
            Node temp = t.get(dateOfProva);
            //nList.add(temp);
            ArrayList<String> regioneList = regionReader(prova.getName(), "Lombardia");
            for(String s : regioneList){
                temp = buildNode(temp, s);
                
            }
              
        }
        catch(IOException e){
            e.printStackTrace();
        }
        t.traverseInOrder();
        
    }

}
