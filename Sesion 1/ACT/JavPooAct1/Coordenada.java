class Coordenada{
    double x,y;
    public Coordenada(){
        x=0;y=0;
    }
    public Coordenada(double x,double y){
        this.x=x;
        this.y=y;
    }
    public Coordenada(Coordenada c){
        this.x=c.x;
        this.y=c.y;
    }
    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
    void setX(double x){
        this.x=x;
    }
    void setY(double y){
        this.y=y;
    }
    double distancia(Coordenada c){
        return Math.sqrt(Math.pow(x-c.x,2)+Math.pow(y-c.y,2));
    }
    static double distancia(Coordenada c1,Coordenada c2){
        return Math.sqrt(Math.pow(c1.x-c2.x,2)+Math.pow(c1.y-c2.y,2));
    }
    public String toString(){
        return "("+x+","+y+")";
    }
}
