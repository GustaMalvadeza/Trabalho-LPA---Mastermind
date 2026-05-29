import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] senha_computador= senhaAleatoria(4);
        String senha_usuario;
        int cont=0;
        boolean passa= false;
        do{
            System.out.println((cont+1)+"° tentativa| Sequencia: ");
            senha_usuario

            cont++;
            if (cont==9){
                passa= true;
            }
        }while (!passa);

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
        for (int i = 0; i < senha_usuario.length; i++) {
            for (int j = 0; j < senha_computador.length; j++) {
                if ((i!=j)&&(senha_usuario[i]==senha_computador[j])){
                    senha_computador[j]= -1;
                    cont++;
                }
            }
        }
        return cont;
    }
}