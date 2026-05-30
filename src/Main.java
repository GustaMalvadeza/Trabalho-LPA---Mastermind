import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int[] senha_computador= senhaAleatoria(4), senha_usuario;
        String senha_string;
        int cont=0, posicoes_corretas, posicoes_diferentes;
        boolean passa= false, venceu=false;
        do{
            System.out.print((cont+1)+"° tentativa| Sequencia: ");
            senha_string = sc.nextLine();
            senha_usuario= senhaUsuario(senha_computador.length, senha_string);
            posicoes_corretas= posicoesCorretas(senha_computador, senha_usuario);
            posicoes_diferentes= posicoesDiferentes(senha_computador, senha_usuario);
            System.out.println("Tentativa: "+ senha_string);
            System.out.println("Digitos corretos: "+posicoes_corretas);
            System.out.println("Digitos deslocados: "+posicoes_diferentes);
            cont++;
            if ((cont==10)){
                passa= true;
            } else if (posicoes_corretas==senha_computador.length) {
                passa= true;
                venceu= true;
            }
        }while (!passa);
        if (venceu){
            System.out.println("Parabens!\nVoce venceu o jogo.");
        }else {
            System.out.println("Perdeu pra mim otario KKKKKKK\n a sequencia era: ");
            verVetor(senha_computador);
        }

    }
    static int[] senhaAleatoria (int n){
        Random rand= new Random();
        int[] senha= new int[n];
        for (int i = 0; i < senha.length; i++) {
            senha[i]= rand.nextInt(1,7);
        }
        return senha;
    }
//  1- Armazenar digitos do usuario
    static int[] senhaUsuario(int n, String sequencia){
        int[] senha= new int[n];
        sequencia= sequencia.replace(" ", "");
        for (int i = 0; i < senha.length; i++) {
            senha[i]= sequencia.charAt(i)-'0';
        }
        return senha;
    }
//  3- Verificar quantidade de Digitos em posições corretas corretas
    static int posicoesCorretas(int[] senha_computador, int[] senha_usuario){
        int cont=0;
        for (int i = 0; i < senha_usuario.length; i++) {
            if (senha_usuario[i]==senha_computador[i]){
                cont++;
            }
        }
        return cont;
        }
    static void verVetor(int[] v){
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i]+" ");
        }
    }
//  2- Verificar quantidade de Digitos corretos em posições erradas
    static int posicoesDiferentes(int[] senha_computador, int[] senha_usuario){
        int cont=0;
        int[] cop_computador= new int[senha_computador.length];
        for (int k = 0; k < cop_computador.length; k++) {
            cop_computador[k]= senha_computador[k];
        }
        for (int i = 0; i < senha_usuario.length; i++) {
            for (int j = 0; j < cop_computador.length; j++) {
                if ((i!=j)&&(senha_usuario[i]==cop_computador[j])){
                    cop_computador[j]= -1;
                    cont++;
                }
            }
        }
        return cont;
    }
}