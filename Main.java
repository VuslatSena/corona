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
     * @param fileName 20200101
     * @return 2020-01-01
     */
    public static String buildDateFileName(String fileName){
        int firstIndex = 4;
        
        //System.out.println("string received: "+fileName);
        String regex = "-";
        String[] tempArr = fileName.split(regex);
        String data = tempArr[4];
        //System.out.println("Data: "+data);
        int pointIndex = data.indexOf(".");
        String firstSubString = "", secondSubString = "";
        String miniString_1 = "", miniString_2 = "";

        firstSubString = data.substring(0, firstIndex);
        //System.out.println("-firstSubString: "+firstSubString);
        secondSubString = data.substring(firstIndex, pointIndex);
        //System.out.println("-secondSubString: "+secondSubString);
        miniString_1 = secondSubString.substring(0, 2);
        miniString_2 = secondSubString.substring(2, secondSubString.length());
        
        firstSubString = firstSubString.concat(regex);
        secondSubString = secondSubString.concat(regex);
        miniString_1 = miniString_1.concat(regex);

        data = firstSubString+miniString_1+miniString_2;
        //System.out.println("string returned: "+data);
        return data;
    }

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
        int codiceProvincia = Integer.parseInt(tempArray[4]);
        temp.setCodiceProvincia(codiceProvincia);
        String nomeProvincia = tempArray[5];
        temp.setNomeProvinia(nomeProvincia);
        String siglaProvincia = tempArray[6];
        temp.setSiglaProvincia(siglaProvincia);
        int totaleCasi = Integer.parseInt(tempArray[9]);
        temp.setTotaleCasi(totaleCasi);
        
        return temp;
    }

    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("file names in: [" +provinceDirectory.toString()+"]");
        FileHandler fHandler = new FileHandler();
        ArrayList<File> fList = fHandler.getFileList();
        String[] strLette = new String[1024];
        Tree t = new Tree();
        try{
            strLette = fHandler.readFile(fList.get(1));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        for(int i = 0; i < strLette.length; i++){
            String str = strLette[i];
            if(str != null)
                System.out.println(i+": "+str);
        }
    }

}
