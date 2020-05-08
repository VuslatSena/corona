import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class FileHandler {

    private String fileFormat = ".*\\.csv";
    private String provinceDirectory = "/Users/mattiamac/Documents/GitHub/coronaVisualiser/dati/";
    private File fileDirectory;
    private ArrayList<String> fileNames;
    //private ArrayList<File> files;
    private Stack<File> filesStack;

    private File f;

    public FileHandler() throws FileNotFoundException{
        fileNames = new ArrayList<String>();
        //files = new ArrayList<File>();
        filesStack = new Stack<File>();
        fileDirectory = new File(provinceDirectory);
        addToFileStack();
        search();
    }

    public ArrayList<String> getFileListNames(){
        return this.fileNames;
    }

    /* public ArrayList<File> getFileList(){
        return this.files;
    } */

    public Stack<File> getFileStack(){
        return this.filesStack;
    }

    public void search(){
        for(File f : filesStack){
            if(f.isDirectory())
                search();
            if(f.isFile()){     
                if(f.getName().matches(fileFormat))
                {
                    fileNames.add(f.getName());
                    System.out.println("added to fileNames: "+f.getName());
                }      
            }
        }
    } 

    public void addToFileStack(){
        File[] fileList = fileDirectory.listFiles();
        for(File f : fileList){
            if(!(filesStack.contains(f)))
                if(f.isFile())
                    filesStack.add(f);
        }
    }

    public File getFile(String fileName){
        System.out.println("file name: "+fileName);
        for(File temp : filesStack){
            if(temp.getName() != null && temp.getName().contains(fileName)) 
                return temp;       
        }   
        return null;
    }

    public String getFileName(File f){
        return f.getName();
    }

    public ArrayList<String> readFile(File f) throws IOException{
        ArrayList<String> righeLette = new ArrayList<String>();
        try{
            removeFirstLine(f.getName());
            File file = getFile(f.getName());
            Scanner sc = new Scanner(file);
            String strLetta = "";
            int i = 0;
            while(sc.hasNextLine()){
                strLetta = sc.nextLine();
                righeLette.add(strLetta);
                i++;
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found: "+f.getPath());
            e.printStackTrace();
        }catch(NullPointerException e){
            System.out.println("File path: "+f.getPath());
        }/* catch(IOException e){
            e.printStackTrace();
        } */
        return  righeLette;
    }
    
    public void removeFirstLine(String fileName){
        String filePath = buildFilePath(fileName);
        File path = new File(filePath);
        try{
            Scanner sc = new Scanner(path);
            ArrayList<String> coll = new ArrayList<String>();
            sc.nextLine();
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                coll.add(line);
            }
            sc.close();
            FileWriter writer = new FileWriter(path);
            for(String line : coll){
                writer.write(line);
                writer.write("\n");
            }
                
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found: "+filePath);
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param fileName 20200101
     * @return 2020-01-01
     */
    public String buildStringDate(String fileName) {
        int firstIndex = 4;
        
        String regex = "-";
        String[] tempArr = fileName.split(regex);
        String data = tempArr[4];
        
        int pointIndex = data.indexOf(".");
        String firstSubString = "", secondSubString = "";
        String miniString_1 = "", miniString_2 = "";

        firstSubString = data.substring(0, firstIndex);
        
        secondSubString = data.substring(firstIndex, pointIndex);
        
        miniString_1 = secondSubString.substring(0, 2);
        miniString_2 = secondSubString.substring(2, secondSubString.length());
        
        firstSubString = firstSubString.concat(regex);
        secondSubString = secondSubString.concat(regex);
        miniString_1 = miniString_1.concat(regex);

        data = firstSubString+miniString_1+miniString_2;
        
        return data;
    }

    public String buildFilePath(String fileName){
        return provinceDirectory+"/"+fileName;
    }
   
}