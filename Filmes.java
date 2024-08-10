import java.time.format.DateTimeFormatter;
import java.lang.Comparable;
import java.time.LocalDateTime;

public class Filmes implements Comparable<Filmes> {
    private String nomeFilme;
    private int notaFilme;
    private boolean favorito;
    private String comentarioFilme;
    private String comentarioSessao;
    private double preco;
    private Local local;
    private Sessao sessao;

    public Filmes(String nomeFilme, int notaFilme, boolean favorito, String comentarioFilme, String comentarioSessao, double preco, LocalDateTime dataHoraSessao,String localSessao) {
        this.nomeFilme = nomeFilme;
        this.notaFilme = notaFilme;
        this.favorito = favorito;
        this.comentarioFilme = comentarioFilme;
        this.comentarioSessao = comentarioSessao;
        this.preco = preco;
        this.local = new Local(localSessao);
        this.sessao = new Sessao(dataHoraSessao);
    }
    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    // Getter e Setter para 'notaFilme'
    public int getNotaFilme() {
        return notaFilme;
    }

    public void setNotaFilme(int notaFilme) {
        this.notaFilme = notaFilme;
    }

    // Getter e Setter para 'favorito'
    public boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    // Getter e Setter para 'comentarioFilme'
    public String getComentarioFilme() {
        return comentarioFilme;
    }

    public void setComentarioFilme(String comentarioFilme) {
        this.comentarioFilme = comentarioFilme;
    }

    // Getter e Setter para 'comentarioSessao'
    public String getComentarioSessao() {
        return comentarioSessao;
    }

    public void setComentarioSessao(String comentarioSessao) {
        this.comentarioSessao = comentarioSessao;
    }

    // Getter e Setter para 'preco'
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Getter e Setter para 'local'
    public String getLocal() {
        return local.getLocalSessao();
    }

    public void setLocal(String local) {
        this.local.setLocalSessao(local);
    }

    // Getter e Setter para 'sessao'
    public LocalDateTime getSessao() {
        return sessao.getDataHoraSessao();
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }


    public String getDataHoraSessaoString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return this.sessao.getDataHoraSessao().format(formatador);}








    @Override
    public int compareTo(Filmes filme) {
        return this.nomeFilme.compareToIgnoreCase(filme.nomeFilme);
    }

}