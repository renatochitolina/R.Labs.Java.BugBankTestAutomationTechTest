package framework.utils.simplereportbuilder;

public class Registro {
    private TipoRegistro tipo;
    private String descricao;
    private byte[] arquivo;

    public Registro(TipoRegistro tipo, String descricao) {
        this(tipo, descricao, null);
    }

    public Registro(TipoRegistro tipo, String descricao, byte[] arquivo) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.arquivo = arquivo;
    }

    public TipoRegistro getTipo() {
        return this.tipo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public byte[] getArquivo() {
        return this.arquivo;
    }
}
