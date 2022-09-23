public class Main extends javax.swing.JFrame {
    
    public Main(){
        initComponents();
    }
                          
    private void initComponents(){
    	//inicia tablero GUI1 (1)
        tableroGUI1 = new TableroGUI( 10, true );
        this.setLocationRelativeTo( null );
        setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );
        javax.swing.GroupLayout tableroGUI1Layout = new javax.swing.GroupLayout( tableroGUI1 );
        tableroGUI1.setLayout( tableroGUI1Layout );
        tableroGUI1Layout.setHorizontalGroup(
            tableroGUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );
        tableroGUI1Layout.setVerticalGroup(
            tableroGUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableroGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tableroGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
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
                        
    private TableroGUI tableroGUI1;                
    
}
