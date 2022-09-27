import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.beancontext.BeanContextContainerProxy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

public class Main extends javax.swing.JFrame implements ActionListener {
	 static JButton botonReinicio = new JButton ("Reinicio");
	 static JButton botonGenerar = new JButton ("Generar aleatorios");
	 static JButton botonGenerar2 = new JButton ("Generar aleatorios");
     JTextField Randomcasilla = new JTextField("holi");

     mapaMatriz mapita;
     private static int [] casillaMarcada = new int[2];
     private TableroGUI tablero;
     private CasillasGUI casillas;
     SwingContainer count;
    public Main(){
        initComponents();
    }
                          
    /**
     * 
     */
  
   
    public void initComponents(){
    	//inicia tablero GUI1 (1)
    	   botonReinicio.addActionListener(this);
           botonGenerar.addActionListener(this);
           botonGenerar2.addActionListener(this);
        tableroGUI1 = new TableroGUI( 8, true );
     

//        this.setLocationRelativeTo( null );
        setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );
        javax.swing.GroupLayout tableroGUI1Layout = new javax.swing.GroupLayout( tableroGUI1 );
        tableroGUI1.setLayout( tableroGUI1Layout );
      /*  tableroGUI1Layout.setHorizontalGroup(
            tableroGUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGap(0, 349, Short.MAX_VALUE)
        );
        tableroGUI1Layout.setVerticalGroup(
            tableroGUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGap(0, 349, Short.MAX_VALUE)
        );*/
        //tamaño horizontalt
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
                .addComponent(botonReinicio , 0, 
                		80, 80)
             
               .addComponent(botonGenerar, 0, 
               		200, 200) 
                .addComponent(tableroGUI1, 0, 
                		280, 280)
                
        )
            
/*            .addGroup(layout.createSequentialGroup()
            		.addComponent(botonGenerar2, 0, 
                    		200, 200))*/
        		);
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup()
                
                .addComponent(botonReinicio , 0, 
                		100, 100)
                
                .addComponent(botonGenerar, 0, 
                		100, 100) 
                .addComponent(tableroGUI1, 0, 
                		280, 280)
          
        )    
           
        
                );

        
        pack();
        
    }

    public static void main( String args[] ){

        java.awt.EventQueue.invokeLater( new Runnable(){
            public void run() {
                new Main().setVisible( true );

            }
        });
    }
                        
    public TableroGUI tableroGUI1;

	@Override
	public void actionPerformed(ActionEvent e) {
		
		    
	}   
	
	public static ArrayList<Integer> generarAleatoriosNoRepetidos (int cantidad) {
		ArrayList<Integer> respuesta = new ArrayList<>();
		for (int i = 0; i < cantidad; i++) {
			respuesta.add(gen(respuesta));
		}			
		return respuesta;
	}
	public static int gen(ArrayList<Integer>a) {
		Random ra = new Random();	
		int numero = ra.nextInt(10);
		if (!a.contains(numero)) {
			return numero;
		}else {
			return gen(a);
		}
	}
	
	public static void imprimir (ArrayList<Integer>num) {
		for (int i = 0; i < num.size(); i++) {
			System.out.println(num.get(i));
		}
	}
	
	public int[] retornarcasilla() {
	int	valor = (int) (Math.random()*10 + 1);
	int	valor2 = (int) (Math.random()*10 + 1);

	casillaMarcada[0]=valor;
	casillaMarcada[1]=valor2;
	
	return	casillaMarcada;
	}
	  public static int[] getCasillaMarcada() {
	        return casillaMarcada;
	    }
	  public static void setCasillaMarcada(int[] aCasillaMarcada) {
	        casillaMarcada = aCasillaMarcada;
	    }

	
	  
}
