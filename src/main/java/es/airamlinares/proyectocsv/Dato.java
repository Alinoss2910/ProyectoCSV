package es.airamlinares.proyectocsv;
public class Dato {
    private String mes;
    private String provincia;
    private float media;
    
    public Dato(){
        
    }
    
    public Dato(String titulo){
        this.mes = titulo.toUpperCase();
    }
    
    public Dato(String titulo, String autor){
        this.mes = titulo.toUpperCase();
        this.provincia = autor;
    }
    
    public String getMes(){
        return mes;
    }
    
    public void setMes(String mes){
        this.mes = mes.toUpperCase();
    }
    
    public void setProvincia(String provincia){
        this.provincia = provincia;
    }
    
    public String getProvincia(){
        return provincia;
    }
    
    public float getMedia(){
        return media;
    }
    
    public void setMedia(int media){
        this.media = media;
    }

    @Override
    public String toString(){
        String r = "";
        r += "Mes: " + mes + "\n";
        r += "Provincia: " + provincia + "\n";
        r += "Media: " + media;
        return r;
    }
    public String horasToString(){
        String r = "Horas: " + media;
        return r;
    }
}
