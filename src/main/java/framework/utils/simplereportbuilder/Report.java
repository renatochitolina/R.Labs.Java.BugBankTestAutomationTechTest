package framework.utils.simplereportbuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Report {
    private static final DateTimeFormatter FormatoPadrao = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS");

    private String nome;
    private String descricao;
    private LocalDateTime criacao;
    private LinkedList<Step> steps;

    public Report(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;

        this.criacao = LocalDateTime.now();

        this.steps = new LinkedList<>();
    }

    public String getNome() {
        return String.format("%s %s", this.nome, this.criacao.format(Report.FormatoPadrao));
    }

    public String getDescricao() {
        return this.descricao;
    }

    public LocalDateTime getCriacao() {
        return this.criacao;
    }

    public LinkedList<Step> getSteps() {
        return this.steps;
    }

    public void addStep(String descricao) {
        this.steps.addLast(new Step(descricao));
    }
}
