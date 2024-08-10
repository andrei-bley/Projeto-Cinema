import java.time.LocalDateTime;


public class Sessao {
    private LocalDateTime dataHoraSessao;
    private int horaSessao;
    private int minutoSessao;

    public Sessao(LocalDateTime dataHoraSessao) {
        this.dataHoraSessao = dataHoraSessao;
    }

    public LocalDateTime getDataHoraSessao() {
        return dataHoraSessao;
    }

    public void setDataHoraSessao(LocalDateTime dataHoraSessao) {
        this.dataHoraSessao = dataHoraSessao;
    }
}