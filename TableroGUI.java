import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TableroGUI extends javax.swing.JPanel implements Runnable{
    private ImageIcon agua, tocado, startFinish, caminoWalk, carrito;
    private boolean tipoTablero;
    private CasillasGUI [][] casillas ;
    mapaMatriz mapita;///////////////
    Thread animacion;////////
    circulo circuloA;/////////
    int x0,y0,x1=200,y1=65+35;
    int termin = 0;
    lista nueva = new lista("nuevesita");
    nodo nuevo;
    
    public TableroGUI() {
        initComponents();
    }

    //tablero GUI, interfaz inicializa se le da el tama�o y si se muestra  (2)
    public TableroGUI(int size, boolean tipo) {
        initComponents();
        mapita = new mapaMatriz( size );
        x0= (1*35)-5;
        y0= (1*35)-5;
        
        //circulo a hace ilusi�n al objeto mu�eco  (3) donde utiliza el metodo draw para pintar
        //por lo que la clase circulo solo se implementa para eso y no tiene relaci�n con el algoritmo
        circuloA = new circulo(0,x0,y0);
        int x,y;
        
//      este elemento del layout no se utiliza  setLayout(new java.awt.GridLayout(size, size));
//      cuando inicializa el tablero es de tipo verdadero (4)
        this.tipoTablero = tipo;
        cargarImagenes();
//se le pasa el tama�o de las casillas que sera el mismo que el tablero (5)
        casillas = new CasillasGUI[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                casillas[i][j] = new CasillasGUI(this,mapita);/////////
                //se carga el fondo (pasto)
                casillas[i][j].setFondo(agua);
                x = (i * 35)+1;
                y = (j * 35)+1;
                //se da el tama�o de las casillas
                casillas[i][j].setBounds(x, y, 34, 34);
                this.add(casillas[i][j]);
            }
        }
    }
    
    //se le pasa el valor true cuando se pinta el tablero en casillas gui
    public boolean getTipoTablero(){
        return this.isTipoTablero();
    }
    
    //este metodo pintar, rellena la casilla con la pared cuando se clickee con el moyse en la casilla donde se pinche
    public void pintar(int x, int y){
        this.casillas[x][y].setFondo(tocado);
        this.repaint();
    }
    //se pinta las casillas de inicio y final
    public void pintarStartFinish(int x, int y){
        this.casillas[x][y].setFondo(startFinish);
        this.repaint();
    }
    //pinta el camino  con el metodo agregar, pasandole un nodo 
    public void pintarWalk(int x, int y){
        this.casillas[x][y].setFondo(caminoWalk);
        nuevo = new nodo(x,y);
        nueva.agregar(nuevo);
       // animacion.start();
        this.repaint();
    }
    //inicia el hilo no hay camino pero en realidad no es necesario ya que se checa antes de que inicie la animaci�n
    //revisar la animaci�n para ver que se puede implementar
    public void pintaCarrito(){
        //animacion = new Thread();
       // x1=x*30;
       // y1=y*30;
        animacion.start();
       // nueva.muestralista();
    }
    
    /////// METODO PARA CARGAR LAS IMAGENES QUE SE UTILIZAN ///////
    private void cargarImagenes() {
        this.agua = this.cargarFondo("pasto.gif");
        this.tocado = this.cargarFondo("pared.gif");
        this.startFinish = this.cargarFondo("starFinish.gif");
        this.caminoWalk = this.cargarFondo("walk2.gif");
        this.carrito = this.cargarFondo("circleChrome.gif");
    }
    //metodo para cargar la imagen , {protected} solo permite el acceso desde subclase y miembros del mismo paquete
    protected static ImageIcon cargarFondo( String ruta ){
        java.net.URL localizacion = TableroGUI.class.getResource(ruta);
        if( localizacion != null ) {
            return new ImageIcon( localizacion );
        } else {
            System.err.println( "No se ha encontrado el archivo: " + ruta );
            return null;
        }
    }
    
    public int[] getCoordenadas( CasillasGUI casilla ){
        int [] coordenadas = new int[2];
        for( int i = 0; i < this.casillas.length; i++ ){
            for( int j = 0; j < this.casillas.length; j++ ){
                if( this.casillas[i][j] == casilla ){
                    coordenadas[0] = i;
                    coordenadas[1] = j;
                }
            }
        }
        return coordenadas;
    }
    
       
    public void setCasillas(CasillasGUI[][] casillas) {
        this.casillas = casillas;
    }
    
    public boolean isTipoTablero() {
        return tipoTablero;
    }    
    public void setTipoTablero(boolean tipoTablero) {
        this.tipoTablero = tipoTablero;
    }
                              
    private void initComponents() {

        setLayout(null);
        animacion = new Thread(this);//////////////
        setBackground(new java.awt.Color(0, 0, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(351, 351));
    }
    
    public void paint (Graphics G)
    {
        super.paint(G);
        
        try{
            circuloA.painter(G,this,0); //se pinta con el paint pero del circulo
        }catch(NullPointerException e){;}
    }
    
    public void run()
    {
        int j;
        nodo aux = mapita.puntoMeta;
        nodo aux2 = nueva.primero;
        nueva.muestralista();
        y0 = ( ( aux.cordX +1) *35 )-5;
        x0 = ( ( aux.cordY +1) *35 )-5;
        y1 = ( ( aux2.cordY +1) *35 )-5;
        x1 = ( ( aux2.cordX +1) *35 )-5;
        
        while( aux2 != null ){
            System.out.println("repeticion 0 de "+(((x0+5)/35)-1)+" "+(((y0+5)/35)-1)+" hacia "+(((x1+5)/35)-1)+" "+(((y1+5)/35)-1));

        
        
            System.out.println("repeticion1 de "+(((x0+5)/35)-1)+" "+(((y0+5)/35)-1)+" hacia "+(((x1+5)/35)-1)+" "+(((y1+5)/35)-1));
            if( ( y0 < y1 ) && ( x0 ==x1 ) ){
                System.out.println("caso 1");
                for(int w = y0; w < y1; w++){
                    circuloA.x = x0;
                    circuloA.y = w;
                
                    repaint();
                    try {Thread.sleep(10);} catch (InterruptedException e) {;}
                }
            }
        
            else if( ( y0 > y1 ) && ( x0 ==x1 ) ){
                System.out.println("caso 2 ");
                for(int w = y0; w > y1; w--){
                    circuloA.x = x0;
                    circuloA.y = w;
                
                    repaint();
                    try {Thread.sleep(10);} catch (InterruptedException e) {;}
                }
            }
        
            else if( ( y0 == y1 ) && ( x0 < x1 ) ){
                System.out.println("caso 3");
                for(int w = x0; w < x1; w++){
                    circuloA.x = w;
                    circuloA.y = y0;
                
                    repaint();
                    try {Thread.sleep(10);} catch (InterruptedException e) {;}
                }
            }
        
            else if( ( y0 == y1 ) && ( x0 > x1 ) ){
                System.out.println("caso 4");
                for(int w = x0; w > x1; w--){
                    circuloA.x = w;
                    circuloA.y = y0;
                
                    repaint();
                    try {Thread.sleep(10);} catch (InterruptedException e) {;}
                }
            }
            else{
                System.out.println(" aqui el porque "+(((x0+5)/35)-1)+" "+(((y0+5)/35)-1)+" hacia "+(((x1+5)/35)-1)+" "+(((y1+5)/35)-1));
       
            }
            aux=aux2;
            aux2=aux2.siguiente;
            if(aux2!=null){
                x0 = ( ( aux.cordX +1) *35 )-5;
                y0 = ( ( aux.cordY +1) *35 )-5;
            
                y1 = ( ( aux2.cordY +1) *35 )-5;
                x1 = ( ( aux2.cordX +1) *35 )-5;
            }
        }//fin while

        
        termin = 1;
    }
                     
}
