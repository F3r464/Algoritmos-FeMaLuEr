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
    void calArea(){
        double area=Math.abs((r.getEsquina2().getX()-r.getEsquina1().getX())*(r.getEsquina2().getY()-r.getEsquina1().getY()));}
        return area;
    }
    public String toString(){
        return "["+esquina1+" , "+esquina2+"]";
    }
}
