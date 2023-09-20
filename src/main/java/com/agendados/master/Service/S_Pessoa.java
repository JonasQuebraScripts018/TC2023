package com.agendados.master.Service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.agendados.master.Model.M_Pessoa;
import com.agendados.master.Model.M_Resposta;
import com.agendados.master.Repository.R_Pessoa;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public static M_Pessoa getPessoaLogin(String usuario, String senha) {
        usuario = NumberCleaner.cleanerNumber(usuario);
        if(usuario.equals("")){
            return r_pessoa.findByUsuarioESenha(null,senha);
        }else{
            return r_pessoa.findByUsuarioESenha(Long.valueOf(usuario), senha);
        }
    }

        public static M_Resposta cadastrarPessoa(String nome, String cpf, String email, String senha, String confsenha) {

        boolean cadastrovalido = true;
        String mensagemRetorno = "";
        if (!senha.equals(confsenha)) {
            mensagemRetorno += "A senha e a confirmação de senha devem ser iguais.<br/>";
            cadastrovalido = false;
        } if (!CpfValidator.validateCPF(cpf)) {
            mensagemRetorno += "CPF inválido.<br/>";
            cadastrovalido = false;
        } if (nome == null || nome.trim() == "") {
            mensagemRetorno += "Deve ser informado um nome.<br/>";
            cadastrovalido = false;
        } if ((email == null || email.trim() == "")) {
            mensagemRetorno += "E-mail precisa ser informado.<br/>";
            cadastrovalido = false;
        } if(cadastrovalido) {
            M_Pessoa m_pessoa = new M_Pessoa();
            m_pessoa.setNome(nome);
            m_pessoa.setCpf(Long.valueOf(NumberCleaner.cleanerNumber(cpf)));
            m_pessoa.setEmail(email);
            m_pessoa.setSenha(senha);
            try {
                r_pessoa.save(m_pessoa);
                mensagemRetorno += "Cadastro realizado com sucesso!";
            }
            catch (DataIntegrityViolationException e){
                if(e.getMessage().contains("u_key")){
                    mensagemRetorno +="O CPF fornecido ja existe.";

                }
                else{
                    mensagemRetorno+="Erro ao cadastrar.";
                }
                cadastrovalido = false;
            }
        }
        M_Resposta m_resposta = new M_Resposta(cadastrovalido, mensagemRetorno);
        return m_resposta;
    }

}