class Zona{
    String mineral;
    double cantidad;
    double pureza;
    public Zona(String mineral,double cantidad,double pureza){
        this.mineral=mineral;
        this.cantidad=cantidad;
        this.pureza=pureza;
    }
    public double valor(){
        return cantidad*pureza;
    }
    @Override
    public String toString(){
        return "["+mineral+", cantidad: "+cantidad+", pureza: "+pureza+"]";
    }
}
