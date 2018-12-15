package higrometro.projeto.com.higrometro.model;

public class Lista {
    private String nome;
    private int imagem;

    public Lista(String nome,int imagem) {
        this.nome = nome;
        this.imagem=imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
