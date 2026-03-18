class ContainerRect{
    Rectangulo[] rectangulos;
    double[] distancias;
    double[] areas;
    int n;
    static int numRec=0;
    public ContainerRect(int n){
        this.n=n;
        rectangulos=new Rectangulo[n];
        distancias=new double[n];
        areas=new double[n];
    }
    public void addRectangulo(Rectangulo r){
        if(numRec<n){
            rectangulos[numRec]=r;
            distancias[numRec]=Coordenada.distancia(r.getEsquina1(),r.getEsquina2());
            areas[numRec]=Math.abs((r.getEsquina2().getX()-r.getEsquina1().getX())*(r.getEsquina2().getY()-r.getEsquina1().getY()));
            numRec++;
        }else{
            System.out.println("No hay espacio");
        }
    }
    @Override
    public String toString(){
        String s = "Rectangulo\tCoordenadas\tDistancia\tArea\n";
        for (int i = 0; i < numRec; i++){
            s += (i + 1) + "\t" + rectangulos[i] + "\t" + distancias[i] + "\t" + areas[i] + "\n";
        }
    return s;
    }
}