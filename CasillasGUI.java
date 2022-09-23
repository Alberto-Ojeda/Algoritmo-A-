import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//se implementa la clase mouseListener y extiende de jpanel heredando todos sus metodos
public class CasillasGUI extends javax.swing.JPanel implements MouseListener {
    // se establecen las variables -- el tablero 
    private TableroGUI tablero;
    // imagen de fondo
    private ImageIcon fondo;
    // casilla marcada --> aun revisamos para que funciona, ya que no afecta si se tiene un 2 o algun otro numero  
    private static int [] casillaMarcada = new int[2];
    //JPopupMenu menu contextual, no se utiliza
    //private JPopupMenu menuPrincipal;
    //mapaMatriz --> se hace una variable que hereda de la clase
    mapaMatriz mapita;
    //no se utilizan
    //JMenuItem itemInicio;
    //JMenuItem itemMeta;
    
    //constructor de la clase
    public CasillasGUI() {        
    }
    
    //casillas Gui implementa el tablero, el metodo mapaMatriz que es el algoritmo 
    public CasillasGUI(TableroGUI t,mapaMatriz mapita) {
    	//implementa la clase mapita
        this.mapita = mapita;
        initComponents();
        this.tablero = t;
        if(this.tablero.getTipoTablero() == true){// tablero responde a clics?
            this.addMouseListener(this);
        }
    }
    
    public void setFondo(ImageIcon fondo){
        this.fondo = fondo;
    }
    
    public ImageIcon getFondo(){        
        return this.fondo;
    }
    
                          
    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
       /* 
        * estos metodos no se utilizan
        * layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );*/
    }
    
    //se pinta el fondo verde en la coordenada cero  con el ancho y el alto necesario
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0,0,this.getWidth(),this.getHeight(),this);
        
    }
  //-------------------------------------------    
    // Al implementar MouseListener se heredan los siguientes metodos
    public void mouseClicked(MouseEvent e){} //se invoca cuando el boton es presionado (presiona y suelta)
    public void mouseEntered(MouseEvent e){} // se invoca cuando el ratón entra en un componente
    public void mouseExited(MouseEvent e){}  //se invoca cuando el ratón sale de un componente 
  //-------------------------------------------
    //se utiliza el siguiente ya que se invoca cuando se presiona un boton del  mouse sobre un componente
    public void mousePressed(MouseEvent e){
    	//si presiona el click derecho que es 3, entonces se marcara la casilla
        if (e.getButton() == 3) {
            this.setCasillaMarcada(tablero.getCoordenadas((CasillasGUI)e.getComponent()));
            this.tablero.pintarStartFinish(this.getCasillaMarcada()[0],this.getCasillaMarcada()[1]);
            //  si la referencia de inicio es diferente al inicio predeterminado entonces el nodo inicio
            //  se volvera null y se creara o pasara a tener los valores de la nueva referencia  
            if( mapita.setReferencias( this.getCasillaMarcada()[1], this.getCasillaMarcada()[0] ) == 1 ){
                try{
                    lista nueva = mapita.getCamino();
                    nodo aux = nueva.primero;
                    nodo aux2 = mapita.puntoMeta;
                    
                    while( aux.siguiente != null ){
//                        System.out.println("-> "+aux2.cordX+" "+aux2.cordY+" -->> "+aux.cordX+" "+aux.cordY+" --->>> "+aux.siguiente.cordX+" "+aux.siguiente.cordY);
                        this.tablero.pintarWalk( aux.cordY, aux.cordX );
                        aux2 = aux;
                        aux = aux.siguiente;
                    }
                }catch(NullPointerException ev){javax.swing.JOptionPane.showMessageDialog(null,"no hay camino");}
                this.tablero.pintaCarrito();
            }
        }
        else{
            this.setCasillaMarcada(tablero.getCoordenadas((CasillasGUI)e.getComponent()));
            this.tablero.pintar(this.getCasillaMarcada()[0],this.getCasillaMarcada()[1]);
            mapita.setParedes( this.getCasillaMarcada()[1], this.getCasillaMarcada()[0] );/////
        }
    }
    public void mouseReleased(MouseEvent e){}
    
    public static int[] getCasillaMarcada() {
        return casillaMarcada;
    }
    public static void setCasillaMarcada(int[] aCasillaMarcada) {
        casillaMarcada = aCasillaMarcada;
    }                  
    
}
