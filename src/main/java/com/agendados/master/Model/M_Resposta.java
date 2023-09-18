package com.agendados.master.Model;

public class M_Resposta {
    private boolean sucesso;
    private String mensagem;

    public M_Resposta(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean isSucesso()  {
        return sucesso;
    }

    public void setStatus(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}