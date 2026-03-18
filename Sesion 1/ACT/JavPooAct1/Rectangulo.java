class Rectangulo{
    Coordenada esquina1;
    Coordenada esquina2;
    public Rectangulo(Coordenada c1,Coordenada c2){
        esquina1=new Coordenada(c1);
        esquina2=new Coordenada(c2);
    }
    Coordenada getEsquina1(){
        return esquina1;
    }
    Coordenada getEsquina2(){
        return esquina2;
    }
    void setEsquina1(Coordenada c){
        esquina1=new Coordenada(c);
    }
    void setEsquina2(Coordenada c){
        esquina2=new Coordenada(c);
    }
    public String toString(){
        return "["+esquina1+" , "+esquina2+"]";
    }
}
