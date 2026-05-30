import java.util.Random;
import java.util.Scanner;

public class Main {
//    Onde a execução do jogo acontece
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int[] senha_computador= senhaAleatoria(4), senha_usuario;
        String senha_usuario_string;
        int tentativas =0, posicoes_corretas, posicoes_diferentes;
        boolean passa= false, venceu=false, controlador_digito;
        do{
            System.out.println();
            do {
                System.out.print((tentativas + 1) + "° tentativa| Sequencia: ");
                senha_usuario_string = sc.nextLine();
                if (!verificadorSequencia(senha_computador.length, senha_usuario_string)) {
                    System.out.println("So e possivel sequencias de 4 digidos, com numeros de 1 a 6.\nTente novamente!\n");
                    controlador_digito= false;
                } else{
                    controlador_digito= true;
                }
            }while (!controlador_digito);
            senha_usuario= senhaUsuario(senha_computador.length, senha_usuario_string);
            posicoes_corretas= posicoesCorretas(senha_computador, senha_usuario);
            posicoes_diferentes= posicoesDiferentes(senha_computador, senha_usuario);
            System.out.println("Tentativa: "+ senha_usuario_string);
            System.out.println("Digitos corretos: "+posicoes_corretas);
            System.out.println("Digitos deslocados: "+posicoes_diferentes);
            tentativas++;
            if ((tentativas ==10) && (posicoes_corretas!=senha_computador.length)){
                passa= true;
            } else if (posicoes_corretas==senha_computador.length) {
                passa= true;
                venceu= true;
            }
        }while (!passa);
        if (venceu){
            System.out.println("Parabens!\nVoce venceu o jogo.");
        }else {
            System.out.print("Que pena!\nVoce acabou perdendo, sequencia era: ");
            verVetor(senha_computador);
        }
    }
//    Gera uma senha aleatoria
    static int[] senhaAleatoria (int n){
        Random rand= new Random();
        int[] senha= new int[n];
        for (int i = 0; i < senha.length; i++) {
            senha[i]= rand.nextInt(1,7);
        }return senha;
    }
//    Verifica se a sequencia digitada pelo usuario é valida ou não
    static boolean verificadorSequencia(int tamanho, String sequencia){
        int digito;
        sequencia= sequencia.replace(" ", "");
        if (sequencia.length()!=tamanho){
            return false;
        }
        for (int i = 0; i < sequencia.length(); i++) {
            digito= sequencia.charAt(i)-'0';
            if (!((digito>0) && (digito<7))){
                return false;
            }
        }
        return true;
    }
//    Formata a senha do usuario para um vetor
    static int[] senhaUsuario(int tamanho, String sequencia){
        int[] senha= new int[tamanho];
        sequencia= sequencia.replace(" ", "");
        for (int i = 0; i < senha.length; i++) {
            senha[i]= sequencia.charAt(i)-'0';
        }return senha;
    }
//    Verifica e devolver o numero de digitos na posicao corretas
    static int posicoesCorretas(int[] senha_computador, int[] senha_usuario){
        int cont=0;
        for (int i = 0; i < senha_usuario.length; i++) {
            if (senha_usuario[i]==senha_computador[i]){
                cont++;
            }
        }
        return cont;
        }
//    Verifica e devolver o numero de digitos na posicao errada mas que pertencem
    static int posicoesDiferentes(int[] senha_computador, int[] senha_usuario){
        int cont=0;
        int[] cop_senha_computador = new int[senha_computador.length];
        int[] cop_senha_usuario = new int[senha_usuario.length];
        for (int k = 0; k < cop_senha_computador.length; k++) {
            cop_senha_computador[k]= senha_computador[k];
            cop_senha_usuario[k]= senha_usuario[k];
            if (cop_senha_usuario[k] == cop_senha_computador[k]) {
                cop_senha_computador[k] = -1;
                cop_senha_usuario[k] = -2;
            }
        }
        for (int i = 0; i < cop_senha_usuario.length; i++) {
            if (cop_senha_usuario[i]!=-2) {
                for (int j = 0; j < cop_senha_computador.length; j++) {
                        if (cop_senha_computador[j] != -1) {
                            if (cop_senha_usuario[i] == cop_senha_computador[j]) {
                                cop_senha_computador[j] = -1;
                                cop_senha_usuario[i] = -2;
                                cont++;
                            }
                        }
                    }
            }
        }
        return cont;
    }
//    Printa um vetor qualquer dado a ela por parametro
    static void verVetor(int[] v){
        for (int j : v) {
            System.out.print(j + " ");
        }
    }
}
