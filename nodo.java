public class nodo{
    nodo padre;
    nodo hijo;
    nodo siguiente;
    boolean visitado;
    int cordX,cordY;
    int costoF, costoG, costoH;
    
    
    ///////CONSTRUCTOR//////////////////////////////////////////////////////////
    public nodo(int x,int y){
        this.cordX = x;
        this.cordY = y;
        visitado = false;
        padre=null;
    }
    
    //////METODO PARA ASIGNAR EL NODO PADRE/////////////////////////////////////
    public void asignarPadre(nodo padre){
        this.padre = padre;
    }
}
