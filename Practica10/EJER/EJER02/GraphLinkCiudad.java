package Practica10.EJER.EJER02;
public class GraphLinkCiudad{

    private ListLinked<AdjListCiudad> grafo;
    public GraphLinkCiudad(){
        grafo = new ListLinked<>();
    }
    public void insertarCiudad(String nombre){
        NodoCiudad ciudad = new NodoCiudad(nombre);
        grafo.addLast(new AdjListCiudad(ciudad));
    }

    private AdjListCiudad buscarCiudad(String nombre){
        for (int i = 0; i < grafo.size(); i++){
            try {
                AdjListCiudad adj = grafo.get(i);
                if (adj.getCiudad().getNombre().equals(nombre)){
                    return adj;
                }
            } catch (ExceptionGraph e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public void insertarCarretera(String origen, String destino, int distancia){

        AdjListCiudad c1 = buscarCiudad(origen);
        AdjListCiudad c2 = buscarCiudad(destino);

        if (c1 == null || c2 == null){
            return;
        }
        c1.getAristas().addLast(new AristaCiudad(c2.getCiudad(), distancia));
        c2.getAristas().addLast(new AristaCiudad(c1.getCiudad(), distancia));
    }

    @Override
    public String toString(){
        String resultado = "";
        for (int i = 0; i < grafo.size(); i++){
            try {
                AdjListCiudad adj = grafo.get(i);
                resultado += adj.getCiudad() + " -> ";
                ListLinked<AristaCiudad> aristas = adj.getAristas();
                for (int j = 0; j < aristas.size(); j++){
                    AristaCiudad a = aristas.get(j);
                    resultado += a.getDestino().getNombre() + "(" + a.getpeso() + " km) ";
                }
                resultado += "\n";
            } catch (ExceptionGraph e){
                System.out.println(e.getMessage());
            }
        }
        return resultado;
    }
}