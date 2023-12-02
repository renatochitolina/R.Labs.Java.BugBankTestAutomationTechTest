package framework.utils.simplereportbuilder;

import java.util.ArrayList;

public class Step {
    private String descricao;
    private ArrayList<Registro> registros;

    public Step(String descricao) {
        this.descricao = descricao;

        this.registros = new ArrayList<>();
    }

    public String getDescricao() {
        return this.descricao;
    }

    public ArrayList<Registro> getRegistros() {
        return this.registros;
    }

    public void addRegistro(TipoRegistro tipo, String descricao) {
        this.registros.add(new Registro(tipo, descricao));
    }

    public void addRegistro(TipoRegistro tipo, String descricao, byte[] arquivo) {
        this.registros.add(new Registro(tipo, descricao, arquivo));
    }
}
