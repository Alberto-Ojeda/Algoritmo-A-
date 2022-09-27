//se declara la matriz
public class mapaMatriz{
    int[][] matriz;
    int cordX,cordY;
    int tamanio;
    nodo puntoInicio,puntoMeta;
    lista abierta;
    lista cerrados;
    lista camino;
    
    ///////CONSTRUCTOR//////////////////////////////////////////////////////////
    //constructor de la matriz donde declara  las listas que tendran 
    //los caminos abiertos y los caminos vacios
    //junto con el tamaño de la matriz
    public mapaMatriz( int tamanio ){
        abierta = new lista( "abierta" );
        cerrados = new lista( "cerrada" );
        camino = new lista ( "camino" );
        
        this.tamanio = tamanio;
        matriz = new int[tamanio][tamanio];
        inicializaMatriz();
        
        puntoInicio = null;
        puntoMeta = null;
        
    }
    
    ///////METODO PARA ESTABLECER LAS PAREDES EL EL LABERINTO///////////////////
    public void setParedes( int x, int y ){
        this.matriz[x][y] = 1;
    	System.out.println(this.matriz[x][y] );

    }
    
    //////METODO PARA IMPRIMIR LA MATRIZ////////////////////////////////////////
  /*  public void imprimeMatriz(){
        System.out.println(" ");
        for(int i = 0; i < tamanio; i++){
            for(int j = 0; j < tamanio; j++){
                System.out.print("prueba"+" "+matriz[i][j] );
            }
            System.out.println(" ");
        }
    }*/
    
    ///////METODO PARA ESTABLECER EL INICIO Y LA META///////////////////////////
    /*puntoInicio y puntoMeta ambos son nodos y se toman cuando son nulos, 
     * se le pasa las coordenadas a cada uno de ellos
     * y se establece el punto de inicio y pùnto final en el tablero 
     * */
    public int setReferencias( int x, int y ){
        if( puntoInicio == null ){
            puntoInicio = new nodo(x,y);
            matriz[x][y] = 3;
            return 0;
        }
        else if( puntoMeta == null ){
            puntoMeta = new nodo(x,y);
            matriz[x][y] = 3;
            return 0;
        }
        else{
            return 1;
        }
    }
    
    ///////METODO QUE INICIALIZA LOS VALORES DE LA MATRIZ///////////////////////
    /* se inicializa la matriz en este punto y se le da el tamaños , con el for se itera en el tamaño
     * */
    public void inicializaMatriz(){
    	
        for(int i = 0; i < tamanio; i++){
            for(int j = 0; j < tamanio; j++){
                matriz[i][j] = 0;
            }
        }
    }
 
    //////METODO QUE EMPIEZA EL ALGORITMO Y REGRESA EL CAMINO FINAL/////////////
    /* primero == nodo , CAMINO==lista 
     * Inicia el algoritmo y se declara un nodo extra para pasarle el camino primero
     * 
     * */
    public lista getCamino(){
        iniciaAlgoritmo( puntoInicio );
        
        nodo extra2 = camino.primero;;
        lista nueva = new lista("nueva");
        
        while( extra2 != null ){
            extra2.siguiente = null;
            nueva.agregar(extra2);
            extra2 = extra2.padre;
        }
        nueva.eliminar( puntoMeta );
        
        return nueva;
    }
    
    
    ///////METODO QUE REALIZA EL ALGORITMO (EN FORMA GENERAL)///////////////////
    /* se le pasa el nodo inicial al algoritmo y comienza el algoritmo 
     * */
    public void iniciaAlgoritmo( nodo inicial ){
    	//se añade a la lista abierta el nodo inicial ya que al comenzar el camino no esta cerrado 
    	
        abierta.agregar( inicial );
        nodo actual = abierta.eliminaCostoMenor();
        
        while( !estaEnAbiertos( puntoMeta )){
            nodo extra = actual;
            extra.siguiente = null;
            cerrados.agregar( extra );

            //si el actual es igual al destino
            if( ( ( actual.cordX == puntoMeta.cordX ) && 
            		( actual.cordY == puntoMeta.cordY )  ) ){		
            	//|| abierta.esVacia() )
            	
                System.out.println("nodo actual cordx= "+actual.cordX);
	            System.out.println("nodo actual cordx= "+actual.cordY);
	            System.out.println("nodo puntoMeta cordx= "+puntoMeta.cordX);
	            System.out.println("nodo puntoMeta cordY= "+puntoMeta.cordY);

            	 
                break;//terminamos
            }            
            else{
                //obtengo adyacentes
                int x = 0, y = 0,w = 0,w2 = 0;
                for( int i = 0; i < 3; i++ ){
                    for( int j = 0; j < 3; j++ ){
                        //verificamos que no sea el de referencia
                        if(i == 1 && j == 1){
                            continue;
                        }
                        //valores de i
                        if(i == 0 )
                            w = -1;
                        else if(i == 1)
                            w = 0;
                        else if(i == 2)
                            w = 1;
                        
                        //valores de j
                        if(j == 0 )
                            w2 = -1;
                        else if(j == 1)
                            w2 = 0;
                        else if(j == 2)
                            w2 = 1;
                        
                        //asignamos los x y y correspondientes
                        x = actual.cordX + w;
                        y = actual.cordY + w2;
                        
                        //los asignamos a un nodo el cual es el adyacente
                        nodo adyacente = new nodo( x, y );
                        // si no es transitable o si estan el la lista cerrada
                        if( ( !( (x >= 0) && (y >= 0) && (x < tamanio) && (y < tamanio) && (matriz[x][y] != 1) ) )  || estaEnCerrados( adyacente ) ){
                            continue;//lo ignoro
                        }
                        
                        //consideramos los casos para avance diagonal donde rosa
                        nodo extra3;
                        if( ( adyacente.cordX != actual.cordX ) && ( adyacente.cordY != actual.cordY ) ){
                            ////QUITANDO EL CONTINUE Y DESCOMENTANDO LO DE ABAJO ACEPTAMOS CAMINOS DIAGONALES///////////////////////////
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            continue;
                 
                        }
                        
                        //comprobamos que no este en la abierta ni en la cerrada 
                        if( !estaEnAbiertos(adyacente) && !estaEnCerrados(adyacente) ){
                            adyacente = setValores( actual, adyacente );
                            adyacente.padre = actual;
                            abierta.agregar( adyacente );
                        }
                        
                        else if( estaEnAbiertos( adyacente ) ){
                            if( setValores( actual, adyacente ).costoF < actual.costoF ){
                                adyacente = setValores( actual, adyacente );
                                adyacente.padre = actual;
                            }
                        }
                        
                        else if( !estaEnAbiertos( adyacente ) ){
                            if( estaEnCerrados( adyacente ) )
                                ;//lo ignoramos
                        }
                    }//fin del primer for
                }//fin del segundo for
                
            }//fin del else
            
            actual = abierta.eliminaCostoMenor();
            
            //preguntamos si ya encontro el camino para mostrarlo
            if( ( actual.cordX == puntoMeta.cordX ) &&  ( actual.cordY == puntoMeta.cordY ) ){
                nodo extra2 = actual;
                
                while( extra2 != null ){
                    extra2.siguiente = null;
                    camino.agregar(extra2);
                    extra2 = extra2.padre;
                }
            }
        }//fin del for
    }
    
    ///////METODO QUE RECOBE UN NODO ACTUAL Y UNO ADYACENTE Y LE PONE///////////
    ///////SUS VALORES RESPECTIVOS DE F,G Y H Y LO REGRESA//////////////////////
    public nodo setValores( nodo actual, nodo adyacente ){
        //si es el mismo
        if( ( actual.cordX == adyacente.cordX ) && ( actual.cordY == adyacente.cordY ) )
            return actual;
        else if( ( actual.cordX == adyacente.cordX ) || ( actual.cordY == adyacente.cordY ) )
            adyacente.costoG = 10;
        else
            adyacente.costoG = 14;
        
        adyacente.costoH = distancia( adyacente, puntoMeta );
        adyacente.costoF = adyacente.costoG + adyacente.costoH;
        
        return adyacente;
    }
    
    //////METODO QUE CALCULA LA DISTANCIA MANHATTAN/////////////////////////////
    public int distancia(nodo a, nodo b){
        int distance = Math.abs(b.cordX-a.cordX) + Math.abs(b.cordY-a.cordY);
/*      System.out.println("la distancia es " + distance*10);
        System.out.println("b cord x " + b.cordX);
        System.out.println("a cord x " + a.cordX);
        System.out.println("b cord Y " + b.cordY);
        System.out.println("a cord Y " + a.cordY);*/
        return distance*10;
    }
    
    ///////METODO QUE VERIFICA SI UN NODO ESTA EN LA LISTA DE CERRADOS//////////
    ///////Y REGRESA UN VERDADERO O FALSO SEGUN SEA EL CASO/////////////////////
    public boolean estaEnCerrados( nodo nodin ){
        nodo aux = cerrados.primero;
        
        //la lista esta vacia
        if(aux == null)
            return false;
        
        while( aux != null ){
            if( ( nodin.cordX == aux.cordX ) &&  ( nodin.cordY == aux.cordY ) )
                return true;
            
            aux = aux.siguiente;
        }
        return nodin.visitado;
    }
    
    ///////METODO QUE VERIFICA SI UN NODO ESTA EN LA LISTA DE ABIERTOS//////////
    ///////Y REGRESA UN VERDADERO O FALSO SEGUN SEA EL CASO/////////////////////
    public boolean estaEnAbiertos( nodo nodin ){
        if( !abierta.esVacia() )
            return false;
        nodo aux = abierta.primero;
        
        //la lista esta vacia
        if(aux == null)
            return false;
        
        while( aux != null ){
            if( ( nodin.cordX == aux.cordX ) &&  ( nodin.cordY == aux.cordY ) )
                return true;
            
            aux = aux.siguiente;
        }
        return false;

    }
}
