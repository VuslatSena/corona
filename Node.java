import java.time.LocalDate;

public class Node{

    public Node leftNode;
    public Node rightNode;
    public Node parentNode;

    private LocalDate dataNodo;

    private String stato;
    private String nomeRegione;
    private String nomeProvincia;
    private String siglaProvincia;

    private int codiceRegione;
    private int codiceProvincia;

    private int totaleCasi;

    public Node(LocalDate dataNodo){
        this.dataNodo = dataNodo;
    }

    public Node(LocalDate dataNodo, String nomeRegione){
        this.dataNodo = dataNodo;
        this.nomeRegione = nomeRegione;
    }

    public Node(LocalDate dataNodo, String nomeRegione, String nomeProvincia){
        this.dataNodo = dataNodo;
        this.nomeRegione = nomeRegione;
        this.nomeProvincia = nomeProvincia;
    }

    public Node get(LocalDate dataNodo){
        if(this.dataNodo == dataNodo)
            return this;
        if(this.dataNodo.isBefore(dataNodo))
            if(leftNode != null)
                return leftNode.get(dataNodo);
        else{
            if(rightNode != null)
                return rightNode.get(dataNodo);
        }
        return null;
    }

    public Node get(LocalDate dataNodo, String nomeRegione){
        if(this.dataNodo == dataNodo){
            if(this.nomeRegione == nomeRegione)
                return this;
            else if(this.nomeRegione != nomeRegione)
                return get(dataNodo, nomeRegione);
        }
        if(this.dataNodo.isBefore(dataNodo)){
            if(leftNode != null)
                return leftNode.get(dataNodo);
        }
        else{
            if(rightNode != null)
                return rightNode.get(dataNodo);
        }
        return null;
    }

    public Node get(LocalDate dataNodo, String nomeRegione, String nomeProvincia){
        if(this.dataNodo == dataNodo){
            if(this.nomeRegione == nomeRegione)
                if(this.nomeProvincia == nomeProvincia)
                    return this;
            else if(this.nomeRegione != nomeRegione || this.nomeProvincia != nomeProvincia)
                return get(dataNodo, nomeRegione, nomeProvincia);
        }
        if(this.dataNodo.isBefore(dataNodo)){
            if(leftNode != null)
                return leftNode.get(dataNodo);
        }
        else{
            if(rightNode != null)
                return rightNode.get(dataNodo);
        }
        return null;
    }

    public void insert(LocalDate dataNodo){
        if(this.dataNodo == dataNodo)
            return;
        if(this.dataNodo.isBefore(dataNodo)){
            if(leftNode == null)
                leftNode = new Node(dataNodo);
            else
                leftNode.insert(dataNodo);
        }
        else{
            if(rightNode == null)
                rightNode = new Node(dataNodo);
            else
                rightNode.insert(dataNodo);
        }

    }

    public void traverseInOrder(){
        if(leftNode != null)
            leftNode.traverseInOrder();
        System.out.println(dataNodo.toString()+", "+" Regione: "+this.nomeRegione+" Provincia: "+this.getNomeProvincia()
                +" totale casi: "+this.getTotaleCasi());
        if(rightNode != null)
            rightNode.traverseInOrder();
    }

    public Node getLeftNode(){
        return this.leftNode;
    }

    public void setLeftNode(Node leftNode){
        this.leftNode = leftNode;
    }

    public Node getRightNode(){
        return this.rightNode;
    }

    public void setRightNode(Node rightNode){
        this.rightNode = rightNode;
    }

    public Node getParentNode(){
        return this.parentNode;
    }

    public void setParentNode(Node parentNode){
        this.parentNode = parentNode;
    }

    public String getStato(){
        return this.stato;
    }

    public void setStato(String stato){
        this.stato = stato;
    }

    public LocalDate getDataNodo(){
        return this.dataNodo;
    }

    public void setDataNodo(LocalDate dataNodo){
        this.dataNodo = dataNodo;
    } 

    public String getNomeRegione(){
        return this.nomeRegione;
    }

    public void setNomeRegione(String nomeRegione){
        this.nomeRegione = nomeRegione;
    }

    public String getNomeProvincia(){
        return this.nomeProvincia;
    }

    public void setNomeProvincia(String nomeProvincia){
        this.nomeProvincia = nomeProvincia;
    }

    public String getSiglaProvincia(){
        return this.siglaProvincia;
    }

    public void setSiglaProvincia(String siglaProvincia){
        this.siglaProvincia = siglaProvincia;
    }

    public int getCodiceRegione(){
        return this.codiceRegione;
    }

    public void setCodiceRegione(int codiceRegione){
        this.codiceRegione = codiceRegione;
    }

    public int getCodiceProvincia(){
        return this.codiceProvincia;
    }

    public void setCodiceProvincia(int codiceProvincia){
        this.codiceProvincia = codiceProvincia;
    }

    public int getTotaleCasi(){
        return this.totaleCasi;
    }

    public void setTotaleCasi(int totaleCasi){
        this.totaleCasi = totaleCasi;
    }

    public LocalDate min(){
        if(leftNode == null)
            return this.dataNodo;
        else
            return leftNode.min();
    }

    public LocalDate max(){
        if(rightNode == null)
            return this.dataNodo;
        else
            return leftNode.max();
    }

}