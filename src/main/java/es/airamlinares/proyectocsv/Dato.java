package es.airamlinares.proyectocsv;
public class Dato {
    private String provincia;
    private int paro;
    private int paroEsp;
    
    public Dato(){
        
    }
    
    public Dato(String provincia){
        this.provincia = provincia;
    }
    
    public void setProvincia(String provincia){
        this.provincia = provincia;
    }
    
    public String getProvincia(){
        return provincia;
    }

    public void setParo(int paro) {
        this.paro = paro;
    }

    public void setParoEsp(int paroEsp) {
        this.paroEsp = paroEsp;
    }

    public int getParoEsp() {
        return paroEsp;
    }
    
    public int getParo() {
        return paro;
    }
    
    @Override
    public String toString(){
        String r = "";
        r += "Provincia: " + provincia;
        return r;
    }
}
