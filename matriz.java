import java.util.Scanner;
import java.util.Random;


public static char[][] criarMatrizes() {
    return new char[10][10];
}


public static char[][] encherDAgua(char[][] matriz){
    for(int l=0; l<matriz.length;l++){
        for(int c=0; c<matriz.length;c++){
            matriz[l][c] = 'A';
        }
    }
 
 
    return matriz;
}


public static void mostrarMatriz(char[][] matriz){
    for(int i=0; i<matriz.length;i++){
        for(int j=0; j<matriz[i].length;j++){
            System.out.print(matriz[i][j] + " ");
        }
        System.out.println();
    }
}
public static void alocaBarco(char[][] matriz, int linha, int coluna, int tamanho, char posicao, int numTotalBarcos) {
    boolean opOk = true;
 
    int tamanhoBarco = 0;


    switch (tamanho) {
        case 1:
        tamanhoBarco = 4;
        break;
        case 2: 
        tamanhoBarco = 3;
        break;
        case 3: 
        tamanhoBarco = 2;
        break;
        case 4: 
        tamanhoBarco = 1;
        break;
        default: {
            System.out.println("Tamanho de barco invalido.");
            opOk = false;
        }
    }
    int tamanhoBarco1 = 0;
    int tamanhoBarco2 = 0;
    int tamanhoBarco3 = 0;
    int tamanhoBarco4 = 0;


   
        if (tamanho == 1) {
            if (tamanhoBarco1 >= 1) {
                System.out.println("Voce ja colocou o maximo de barcos de tamanho 1.");
                opOk = false;
            } else {
             tamanhoBarco1++;
            }
        } else if (tamanho == 2) {
            if (tamanhoBarco2 < 2) {
                tamanhoBarco2++;
            } else {
                System.out.println("Voce ja colocou o maximo de barcos de tamanho 2.");
                opOk = false;
            }
        } else if (tamanho == 3) {
            if (tamanhoBarco3 < 3) {
                tamanhoBarco3++;
            } else {
                System.out.println("Voce ja colocou o maximo de barcos de tamanho 3.");
                opOk = false;
            }
        } else if (tamanho == 4) {
            if (tamanhoBarco4 < 4) {
                tamanhoBarco4++;
            } else {
                System.out.println("Voce ja colocou o maximo de barcos de tamanho 4.");
                opOk = false;
            }
        } else {
            System.out.println("Tamanho de barco invalido.");
            opOk = false;
        }
       
               
        if (posicao == 'V') { // Vertical
            if (linha + tamanhoBarco > matriz.length) {
                System.out.println("Espaco insuficiente para alocar o barco na posicao vertical.");
                opOk = false;
            } else {
                for (int i = 0; i < tamanhoBarco; i++) {
                    if (matriz[linha + i][coluna] == 'B') {
                        System.out.println("Nao e possivel alocar o barco na posicao desejada, ha um barco nessas coordenadas.");
                        opOk = false;
                        
                    }
                }
                if (opOk) {
                    for (int i = 0; i < tamanhoBarco; i++) {
                        matriz[linha + i][coluna] = 'B';
                    }
                }
            }
        } else if (posicao == 'H') { // Horizontal
            if (coluna + tamanhoBarco > matriz[linha].length) {
                System.out.println("Espaco insuficiente para alocar o barco na posiaao horizontal.");
                opOk = false;
            } else {
                for (int i = 0; i < tamanhoBarco; i++) {
                    if (matriz[linha][coluna + i] == 'B') {
                        System.out.println("Nao E possível alocar o barco na posicao desejada, ha um barco nessas coordenadas.");
                        opOk = false;
                        
                    }
                }
                if (opOk) {
                    for (int i = 0; i < tamanhoBarco; i++) {
                        matriz[linha][coluna + i] = 'B';
                        numTotalBarcos --;
                    }
               
                }
            }
        } else {
            System.out.println("Posicao invalida. Por favor, escolha 'V' para vertical ou 'H' para horizontal.");
            opOk = false;
        }
   


    if (opOk) {
        numTotalBarcos--; // Decrementa o número total de barcos
        mostrarMatriz(matriz);
    }
}




public static void atacar( char[][] tabuleiroAtaque, char[][] tabuleiroInimigo, Scanner ler, String nomeJogador) {
    System.out.println(nomeJogador + " E sua vez de lancar uma bomba no tabuleiro do oponente ");
    System.out.println("Escolha as coordenadas para lancar a bomba:");
    System.out.println("Linha 1-10:");
    int linha = ler.nextInt();
    System.out.println("Coluna 1-10:");
    int coluna = ler.nextInt();




    if (linha >= 1 && linha <= 10 && coluna >= 1 && coluna <= 10) {
        if (tabuleiroInimigo[linha - 1][coluna - 1] == 'B') {
            System.out.println("Parabéns! Voce acertou um navio do oponente!");
            tabuleiroInimigo[linha - 1][coluna - 1] = 'X';
        } else {
            System.out.println("Voce errou o ataque. Nao ha nenhum navio nesta posicao.");
        }
    } else {
        System.out.println("Coordenadas invalidas. O ataque foi perdido.");
    }
    System.out.println("Tabuleiro da maquina apos seu ataque:");
    mostrarMatriz(tabuleiroInimigo);
}


public static void maquinaAtaca(char[][] tabuleiroInimigo){  
    Random aleatorio = new Random();
     int linha = aleatorio.nextInt(10);
    int coluna = aleatorio.nextInt(10);


    if (linha >= 1 && linha <= 10 && coluna >= 1 && coluna <= 10) {
        if (tabuleiroInimigo[linha - 1][coluna - 1] == 'B') {
            tabuleiroInimigo[linha - 1][coluna - 1] = 'X';
        }
    }
}


public static boolean jogoAcabou(char[][] tabuleiroUm, char[][] tabuleiroDois) {
    boolean todosBarcosDestruidosUm = true;
    boolean todosBarcosDestruidosDois = true;


 
    for (int i = 0; i < tabuleiroUm.length; i++) {
        for (int j = 0; j < tabuleiroUm[i].length; j++) {
            if (tabuleiroUm[i][j] == 'B') {
                todosBarcosDestruidosUm = false;
                
            }
        }
    }


    // Verifica se todos os navios no tabuleiroDois foram destruídos
    for (int i = 0; i < tabuleiroDois.length; i++) {
        for (int j = 0; j < tabuleiroDois[i].length; j++) {
            if (tabuleiroDois[i][j] == 'B') {
                todosBarcosDestruidosDois = false; //seencontrar pelo menos um navio no tabuleiroDois atualiza para indicar que nem todos os navios foram destruídos
                
            }
        }
    }


    // Retorna true se todos os navios em ambos os tabuleiros foram destruídos, caso contrario, retorna false
    return todosBarcosDestruidosUm && todosBarcosDestruidosDois;
}


public static char[][] maquina() {
    char[][] tabuleiroMaquinaa = criarMatrizes();
    tabuleiroMaquinaa = encherDAgua(tabuleiroMaquinaa);
    return alocaBarcosParaMaquina(tabuleiroMaquinaa);
}




public static char[][] alocaBarcosParaMaquina(char[][] matriz) {
    int totalDeBarcos = 10;
    int[] tamanhos = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};


    for (int tamanho : tamanhos) {
        while (totalDeBarcos > 0) {
            alocaBarcoParaMaquina(matriz, tamanho);
            totalDeBarcos -= tamanho; // Reduz o número total de barcos pelo tamanho do barco alocado
        }
    }


    return matriz;
}


public static void alocaBarcoParaMaquina(char[][] matriz, int tamanho) {
    Random aleatorio = new Random();
    boolean opOk = false;


    while (!opOk) {
        int linha = aleatorio.nextInt(matriz.length);
        int coluna = aleatorio.nextInt(matriz[0].length);
        char posicao;
        if (aleatorio.nextBoolean()) {
            posicao = 'V';
        } else {
            posicao = 'H';
        }
       


        if (posicao == 'V') { // Vertical
            if (linha + tamanho <= matriz.length) {
                opOk = true;
                for (int i = 0; i < tamanho; i++) {
                    if (matriz[linha + i][coluna] != 'A') {
                        opOk = false;
        
                    }
                }
                if (opOk) {
                    for (int i = 0; i < tamanho; i++) {
                        matriz[linha + i][coluna] = 'B'; // Aloca o barco na posicao vertical
                    }
                }
            }
        } else { // Horizontal
            if (coluna + tamanho <= matriz[0].length) {
                opOk = true;
                for (int i = 0; i < tamanho; i++) {
                    if (matriz[linha][coluna + i] != 'A') {
                        opOk = false; // Espaco ja ocupado
                        
                    }
                }
                if (opOk) {
                    for (int i = 0; i < tamanho; i++) {
                        matriz[linha][coluna + i] = 'B'; // Aloca o barco na posicao horizontal
                    }
                }
            }
        }
    }
}






    public static void main(String[] args) {
        int totalDeBarocos = 10; // total de barcos
        int totalDeBarcos2 = 10;
        int vezDoJogador = 1;
       
       
        try (Scanner ler = new Scanner(System.in)) {


            boolean opcaoDjogoValida = true;
            do{
            System.out.println("BEM VINDO AO BATALHA NAVAL!");
            System.out.println("Para dar início ao game, escolha o modo de jogo: ");
            System.out.println("Opcao 1: jogar com outro jogador;");
            System.out.println("Opcao 2: jogar com a maquina.");
            int opcaoDJogo = ler.nextInt();
            if(opcaoDJogo == 1){
                    char[][] tabuleiroUm = criarMatrizes();
                    tabuleiroUm = encherDAgua(tabuleiroUm);
                    System.out.println("Digite o nome do jogador 1: ");
                    String NomeJogadorUm = ler.nextLine();
                    System.out.println("Essa E sua tabela "+ NomeJogadorUm+ ", voce tem um total de "+totalDeBarocos + " barcos restantes");  
                    mostrarMatriz(tabuleiroUm);
               
                        while(totalDeBarocos>=0){
                           
                     System.out.println("Voce deseja posicionar qual barco? ");
                     System.out.println(" 1 - Navio que ocupa quatro espacos, no jogo E disponibilizado um navio de tamanho quatro, escolha bem. ");        
                     System.out.println(" 2 - Navio que ocupa tres espacos, no jogo E disponibilizado dois navios de tamanho tres, escolha bem.");  
                     System.out.println(" 3 - Navio que ocupa dois espacos, no jogo E disponibilizado tres navios de tamanho dois, escolha bem.");  
                     System.out.println(" 4 - Navio que ocupa um espaco, no jogo E disponibilizado quatro navios de tamanho um, escolha bem.");  
                     int opcaoDoNavio = ler.nextInt();
                     System.out.println("V - Para por o barco na vertical    e   H - para por o barco na horizontal: ");
                    char posicaoVertHori = ler.next().charAt(0);
                    System.out.println("Escolha a posicao que deseja alocar seu barco: ");
                    System.out.println("Coluna: 1-10");  
                    int coluna = ler.nextInt();
                    if(coluna>10 || coluna<0){
                        System.out.println("Digite um número entre 1-10 para a coluna");
                        coluna = ler.nextInt();
                    }
                    System.out.println("Linha: 1-10");
                    int linha = ler.nextInt();
                    if(linha>10 || linha<0){
                        System.out.println("Digite um número entre 1-10 para a linha");
                        linha = ler.nextInt();
                    }
                 
                    alocaBarco(tabuleiroUm, linha, coluna, opcaoDoNavio, posicaoVertHori, totalDeBarocos);
                        totalDeBarocos--;
                        }
                    ler.nextLine();
                    System.out.println("Agora sera a vez do jogador número dois de escolher a posicao de seus barcos na matriz: ");
                    char[][] tabuleiroDois = criarMatrizes();
                    tabuleiroDois = encherDAgua(tabuleiroDois);
                    System.out.println("Digite o nome do jogador 2: ");
                    String NomeJogadorDois = ler.nextLine();
                    System.out.println("Essa E sua tabela "+ NomeJogadorDois+ ", voce tem um total de "+totalDeBarocos + "barcos restantes");
                    mostrarMatriz(tabuleiroDois);
                   


                    while(totalDeBarcos2>=0){


                        System.out.println("Voce deseja posicionar qual barco? ");
                        System.out.println(" 1 - Navio que ocupa quatro espacos, no jogo E disponibilizado um navio de tamanho quatro, escolha bem. ");        
                        System.out.println(" 2 - Navio que ocupa tres espacos, no jogo E disponibilizado dois navios de tamanho tres, escolha bem.");  
                        System.out.println(" 3 - Navio que ocupa dois espacos, no jogo E disponibilizado tres navios de tamanho dois, escolha bem.");  
                        System.out.println(" 4 - Navio que ocupa um espaco, no jogo E disponibilizado quatro navios de tamanho um, escolha bem.");  
                        int opcaoDoNavio = ler.nextInt();
                        System.out.println("V - Para por o barco na vertical    e   H - para por o barco na horizontal: ");
                       
                       char posicaoVertHori = Character.toUpperCase(ler.next().charAt(0));


                       System.out.println("Escolha a posicao que deseja alocar seu barco: ");
                       System.out.println("Coluna: 1-10");
                       int coluna = ler.nextInt();
                       if(coluna>10 || coluna<0){
                           System.out.println("Digite um número entre 1-10 para a coluna");
                           coluna = ler.nextInt();
                       }
                       System.out.println("Linha: 1-10");
                       int linha = ler.nextInt();
                       if(linha>10 || linha<0){
                           System.out.println("Digite um número entre 1-10 para a linha");
                           linha = ler.nextInt();
                       }
                     
                       alocaBarco(tabuleiroDois, linha, coluna, opcaoDoNavio, posicaoVertHori, totalDeBarcos2);
                       totalDeBarcos2--;
                           }


                           while (!jogoAcabou(tabuleiroUm, tabuleiroDois)) {
                            if (vezDoJogador == 1) {
                                atacar(tabuleiroUm, tabuleiroDois, ler, NomeJogadorUm);
                            } else {
                                atacar(tabuleiroDois, tabuleiroDois, ler, NomeJogadorDois);
                            }
                   
                            vezDoJogador = 3 - vezDoJogador; // Alternar entre 1 e 2
                        }
                   
                   
                        System.out.println("Fim do jogo!");
                        
                    }
                    else if(opcaoDJogo == 2){
                    int totalDeBarcos3 = 10;
                    System.out.println("Ola jogador, antes de comecar a jogar contra a maquina, digite seu nome: ");
                    System.out.println("Nome do jogador: ");
                    String NomeJogadorMaquina = ler.nextLine();
                    char[][] tabuleiroTres = criarMatrizes();
                    tabuleiroTres = encherDAgua(tabuleiroTres);
                    System.out.println("Digite o nome do jogador 1: ");
                    System.out.println("Essa E sua tabela "+ NomeJogadorMaquina+ ", voce tem um total de "+totalDeBarocos + " barcos restantes"); // barcos disponiveis
                    mostrarMatriz(tabuleiroTres);
               
                        while(totalDeBarcos3>=0 ){
                           
                     System.out.println("Voce deseja posicionar qual barco? ");
                     System.out.println(" 1 - Navio que ocupa quatro espacos, no jogo E disponibilizado um navio de tamanho quatro, escolha bem. ");
                     System.out.println(" 2 - Navio que ocupa tres espacos, no jogo E disponibilizado dois navios de tamanho tres, escolha bem.");
                     System.out.println(" 3 - Navio que ocupa dois espacos, no jogo E disponibilizado tres navios de tamanho dois, escolha bem.");
                     System.out.println(" 4 - Navio que ocupa um espaco, no jogo E disponibilizado quatro navios de tamanho um, escolha bem.");  
                     int opcaoDoNavio = ler.nextInt();
                     System.out.println("V - Para por o barco na vertical    e   H - para por o barco na horizontal: ");
                    char posicaoVertHori = ler.next().charAt(0);
                    System.out.println("Escolha a posicao que deseja alocar seu barco: ");
                    System.out.println("Coluna: 1-10"); //n pode ser maior q 11  e menor q 1     e temq verificar se n tem nada no lugar
                    int coluna = ler.nextInt();
                    if(coluna>10 || coluna<0){
                        System.out.println("Digite um número entre 1-10 para a coluna");
                        coluna = ler.nextInt();
                    }
                    System.out.println("Linha: 1-10");
                    int linha = ler.nextInt();
                    if(linha>10 || linha<0){
                        System.out.println("Digite um número entre 1-10 para a linha");
                        linha = ler.nextInt();
                    }
                 
                    alocaBarco(tabuleiroTres, linha, coluna, opcaoDoNavio, posicaoVertHori, totalDeBarcos3);
                    totalDeBarcos3--;
                        }
                        char[][] tabuleiroMaquinaa = maquina();


                       
                        while (!jogoAcabou(tabuleiroTres, tabuleiroMaquinaa)) {
                           
                            atacar(tabuleiroTres, tabuleiroMaquinaa, ler, NomeJogadorMaquina);
                       
                           
                            if (jogoAcabou(tabuleiroTres, tabuleiroMaquinaa)) {
                                System.out.println("ParabEns! Voce venceu!");
                                
                            }
                       
                            // Maquina ataca
                            maquinaAtaca(tabuleiroTres);
                            mostrarMatriz(tabuleiroTres);
                       
                            if (jogoAcabou(tabuleiroTres, tabuleiroMaquinaa)) {
                                System.out.println("A Maquina venceu!");
                            }
                           
                        }
                }else{
                    System.out.println("O número escolhido E invalido, por favor escolha novamente");
                    opcaoDjogoValida = false;
        
            }
        }

          while (!opcaoDjogoValida);  
           
    
            
        }




    }



